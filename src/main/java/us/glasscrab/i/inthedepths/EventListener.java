package us.glasscrab.i.inthedepths;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Container;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDropItemEvent;

public class EventListener implements Listener {
    private final Manager manager;
    private final int jackpot = 66;
    private final int chance = 250;


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
