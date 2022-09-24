package me.levitate.cosmodailyrewards.managers.data;

import org.bukkit.entity.Player;

public interface DataStorage<T>
{
    void init();

    void close();

    void save(Player player, T data);

    T load(Player player);
}
