package me.ryandw11.kdebug.commands;


import me.ryandw11.kdebug.ColorUtil;
import org.kakara.client.local.game.DroppedItem;
import org.kakara.client.local.game.player.ClientPlayer;
import org.kakara.client.local.game.world.ClientWorld;
import org.kakara.client.scenes.maingamescene.MainGameScene;
import org.kakara.core.common.ControllerKey;
import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.mod.game.GameMod;
import org.kakara.core.common.mod.game.ModCommand;
import org.kakara.engine.GameHandler;
import org.kakara.engine.gameitems.GameItem;
import org.kakara.engine.physics.collision.ColliderComponent;

import java.util.*;

/**
 * Check the data of a dropped item by looking at it.
 *
 * <p>Note: This command is not a good example as it hooks into the Engine and the Client.</p>
 */
public class CheckDroppedCommand extends ModCommand {
    public CheckDroppedCommand(GameMod mod) {
        super(new HashSet<>(Arrays.asList("cdrop", "chkdrop")), "Check a dropped item.", mod, "checkdrop");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        MainGameScene currentScene = (MainGameScene) GameHandler.getInstance().getCurrentScene();

        Optional<UUID> playerID = ((ClientPlayer) currentScene.getServer().getPlayerEntity()).getGameItemID();
        if (playerID.isEmpty()) {
            commandSender.sendMessage(ColorUtil.RED + "Cannot find player UUID!");
            return;
        }

        ColliderComponent selectedItem = currentScene.selectGameItems(20, playerID.get());

        if (selectedItem == null || selectedItem.getGameItem() == null) {
            commandSender.sendMessage(ColorUtil.RED + "Please look at a dropped item!");
            return;
        }

        if (selectedItem.getGameItem().getTag().equals("pickupable")) {
            ControllerKey item = (ControllerKey) selectedItem.getGameItem().getData().get(0);
            commandSender.sendMessage("Looking at: " + item.toString());
            ClientWorld clientWorld = (ClientWorld) currentScene.getServer().getPlayerEntity().getLocation().getNullableWorld();
            List<DroppedItem> droppedItems = new ArrayList<>(clientWorld.getDroppedItems());
            boolean found = false;
            for (DroppedItem droppedItem : droppedItems) {
                Optional<GameItem> droppedGameItem = currentScene.getItemHandler().getItemWithId(droppedItem.getGameID());
                if (droppedGameItem.isPresent()) {
                    found = true;

                    commandSender.sendMessage(String.format("Client Location: %s, Engine Location: %s",
                            droppedItem.getLocation(),
                            selectedItem.getPosition()));

                    break;
                }
            }

            if (found) {
                commandSender.sendMessage("That dropped item was found in the server Drop Item list.");
            } else {
                commandSender.sendMessage("That dropped item was NOT found in the sever Drop Item list.");
            }
        } else {
            commandSender.sendMessage(ColorUtil.RED + "Please look at a dropped item!");
        }
    }
}
