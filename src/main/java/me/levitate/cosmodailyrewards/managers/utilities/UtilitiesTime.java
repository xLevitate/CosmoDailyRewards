package me.levitate.cosmodailyrewards.managers.utilities;

import java.util.concurrent.TimeUnit;

public class UtilitiesTime
{
    public static boolean return_elapsed(long time, long since)
    {
        return System.currentTimeMillis() - since > time;
    }

    public static String return_remaining(long time)
    {
        long hours = TimeUnit.MILLISECONDS.toHours(time);
        String hoursString = UtilitiesString.plural(hours, "hour", "hours");

        long minutes = TimeUnit.MILLISECONDS.toMinutes(time - (hours * 3600000));
        String minutesString = UtilitiesString.plural(minutes, "minute", "minutes");

        if (hours == 0 && minutes == 0)
        {
            int seconds = (int) TimeUnit.MILLISECONDS.toSeconds(time);

            return seconds + " " + UtilitiesString.plural(seconds, "second", "seconds");
        }

        String timeString = "";

        if (hours != 0)
        {
            timeString += hours + " " + hoursString;
        }

        if (minutes != 0)
        {
            timeString += " and " + minutes + " " + minutesString;
        }

        return timeString;
    }
}
