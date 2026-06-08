package fr.Aximoxx.linkedLife;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jspecify.annotations.NonNull;

public class Command implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, org.bukkit.command.@NonNull Command cmd, @NonNull String s, String @NonNull [] args) {

        if (!(sender instanceof Player p)) {
            sender.sendMessage("§cOnly players can use this command.");
            return true;
        }

        if (!p.isOp()){
            p.sendMessage("§cYou don't have permission to use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("linkedlife")) {
            if (args.length != 1) {
                p.sendMessage("§cUsage: /linkedlife <start|stop>");
                return true;
            }

            switch (args[0]){
                case "start" -> Main.getInstance().getManager().StartGame(p);
                case "stop" -> Main.getInstance().getManager().StopGame(p);
                default -> {
                    p.sendMessage("§cUsage: /linkedlife <start|stop>");
                    return true;
                }
            }
            return true;
        }
        return false;
    }
}
