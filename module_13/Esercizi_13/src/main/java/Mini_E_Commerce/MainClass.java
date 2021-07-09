package Mini_E_Commerce;

import com.google.gson.Gson;

import java.util.UUID;

import static spark.Spark.*;

public class MainClass {
    public static void main(String[] args) {
        Mini_E_Commerce e_commerce = Mini_E_Commerce.getInstance();
        Gson gson = new Gson();
        System.out.println(UUID.randomUUID().toString());
        get("/prodotti", (request, response)->gson.toJson(e_commerce.getProdotto()));
        post("/prodotti/:id/:nome/:descrizione/:quantita/:prezzo", (request, response)->{
            UUID id  = UUID.fromString(request.params(":id"));
            String nome = request.params(":nome");
            String desc = request.params(":descrizione");
            long quantita = Long.parseLong(request.params(":quantita"));
            float prezzo = Float.parseFloat(request.params(":prezzo"));
            Prodotto p = new Prodotto(id, nome, desc, prezzo, quantita);
            e_commerce.addProdotto(p);
            return gson.toJson(p);
        });
        delete("/prodotti/:id", (request, response)->{
            UUID id  = UUID.fromString(request.params(":id"));
            Prodotto p = e_commerce.removeProdotto(id);
            return gson.toJson(p);
        });
        //buy
        patch("/prodotti/:id/:quantity", (request, response)->{
            UUID id  = UUID.fromString(request.params(":id"));
            long quantita = Long.parseLong(request.params(":quantita"));
            boolean b = e_commerce.buyProdotto(id, quantita);
            return "{'operation_completed':"+b+"}";
        });

        exception(IllegalArgumentException.class, (exception, request, response) -> {
            response.status(406);
            response.body("");
        });
    }
}
