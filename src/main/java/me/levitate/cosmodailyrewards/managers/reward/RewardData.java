package me.levitate.cosmodailyrewards.managers.reward;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.utilities.UtilitiesTime;

public class RewardData
{
    private final CosmoDailyRewards plugin;

    private long lastClaim;
    private int streak;

    public RewardData(CosmoDailyRewards plugin, long lastClaim, int streak)
    {
        this.plugin = plugin;

        this.lastClaim = lastClaim;
        this.streak = streak;
    }

    public long getLastClaim()
    {
        return lastClaim;
    }

    public int getStreak()
    {
        return streak;
    }

    void update()
    {
        lastClaim = System.currentTimeMillis();
        streak++;

        if (streak == 7)
        {
            streak = 0;
        }
    }

    public void resetIfExpired()
    {
        if (streak != 0 && UtilitiesTime.return_elapsed(plugin.getMaxMillis(), lastClaim))
        {
            streak = 0;
        }
    }

    public boolean canClaim()
    {
        return UtilitiesTime.return_elapsed(plugin.getCooldownMillis(), lastClaim);
    }
}
