package TweetParser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    private final BufferedReader br;
    private final Map<String, Integer> map2;
    private boolean finished;
    private final String regexToRemoveFromInputLine;

    public Parser(String csvName, String ignoreName) throws FileNotFoundException {
        finished = false;
        br = new BufferedReader(new FileReader(csvName));
        map2 = new HashMap<>();
        BufferedReader brTmp = new BufferedReader(new FileReader(ignoreName));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            while ((line = brTmp.readLine()) != null) {
                sb.append(line).append('|');
            }
        }catch(IOException ex){
            //ignored
        }
        //regex to ignore dot, minus, quotes and links
        //sb.append(".|-|\"|'|https?://(www.)?[a-zA-Z0-9@:%._\\+~#=]{1,256}.[a-zA-Z0-9()]{1,6}/?([a-zA-Z0-9()@:%_\\+.~#?&/=]*)");
        regexToRemoveFromInputLine = sb.substring(0, sb.length()-1);
    }

    public void parse() throws AlreadyFinishedException, IOException {
        if(finished){
            throw new AlreadyFinishedException();
        }
        String line;
        br.readLine();
        while((line = br.readLine())!=null) {
            line = line.split(",")[2];
            line = line.replaceAll(regexToRemoveFromInputLine, "");
            System.out.println(line);
            for (String s : line.split(" ")) {
                System.out.println(s);
                if (map2.containsKey(s)) {
                    map2.put(s, map2.get(s) + 1);
                } else {
                    map2.put(s, 1);
                }
            }
        }
        finished = true;
    }

    public List<String> getResult() throws NotYetFinishedException{
        if(!finished){
            throw new NotYetFinishedException();
        }
        List<String> out = new ArrayList<>(map2.keySet());
        Collections.sort(out, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int cmp = Integer.compare(map2.get(o2), map2.get(o1));
                if(cmp==0){
                    cmp = o1.compareTo(o2);
                }
                return cmp;
            }
        });
        return out;
    }

    public boolean isFinished() {
        return finished;
    }
}
