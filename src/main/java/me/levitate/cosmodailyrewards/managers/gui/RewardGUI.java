package me.levitate.cosmodailyrewards.managers.gui;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.items.CustomItem;
import me.levitate.cosmodailyrewards.managers.reward.Reward;
import me.levitate.cosmodailyrewards.managers.reward.RewardData;
import me.levitate.cosmodailyrewards.managers.utilities.UtilitiesTime;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

public class RewardGUI
{
    private final Inventory inventory;

    RewardGUI(Player player, CosmoDailyRewards plugin)
    {
        this.inventory = Bukkit.createInventory(null, 27, plugin.getMenuManager().getMenuName(player));

        int index = 9;

        RewardData data = plugin.getRewardManager().getData(player);
        data.resetIfExpired();

        int streak = data.getStreak();

        for (Reward reward : plugin.getRewardManager().getRewards(player))
        {
            index++;

            CustomItem stack = reward.getStack().dupe();

            if (reward == plugin.getRewardManager().getRewards(player).get(streak))
            {
                if (data.canClaim())
                {
                    stack.addGlow();
                }
                else
                {
                    List<String> lore = stack.getItemMeta().getLore();

                    lore.add("");

                    String time = UtilitiesTime.return_remaining((data.getLastClaim() + plugin.getCooldownMillis()) - System.currentTimeMillis());
                    lore.add(plugin.getMenuManager().getCooldownMessage().replace("%time%", time));

                    stack.setLore(lore);
                }
            }

            inventory.setItem(index, stack);
        }

        player.openInventory(inventory);
    }
}
