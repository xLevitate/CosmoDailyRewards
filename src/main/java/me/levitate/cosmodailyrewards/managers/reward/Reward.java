package me.levitate.cosmodailyrewards.managers.reward;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.items.CustomItem;
import me.levitate.cosmodailyrewards.managers.message.Message;
import me.levitate.cosmodailyrewards.managers.utilities.Utilities;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.stream.Collectors;

public class Reward
{
    private final CosmoDailyRewards plugin;

    private final String name;
    private final int tier;
    private final List<String> actions;

    private final CustomItem stack;

    Reward(CosmoDailyRewards plugin, String name, int tier, Material material, int data, List<String> lore, List<String> actions)
    {
        this.plugin = plugin;

        this.name = name;
        this.tier = tier;
        this.actions = actions;

        this.stack =
                new CustomItem(material)
                        .setDamage((short) data)
                        .setName(Utilities.color(name))
                        .setLore(lore.stream().map(Utilities::color).collect(Collectors.toList()));
    }

    String getName()
    {
        return name;
    }

    int getTier()
    {
        return tier;
    }

    public CustomItem getStack()
    {
        return stack;
    }

    public void apply(Player player)
    {
        player.sendMessage(Message.CLAIMED_REWARD.value().replace("%reward%", getName()));
        plugin.getActionUtil().executeActions(player, actions);

        plugin.getRewardManager().getData(player).update();
    }
}
