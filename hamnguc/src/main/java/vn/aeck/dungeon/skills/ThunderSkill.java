package vn.aeck.dungeon.skills;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class ThunderSkill extends SkillBase {
    public ThunderSkill(String id, FileConfiguration config) { super(id, config); }

    @Override
    public void cast(LivingEntity caster) {
        caster.getWorld().strikeLightningEffect(caster.getLocation());
        caster.getNearbyEntities(radius, radius, radius).forEach(e -> {
            if (e instanceof Player p) p.damage(damage, caster);
        });
    }
}