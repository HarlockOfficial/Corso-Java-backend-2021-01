package tree.java.esercizi_14_2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tree.java.esercizi_14_2.Entity.Match;
import tree.java.esercizi_14_2.Entity.Move;
import tree.java.esercizi_14_2.Entity.Player;
import tree.java.esercizi_14_2.Repository.MatchRepository;
import tree.java.esercizi_14_2.Repository.PlayerRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MatchService {
    private final MatchRepository matchRepository;
    private final PlayerRepository playerRepository;

    @Autowired
    public MatchService(MatchRepository matchRepository, PlayerRepository playerRepository) {
        this.matchRepository = matchRepository;
        this.playerRepository = playerRepository;
    }

    public Match newMatch(UUID playerId, Player player2) {
        Optional<Player> player1 = playerRepository.getPlayerList().stream().filter(p->playerId.equals(p.getId())).findFirst();
        if(player1.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found, are you logged in?");
        Match match = new Match(player1.get(), player2);
        matchRepository.addMatch(match);
        return match;
    }

    public Match resetMatch(UUID matchId) {
        Optional<Match> match = matchRepository.getMatchList().stream().filter(m->matchId.equals(m.getId())).findFirst();
        if(match.isPresent()){
            match.get().resetField();
            return match.get();
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find a match with id: "+matchId.toString());
    }

    public Match move(UUID playerId, Short pos_i, Short pos_j) {
        List<Match> matchList = getMatchList(playerId);
        if(matchList.size()<=0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find a match with a player having as id: "+playerId.toString());
        }
        Match match = matchList.get(matchList.size()-1);
        if(!playerId.equals(match.getTurn().getId())){
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Wait for your turn");
        }
        match.addMove(playerId.equals(match.getPlayer1().getId())? Move.X: Move.O, pos_i, pos_j);
        return match;
    }

    public Match back(UUID playerId) {
        List<Match> matchList = getMatchList(playerId);
        if(matchList.size()<=0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cannot find a match with a player having as id: "+playerId.toString());
        }
        Match match = matchList.get(matchList.size()-1);
        if(playerId.equals(match.getTurn().getId())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Cannot undo last move");
        }
        match.removeMove();
        return match;
    }

    public Match getLastMatch(UUID playerId){
        List<Match> matchList = getMatchList(playerId);
        return matchList.get(matchList.size()-1);
    }

    private List<Match> getMatchList(UUID playerId) {
        return matchRepository.getMatchList().stream()
                .filter(m->playerId.equals(m.getPlayer1().getId()) ||
                        playerId.equals(m.getPlayer2().getId())).toList();
    }
}
