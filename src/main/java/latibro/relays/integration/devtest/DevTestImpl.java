package latibro.relays.integration.devtest;

import latibro.relays.api.devtest.DevTestAPI;

public class DevTestImpl implements DevTestAPI {

    @Override
    public void returnVoid() {
        System.out.println("DevTestAPI:returnVoid");
    }

    @Override
    public String returnString() {
        System.out.println("DevTestAPI:returnString");
        return "some string";
    }

    @Override
    public Number returnNumber() {
        System.out.println("DevTestAPI:returnNumber");
        return 123;
    }

    @Override
    public Boolean returnBoolean() {
        System.out.println("DevTestAPI:returnBoolean");
        return true;
    }

    @Override
    public void inputString(String input) {
        System.out.println("DevTestAPI:inputString - " + input);
    }

    @Override
    public void inputNumber(Number input) {
        System.out.println("DevTestAPI:inputNumber - " + input);
    }

    @Override
    public void inputBoolean(Boolean input) {
        System.out.println("DevTestAPI:inputBoolean - " + input);
    }

    @Override
    public void inputTwoString(String inputOne, String inputTwo) {
        System.out.println("DevTestAPI:inputTwoString - " + inputOne + " - " + inputTwo);
    }

}
