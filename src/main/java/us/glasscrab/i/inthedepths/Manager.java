package us.glasscrab.i.inthedepths;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Manager {
    private static Manager manager;
    private List<Material> upgradeableItems = new ArrayList<>();
    public Manager() {
        manager = this;
    }

    public boolean isUpgradeable(Material material) {
        // Netherite Armor:
        upgradeableItems.add(Material.NETHERITE_HELMET);
        upgradeableItems.add(Material.NETHERITE_CHESTPLATE);
        upgradeableItems.add(Material.NETHERITE_LEGGINGS);
        upgradeableItems.add(Material.NETHERITE_BOOTS);

        // Netherite Tools:
        upgradeableItems.add(Material.NETHERITE_PICKAXE);
        upgradeableItems.add(Material.NETHERITE_AXE);
        upgradeableItems.add(Material.NETHERITE_SHOVEL);
        upgradeableItems.add(Material.NETHERITE_HOE);
        upgradeableItems.add(Material.NETHERITE_SWORD);

        return upgradeableItems.contains(material);
    }

    public boolean isNetheriteArmor (Material material){
        Set<Material> netheriteArmorList = new HashSet<>();
        netheriteArmorList.add(Material.NETHERITE_HELMET);
        netheriteArmorList.add(Material.NETHERITE_CHESTPLATE);
        netheriteArmorList.add(Material.NETHERITE_LEGGINGS);
        netheriteArmorList.add(Material.NETHERITE_BOOTS);
        return netheriteArmorList.contains(material);
    }

    public boolean isNetheriteTool (Material material){
        Set<Material> netheriteToolList= new HashSet<>();
        netheriteToolList.add(Material.NETHERITE_PICKAXE);
        netheriteToolList.add(Material.NETHERITE_AXE);
        netheriteToolList.add(Material.NETHERITE_SHOVEL);
        netheriteToolList.add(Material.NETHERITE_HOE);
        return netheriteToolList.contains(material);
    }

    public boolean isNetheriteSword (Material material){
        return material == Material.NETHERITE_SWORD;
    }

    public void dropCrystal(Item droppedItem){
        droppedItem.getWorld().dropItem(droppedItem.getLocation(), this.makeCrystal());
    }

    public ItemStack makeCrystal() {
        List<String> lore = new ArrayList<>();
        ItemStack item = new ItemStack(Material.ECHO_SHARD, 1);
        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(ChatColor.AQUA + "Charged Opal");
        lore.add(ChatColor.GRAY + "A shimmering jewel that can be inset into netherite items.");
        meta.setLore(lore);

        lore.clear();

        meta.setCustomModelData(1);

        item.setItemMeta(meta);

        return item;
    }

    public static Manager getManager() {
        return manager;
    }
}
