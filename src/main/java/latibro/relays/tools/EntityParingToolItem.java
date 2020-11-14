package latibro.relays.tools;

import cam72cam.mod.util.TagCompound;
import latibro.relays.RelaysCreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;

public class EntityParingToolItem extends Item {

    public EntityParingToolItem() {
        super();
        setRegistryName("entity_paring_tool");
        setUnlocalizedName("relays.entity_paring_tool");
        setCreativeTab(RelaysCreativeTabs.DEFAULT);
        setMaxStackSize(1);
    }

    @Override
    public boolean onLeftClickEntity(ItemStack itemStack, EntityPlayer player, Entity target) {
        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        itemStack.getTagCompound().setString("target", String.valueOf(target.getUniqueID()));
        player.setHeldItem(EnumHand.MAIN_HAND, itemStack);
        player.sendMessage(new TextComponentString("Entity ID stored: " + target.getUniqueID()));
        return true;
    }

    @Override
    public boolean itemInteractionForEntity(ItemStack itemStack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if (!EnumHand.MAIN_HAND.equals(hand)) {
            return false;
        }

        if (!itemStack.hasTagCompound()) {
            itemStack.setTagCompound(new NBTTagCompound());
        }
        itemStack.getTagCompound().setString("target", String.valueOf(target.getUniqueID()));
        player.setHeldItem(hand, itemStack);
        player.sendMessage(new TextComponentString("Entity ID stored: " + target.getUniqueID()));
        return true;
    }

}
