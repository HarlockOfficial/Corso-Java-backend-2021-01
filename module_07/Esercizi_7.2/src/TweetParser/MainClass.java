package TweetParser;

import java.io.IOException;

public class MainClass {
    public static void main(String[] args) throws AlreadyFinishedException, IOException, NotYetFinishedException {
        Parser p = new Parser("realdonaldtrump.csv", "english_stopwords.txt");
        p.parse();
        if(!p.isFinished()){
            System.out.println("Weird!!!");
        }
        System.out.println(p.getResult().toString());
    }
}
