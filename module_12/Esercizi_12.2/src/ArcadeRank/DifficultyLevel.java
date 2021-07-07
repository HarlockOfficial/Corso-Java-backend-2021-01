package ArcadeRank;

public enum DifficultyLevel{
    ONE (1),
    TWO (2),
    THREE (3),
    FOUR (4),
    FIVE (5);
    private final int difficultyLevel;
    DifficultyLevel(int difficultyLevel){
        this.difficultyLevel = difficultyLevel;
    }

    @Override
    public String toString() {
        return difficultyLevel+"";
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }
}
