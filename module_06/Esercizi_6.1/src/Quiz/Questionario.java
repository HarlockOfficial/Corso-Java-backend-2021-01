package Quiz;

import com.botticelli.bot.Bot;
import com.botticelli.bot.request.methods.MessageToSend;
import com.botticelli.bot.request.methods.types.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Questionario extends Bot {
    private final List<Quiz> quizList;
    private final Map<Long, Player> giocatori;

    public Questionario(String token) {
        super(token);
        quizList = new ArrayList<>();
        giocatori = new HashMap<>();
    }

    public void addQuiz(Quiz quiz) {
        try {
            quizList.add((Quiz) quiz.clone());
        } catch (CloneNotSupportedException ex) {
            //Impossible exception
            System.out.println("Impossible exception: " + ex.getMessage());
        }
    }

    @Override
    public void textMessage(Message message) {
        long userId = message.getFrom().getId();
        if (message.getText().equalsIgnoreCase("/start")) {
            if (giocatori.containsKey(userId)) return;
            giocatori.put(userId, new Player());
            sendMainMenu(userId);
            return;
        }
        if (!giocatori.containsKey(userId)) return;
        Player p = giocatori.get(userId);
        String msg = message.getText();
        if (p.getAzione() == Action.NONE) {
            boolean found = false;
            for (Quiz q : quizList) {
                if (msg.equals(q.getName())) {
                    found = true;
                    p.setQuiz(q);
                    break;
                }
            }
            if (!found) {
                sendMessage(new MessageToSend(userId, "Quiz non trovato, selezionare un quiz valido"));
                return;
            }
        } else {
            p.setAnswer(msg);
        }
        if(!p.isQuizOver())
            sendQuestion(userId, p);
        else{
            closeGame(userId, p);
            sendMainMenu(userId);
        }
    }

    private void closeGame(long userId, Player p) {
        StringBuilder sb = new StringBuilder("Hai totalizzato un punteggio di: ");
        sb.append(p.getCurrentScore()).append("/").append(p.getMaxScore());
        if(p.getCurrentScore() == p.getMaxScore()){
            sb.append("\nComplimanti, punteggio pieno!!!!\n");
        }
        MessageToSend mts = new MessageToSend(userId, sb.toString());
        sendMessage(mts);
    }

    private void sendMainMenu(long userId) {
        List<List<KeyboardButton>> keyboard = new ArrayList<>();
        for (Quiz q : quizList) {
            List<KeyboardButton> line = new ArrayList<>();
            line.add(new KeyboardButton(q.getName()));
            keyboard.add(line);

        }
        ReplyKeyboardMarkupWithButtons replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
        replyKeyboard.setResizeKeyboard(true);
        MessageToSend mts = new MessageToSend(userId, "Benvenuto nel quizbot, seleziona un quiz");
        mts.setReplyMarkup(replyKeyboard);
        sendMessage(mts);
    }

    private void sendQuestion(long userId, Player p) {
        Question q = p.getNextQuestion();
        List<String> risposte = q.getRisposte();
        List<List<KeyboardButton>> keyboard = new ArrayList<>();
        for (String s : risposte) {
            List<KeyboardButton> line = new ArrayList<>();
            line.add(new KeyboardButton(s));
            keyboard.add(line);
        }
        ReplyKeyboardMarkupWithButtons replyKeyboard = new ReplyKeyboardMarkupWithButtons(keyboard);
        replyKeyboard.setResizeKeyboard(true);
        MessageToSend mts = new MessageToSend(userId, q.getDomanda());
        mts.setReplyMarkup(replyKeyboard);
        sendMessage(mts);
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
