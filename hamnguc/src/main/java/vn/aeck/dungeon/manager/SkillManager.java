package vn.aeck.dungeon.manager;

import org.bukkit.Sound;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.util.Vector;

public class SkillManager implements Listener {

    public void castSkill(String skillId, LivingEntity caster) {
        switch (skillId.toLowerCase()) {
            case "thunder_strike":
                caster.getWorld().strikeLightningEffect(caster.getLocation());
                caster.getNearbyEntities(6, 6, 6).stream()
                    .filter(e -> e instanceof Player)
                    .forEach(p -> ((Player) p).damage(10, caster));
                break;

            case "gravity_pull":
                caster.getWorld().playSound(caster.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1, 1);
                for (Entity e : caster.getNearbyEntities(10, 10, 10)) {
                    if (e instanceof Player) {
                        Vector direction = caster.getLocation().toVector().subtract(e.getLocation().toVector()).normalize();
                        e.setVelocity(direction.multiply(1.5));
                    }
                }
                break;

            case "summon_minions":
                for (int i = 0; i < 3; i++) {
                    caster.getWorld().spawnEntity(caster.getLocation().add(Math.random(), 0, Math.random()), EntityType.SKELETON);
                }
                break;
        }
    }

    @EventHandler
    public void onPassiveSkills(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof LivingEntity attacker)) return;

        // Skill Lifesteal: Hồi máu theo % sát thương thực tế
        if (attacker.hasMetadata("skill_lifesteal")) {
            double heal = e.getFinalDamage() * 0.15;
            attacker.setHealth(Math.min(attacker.getMaxHealth(), attacker.getHealth() + heal));
        }
    }
}