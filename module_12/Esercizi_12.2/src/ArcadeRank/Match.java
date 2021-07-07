package ArcadeRank;

public class Match implements Comparable<Match>{
    private static long ID = 0;
    private final long id;
    private final User player;
    private final VideoGame game;
    private final int score;

    public Match(User player, VideoGame game, int score) {
        id = ++ID;
        this.player = player;
        this.game = game;
        this.score = score;
    }

    public long getID() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public User getPlayer() {
        return player;
    }

    public VideoGame getGame() {
        return game;
    }

    @Override
    public int compareTo(Match o) {
        return Long.compare(id, o.id);
    }
}
