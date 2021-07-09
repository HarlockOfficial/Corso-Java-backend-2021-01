package Mini_E_Commerce;

import java.util.UUID;

public class Prodotto {
    private UUID id;
    private String nome;
    private String descrizione;
    private float prezzo;
    private long quantitaStock;

    private void init(UUID id, String nome, String descrizione, float prezzo, long quantitaStock) throws IllegalArgumentException{
        nome = nome.trim();
        descrizione = descrizione.trim();
        if(nome.length()<=0 || descrizione.length()<=0)
            throw new IllegalArgumentException();
        this.id = id;
        this.nome = nome;
        this.descrizione = descrizione;
        setPrezzo(prezzo);
        setQuantitaStock(quantitaStock);
    }

    public Prodotto(UUID id, String nome, String descrizione, float prezzo, long quantitaStock) throws IllegalArgumentException{
        init(id, nome, descrizione, prezzo, quantitaStock);
    }

    public Prodotto(String nome, String descrizione, float prezzo, long quantitaStock) throws IllegalArgumentException{
        init(UUID.randomUUID(), nome, descrizione, prezzo, quantitaStock);
    }

    public void setPrezzo(float prezzo) throws IllegalArgumentException{
        if(prezzo<=0)
            throw new IllegalArgumentException();
        this.prezzo = prezzo;
    }

    public void setQuantitaStock(long quantitaStock) throws IllegalArgumentException{
        if(quantitaStock<=0)
            throw new IllegalArgumentException();
        this.quantitaStock = quantitaStock;
    }

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public long getQuantitaStock() {
        return quantitaStock;
    }

    public String getDescrizione() {
        return descrizione;
    }
}
