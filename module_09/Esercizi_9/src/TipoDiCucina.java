public enum TipoDiCucina {
    MESSICANA("Messicana"),
    ITALIANA("Italiana"),
    CINESE("Cinese"),
    GIAPPONESE("Giapponese"),
    FAST_FOOD("Americana");
    private String s;
    TipoDiCucina(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
