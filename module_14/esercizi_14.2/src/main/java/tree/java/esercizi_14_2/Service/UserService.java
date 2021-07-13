package tree.java.esercizi_14_2.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import tree.java.esercizi_14_2.Entity.Player;
import tree.java.esercizi_14_2.Repository.PlayerRepository;

import java.util.Optional;

@Service
public class UserService {
    private final PlayerRepository playerRepository;

    @Autowired
    public UserService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player addPlayer(Player player){
        playerRepository.addPlayer(player);
        return player;
    }

    public Player loginPlayer(Player player) {
        Optional<Player> pl = playerRepository.getPlayerList().stream().filter(player::equals).findFirst();
        if(pl.isPresent()) return pl.get();
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Player not found, are you registered?");
    }
}
