package Store;

import java.util.Objects;

public class Prodotto {
    private final boolean isProdottoAlimentare;
    private final String nome;
    private float prezzo;
    Prodotto(boolean isProdottoAlimentare, String nome, float prezzo){
        this.isProdottoAlimentare = isProdottoAlimentare;
        this.nome = nome;
        setPrezzo(prezzo);
    }
    public String getNome() {
        return nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public boolean isProdottoAlimentare() {
        return isProdottoAlimentare;
    }

    public void setPrezzo(float prezzo) {
        if(prezzo>0)
            this.prezzo = prezzo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Prodotto prodotto = (Prodotto) o;
        return isProdottoAlimentare == prodotto.isProdottoAlimentare && nome.equals(prodotto.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isProdottoAlimentare, nome);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Prodotto(isProdottoAlimentare, nome, prezzo);
    }

    @Override
    public String toString() {
        return nome;
    }
}
