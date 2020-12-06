package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.kakara.core.client.client.Client;
import org.kakara.core.common.ControllerKey;
import org.kakara.core.common.KValidate;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.game.Item;
import org.kakara.core.common.game.ItemStack;
import org.kakara.core.common.mod.Mod;
import org.kakara.core.common.mod.game.ModCommand;
import org.kakara.core.common.player.Player;
import org.kakara.core.server.ServerGameInstance;
import org.kakara.core.server.game.ServerItemStack;
import org.kakara.core.server.gui.ServerInventory;


import java.util.Collections;
import java.util.Optional;

public class GiveCommand extends ModCommand {
    public GiveCommand(Mod mod){
        super(Collections.singleton("item"), "Give yourself an item.", mod, "give");
        KValidate.checkServer();
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage(ColorUtil.RED + "This command can only be ran by players!");
            return;
        }
        Player p = (Player) commandSender;
        if(strings.length == 1){
            Item item;
            try{
                item =  Kakara.getGameInstance().getItemManager().getItem(new ControllerKey(strings[0].toUpperCase()));
            }catch(IllegalArgumentException ex){
                commandSender.sendMessage(ColorUtil.RED + "Invalid item! (Be sure you formatted the item name as 'mod:item'!)");
                return;
            }
            if(item==null){
                commandSender.sendMessage(ColorUtil.RED + "Error: Invalid item!");
                return;
            }
            ServerItemStack stonk = ((ServerGameInstance) Kakara.getGameInstance()).createItemStack(item);
            stonk.setCount(100);
            ((ServerInventory) p.getInventory()).addItemStack(stonk);
        }else if(strings.length == 2){
            Item item;
            try{
                item =  Kakara.getGameInstance().getItemManager().getItem(new ControllerKey(strings[0].toUpperCase()));
            }catch(IllegalArgumentException ex){
                commandSender.sendMessage(ColorUtil.RED + "Invalid item! (Be sure you formatted the item name as 'mod:item'!)");
                return;
            }
            if(item==null){
                commandSender.sendMessage(ColorUtil.RED + "Error: Invalid item!");
                return;
            }
            int count;
            try{
                count = Integer.parseInt(strings[1]);
            }catch (NumberFormatException ex){
                commandSender.sendMessage(ColorUtil.RED + "Error: Invalid item count.");
                return;
            }
            ServerItemStack stonk = ((ServerGameInstance) Kakara.getGameInstance()).createItemStack(item);
            stonk.setCount(count);
            ((ServerInventory) p.getInventory()).addItemStack(stonk);
        }else{
            commandSender.sendMessage(ColorUtil.RED + "Invalid Usage. /give {item} [count]");
        }

    }
}
