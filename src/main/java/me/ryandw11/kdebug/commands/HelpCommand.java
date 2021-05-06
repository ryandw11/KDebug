package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.apache.commons.lang3.CharUtils;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.command.Command;
import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.mod.Mod;
import org.kakara.core.common.mod.game.ModCommand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class HelpCommand extends ModCommand {
    public HelpCommand(Mod mod){
        super(Collections.singleton("h3lp"), "Ask for help.", mod, "help");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        int page = 0;
        List<Command> commandList = new ArrayList<>(Kakara.getGameInstance().getCommandManager().getRegisteredCommands()).stream()
                .sorted(Comparator.comparing(c -> c.command().getKey())).collect(Collectors.toList());
        if(strings.length == 1){
            try{
                page = Integer.parseInt(strings[0]);
                page--;
            }catch (NumberFormatException ex){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
            if(page < 0){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
            if(page * 5 > commandList.size()){
                commandSender.sendMessage(ColorUtil.RED + "Invalid page number!");
                return;
            }
        }

        commandSender.sendMessage(ColorUtil.GOLD + "=========[" + ColorUtil.MAROON + "HELP #" + (page + 1) + ColorUtil.GOLD + "]==========");
        int numOfCmds = Kakara.getGameInstance().getCommandManager().getRegisteredCommands().size();
        for(int i = page * 5; i < (page * 5) + 5; i++){
            if(i >= numOfCmds) break;
            Command cmd = commandList.get(i);
            commandSender.sendMessage(ColorUtil.MAROON + "/" + cmd.command().getKey().toLowerCase() + ColorUtil.GOLD + " - " + cmd.getDescription());
        }
        if((page * 5) + 5 < commandList.size()){
            commandSender.sendMessage( ColorUtil.GOLD + "View more commands by doing " + ColorUtil.MAROON + "/help " + (page + 2) + ColorUtil.GOLD + ".");
        }

    }
}
