package me.levitate.cosmodailyrewards.managers.message;

import java.util.Arrays;

import me.levitate.cosmodailyrewards.CosmoDailyRewards;
import me.levitate.cosmodailyrewards.managers.utilities.Utilities;

public enum Message
{
    NO_CONSOLE,
    CLAIMED_REWARD,
    CANNOT_CLAIM;

    private String msg;
    public String value()
    {
        return msg;
    }

    private void setValue(String msg)
    {
        this.msg = msg;
    }

    public static void initialize(CosmoDailyRewards plugin)
    {
        Arrays.stream(values()).forEach(message ->
        {
            String raw = plugin.getConfig("messages." + message.name().toLowerCase());

            if (raw == null)
            {
                Utilities.logError("Unable to find message value for message '" + message.name() + "'");

                raw = message.name();
            }

            message.setValue(Utilities.color(raw));
        });
    }
}
