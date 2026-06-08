package fr.Aximoxx.linkedLife;

import org.bukkit.Bukkit;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;

public class Listener implements org.bukkit.event.Listener {

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        if (!(e.getEntity() instanceof Player p)) return;
        if (!Main.getInstance().getManager().isStarted()) return;

        double newHealth = p.getHealth() - e.getFinalDamage();

        for (Player pls : Bukkit.getOnlinePlayers()){
            if (pls.equals(p)) continue;

            if (e.getFinalDamage() >= p.getHealth()) {
                p.sendMessage("§e" + p.getName() + " §6est mort.. §7(insulté le plus fort)");
                pls.sendMessage("§e" + p.getName() + " §6est mort.. §7(insulté le plus fort)");
                pls.playHurtAnimation(1);
                pls.setHealth(0);
                continue;
            }

            ActionBar.send(pls, "§e" + p.getName() + " §6a perdu §e" + String.format("%.2f", e.getDamage() / 2) + "§c❤ §6! §7(insulté le)");
            ActionBar.send(p, "§e" + p.getName() + " §6a perdu §e" + String.format("%.2f", e.getDamage() / 2) + "§c❤ §6! §7(insulté le)");
            pls.playHurtAnimation(1);
            pls.setHealth(newHealth);
        }
    }

    @EventHandler
    public void onRegen(EntityRegainHealthEvent e){
        if (!(e.getEntity() instanceof Player p)) return;
        if (!Main.getInstance().getManager().isStarted()) return;

        for (Player pls : Bukkit.getOnlinePlayers()){
            if (pls.equals(p)) continue;

            ActionBar.send(pls, "§e" + p.getName() + " §6a gagné §e" + String.format("%.2f", e.getAmount() / 2) + "§a❤ §6! §7(remerciez le)");
            ActionBar.send(p, "§e" + p.getName() + " §6a gagné §e" + String.format("%.2f", e.getAmount() / 2) + "§a❤ §6! §7(remerciez le)");
            pls.setHealth(Math.min(pls.getHealth() + e.getAmount(), pls.getAttribute(Attribute.MAX_HEALTH).getValue()));
        }
    }

    @EventHandler
    public void onFood(FoodLevelChangeEvent e){
        if (!(e.getEntity() instanceof Player p)) return;
        if (!Main.getInstance().getManager().isStarted()) return;

        int newFood = e.getFoodLevel();

        for (Player pls : Bukkit.getOnlinePlayers()){
            if (pls.equals(p)) continue;
            pls.setFoodLevel(newFood);
        }
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e){
        e.setDeathMessage(null);

        e.getDrops().clear();
        e.setDroppedExp(0);

        for (Player pls : Bukkit.getOnlinePlayers()){
            pls.getInventory().clear();
            pls.setExp(0);
        }
    }
}
