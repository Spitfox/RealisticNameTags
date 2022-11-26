package io.github.spitfox.realisticnametag.events;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
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
        BlockPos pos = event.getPos();
        if(entity.getCustomName() != null)
        {
            if(event.getItemStack().getItem() == Items.NAME_TAG)
            {
                String customName = entity.getCustomName().getString();
                ItemStack name_tag = new ItemStack(Items.NAME_TAG, 1);
                CompoundTag tag = name_tag.getOrCreateTag();
                CompoundTag name = new CompoundTag();
                name.putString("Name", "{\"text\":\"" + customName + "\"}");
                tag.put("display", name);
                name_tag.setTag(tag);

                event.getLevel().addFreshEntity(new ItemEntity(event.getLevel(), pos.getX(), pos.getY(), pos.getZ(), name_tag));
            }

            if (player.getMainHandItem().getItem() == Items.SHEARS) {
                if (player.isCrouching())
                {
                    String customName = entity.getCustomName().getString();
                    entity.setCustomName(null);
                    ItemStack name_tag = new ItemStack(Items.NAME_TAG, 1);
                    CompoundTag tag = name_tag.getOrCreateTag();
                    CompoundTag name = new CompoundTag();
                    name.putString("Name", "{\"text\":\"" + customName + "\"}");
                    tag.put("display", name);
                    name_tag.setTag(tag);
                    player.addItem(name_tag);
                }
            }
        }
    }
}