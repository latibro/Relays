package latibro.relays;

import latibro.relays.computer.relaybox.ComputerRelayBoxItem;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class RelaysCreativeTabs {

    public static final CreativeTabs DEFAULT = new CreativeTabs("relays") {
        @Override
        public ItemStack getTabIconItem() {
            return new ItemStack(new ComputerRelayBoxItem());
        }
    };

}
