package BotBase;

public enum Comando {
    SALUTA ("ciao"),
    MONOPATTINO ("\uD83D\uDEF4"),
    SCOOTER ("\uD83D\uDEF5"),
    MOTO ("\uD83C\uDFCD"),
    TASTIERA ("tastiera");
    private String s;
    Comando(String s){
        this.s = s;
    }

    //https://stackoverflow.com/questions/604424/how-to-get-an-enum-value-from-a-string-value-in-java
    public String getText() {
        return this.s;
    }

    public static Comando fromString(String text) {
        for (Comando b : Comando.values()) {
            if (b.s.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
