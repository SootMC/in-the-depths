package us.glasscrab.i.inthedepths;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Container;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class EventListener implements Listener {
    private final Manager manager;
    private final int jackpot = 66;
    private final int chance = 250;
    private final String message = ChatColor.AQUA + "Crystal set into tool!";
    private ArrayList<String> itemLore = new ArrayList<>();

    public EventListener(Manager manager) {
        this.manager = manager;
        itemLore.add(ChatColor.AQUA + "♢" + ChatColor.GRAY + "Depth Crystal" + ChatColor.AQUA + "♢");
    }

    @EventHandler
    public void crystalCraft(PlayerSwapHandItemsEvent e){
        if(e.getMainHandItem() != null &&
                e.getOffHandItem() != null &&
                manager.isUpgradeable(e.getOffHandItem().getType()) &&
                e.getMainHandItem().getItemMeta() != null &&
                e.getMainHandItem().getItemMeta().hasCustomModelData() &&
                e.getMainHandItem().getItemMeta().getCustomModelData() == 1){

            ItemStack netheriteItem = e.getOffHandItem();
            ItemMeta meta = netheriteItem.getItemMeta();
            ItemStack crystal = e.getMainHandItem();

            if(manager.isNetheriteTool(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.DIG_SPEED)){
                if(meta.getEnchantLevel(Enchantment.DIG_SPEED) < 6){
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);

                    meta.addEnchant(Enchantment.DIG_SPEED,6, true);

                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);

                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);

                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1,1);

                    return;
                }
            } else if(manager.isNetheriteArmor(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)) {
                if (meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 5) {
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() - 1);

                    meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 5, true);

                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);

                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);


                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1, 1);

                    return;
                }
            } else if (manager.isNetheriteSword(e.getOffHandItem().getType()) && e.getOffHandItem().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)) {
                if (meta.getEnchantLevel(Enchantment.DAMAGE_ALL) < 6) {
                    e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() - 1);

                    meta.addEnchant(Enchantment.DAMAGE_ALL, 6, true);

                    meta.setLore(itemLore);
                    netheriteItem.setItemMeta(meta);

                    e.setMainHandItem(netheriteItem);
                    e.setOffHandItem(crystal);

                    e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                    e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1, 1);

                    return;
                }
            } else {
                String message = ChatColor.RED + "This item is inert, it cannot accept a crystal!";
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                e.setCancelled(true);

                return;
            }

            String message = ChatColor.RED + "This item already has a crystal inset!";
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void blockEvent(BlockDropItemEvent e){
        if (e.getItems().size() > 0) {
            Item droppedItem = e.getItems().get(0);
            if (droppedItem.getItemStack().getType().equals(Material.DIAMOND) && !(e.getBlockState() instanceof Container)) {
                int rand = (int) (Math.random() * chance) + 1;
                if (rand == jackpot) {
                    manager.dropCrystal(droppedItem);
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + e.getPlayer().getName()+ChatColor.RESET + " unearthed a " + ChatColor.AQUA + "Depth Crystal!");
                }
            }
        }
    }
}
