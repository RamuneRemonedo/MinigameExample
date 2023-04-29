package net.ramuremo.minigameexample.asset;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public enum ItemAsset {
    EXPLOSION_STICK(Material.STICK, Component.text("破裂! スティック").color(TextColor.color(0xFF7BBD)));

    private final Material material;
    private final Component displayName;

    ItemAsset(Material material, Component displayName) {
        this.material = material;
        this.displayName = displayName;
    }

    public Material getMaterial() {
        return material;
    }

    public Component getDisplayName() {
        return displayName;
    }

    public ItemStack toItemStack() {
        final ItemStack itemStack = new ItemStack(material);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.displayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
