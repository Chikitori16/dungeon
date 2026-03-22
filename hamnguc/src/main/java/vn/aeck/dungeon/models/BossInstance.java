package vn.aeck.dungeon.models;

import org.bukkit.Bukkit;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class BossInstance {
    private final LivingEntity entity;
    private final BossBar bossBar;
    private final double maxHealth;

    public BossInstance(LivingEntity entity, String name, BarColor color, BarStyle style) {
        this.entity = entity;
        this.maxHealth = entity.getMaxHealth();
        this.bossBar = Bukkit.createBossBar(name.replace("&", "§"), color, style);
    }

    public void update() {
        if (entity.isDead()) {
            bossBar.removeAll();
            return;
        }
        double progress = entity.getHealth() / maxHealth;
        bossBar.setProgress(Math.max(0, Math.min(1, progress)));
    }

    public void addPlayer(Player player) { bossBar.addPlayer(player); }
    public LivingEntity getEntity() { return entity; }
}