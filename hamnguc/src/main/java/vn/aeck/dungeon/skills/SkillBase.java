package vn.aeck.dungeon.skills;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import java.util.Map;

public abstract class SkillBase {
    protected String id;
    protected double damage;
    protected double radius;
    protected int cooldown; // Tính bằng giây

    public SkillBase(String id, FileConfiguration config) {
        this.id = id;
        this.damage = config.getDouble("skills." + id + ".damage", 0);
        this.radius = config.getDouble("skills." + id + ".radius", 5);
        this.cooldown = config.getInt("skills." + id + ".cooldown", 10);
    }

    public abstract void cast(LivingEntity caster);
    public String getId() { return id; }
}