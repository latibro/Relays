package latibro.relays.api.entity;

public interface EntityControlAPI {

    Object getPosition();

    boolean tryMoveTo(Number x, Number y, Number z);

    void moveTo(Number x, Number y, Number z);

}
