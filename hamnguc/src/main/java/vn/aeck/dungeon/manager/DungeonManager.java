package vn.aeck.dungeon.manager;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import java.util.List;

public class DungeonManager {

    /**
     * Tính toán chỉ số quái vật dựa trên số người chơi trong Party
     * Công thức: HP = Base * (1 + (Số người - 1) * 0.5)
     */
    public void scaleMob(LivingEntity mob, double baseHealth, double baseDamage, int partySize) {
        double multiplier = 1.0 + (partySize - 1) * 0.5;
        
        double finalHealth = baseHealth * multiplier;
        double finalDamage = baseDamage * multiplier;

        mob.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(finalHealth);
        mob.setHealth(finalHealth);
        
        if (mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
            mob.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE).setBaseValue(finalDamage);
        }
    }

    // Logic dán Schematic và quét Sign (Biển hiệu)
    // Bạn cần tích hợp FAWE API tại đây
    public void spawnDungeonInstance(String schemName, List<Player> party) {
        // 1. Paste Async schematic bằng FAWE
        // 2. Duyệt qua các Block đã Index (Sign/Chest)
        // 3. Nếu Sign có text {boss:id} -> Gọi BossManager để spawn
    }
}