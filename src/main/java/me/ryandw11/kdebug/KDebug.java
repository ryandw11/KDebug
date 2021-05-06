package me.ryandw11.kdebug;

import me.ryandw11.kdebug.commands.*;
import org.kakara.core.common.EnvType;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.annotations.Environment;
import org.kakara.core.common.command.CommandManager;
import org.kakara.core.common.mod.annotations.LoadingStage;
import org.kakara.core.common.mod.game.GameMod;

public class KDebug extends GameMod {
    @LoadingStage
    public void defaultCommandLoad(CommandManager commandManager){
        commandManager.registerCommand(new PingCommand(this));
        commandManager.registerCommand(new HelpCommand(this));
        commandManager.registerCommand(new ModsCommand(this));
        commandManager.registerCommand(new ModInfoCommand(this));
        commandManager.registerCommand(new CheckDroppedCommand(this));
    }
    @LoadingStage
    @Environment(EnvType.SERVER)
    public void serverCommandLoad(CommandManager commandManager){
        commandManager.registerCommand(new GiveCommand(this));
        commandManager.registerCommand(new ClearInvCommand(this));
    }

    @Override
    public void postEnable() {
        Kakara.LOGGER.info("Loaded KDebug v " + getVersion());
    }
}
