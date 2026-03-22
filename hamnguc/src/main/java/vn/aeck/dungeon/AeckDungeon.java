package vn.aeck.dungeon;

import org.bukkit.plugin.java.JavaPlugin;
import vn.aeck.dungeon.manager.*;

public class AeckDungeon extends JavaPlugin {
    private static AeckDungeon instance;
    private PartyManager partyManager;
    private DungeonManager dungeonManager;
    private SkillManager skillManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        createCustomConfigs();
        
        this.skillManager = new SkillManager();
        this.partyManager = new PartyManager();
        this.dungeonManager = new DungeonManager();
        
        getCommand("hamnguc").setExecutor(new DungeonCommand());
        getServer().getPluginManager().registerEvents(new DungeonGUIListener(), this);
        getServer().getPluginManager().registerEvents(skillManager, this);
        
        getLogger().info("§aAeckDungeon đã sẵn sàng phục vụ aeck.online!");
    }

    private void createCustomConfigs() {
        String[] files = {"hamnguc.yml", "quaivat.yml", "loots.yml"};
        for (String f : files) {
            if (!new java.io.File(getDataFolder(), f).exists()) saveResource(f, false);
        }
        java.io.File skillFolder = new java.io.File(getDataFolder(), "skills");
        if (!skillFolder.exists()) skillFolder.mkdirs();
    }

    public static AeckDungeon getInstance() { return instance; }
    public PartyManager getPartyManager() { return partyManager; }
    public SkillManager getSkillManager() { return skillManager; }
}