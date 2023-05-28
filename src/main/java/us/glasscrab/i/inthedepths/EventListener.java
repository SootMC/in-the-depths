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
import java.util.List;

public class EventListener implements Listener {
    private final Manager manager;
    private final int jackpot = 66;
    private final int chance = 250;
    private final String message = ChatColor.AQUA + "Opal inset into tool!";


    public EventListener(Manager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void blockEvent(BlockDropItemEvent e){
        if (e.getItems().size() > 0) {
            Item droppedItem = e.getItems().get(0);
            if (droppedItem.getItemStack().getType().equals(Material.DIAMOND) && !(e.getBlockState() instanceof Container)) {
                int rand = (int) (Math.random() * chance) + 1;
                if (rand == jackpot) {
                    manager.dropOpal(droppedItem);
                    Bukkit.broadcastMessage(ChatColor.DARK_AQUA + "" + ChatColor.BOLD + e.getPlayer().getName()+ChatColor.RESET + " unearthed a " + ChatColor.AQUA + "Charged Opal!");
                }
            }
        }
    }
}
