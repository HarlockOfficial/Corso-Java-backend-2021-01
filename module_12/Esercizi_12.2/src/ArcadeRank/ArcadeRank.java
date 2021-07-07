package ArcadeRank;

import java.util.*;

public class ArcadeRank {
    private final Set<User> users;
    private final Set<VideoGame> games;
    private final Queue<Match> matches;

    public ArcadeRank(){
        matches = new ArrayDeque<>();
        users = new HashSet<>();
        games = new HashSet<>();
    }

    public boolean addUser(User user){
        return users.add(user);
    }

    public boolean addGames(VideoGame game){
        return games.add(game);
    }

    public boolean addPlayerScore(UUID player, UUID game, int score){
        Optional<User> user = users.stream().filter((u)->player.equals(u.getId())).findFirst();
        Optional<VideoGame> videoGame = games.stream().filter((g)->game.equals(g.getId())).findFirst();
        if(user.isEmpty() || videoGame.isEmpty()) return false;
        return matches.add(new Match(user.get(), videoGame.get(), score));
    }

    public List<String> getRankForGame(UUID game){
        return matches.stream().filter((m)->game.equals(m.getGame().getId()))
                .sorted((a,b)->Integer.compare(b.getScore(), a.getScore()))
                .limit(3)
                .map(m->m.getPlayer().getUsername()+":"+m.getScore()).toList();
    }

    public List<Match> lastNGames(long N){
        return matches.stream().sorted(Comparator.reverseOrder())
                .limit(N).sorted((a,b)->Long.compare(b.getID(), a.getID())).toList();
    }

    private int getGlobalScore(User player){
        return matches.stream().parallel().filter(m -> player.equals(m.getPlayer()))
                .mapToInt(m -> m.getScore()*m.getGame().getDifficultyLevel()).sum();
    }
    
    public List<String> getOverallRank(){
        return matches.stream()
                .map(m->Map.entry(m.getPlayer(), getGlobalScore(m.getPlayer())))
                .sorted((a,b) -> b.getValue().compareTo(a.getValue()))
                .limit(3)
                .map(entry->entry.getKey().getUsername()+":"+entry.getValue()).toList();
    }
}















