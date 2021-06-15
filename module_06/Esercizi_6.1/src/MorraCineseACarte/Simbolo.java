package MorraCineseACarte;

import com.sun.tools.attach.AgentInitializationException;

import java.util.*;

public enum Simbolo {
    CARTA (2),
    FORBICI (3),
    SASSO (1);
    private final int n;
    Simbolo(int n){
        this.n = n;
    }
    public int getN() {
        return n;
    }
    public static Simbolo getRandom() {
        List<Simbolo> lst = Arrays.asList(Simbolo.values());
        Collections.shuffle(lst);
        return lst.get(0);
    }
}
