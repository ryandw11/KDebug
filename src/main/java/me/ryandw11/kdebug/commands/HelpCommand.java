package me.ryandw11.kdebug.commands;

import org.kakara.core.Kakara;
import org.kakara.core.command.Command;
import org.kakara.core.command.CommandSender;
import org.kakara.core.mod.Mod;
import org.kakara.core.mod.game.ModCommand;

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
        List<Command> commandList = new ArrayList<>(Kakara.getCommandManager().getRegisteredCommands()).stream()
                .sorted(Comparator.comparing(c -> c.command().getName())).collect(Collectors.toList());
        if(strings.length == 1){
            try{
                page = Integer.parseInt(strings[0]);
                page--;
            }catch (NumberFormatException ex){
                commandSender.sendMessage("Invalid page number!");
                return;
            }
            if(page < 0){
                commandSender.sendMessage("Invalid page number!");
                return;
            }
            if(page * 5 > commandList.size()){
                commandSender.sendMessage("Invalid page number!");
                return;
            }
        }

        commandSender.sendMessage("=========[HELP #" + (page + 1) + "]==========");
        int numOfCmds = Kakara.getCommandManager().getRegisteredCommands().size();
        for(int i = page * 5; i < (page * 5) + 5; i++){
            if(i >= numOfCmds) break;
            Command cmd = commandList.get(i);
            commandSender.sendMessage("/" + cmd.command().getKey().toLowerCase() + " - " + cmd.getDescription());
        }
        if((page * 5) + 5 < commandList.size()){
            commandSender.sendMessage("View more commands by doing /help " + (page + 2) + ".");
        }

    }
}
