package tree.java.esercizi_14_2.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tree.java.esercizi_14_2.Entity.Player;
import tree.java.esercizi_14_2.Service.UserService;

@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(path = "/player", method = RequestMethod.PUT)
    public Player addPlayer(@RequestBody Player player){
        return userService.addPlayer(player);
    }

    @RequestMapping(path = "/player", method = RequestMethod.POST)
    public ResponseEntity<Player> loginPlayer(@RequestBody Player player){
        Player p = userService.loginPlayer(player);
        MultiValueMap<String, String> headers = new HttpHeaders();
        headers.add("playerId", p.getId().toString());
        return new ResponseEntity<>(p, headers, HttpStatus.OK);
    }
}
