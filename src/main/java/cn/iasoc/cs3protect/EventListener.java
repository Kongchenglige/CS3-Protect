package cn.iasoc.cs3protect;

import com.Acrobot.ChestShop.Events.PreTransactionEvent;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import static com.Acrobot.ChestShop.Events.PreTransactionEvent.TransactionOutcome.SPAM_CLICKING_PROTECTION;

public class EventListener implements Listener {
    private final Plugin plugin;

    public EventListener(Plugin plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPrePurchaseEvent(PreTransactionEvent event) {
        if (event.getClient() == null) return;

        Player player = event.getClient();
        Block targetBlock = player.getTargetBlock(null, 5);

        // Check if the target block is a sign and calculate the distance
        boolean isTargetBlockSign = (targetBlock.getState() instanceof Sign);
        double distance = event.getSign().getLocation().distance(event.getClient().getLocation());

        // Prevent buying from a far distance or not targeting a sign
        if (!isTargetBlockSign || distance > 3.5) {
            // Send a message to the player
            player.sendMessage("§c[FPCSP] 您距離過遠或未對準商店牌!");
            String pos = "[" + player.getLocation().getBlockX() + "," + player.getLocation().getBlockY() + "," + player.getLocation().getBlockZ() + "]";
            this.plugin.getLogger().info("Player " + player.getName() + " tried to buy " + event.getSign().getLine(3) + " from a far distance or not targeting a sign at " + pos + " .");
            // Prevent spam clicking
            event.setCancelled(SPAM_CLICKING_PROTECTION);
        }
    }
}
