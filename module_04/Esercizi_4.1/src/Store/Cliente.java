package Store;

public class Cliente {
    private byte age;
    private int puntiRegalo;
    Cliente(byte age){
        puntiRegalo = 0;
        this.age = age;
    }
    public byte getAge() {
        return age;
    }
    public void setAge(byte newAge){
        if(age<newAge){
            age = newAge;
        }
    }
    public void setPuntiRegalo(int n){
        puntiRegalo = n;
    }
    public int getPuntiRegalo(){
        return puntiRegalo;
    }
}
