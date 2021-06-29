package CovidSimulator.Simulator;

import java.util.UUID;

public class User {
    private final UUID id;
    private final double temperatura;
    private final int eta;
    private final boolean gusto, tosse, debolezza;
    private final SituazioneClinica situazioneClinica;

    public User(UUID id, double temperatura, int eta, boolean gusto, boolean tosse, boolean debolezza,
                String situazioneClinica) {
        this.id = id;
        this.temperatura = temperatura;
        this.eta = eta;
        this.gusto = gusto;
        this.tosse = tosse;
        this.debolezza = debolezza;
        this.situazioneClinica = SituazioneClinica.valueOf(situazioneClinica);
    }
    public boolean hasCovid(){
        return temperatura >= 40 ||
                (temperatura >= 38 && gusto && tosse && debolezza) ||
                (situazioneClinica == SituazioneClinica.CRITICA &&
                        (temperatura >= 38.5 || gusto || tosse || debolezza)
                ) ||
                eta >= 50 && temperatura >= 37 ||
                eta >= 60 && gusto && situazioneClinica == SituazioneClinica.CAUTELA;
    }

    public UUID getId() {
        return id;
    }
}
