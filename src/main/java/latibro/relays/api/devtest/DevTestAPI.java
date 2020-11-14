package latibro.relays.api.devtest;

public interface DevTestAPI {

    void returnVoid();

    String returnString();

    Number returnNumber();

    Boolean returnBoolean();

    void inputString(String input);

    void inputNumber(Number input);

    void inputBoolean(Boolean input);

    void inputTwoString(String inputOne, String inputTwo);

}
