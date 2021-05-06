package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.kakara.core.common.KValidate;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.game.Item;
import org.kakara.core.common.mod.Mod;
import org.kakara.core.common.mod.game.GameMod;
import org.kakara.core.common.mod.game.ModCommand;
import org.kakara.core.common.player.Player;
import org.kakara.core.server.ServerGameInstance;
import org.kakara.core.server.gui.ServerInventory;

import java.util.Collections;

public class ClearInvCommand extends ModCommand {
    private final ServerGameInstance serverGameInstance;
    public ClearInvCommand(GameMod mod) {
        super(Collections.singleton("ci"), "Clear your inventory.", mod, "clearinv");
        KValidate.environmentCheckServer();
        serverGameInstance = (ServerGameInstance) Kakara.getGameInstance();
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(ColorUtil.RED + "This command is for players only!");
            return;
        }
        Player p = (Player) commandSender;
        if (serverGameInstance.getItemRegistry().getItem(0) == null) {
            commandSender.sendMessage(ColorUtil.RED + "A fatal error has occurred!");
            Kakara.LOGGER.error("Error: Cannot find item KAKARA:AIR!");
            return;
        }
        Item air = serverGameInstance.getItemRegistry().getItem(0);
        for (int i = 0; i < p.getInventory().getSize(); i++) {
            ((ServerInventory) p.getInventory()).setItemStack(serverGameInstance.createItemStack(air), i);
        }
    }
}
