package ListaDellaSpesa;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.MessageToSend;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListaDellaSpesa extends Bot {
    private final Map<Long, Map<String, Integer>> liste;
    private final Map<Long, Action> azione;

    public ListaDellaSpesa(String token) {
        super(token);
        liste = new HashMap<>();
        azione = new HashMap<>();
    }

    @Override
    public void textMessage(Message message) {
        long userId = message.getFrom().getId();
        if (message.getText().equalsIgnoreCase("/start")) {
            if (liste.containsKey(userId)) return;
            liste.put(userId, new HashMap<>());
            azione.put(userId, Action.NONE);
            List<List<KeyboardButton>> keyboard = new ArrayList<>();
            List<KeyboardButton> line = new ArrayList<>();
            line.add(new KeyboardButton("Lista della Spesa"));
            keyboard.add(line);
            line = new ArrayList<>();
            line.add(new KeyboardButton("Aggiungi"));
            line.add(new KeyboardButton("Rimuovi"));
            keyboard.add(line);
            ReplyKeyboardMarkupWithButtons replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
            replyKeyboard.setResizeKeyboard(true);
            MessageToSend mts = new MessageToSend(userId, "Benvenuto nella tua personale lista della spesa");
            mts.setReplyMarkup(replyKeyboard);
            sendMessage(mts);
            return;
        }
        if (!liste.containsKey(userId)) return;
        switch (azione.get(userId)) {
            case NONE -> {
                switch (message.getText()) {
                    case "Lista della Spesa" -> {
                        Map<String, Integer> lista = liste.get(userId);
                        StringBuilder sb = new StringBuilder("Attualmente nella lista sono presenti:\n");
                        for(String key : lista.keySet()){
                            sb.append("\t").append(key).append(" ").append(lista.get(key)).append("\n");
                        }
                        sendMessage(new MessageToSend(userId, sb.toString()));
                    }
                    case "Aggiungi" -> azione.put(userId, Action.AGGIUNGI);
                    case "Rimuovi" -> azione.put(userId, Action.RIMUOVI);
                }
            }
            case RIMUOVI -> {
                String[] msg = message.getText().toLowerCase().split("-");
                if (msg.length < 1 || msg.length > 2) {
                    sendMessage(new MessageToSend(userId, "Formato messaggio non valido, inserire <Nome prodotto>-<quantità prodotto>"));
                    return;
                }
                Map<String, Integer> lista = liste.get(userId);
                if (!lista.containsKey(msg[0])) {
                    sendMessage(new MessageToSend(userId, "Prodotto non presente nella lista, inserisci un prodotto valido"));
                    return;
                }
                azione.put(userId, Action.NONE);
                if (msg.length == 2) {
                    if (lista.get(msg[0]) <= Integer.parseInt(msg[1])) {
                        lista.remove(msg[0]);
                        sendMessage(new MessageToSend(userId, "Prodotto rimosso correttamente"));
                        return;
                    }
                    lista.put(msg[0], lista.get(msg[0]) - Integer.parseInt(msg[1]));
                    sendMessage(new MessageToSend(userId, "La quantità indicata è stata rimossa"));
                    return;
                }
                lista.remove(msg[0]);
                sendMessage(new MessageToSend(userId, "Prodotto rimosso correttamente"));
            }
            case AGGIUNGI -> {
                String[] msg = message.getText().toLowerCase().split("-");
                if (msg.length < 1 || msg.length > 2) {
                    sendMessage(new MessageToSend(userId, "Formato messaggio non valido, inserire <Nome prodotto>-<quantità prodotto>"));
                    return;
                }
                azione.put(userId, Action.NONE);
                Map<String, Integer> lista = liste.get(userId);
                if(msg.length == 2) {
                    if (lista.containsKey(msg[0])) {
                        lista.put(msg[0], lista.get(msg[0]) + Integer.parseInt(msg[1]));
                        sendMessage(new MessageToSend(userId, "La quantità indicata è stata aggiunta"));
                        return;
                    }
                    lista.put(msg[0], Integer.parseInt(msg[1]));
                }else{
                    lista.put(msg[0], 1);
                }
                sendMessage(new MessageToSend(userId, "Prodotto aggiunto correttamente"));
            }
        }
    }

    @Override
    public void audioMessage(Message message) {

    }

    @Override
    public void videoMessage(Message message) {

    }

    @Override
    public void voiceMessage(Message message) {

    }

    @Override
    public void stickerMessage(Message message) {

    }

    @Override
    public void documentMessage(Message message) {

    }

    @Override
    public void photoMessage(Message message) {

    }

    @Override
    public void contactMessage(Message message) {

    }

    @Override
    public void locationMessage(Message message) {

    }

    @Override
    public void venueMessage(Message message) {

    }

    @Override
    public void newChatMemberMessage(Message message) {

    }

    @Override
    public void newChatMembersMessage(Message message) {

    }

    @Override
    public void leftChatMemberMessage(Message message) {

    }

    @Override
    public void newChatTitleMessage(Message message) {

    }

    @Override
    public void newChatPhotoMessage(Message message) {

    }

    @Override
    public void groupChatPhotoDeleteMessage(Message message) {

    }

    @Override
    public void groupChatCreatedMessage(Message message) {

    }

    @Override
    public void inLineQuery(InlineQuery inlineQuery) {

    }

    @Override
    public void chose_inline_result(ChosenInlineResult chosenInlineResult) {

    }

    @Override
    public void callback_query(CallbackQuery callbackQuery) {

    }

    @Override
    public void gameMessage(Message message) {

    }

    @Override
    public void videoNoteMessage(Message message) {

    }

    @Override
    public void pinnedMessage(Message message) {

    }

    @Override
    public void preCheckOutQueryMessage(PreCheckoutQuery preCheckoutQuery) {

    }

    @Override
    public void shippingQueryMessage(ShippingQuery shippingQuery) {

    }

    @Override
    public void invoiceMessage(Message message) {

    }

    @Override
    public void successfulPaymentMessage(Message message) {

    }

    @Override
    public void routine() {

    }
}
