package tree.java.esercizi_14_2.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tree.java.esercizi_14_2.Entity.Match;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MatchRepository {
    private final List<Match> matchList;

    public MatchRepository() {
        this.matchList = new ArrayList<>();
    }

    public List<Match> getMatchList() {
        return matchList;
    }

    public void addMatch(Match match){
        matchList.add(match);
    }
}
