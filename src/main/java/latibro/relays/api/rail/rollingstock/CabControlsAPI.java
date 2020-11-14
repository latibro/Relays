package latibro.relays.api.rail.rollingstock;

public interface CabControlsAPI {

    void setThrottleLevel(Number level);

    Number getThrottleLevel();

    void setBrakeLevel(Number level);

    Number getBrakeLevel();

    void setIgnitionState(Boolean state);

    Boolean getIgnitionState();

    void soundHorn();

}
