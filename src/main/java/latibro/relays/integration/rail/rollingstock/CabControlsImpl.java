package latibro.relays.integration.rail.rollingstock;

import cam72cam.immersiverailroading.entity.Locomotive;
import cam72cam.immersiverailroading.entity.LocomotiveDiesel;
import cam72cam.mod.entity.ModdedEntity;
import latibro.relays.api.rail.rollingstock.CabControlsAPI;
import latibro.relays.computer.relaybox.ComputerRelayBoxTileEntity;
import net.minecraft.entity.Entity;

public class CabControlsImpl implements CabControlsAPI {

    private final ComputerRelayBoxTileEntity computerRelayBox;

    public CabControlsImpl(ComputerRelayBoxTileEntity computerRelayBox) {
        this.computerRelayBox = computerRelayBox;
    }

    private Locomotive getLocomotive() {
        Entity entity = computerRelayBox.getSource();
        if (entity instanceof ModdedEntity) {
            cam72cam.mod.entity.Entity e = ((ModdedEntity) entity).getSelf();
            if (e instanceof Locomotive) {
                return (Locomotive) e;
            }
        }
        return null;
    }

    @Override
    public void setThrottleLevel(Number level) {
        getLocomotive().setThrottle(level.floatValue());
    }

    @Override
    public Number getThrottleLevel() {
        return getLocomotive().getThrottle();
    }

    @Override
    public void setBrakeLevel(Number level) {
        getLocomotive().setAirBrake(level.floatValue());
    }

    @Override
    public Number getBrakeLevel() {
        return getLocomotive().getAirBrake();
    }

    @Override
    public void setIgnitionState(Boolean state) {
        ((LocomotiveDiesel) getLocomotive()).setTurnedOn(state);
    }

    @Override
    public Boolean getIgnitionState() {
        return ((LocomotiveDiesel) getLocomotive()).isTurnedOn();
    }

    @Override
    public void soundHorn() {
        getLocomotive().setHorn(40, null);
    }

}
