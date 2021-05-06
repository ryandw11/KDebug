package me.ryandw11.kdebug.commands;


import org.kakara.core.common.command.CommandSender;
import org.kakara.core.common.mod.Mod;
import org.kakara.core.common.mod.game.GameMod;
import org.kakara.core.common.mod.game.ModCommand;

import java.util.Collections;

public class PingCommand extends ModCommand {
    public PingCommand(GameMod mod){
        super(Collections.singleton("pong"), "Test command.", mod, "ping");
    }

    @Override
    public void execute(String s, String[] strings, String s1, CommandSender commandSender) {
        commandSender.sendMessage("Pong!");
    }
}
