package latibro.relays.integration.entiry;

import latibro.relays.api.entity.EntityControlAPI;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.ai.EntityAIMoveToBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EntityControlImpl implements EntityControlAPI {

    private final ComputerRelayBoxTileEntity computerRelayBox;

    public EntityControlImpl(ComputerRelayBoxTileEntity computerRelayBox) {
        this.computerRelayBox = computerRelayBox;
    }

    protected EntityLiving getEntityLiving() {
        Entity entity = computerRelayBox.getSource();
        if (entity instanceof EntityLiving) {
            return (EntityLiving) entity;
        }
        return null;
    }

    @Override
    public Object getPosition() {
        try {
            System.out.println("path: " + getEntityLiving().getNavigator().getPath());

            System.out.println("lenght: " + getEntityLiving().getNavigator().getPath().getCurrentPathLength());

            System.out.println("final.x: " + getEntityLiving().getNavigator().getPath().getFinalPathPoint().x);
            System.out.println("final.z: " + getEntityLiving().getNavigator().getPath().getFinalPathPoint().z);

            System.out.println("current.x: " + getEntityLiving().getNavigator().getPath().getCurrentPos().x);
            System.out.println("current.z: " + getEntityLiving().getNavigator().getPath().getCurrentPos().z);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return getEntityLiving().getPosition();
    }

    @Override
    public boolean tryMoveTo(Number x, Number y, Number z) {
        return getEntityLiving().getNavigator().tryMoveToXYZ(x.doubleValue(), y.doubleValue(), z.doubleValue(), getEntityLiving().getAIMoveSpeed());
    }

    @Override
    public void moveTo(Number x, Number y, Number z) {
        getEntityLiving().getMoveHelper().setMoveTo(x.doubleValue(), y.doubleValue(), z.doubleValue(), getEntityLiving().getAIMoveSpeed());
    }

    public void move(Number x, Number y, Number z) {
        getEntityLiving().move(MoverType.SELF, x.doubleValue(), y.doubleValue(), z.doubleValue());
    }

    public void setNoAI(Boolean diabled) {
        getEntityLiving().setNoAI(diabled);
    }

    public void speak() {
        getEntityLiving().playLivingSound();
    }

    public void jumping() {
        getEntityLiving().getJumpHelper().doJump();
    }

}
