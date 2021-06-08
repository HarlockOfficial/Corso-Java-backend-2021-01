package Lampadina;

public enum Status {
    ON ("On"),
    OFF ("Off"),
    BROKEN ("Broken");
    private String status;
    Status(String status){
        this.status = status;
    }

    @Override
    public String toString() {
        return status;
    }
}
