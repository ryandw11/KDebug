package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.kakara.core.Kakara;
import org.kakara.core.command.Command;
import org.kakara.core.command.CommandSender;
import org.kakara.core.game.Item;
import org.kakara.core.game.ItemStack;
import org.kakara.core.mod.Mod;
import org.kakara.core.mod.game.ModCommand;
import org.kakara.core.player.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ClearInvCommand extends ModCommand {
    public ClearInvCommand(Mod mod){
        super(Collections.singleton("ci"), "Clear your inventory.", mod, "clearinv");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ColorUtil.RED + "This command is for players only!");
            return;
        }
        Player p = (Player) commandSender;
        if(Kakara.getItemManager().getItem("KAKARA:AIR").isEmpty()){
            commandSender.sendMessage(ColorUtil.RED + "A fatal error has occurred!");
            Kakara.LOGGER.error("Error: Cannot find item KAKARA:AIR!");
            return;
        }
        Item air = Kakara.getItemManager().getItem("KAKARA:AIR").get();
        for(int i = 0; i < p.getInventory().getSize(); i++){
            p.getInventory().setItemStack(Kakara.createItemStack(air), i);
        }
    }
}
