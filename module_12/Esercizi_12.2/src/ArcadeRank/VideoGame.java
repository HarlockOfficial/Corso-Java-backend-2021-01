package ArcadeRank;

import java.util.Objects;
import java.util.UUID;

public class VideoGame implements Cloneable{
    private final UUID id;
    private final String name;
    private final DifficultyLevel difficultyLevel;

    public VideoGame(String name, DifficultyLevel difficultyLevel){
        id = UUID.randomUUID();
        this.name = name;
        this.difficultyLevel = difficultyLevel;
    }

    private VideoGame(UUID id, String name, DifficultyLevel difficultyLevel){
        this.id = id;
        this.name = name;
        this.difficultyLevel = difficultyLevel;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getDifficultyLevel(){
        return difficultyLevel.getDifficultyLevel();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new VideoGame(id, name, difficultyLevel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VideoGame videoGame = (VideoGame) o;
        return id.equals(videoGame.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "VideoGame{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", difficultyLevel=" + difficultyLevel +
                '}';
    }
}
