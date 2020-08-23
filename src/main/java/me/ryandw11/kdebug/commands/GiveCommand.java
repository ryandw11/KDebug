package me.ryandw11.kdebug.commands;

import org.kakara.core.Kakara;
import org.kakara.core.NameKey;
import org.kakara.core.command.CommandSender;
import org.kakara.core.game.Item;
import org.kakara.core.game.ItemStack;
import org.kakara.core.mod.Mod;
import org.kakara.core.mod.game.ModCommand;
import org.kakara.core.player.Player;

import java.util.Collections;
import java.util.Optional;

public class GiveCommand extends ModCommand {
    public GiveCommand(Mod mod){
        super(Collections.singleton("item"), "Give yourself an item.", mod, "give");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        if(!(commandSender instanceof Player)){
            commandSender.sendMessage("This command can only be ran by players!");
            return;
        }
        Player p = (Player) commandSender;
        if(strings.length == 1){
            Optional<Item> item;
            try{
                item =  Kakara.getItemManager().getItem(new NameKey(strings[0].toUpperCase()));
            }catch(IllegalArgumentException ex){
                commandSender.sendMessage("Invalid item! (Be sure you formatted the item name as 'mod:item'!)");
                return;
            }
            if(item.isEmpty()){
                commandSender.sendMessage("Error: Invalid item!");
                return;
            }
            ItemStack stonk = Kakara.createItemStack(item.get());
            stonk.setCount(100);
            p.getInventory().addItemStack(stonk);
        }else if(strings.length == 2){
            Optional<Item> item;
            try{
                item =  Kakara.getItemManager().getItem(new NameKey(strings[0].toUpperCase()));
            }catch(IllegalArgumentException ex){
                commandSender.sendMessage("Invalid item! (Be sure you formatted the item name as 'mod:item'!)");
                return;
            }
            if(item.isEmpty()){
                commandSender.sendMessage("Error: Invalid item!");
                return;
            }
            int count;
            try{
                count = Integer.parseInt(strings[1]);
            }catch (NumberFormatException ex){
                commandSender.sendMessage("Error: Invalid item count.");
                return;
            }
            ItemStack stonk = Kakara.createItemStack(item.get());
            stonk.setCount(count);
            p.getInventory().addItemStack(stonk);
        }else{
            commandSender.sendMessage("Invalid Usage. /give {item} [count]");
        }

    }
}
