package BotBase;

import MorraCineseACarte.MorraBot;
import Quiz.Question;
import Quiz.Questionario;
import Quiz.Quiz;
import com.botticelli.bot.Bot;
import com.botticelli.messagereceiver.MessageReceiver;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static String filePath;

    public static void main(String[] args) throws FileNotFoundException, IllegalArgumentException {
        filePath = new File("").getAbsolutePath() + System.getProperty("file.separator");
        File tokenFile = new File(filePath + "token.txt");
        String token = "";
        try (Scanner s = new Scanner(tokenFile))
        {
            while (s.hasNext())
            {
                token = s.nextLine();
            }
        }
        //Bot bot = new PrimoBot(token);
        //Bot bot = new RandomMedia(token);
        //Bot bot = new ListaDellaSpesa(token);
        //Bot bot = getQuestionario(token);
        Bot bot = new MorraBot(token);
        MessageReceiver mr = new MessageReceiver(bot, 500, 1);
        mr.ignoreEditedMessages();
        System.out.println("Starting");
        mr.start();
        System.out.println("Started");
    }

    private static Bot getQuestionario(String token) {
        Questionario q = new Questionario(token);

        ArrayList<Question> domande = new ArrayList<>();

        ArrayList<String> risposte = new ArrayList<>();
        risposte.add("Marcello");
        risposte.add("prova 123");
        risposte.add("Boh");

        Question tmp = new Question("Come ti chiami?", risposte,"Boh");

        domande.add(tmp);

        risposte = new ArrayList<>();
        risposte.add("aaaa");
        risposte.add("bbbb");
        risposte.add("bla");

        tmp = new Question("Domanda 2", risposte, "bla");

        domande.add(tmp);

        Quiz quiz = new Quiz(domande, "Prova quiz");
        q.addQuiz(quiz);


        quiz = new Quiz(domande, "Bla2 aa");
        q.addQuiz(quiz);
        return q;
    }
}
