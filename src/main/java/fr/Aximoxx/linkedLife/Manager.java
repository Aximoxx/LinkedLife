package fr.Aximoxx.linkedLife;

import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

public class Manager {
    private boolean started = false;
    private boolean keepInventory = false;

    public void StartGame(Player p){
        if (isStarted()) return;

        p.sendTitle("§cLinked §aLife", "§7Le jeu démarre !", 10, 70, 20);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.BLOCKS, 1.0f, 1.0f);
        setStarted(true);
    }

    public void StopGame(Player p){
        if (!isStarted()) return;

        p.sendTitle("§cLinked §aLife", "§7Le jeu s'arrête !", 10, 70, 20);
        p.playSound(p.getLocation(), Sound.ENTITY_ENDER_DRAGON_DEATH, SoundCategory.BLOCKS, 1.0f, 1.0f);
        setStarted(false);
    }


    public boolean isStarted() {
        return started;
    }
    public boolean isKeepInventory() {
        return keepInventory;
    }

    public void setStarted(boolean started) {
        this.started = started;
    }
    public void setKeepInventory(boolean keepInventory) {
        this.keepInventory = keepInventory;
    }
}
