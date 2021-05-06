package me.ryandw11.kdebug.commands;

import me.ryandw11.kdebug.ColorUtil;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.mod.Mod;
import org.kakara.core.common.mod.game.GameMod;
import org.kakara.core.common.mod.game.ModCommand;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModsCommand extends ModCommand {
    public ModsCommand(GameMod mod){
        super(Collections.singleton("modlist"), "List the mods installed.", mod, "mods");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        int page = 0;
        List<Mod> commandList = new ArrayList<>(Kakara.getGameInstance().getModManager().getLoadedMods()).stream()
                .sorted(Comparator.comparing(Mod::getName)).collect(Collectors.toList());
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

        commandSender.sendMessage("=========[MOD LIST #" + (page + 1) + "]==========");
        int numOfCmds = Kakara.getGameInstance().getModManager().getLoadedMods().size();
        for(int i = page * 5; i < (page * 5) + 5; i++){
            if(i >= numOfCmds) break;
            Mod mod = commandList.get(i);
            commandSender.sendMessage(" - " + mod.getName() + " (" + mod.getVersion() + ")");
        }
        if((page * 5) + 5 < commandList.size()){
            commandSender.sendMessage("View more mods by doing /mods " + (page + 2) + ".");
        }

    }
}
