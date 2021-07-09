package TreeExpress;

import com.google.gson.Gson;

import java.util.MissingResourceException;
import java.util.UUID;

import static spark.Spark.*;

public class MainClass {
    public static void main(String[] args){
        Gson gson = new Gson();
        TreeExpress treeExpress = TreeExpress.getInstance();

        //crea utente
        post("/utente/:nome/:congome/:indirizzo", (request, response) -> {
            String nome = request.params(":nome");
            String cognome = request.params(":cognome");
            String indirizzo = request.params(":indirizzo");
            Utente u = new Utente(nome, cognome, indirizzo);
            response.status(201);
            response.body(gson.toJson(treeExpress.addUtente(u)));
            response.type("text/json");
            return response;
        });

        //crea spedizione
        post("/spedizione/:mittente/:destinatario/:peso", (request, response) -> {
            UUID mittente = UUID.fromString(request.params(":mittente"));
            UUID destinatario = UUID.fromString(request.params(":destinatario"));
            double peso = Double.parseDouble(request.params(":peso"));
            Utente mitt = treeExpress.getUtente(mittente);
            Utente dest = treeExpress.getUtente(destinatario);
            response.status(201);
            response.body(gson.toJson(treeExpress.addSpedizione(mitt, dest, peso)));
            response.type("text/json");
            return response;
        });

        //annulla spedizione
        delete("/spedizione/:id_spedizione", (request, response) -> {
            UUID spedizione = UUID.fromString(request.params(":id_spedizione"));
            return gson.toJson(treeExpress.deleteSpedizione(spedizione));
        });

        //consegna spedizione
        put("/spedizione/:id_spedizione", (request, response) -> {
            UUID spedizione = UUID.fromString(request.params(":id_spedizione"));
            return gson.toJson(treeExpress.completeSpedizione(spedizione));
        });

        //spedizioni in transito
        get("/spedizioni", (request, response) -> gson.toJson(treeExpress.getSpedizioni().stream().filter(s->s.getStato()==StatoSpedizione.IN_CONSEGNA).toList()));

        //get spedizioni delle quali l'utente è destinatario
        get("/spedizioni/:id_utente", (request, response) -> {
            UUID utente = UUID.fromString(request.params(":id_utente"));
            return gson.toJson(treeExpress.getSpedizioni().stream().filter(s -> utente.equals(s.getDestinatario().getId())).toList());
        });
        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });
        exception(NumberFormatException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });
        exception(MissingResourceException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });
        exception(IllegalStateException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });
    }
}
