package latibro.relays.computer.relaybox;

import cam72cam.mod.entity.ModdedEntity;
import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;

import java.util.List;
import java.util.UUID;

public class ComputerRelayBoxTileEntity extends TileEntity {

    public void setSourceID(String id) {
        NBTTagCompound sourceTag = new NBTTagCompound();
        sourceTag.setString("id", id);
        getTileData().setTag("source", sourceTag);
    }

    public String getSourceID() {
        if (getTileData().hasKey("source")) {
            NBTTagCompound sourceTag = getTileData().getCompoundTag("source");
            if (sourceTag.hasKey("id")) {
                String id = sourceTag.getString("id");
                return id;
            }
        }
        return null;
    }

    public Entity getSource() {
        UUID uuid = UUID.fromString(getSourceID());
        Predicate p = new Predicate<Entity>() {
            @Override
            public boolean apply(Entity input) {
                return input.getUniqueID().equals(uuid);
            }
        };
        List<Entity> entities = world.getEntities(Entity.class, p);
        if (entities.isEmpty()) {
            return null;
        } else {
            return entities.get(0);
        }
    }

}
