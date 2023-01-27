package us.glasscrab.i.inthedepths;

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
    public Manager() {
        manager = this;
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

    public boolean isUpgradeableItem (Material material){
        Set<Material> upgradeableItemList= new HashSet<>();
        upgradeableItemList.add(Material.NETHERITE_PICKAXE);
        upgradeableItemList.add(Material.NETHERITE_AXE);
        upgradeableItemList.add(Material.NETHERITE_SHOVEL);
        upgradeableItemList.add(Material.NETHERITE_HOE);
        upgradeableItemList.add(Material.NETHERITE_HELMET);
        upgradeableItemList.add(Material.NETHERITE_CHESTPLATE);
        upgradeableItemList.add(Material.NETHERITE_LEGGINGS);
        upgradeableItemList.add(Material.NETHERITE_BOOTS);
        upgradeableItemList.add(Material.NETHERITE_SWORD);
        return upgradeableItemList.contains(material);
    }

    public boolean isNetheriteSword (Material material){
        Set<Material> netheriteSwordList = new HashSet<>();
        netheriteSwordList.add(Material.NETHERITE_SWORD);
        return netheriteSwordList.contains(material);
    }

    public void giveCrystal(ItemStack item, Item droppedItem){
        List<String> lore = new ArrayList<>();
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bDepth Crystal");
        lore.add("§7A shimmering jewel that can be inset into netherite items.");
        meta.setLore(lore);
        lore.clear();
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        droppedItem.getWorld().dropItem(droppedItem.getLocation(), item);
    }

    public static Manager getManager() {
        return manager;
    }
}
