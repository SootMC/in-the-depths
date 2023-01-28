package us.glasscrab.i.inthedepths;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GiveCrystalCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        if(args.length != 1) {
            sender.sendMessage("Usage: /givecrystal <player>");
            return true;
        }

        String playerName = args[0];
        Player player = Bukkit.getPlayer(args[0]);

        if(player == null) {
            sender.sendMessage("Player " + playerName + " not found!");
            return true;
        }

        player.getInventory().addItem(Manager.getManager().makeCrystal());

        sender.sendMessage("Gave " + playerName + " a crystal!");
        return true;
    }
}
