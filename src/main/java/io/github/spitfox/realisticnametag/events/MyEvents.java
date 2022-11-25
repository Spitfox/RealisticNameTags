package io.github.spitfox.realisticnametag.events;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class MyEvents
{
    @SubscribeEvent
    public void PlayerInteract(PlayerInteractEvent.EntityInteract event) {
        Entity entity = event.getTarget();
        Player player = event.getEntity();
        if(entity.getCustomName() != null) {
            if (player.getMainHandItem().getItem() == Items.SHEARS) {
                if (player.isCrouching()) {
                    entity.removeTag("CustomName");
                    player.addItem(new ItemStack(Items.NAME_TAG,1));
                }
            }
        }
    }
}