package me.levitate.cosmodailyrewards.managers.data;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.reward.RewardData;
import me.levitate.cosmodailyrewards.managers.utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class YamlDataStorage implements DataStorage<RewardData>
{
    private final CosmoDailyRewards plugin;

    public YamlDataStorage(CosmoDailyRewards plugin)
    {
        this.plugin = plugin;

        init();
    }

    private File dataFile;
    private YamlConfiguration config;

    @Override
    public void init()
    {
        dataFile = new File(plugin.getDataFolder(), "data.yml");

        try
        {
            if (dataFile.createNewFile())
            {
                Utilities.log("Generated data.yml file");
            }

            if (new File(plugin.getDataFolder(), "menu").mkdir())
            {
                plugin.saveResource("menu" + File.separator + "1.yml", false);
            }

            config = YamlConfiguration.loadConfiguration(dataFile);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void close()
    {
        Bukkit.getOnlinePlayers().forEach(player ->
        {
            RewardData data = plugin.getRewardManager().getData(player);
            save(player, data);
        });

        try
        {
            config.save(dataFile);
        }
        catch (IOException ex)
        {
            ex.printStackTrace();
        }
    }

    @Override
    public void save(Player player, RewardData data)
    {
        final String path = getPath(player);

        config.set(path + "last_claim", data.getLastClaim());
        config.set(path + "streak", data.getStreak());
    }

    @Override
    public RewardData load(Player player)
    {
        final String path = getPath(player);

        if (!config.contains(path))
        {
            RewardData data = new RewardData(plugin, System.currentTimeMillis() - plugin.getCooldownMillis(), 0);
            save(player, data);

            return data;
        }

        long lastClaim = config.getLong(path + "last_claim");
        int streak = config.getInt(path + "streak");

        return new RewardData(plugin, lastClaim, streak);
    }

    private String getPath(Player player)
    {
        return "players." + player.getUniqueId().toString() + ".";
    }
}
