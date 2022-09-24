package me.levitate.cosmodailyrewards.managers.items;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class CustomItem extends ItemStack
{
    public CustomItem(Material material)
    {
        this(material, 1);
    }

    public CustomItem(Material material, int amount)
    {
        super(material, amount);
    }

    public CustomItem setDamage(short damage)
    {
        setDurability(damage);

        return this;
    }

    public CustomItem setName(String name)
    {
        executeMetaMethod(meta -> meta.setDisplayName(name));

        return this;
    }

    public CustomItem setLore(String... lore)
    {
        setLore(Arrays.asList(lore));

        return this;
    }

    public CustomItem setLore(List<String> lore)
    {
        executeMetaMethod(meta -> meta.setLore(lore));

        return this;
    }

    public CustomItem addEnchant(Enchantment enchantment, int level)
    {
        addUnsafeEnchantment(enchantment, level);

        return this;
    }

    public CustomItem addGlow()
    {
        addEnchant(Enchantment.DURABILITY, 999);

        executeMetaMethod(meta -> meta.addItemFlags(ItemFlag.HIDE_ENCHANTS));

        return this;
    }

    public CustomItem addFlags(ItemFlag... flags)
    {
        executeMetaMethod(meta -> meta.addItemFlags(flags));

        return this;
    }

    public CustomItem makeUnbreakable()
    {
        executeMetaMethod(meta -> meta.setUnbreakable(true));

        return this;
    }

    public CustomItem dupe()
    {
        return (CustomItem) clone();
    }

    private void executeMetaMethod(Consumer<ItemMeta> consumer)
    {
        ItemMeta meta = getItemMeta();
        consumer.accept(meta);
        setItemMeta(meta);
    }
}
