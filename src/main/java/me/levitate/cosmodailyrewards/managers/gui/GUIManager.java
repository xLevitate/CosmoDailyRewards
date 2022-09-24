package me.levitate.cosmodailyrewards.managers.gui;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.message.Message;
import me.levitate.cosmodailyrewards.managers.reward.Reward;
import me.levitate.cosmodailyrewards.managers.utilities.Utilities;
import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class GUIManager implements Listener
{
    private final CosmoDailyRewards plugin;

    private final String menuName;
    private final String cooldownMessage;

    public GUIManager(CosmoDailyRewards plugin)
    {
        this.plugin = plugin;

        menuName = Utilities.color(plugin.getConfig("gui.title"));
        cooldownMessage = Utilities.color(plugin.getConfig("gui.cooldown"));

        Bukkit.getPluginManager().registerEvents(this, plugin);

        plugin.getCommand("dailyrewards").setExecutor(new RewardCommand(plugin));
    }

    String getCooldownMessage()
    {
        return cooldownMessage;
    }

    String getMenuName(Player player)
    {
        return menuName;
    }

    @EventHandler
    public void onClick(InventoryClickEvent event)
    {
        Player player = (Player)event.getWhoClicked();

        if (player.getOpenInventory().getTitle().equals(getMenuName(player)))
        {
            event.setCancelled(true);

            ItemStack clicked = event.getCurrentItem();

            if (clicked != null && clicked.getItemMeta() != null && clicked.getItemMeta().hasDisplayName())
            {
                if (!clicked.getItemMeta().hasEnchant(Enchantment.DURABILITY))
                {
                    Utilities.message(player, Message.CANNOT_CLAIM);
                    return;
                }

                Reward reward = plugin.getRewardManager().getRewardFromStack(clicked);

                reward.apply(player);

                player.getOpenInventory().close();
            }
        }
    }

    void openRewards(Player player)
    {
        new RewardGUI(player, plugin);
    }

}
