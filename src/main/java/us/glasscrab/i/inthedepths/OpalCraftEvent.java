package us.glasscrab.i.inthedepths;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class OpalCraftEvent implements Listener {
    private final Manager manager;
    private final String message = ChatColor.AQUA + "Opal inset into tool!";

    public OpalCraftEvent(Manager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void opalCombine(PlayerSwapHandItemsEvent e){
        if(e.getMainHandItem() == null) return;
        if(e.getOffHandItem() == null) return;
        if(!manager.isUpgradeable(e.getOffHandItem().getType())) return;
        if(!e.getMainHandItem().getType().equals(Material.ECHO_SHARD)) return;
        if(e.getMainHandItem().getItemMeta() == null) return;
        if(!e.getMainHandItem().getItemMeta().hasCustomModelData()) return;
        if(e.getMainHandItem().getItemMeta().getCustomModelData() != 1) return;

        ItemStack netheriteItem = e.getOffHandItem();
        ItemMeta meta = netheriteItem.getItemMeta();
        ItemStack opal = e.getMainHandItem();

        if(e.getOffHandItem().getItemMeta().hasLore()){
            if(e.getOffHandItem().getItemMeta().getLore().contains(ChatColor.AQUA + "♢" + ChatColor.GRAY + "Charged Opal" + ChatColor.AQUA + "♢")){
                String message = ChatColor.RED + "This item already has an opal inset!";
                e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                e.setCancelled(true);
                return;
            }
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.DIG_SPEED)){
            if(meta.getEnchantLevel(Enchantment.DIG_SPEED) == 6) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.DIG_SPEED,6, true);
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_ENVIRONMENTAL)){
            if(meta.getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) == 5) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL,5, true);
        }
        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_PROJECTILE)){
            if(meta.getEnchantLevel(Enchantment.PROTECTION_PROJECTILE) == 5) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.PROTECTION_PROJECTILE,5, true);
        }
        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_EXPLOSIONS)){
            if(meta.getEnchantLevel(Enchantment.PROTECTION_EXPLOSIONS) == 5) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.PROTECTION_EXPLOSIONS,5, true);
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.PROTECTION_FIRE)){
            if(meta.getEnchantLevel(Enchantment.PROTECTION_FIRE) == 5) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.PROTECTION_FIRE,5, true);
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.DAMAGE_ALL)){
            if(meta.getEnchantLevel(Enchantment.DAMAGE_ALL) == 6) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.DAMAGE_ALL,6, true);
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.DAMAGE_UNDEAD)){
            if(meta.getEnchantLevel(Enchantment.DAMAGE_UNDEAD) == 6) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.DAMAGE_UNDEAD,6, true);
        }

        else if(e.getOffHandItem().getEnchantments().containsKey(Enchantment.DAMAGE_ARTHROPODS)){
            if(meta.getEnchantLevel(Enchantment.DAMAGE_ARTHROPODS) == 6) return;
            e.getMainHandItem().setAmount(e.getMainHandItem().getAmount() -1);
            meta.addEnchant(Enchantment.DAMAGE_ARTHROPODS,6, true);
        }

        else{
            String message = ChatColor.RED + "This item is inert, it cannot accept an opal!";
            e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
            e.setCancelled(true);
            return;
        }

        List<String> itemLore;
        if(meta.getLore() == null){
            itemLore = new ArrayList<>();
        }
        else{
            itemLore = meta.getLore();
        }
        itemLore.add(ChatColor.AQUA + "♢" + ChatColor.GRAY + "Charged Opal" + ChatColor.AQUA + "♢");
        meta.setLore(itemLore);
        netheriteItem.setItemMeta(meta);

        e.setMainHandItem(netheriteItem);
        e.setOffHandItem(opal);

        e.getPlayer().spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
        e.getPlayer().playSound(e.getPlayer(), Sound.BLOCK_AMETHYST_BLOCK_FALL, 1,1);

    }
}
