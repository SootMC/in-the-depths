package us.glasscrab.i.inthedepths;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

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

    public Set<Enchantment> getUpgradeableToolEnchantmentList(){
        Set<Enchantment> upgradeableToolEnchantmentList = new HashSet<>();
        upgradeableToolEnchantmentList.add(Enchantment.DAMAGE_ALL);
        upgradeableToolEnchantmentList.add(Enchantment.DAMAGE_UNDEAD);
        upgradeableToolEnchantmentList.add(Enchantment.DAMAGE_ARTHROPODS);
        upgradeableToolEnchantmentList.add(Enchantment.DIG_SPEED);
        return upgradeableToolEnchantmentList;
    }

    public boolean containsUpgradeableToolEnchant (Map<Enchantment,Integer> enchantments){
        Set<Enchantment> upgradeableToolEnchantmentList = getUpgradeableToolEnchantmentList();
        for(Enchantment e : enchantments.keySet()){
            if(upgradeableToolEnchantmentList.contains(e)){
                return true
            }
        }
        return false;
    }

    public Set<Enchantment> getUpgradeableArmorEnchantmentList(){
        Set<Enchantment> upgradeableArmorEnchantmentList = new HashSet<>();
        upgradeableArmorEnchantmentList.add(Enchantment.PROTECTION_ENVIRONMENTAL);
        upgradeableArmorEnchantmentList.add(Enchantment.PROTECTION_EXPLOSIONS);
        upgradeableArmorEnchantmentList.add(Enchantment.PROTECTION_FIRE);
        upgradeableArmorEnchantmentList.add(Enchantment.PROTECTION_PROJECTILE);
        return upgradeableArmorEnchantmentList;
    }

    public boolean containsUpgradeableArmorEnchant(Map<Enchantment,Integer> enchantments){
        Set<Enchantment> upgradeableArmorEnchantmentList = getUpgradeableArmorEnchantmentList();
        for(Enchantment e : enchantments.keySet()){
            if(upgradeableArmorEnchantmentList.contains(e)){
                return true;
            }
        }
        return false;
    }

    public void dropOpal(Item droppedItem){
        droppedItem.getWorld().dropItem(droppedItem.getLocation(), this.makeOpal());
    }

    public ItemStack makeOpal() {
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
