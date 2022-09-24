package me.levitate.cosmodailyrewards.managers.gui;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.message.Message;
import me.levitate.cosmodailyrewards.managers.utilities.Utilities;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class RewardCommand implements CommandExecutor
{
    private final CosmoDailyRewards plugin;

    RewardCommand(CosmoDailyRewards plugin)
    {
        this.plugin = plugin;
    }

    private void handleArgs(Player player)
    {
        plugin.getMenuManager().openRewards(player);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            Utilities.message(sender, Message.NO_CONSOLE);
            return true;
        }

        handleArgs((Player) sender);
        return true;
    }
}
