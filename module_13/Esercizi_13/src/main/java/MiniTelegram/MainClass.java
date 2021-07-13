package MiniTelegram;

import com.google.gson.Gson;

import javax.management.AttributeNotFoundException;
import javax.naming.directory.InvalidAttributesException;
import java.util.MissingResourceException;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static spark.Spark.*;

public class MainClass {
    public static void main(String[] args) {
        Gson gson = new Gson();
        MiniTelegram telegram = MiniTelegram.getInstance();

        post("/messaggi/:token/:id_destinatario/:contenuto", (request, response) ->{
           UUID destinatario = UUID.fromString(request.params(":id_destinatario"));
           String contenuto = request.params(":contenuto");
           String token = request.params(":token");
           return gson.toJson(telegram.addMessaggio(token, destinatario, contenuto));
        });

        patch("/messaggi/:token/:id_messaggio/:contenuto", (request, response) -> {
            UUID messaggio = UUID.fromString(request.params(":id_destinatario"));
            String contenuto = request.params(":contenuto");
            String token = request.params(":token");
            return gson.toJson(telegram.editMessaggio(token, messaggio, contenuto));
        });

        get("/messaggi/:token/:id_destinatario", (request, response) -> {
            UUID destinatario = UUID.fromString(request.params(":id_destinatario"));
            String token = request.params(":token");
            return gson.toJson(telegram.getMessaggi(token, destinatario));
        });

        get("/utente/:token/:username", (request, response) -> {
            String username = request.params(":username");
            return gson.toJson(telegram.getUtente(username));
        });

        post("/utente/:username", (request, response) -> {
            String username = request.params(":username");
            return telegram.loginUtente(username);
        });

        head("/utente/:token", (request, response) -> {
            String token = request.params(":token");
            telegram.logoutUtente(token);
            response.status(200);
            return response;
        });

        put("/utente/:username", (request, response) -> {
            String username = request.params(":username");
            response.status(201);
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

        exception(InvalidAttributesException.class, (exception, request, response) -> {
            response.status(401);
            response.body(exception.getMessage());
        });

        exception(AttributeNotFoundException.class, (exception, request, response) -> {
            response.status(404);
            response.body(exception.getMessage());
        });

        ExecutorService pool = Executors.newCachedThreadPool();
        pool.submit(new Runnable() {
            @Override
            public void run() {
                telegram.cleanTokens();
                try {
                    Thread.sleep(120);
                } catch (InterruptedException e) {
                    //ignored
                }
                pool.submit(this);
            }
        });
    }
}
