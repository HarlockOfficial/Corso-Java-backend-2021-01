import java.util.Collection;
import java.util.Objects;

public class Ristorante {
    private final String nome;
    private Menu menu;
    public Ristorante(String nome){
        this.nome = nome;
        menu = new Menu();
    }
    public Ristorante(String nome, Menu menu){
        this.nome = nome;
        this.menu = new Menu(menu);
    }
    public Ristorante(String nome, Collection<Piatto> menu){
        this.nome = nome;
        this.menu = new Menu(menu);
    }
    public boolean addPiatto(Piatto p){
        return menu.addPiatto(p);
    }
    public boolean removePiatto(Piatto p){
        return menu.removePiatto(p);
    }
    public Menu getMenu() {
        return menu;
    }
    public void setMenu(Menu menu){
        this.menu = new Menu(menu);
    }
    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ristorante that = (Ristorante) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "Nome Ristorante: '" + nome + '\'' +
                "," + menu.toString();
    }
}
