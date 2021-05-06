package me.ryandw11.kdebug;

import me.ryandw11.kdebug.commands.*;
import org.kakara.core.common.EnvType;
import org.kakara.core.common.Kakara;
import org.kakara.core.common.annotations.Environment;
import org.kakara.core.common.command.CommandManager;
import org.kakara.core.common.game.ItemManager;
import org.kakara.core.common.mod.annotations.LoadingStage;
import org.kakara.core.common.mod.game.GameMod;

public class KDebug extends GameMod {
    @LoadingStage
    public void defaultCommandLoad(CommandManager itemManager){
        Kakara.getGameInstance().getCommandManager().registerCommand(new PingCommand(this));
        Kakara.getGameInstance().getCommandManager().registerCommand(new HelpCommand(this));
        Kakara.getGameInstance().getCommandManager().registerCommand(new ModsCommand(this));
        Kakara.getGameInstance().getCommandManager().registerCommand(new ModInfoCommand(this));
    }
    @LoadingStage
    @Environment(EnvType.SERVER)
    public void serverCommandLoad(CommandManager itemManager){
        Kakara.getGameInstance().getCommandManager().registerCommand(new GiveCommand(this));
        Kakara.getGameInstance().getCommandManager().registerCommand(new ClearInvCommand(this));
    }

    @Override
    public void postEnable() {
        Kakara.LOGGER.info("Loaded KDebug v 1.0");
    }
}
