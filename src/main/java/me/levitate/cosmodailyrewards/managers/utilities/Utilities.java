package me.levitate.cosmodailyrewards.managers.utilities;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.message.Message;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class Utilities
{
    private static final CosmoDailyRewards plugin = JavaPlugin.getPlugin(CosmoDailyRewards.class);

    public static String color(String input)
    {
        return ChatColor.translateAlternateColorCodes('&', input);
    }

    public static void message(CommandSender sender, Message message)
    {
        sender.sendMessage(message.value());
    }

    public static void log(String msg)
    {
        plugin.getLogger().info(msg);
    }

    public static void logError(String msg)
    {
        plugin.getLogger().log(Level.WARNING, msg);
    }

    public static void logFatal(String msg)
    {
        plugin.getLogger().severe(msg);
    }
}
