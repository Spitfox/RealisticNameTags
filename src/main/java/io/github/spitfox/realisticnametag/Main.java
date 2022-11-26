package io.github.spitfox.realisticnametag;

import com.mojang.logging.LogUtils;
import io.github.spitfox.realisticnametag.events.MyEvents;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(Main.MODID)
public class Main {
    public static final String MODID = "realisticnametag";
    private static final Logger LOGGER = LogUtils.getLogger();
    public Main()
    {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new MyEvents());
    }

}
