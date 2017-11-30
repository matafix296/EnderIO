package crazypants.enderio.base.network;

import com.enderio.core.common.network.ThreadedNetworkWrapper;

import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.handler.darksteel.PacketDarkSteelPowerPacket;
import crazypants.enderio.base.handler.darksteel.PacketUpgradeState;
import crazypants.enderio.base.item.conduitprobe.PacketConduitProbe;
import crazypants.enderio.base.item.conduitprobe.PacketConduitProbeMode;
import crazypants.enderio.base.item.coordselector.PacketUpdateLocationPrintout;
import crazypants.enderio.base.item.magnet.PacketMagnetState;
import crazypants.enderio.base.item.travelstaff.PacketDrainStaff;
import crazypants.enderio.base.item.xptransfer.PacketXpTransferEffects;
import crazypants.enderio.base.item.yetawrench.YetaWrenchPacketProcessor;
import crazypants.enderio.base.machine.baselegacy.PacketLegacyPowerStorage;
import crazypants.enderio.base.machine.modes.PacketIoMode;
import crazypants.enderio.base.machine.modes.PacketRedstoneMode;
import crazypants.enderio.base.teleport.packet.PacketOpenAuthGui;
import crazypants.enderio.base.teleport.packet.PacketTravelEvent;
import crazypants.enderio.base.transceiver.PacketAddRemoveChannel;
import crazypants.enderio.base.transceiver.PacketChannelList;
import crazypants.enderio.base.xp.PacketExperienceContainer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;

public class PacketHandler {

  public static final ThreadedNetworkWrapper INSTANCE = new ThreadedNetworkWrapper(EnderIO.DOMAIN);

  private static int ID = 0;

  public static int nextID() {
    return ID++;
  }

  public static void sendToAllAround(IMessage message, TileEntity te, int range) {
    BlockPos p = te.getPos();
    INSTANCE.sendToAllAround(message, new TargetPoint(te.getWorld().provider.getDimension(), p.getX(), p.getY(), p.getZ(), range));
  }

  public static void sendToAllAround(IMessage message, TileEntity te) {
    sendToAllAround(message, te, 64);
  }

  public static void sendTo(IMessage message, EntityPlayerMP player) {
    INSTANCE.sendTo(message, player);
  }

  public static void init(FMLInitializationEvent event) {
    INSTANCE.registerMessage(PacketRedstoneMode.Handler.class, PacketRedstoneMode.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(GuiPacket.Handler.class, GuiPacket.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketExperienceContainer.class, PacketExperienceContainer.class, nextID(), Side.CLIENT);
    // TODO 1.11 move to sub-mod
    // INSTANCE.registerMessage(PacketNutrientTank.class, PacketNutrientTank.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketConduitProbe.Handler.class, PacketConduitProbe.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketConduitProbeMode.class, PacketConduitProbeMode.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(YetaWrenchPacketProcessor.Handler.class, YetaWrenchPacketProcessor.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketXpTransferEffects.Handler.class, PacketXpTransferEffects.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketTravelEvent.Handler.class, PacketTravelEvent.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketAddRemoveChannel.Handler.class, PacketAddRemoveChannel.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketAddRemoveChannel.Handler.class, PacketAddRemoveChannel.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketChannelList.Handler.class, PacketChannelList.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketDarkSteelPowerPacket.class, PacketDarkSteelPowerPacket.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketUpgradeState.class, PacketUpgradeState.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketUpgradeState.class, PacketUpgradeState.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketDrainStaff.Handler.class, PacketDrainStaff.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketIoMode.Handler.class, PacketIoMode.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketLegacyPowerStorage.Handler.class, PacketLegacyPowerStorage.class, nextID(), Side.CLIENT);
    INSTANCE.registerMessage(PacketMagnetState.Handler.class, PacketMagnetState.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketOpenAuthGui.Handler.class, PacketOpenAuthGui.class, nextID(), Side.SERVER);
    INSTANCE.registerMessage(PacketUpdateLocationPrintout.Handler.class, PacketUpdateLocationPrintout.class, nextID(), Side.SERVER);

  }

}