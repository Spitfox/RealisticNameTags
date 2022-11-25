package io.github.spitfox.realisticnametag.events;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.NameTagItem;
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
                if (player.isCrouching())
                {
                    entity.setCustomName(null);
                    ItemStack nametag = new ItemStack(Items.NAME_TAG, 1);
                    CompoundTag tag = nametag.getOrCreateTag();
                    CompoundTag name = new CompoundTag();
                    name.putString("Name", "{\"text\":\"test\"}");
                    tag.put("display", name);
                    System.out.print(tag.get("Name"));
                    nametag.setTag(tag);
                    player.addItem(nametag);
                }
            }
        }
    }
}