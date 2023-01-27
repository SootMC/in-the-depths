package us.glasscrab.i.inthedepths;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class Inthedepths extends JavaPlugin implements Listener {

    int jackpot = 66;
    int chance = 250;
    ArrayList<String> lore = new ArrayList<>();
    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(this, this);
        this.getLogger().info("InTheDepths has been enabled!");
    }

    ItemStack item = new ItemStack(Material.ECHO_SHARD, 1);

    public void giveCrystal(ItemStack item, Item droppedItem){
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§bDepth Crystal");
        lore.add("§7A shimmering jewel that can be inset into netherite items.");
        meta.setLore(lore);
        lore.clear();
        meta.setCustomModelData(1);
        item.setItemMeta(meta);
        droppedItem.getWorld().dropItem(droppedItem.getLocation(), item);
    }

    @EventHandler
    public void crystalCraft(PlayerSwapHandItemsEvent e){
        if(     e.getMainHandItem() != null &&
                e.getOffHandItem() != null &&
                isUpgradeableItem(e.getOffHandItem().getType()) &&
                e.getMainHandItem().getItemMeta() != null &&
                e.getMainHandItem().getItemMeta().hasCustomModelData() &&
                e.getMainHandItem().getItemMeta().getCustomModelData() == 1){

            ItemStack netheriteItem = e.getOffHandItem();
            ItemMeta meta = netheriteItem.getItemMeta();
            ItemStack crystal = e.getMainHandItem();
            if(isNetheriteTool(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.DIG_SPEED)){
                if(meta.getEnchantLevel(Enchantment.DIG_SPEED) < 6){
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
                    meta.addEnchant(Enchantment.DIG_SPEED,6, true);
                    ArrayList<String> itemLore = new ArrayList<String>();
                    itemLore.add("§b♢§7Depth Crystal§b♢");
                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);
                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);
                    String message = "§bCrystal set into item!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1,1);

                }
                else{
                    String message = "§cThis item already has a crystal inset!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.setCancelled(true);
                }
            }
            else if(isNetheriteArmor(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)){
                if(meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 5){
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,5, true);
                    ArrayList<String> itemLore = new ArrayList<String>();
                    itemLore.add("§b♢§7Depth Crystal§b♢");
                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);
                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);
                    String message = "§bCrystal set into tool!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1,1);
                }
                else{
                    String message = "§cThis item already has a crystal inset!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.setCancelled(true);
                }
            }
            else if(isNetheriteSword(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)){
                if(meta.getEnchantLevel(Enchantment.DAMAGE_ALL) < 6){
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
                    meta.addEnchant(Enchantment.DAMAGE_ALL,6, true);
                    ArrayList<String> itemLore = new ArrayList<String>();
                    itemLore.add("§b♢§7Depth Crystal§b♢");
                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);
                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);
                    String message = "§bCrystal set into tool!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1,1);
                }
                else{
                    String message = "§cThis item already has a crystal inset!";
                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.setCancelled(true);
                }
            }
            else{
                String message = "§cThis item is inert, it cannot accept a crystal!";
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void blockEvent(BlockDropItemEvent e){
        if (e.getItems().size() > 0) {
            Item droppedItem = e.getItems().get(0);
            if (droppedItem.getItemStack().getType().equals(Material.DIAMOND) && !(e.getBlockState() instanceof Container)) {
                int rand = (int) (Math.random() * chance) + 1;
                if (rand == jackpot) {
                    giveCrystal(item, droppedItem);
                    Bukkit.broadcastMessage("§3§l"+e.getPlayer().getName()+"§r unearthed a §bDepth Crystal!");
                }
            }
        }
    }

    public static boolean isNetheriteArmor (Material material){
        Set<Material> netheriteArmorList = new HashSet<>();
        netheriteArmorList.add(Material.NETHERITE_HELMET);
        netheriteArmorList.add(Material.NETHERITE_CHESTPLATE);
        netheriteArmorList.add(Material.NETHERITE_LEGGINGS);
        netheriteArmorList.add(Material.NETHERITE_BOOTS);
        return netheriteArmorList.contains(material);
    }

    public static boolean isNetheriteTool (Material material){
        Set<Material> netheriteToolList= new HashSet<>();
        netheriteToolList.add(Material.NETHERITE_PICKAXE);
        netheriteToolList.add(Material.NETHERITE_AXE);
        netheriteToolList.add(Material.NETHERITE_SHOVEL);
        netheriteToolList.add(Material.NETHERITE_HOE);
        return netheriteToolList.contains(material);
    }

    public static boolean isUpgradeableItem (Material material){
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

    public static boolean isNetheriteSword (Material material){
        Set<Material> netheriteSwordList = new HashSet<>();
        netheriteSwordList.add(Material.NETHERITE_SWORD);
        return netheriteSwordList.contains(material);
    }


    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
