package vn.aeck.dungeon.skills;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import java.util.concurrent.ThreadLocalRandom;

public class SummonSkill extends SkillBase {
    private final EntityType mobType;
    private final int amount;

    public SummonSkill(String id, FileConfiguration config) {
        super(id, config);
        // Đọc loại quái vật và số lượng từ config, mặc định là Zombie
        String typeStr = config.getString("skills." + id + ".mob_type", "ZOMBIE");
        this.mobType = EntityType.valueOf(typeStr.toUpperCase());
        this.amount = config.getInt("skills." + id + ".amount", 3);
    }

    @Override
    public void cast(LivingEntity caster) {
        Location loc = caster.getLocation();
        
        // Hiệu ứng âm thanh và hạt khi triệu hồi
        loc.getWorld().playSound(loc, Sound.ENTITY_EVOKER_PREPARE_SUMMON, 1.0f, 1.0f);
        loc.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, loc, 3);

        for (int i = 0; i < amount; i++) {
            // Spawn quái vật quanh vị trí Boss (trong bán kính 2 block)
            double offsetX = ThreadLocalRandom.current().nextDouble(-2, 2);
            double offsetZ = ThreadLocalRandom.current().nextDouble(-2, 2);
            Location spawnLoc = loc.clone().add(offsetX, 0.5, offsetZ);
            
            LivingEntity minion = (LivingEntity) loc.getWorld().spawnEntity(spawnLoc, mobType);
            
            // Đánh dấu đây là quái vật triệu hồi để tránh rơi đồ (tùy chọn)
            minion.setCustomName("§7Đệ tử của " + caster.getCustomName());
            minion.setRemoveWhenFarAway(true);
        }
    }
}