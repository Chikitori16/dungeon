package vn.aeck.dungeon.utils;

import net.Indyuce.mmoitems.MMOItems;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemParser {
    public static ItemStack parse(String input) {
        if (input.startsWith("mmoitem:")) {
            String[] p = input.split(":");
            return MMOItems.plugin.getItem(p[1].toUpperCase(), p[2].toUpperCase());
        }
        Material mat = Material.matchMaterial(input.toUpperCase());
        return (mat != null) ? new ItemStack(mat) : new ItemStack(Material.BARRIER);
    }
}