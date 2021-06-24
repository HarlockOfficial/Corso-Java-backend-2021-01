package TweetParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Parser {
    private final BufferedReader br;
    private final Map<String, Integer> map2;
    private final Set<String> set;
    private boolean finished;

    public Parser(String csvName, String ignoreName) throws IOException {
        finished = false;
        br = new BufferedReader(new FileReader(csvName));
        map2 = new HashMap<>();
        set = new HashSet<>();
        BufferedReader brTmp = new BufferedReader(new FileReader(ignoreName));
        String tmp;
        while((tmp = brTmp.readLine()) != null){
            set.add(tmp);
        }
    }

    public void parse() throws AlreadyFinishedException, IOException {
        if(finished){
            throw new AlreadyFinishedException();
        }
        String line;
        br.readLine();
        while((line = br.readLine())!=null) {
            line = line.split(",")[2];
            line = line.replaceAll("[\\d]+[,https?\\://[a-zA-Z0-9\\-\\.]+\\.[a-zA-Z]{2,3}(/\\S)?]+[\\d]", "");
            //System.out.println(line);
            for (String s : line.split(" ")) {
                s = s.trim().toLowerCase();
                if(s.length()<=1 || set.contains(s) || false/*checkWord(s)*/){
                    continue;
                }
                if (map2.containsKey(s)) {
                    map2.put(s, map2.get(s) + 1);
                } else {
                    map2.put(s, 1);
                }
            }
        }
        finished = true;
    }

    private boolean checkWord(String s) {
        for(String elem: set){
            if(s.contains(elem)){
                return true;
            }
        }
        return false;
    }

    public List<String> getResult() throws NotYetFinishedException{
        if(!finished){
            throw new NotYetFinishedException();
        }
        List<String> out = new ArrayList<>(map2.keySet());
        out.sort((o1, o2) -> {
            int cmp = Integer.compare(map2.get(o2), map2.get(o1));
            if (cmp == 0) {
                cmp = o1.compareTo(o2);
            }
            return cmp;
        });
        return out;
    }

    public boolean isFinished() {
        return finished;
    }
}
