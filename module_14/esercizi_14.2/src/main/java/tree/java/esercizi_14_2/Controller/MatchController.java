package tree.java.esercizi_14_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import tree.java.esercizi_14_2.Entity.Match;
import tree.java.esercizi_14_2.Entity.Player;
import tree.java.esercizi_14_2.Service.MatchService;

import java.util.UUID;

@RestController
public class MatchController {
    //TODO use playerId to better verify data and requests
    private final MatchService matchService;

    @Autowired
    public MatchController(MatchService matchService) {
        this.matchService = matchService;
    }

    @RequestMapping(name = "/newmatch", method = RequestMethod.POST)
    public ResponseEntity<Match> addMatch(@RequestBody Player player2, @RequestHeader UUID playerId){
        Match match = matchService.newMatch(playerId, player2);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", playerId.toString());
        return new ResponseEntity<>(match, headers, HttpStatus.CREATED);
    }

    @RequestMapping(name = "/reset/{matchId}", method = RequestMethod.POST)
    public ResponseEntity<Match> resetMatch(@PathVariable UUID matchId, @RequestHeader UUID playerId){
        Match match = matchService.resetMatch(matchId);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", playerId.toString());
        return new ResponseEntity<>(match, headers, HttpStatus.OK);
    }

    @RequestMapping(name = "/move/{pos_i}/{pos_j}", method = RequestMethod.GET)
    public ResponseEntity<Match> move(@PathVariable Short pos_i, @PathVariable Short pos_j, @RequestHeader UUID playerId){
        Match match = matchService.move(playerId, pos_i, pos_j);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", playerId.toString());
        return new ResponseEntity<>(match, headers, HttpStatus.OK);
    }

    @RequestMapping(name = "/back", method = RequestMethod.GET)
    public ResponseEntity<Match> back(@RequestHeader UUID playerId){
        Match match = matchService.back(playerId);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", playerId.toString());
        return new ResponseEntity<>(match, headers, HttpStatus.OK);
    }

    @RequestMapping(name = "/match", method = RequestMethod.GET)
    public ResponseEntity<Match> getLastMatch(@RequestHeader UUID playerId){
        Match match = matchService.getLastMatch(playerId);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", playerId.toString());
        return new ResponseEntity<>(match, headers, HttpStatus.OK);
    }
}
