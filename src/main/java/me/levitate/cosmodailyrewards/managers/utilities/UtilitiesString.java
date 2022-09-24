package me.levitate.cosmodailyrewards.managers.utilities;

public class UtilitiesString
{
    public static String plural(long amount, String singular, String plural)
    {
        return amount == 1 ? singular : plural;
    }
}
