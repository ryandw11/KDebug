package me.ryandw11.kdebug.commands;

import org.kakara.core.Kakara;
import org.kakara.core.command.CommandSender;
import org.kakara.core.mod.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ModInfoCommand extends org.kakara.core.mod.game.ModCommand {
    public ModInfoCommand(Mod mod){
        super(Collections.singleton("mod"), "View more information about a mod.", mod, "modinfo");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        if(strings.length == 1){
            List<Mod> mods = Kakara.getModManager().getLoadedMods().stream().filter(m -> m.getName()
                    .equalsIgnoreCase(strings[0])).collect(Collectors.toList());
            if(mods.size() < 1){
                commandSender.sendMessage("Error: Mod not found!");
                return;
            }
            Mod mod = mods.get(0);
            String[] authors = mod.getAuthors() == null ? new String[]{"N/A"} : mod.getAuthors();
            String desc = mod.getDescription() == null ? "N/A" : mod.getDescription();
            commandSender.sendMessage(mod.getName() + " Version " + mod.getVersion() + ".");
            commandSender.sendMessage("Authors: " + String.join(", ", authors));
            commandSender.sendMessage("Mod Desc: " + desc);
        }else{
            commandSender.sendMessage("Invalid Usage: /modinfo {mod}");
        }
    }
}
