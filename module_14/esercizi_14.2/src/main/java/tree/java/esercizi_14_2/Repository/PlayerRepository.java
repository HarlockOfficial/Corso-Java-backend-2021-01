package tree.java.esercizi_14_2.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import tree.java.esercizi_14_2.Entity.Player;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlayerRepository {
    private final List<Player> playerList;

    public PlayerRepository() {
        this.playerList = new ArrayList<>();
    }

    public void addPlayer(Player player){
        playerList.add(player);
    }

    public List<Player> getPlayerList() {
        return playerList;
    }
}
