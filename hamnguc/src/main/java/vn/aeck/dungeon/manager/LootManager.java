package vn.aeck.dungeon.manager;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import vn.aeck.dungeon.utils.ItemParser;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class LootManager {
    public static void fillRandomChest(Inventory inv, List<LootNode> pool) {
        inv.clear();
        double totalWeight = pool.stream().mapToDouble(n -> n.weight).sum();
        int itemsCount = ThreadLocalRandom.current().nextInt(2, 6); // Rải 2-5 món

        for (int i = 0; i < itemsCount; i++) {
            ItemStack item = getRandom(pool, totalWeight);
            int slot = ThreadLocalRandom.current().nextInt(inv.getSize());
            inv.setItem(slot, item);
        }
    }

    private static ItemStack getRandom(List<LootNode> pool, double total) {
        double r = ThreadLocalRandom.current().nextDouble() * total;
        double count = 0;
        for (LootNode node : pool) {
            count += node.weight;
            if (r <= count) return ItemParser.parse(node.id);
        }
        return null;
    }
    
    public static class LootNode {
        public String id; public double weight;
        public LootNode(String id, double weight) { this.id = id; this.weight = weight; }
    }
}