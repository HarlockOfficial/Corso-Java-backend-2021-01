package MiniTelegram;

import com.google.gson.Gson;

import java.util.MissingResourceException;
import java.util.UUID;

import static spark.Spark.*;

public class MainClass {
    public static void main(String[] args) {
        Gson gson = new Gson();
        MiniTelegram telegram = MiniTelegram.getInstance();

        post("/messaggi/:id_mittente/:id_destinatario/:contenuto", (request, response) ->{
           UUID mittente = UUID.fromString(request.params(":id_mittente"));
           UUID destinatario = UUID.fromString(request.params(":id_destinatario"));
           String contenuto = request.params(":contenuto");
           return gson.toJson(telegram.addMessaggio(mittente, destinatario, contenuto));
        });

        patch("/messaggi/:id_messaggio/:contenuto", (request, response) -> {
            UUID messaggio = UUID.fromString(request.params(":id_destinatario"));
            String contenuto = request.params(":contenuto");
            return gson.toJson(telegram.editMessaggio(messaggio, contenuto));
        });

        get("/messaggi/:id_mittente/:id_destinatario", (request, response) -> {
            UUID mittente = UUID.fromString(request.params(":id_mittente"));
            UUID destinatario = UUID.fromString(request.params(":id_destinatario"));
            return gson.toJson(telegram.getMessaggi(mittente, destinatario));
        });

        post("/utente/:username", (request, response) -> {
            String username = request.params(":username");
            return gson.toJson(telegram.addUtente(new Utente(username)));
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });

        exception(MissingResourceException.class, (exception, request, response) -> {
            response.status(406);
            response.body(exception.getMessage());
        });
    }
}
