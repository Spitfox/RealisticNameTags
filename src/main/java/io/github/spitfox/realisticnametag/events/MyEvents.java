package io.github.spitfox.realisticnametag.events;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
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
        BlockPos pos = event.getPos();
        if(entity.getCustomName() != null)
        {
            String customName = entity.getCustomName().getString();
            ItemStack name_tag = new ItemStack(Items.NAME_TAG, 1);
            CompoundTag tag = name_tag.getOrCreateTag();
            CompoundTag name = new CompoundTag();

            name.putString("Name", "{\"text\":\"" + customName + "\"}");
            tag.put("display", name);

            name_tag.setTag(tag);

            if(event.getItemStack().getItem() == Items.NAME_TAG)
            {
                event.getLevel().addFreshEntity(new ItemEntity(event.getLevel(), pos.getX(), pos.getY(), pos.getZ(), name_tag));
            }

            if (player.getMainHandItem().getItem() == Items.SHEARS) {
                if (player.isCrouching())
                {
                    player.addItem(name_tag);
                }
            }
        }
    }
}