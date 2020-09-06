package latibro.relays.computer.relaybox;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;

public class ComputerRelayBoxContainer extends Container {

    @Override
    public boolean canInteractWith(EntityPlayer player) {
        return true;
    }

}
