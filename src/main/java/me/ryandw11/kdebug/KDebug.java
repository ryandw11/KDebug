package me.ryandw11.kdebug;

import me.ryandw11.kdebug.commands.*;
import org.kakara.core.Kakara;
import org.kakara.core.game.ItemManager;
import org.kakara.core.mod.annotations.LoadingStage;
import org.kakara.core.mod.game.GameMod;

public class KDebug extends GameMod {
    @LoadingStage
    public void itemLoad(ItemManager itemManager){
        Kakara.getCommandManager().registerCommand(new PingCommand(this));
        Kakara.getCommandManager().registerCommand(new GiveCommand(this));
        Kakara.getCommandManager().registerCommand(new HelpCommand(this));
        Kakara.getCommandManager().registerCommand(new ModsCommand(this));
        Kakara.getCommandManager().registerCommand(new ModInfoCommand(this));
        Kakara.getCommandManager().registerCommand(new ClearInvCommand(this));
        Kakara.LOGGER.info("Loaded KDebug v 1.0");
    }
}
