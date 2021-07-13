package tree.java.esercizi_14_1.Repository;


public class StringObject {
    private final String string;
    private final int stringLength;

    public StringObject(String string) {
        this.string = string.toUpperCase();
        this.stringLength = string.length();
    }

    public String getString() {
        return string;
    }

    public int getStringLength() {
        return stringLength;
    }
}
