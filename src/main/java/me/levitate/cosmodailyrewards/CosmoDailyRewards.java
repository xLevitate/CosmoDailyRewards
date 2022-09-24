package me.levitate.cosmodailyrewards;

import me.levitate.cosmodailyrewards.managers.data.DataStorage;
import me.levitate.cosmodailyrewards.managers.data.YamlDataStorage;
import me.levitate.cosmodailyrewards.managers.gui.GUIManager;
import me.levitate.cosmodailyrewards.managers.message.Message;
import me.levitate.cosmodailyrewards.managers.reward.RewardData;
import me.levitate.cosmodailyrewards.managers.reward.RewardManager;
import org.bukkit.plugin.java.JavaPlugin;

import io.samdev.actionutil.ActionUtil;

public final class CosmoDailyRewards extends JavaPlugin
{
    private DataStorage<RewardData> dataStorage;
    private ActionUtil actionUtil;
    private RewardManager rewardManager;
    private GUIManager menuManager;

    private long cooldownMillis;
    public long getCooldownMillis()
    {
        return cooldownMillis;
    }

    private long maxMillis;
    public long getMaxMillis()
    {
        return maxMillis;
    }

    @Override
    public void onEnable()
    {
        saveDefaultConfig();

        cooldownMillis = getConfig().getLong("claims.cooldown") * 1000;
        maxMillis = getConfig().getLong("claims.max") * 1000;

        Message.initialize(this);

        dataStorage = new YamlDataStorage(this);
        getDataStorage().init();

        actionUtil = ActionUtil.init(this);

        rewardManager = new RewardManager(this);

        menuManager = new GUIManager(this);
    }

    @Override
    public void onDisable()
    {
        dataStorage.close();
    }

    public <T> T getConfig(String path)
    {
        return getConfig(path, null);
    }

    @SuppressWarnings("unchecked")
    public <T> T getConfig(String path, Object def)
    {
        return (T) getConfig().get(path, def);
    }

    public DataStorage<RewardData> getDataStorage()
    {
        return dataStorage;
    }

    public RewardManager getRewardManager()
    {
        return rewardManager;
    }

    public GUIManager getMenuManager()
    {
        return menuManager;
    }

    public ActionUtil getActionUtil() {
        return actionUtil;
    }
}
