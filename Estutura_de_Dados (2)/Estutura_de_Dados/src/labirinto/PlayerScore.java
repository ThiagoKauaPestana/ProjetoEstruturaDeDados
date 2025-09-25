package labirinto;

public class PlayerScore {
    private String playerName;
    private int score;
    private String timestamp;

    public PlayerScore(String playerName, int score, String timestamp) {
        this.playerName = playerName;
        this.score = score;
        this.timestamp = timestamp;
    }

    // Getters
    public String getPlayerName() { return playerName; }
    public int getScore() { return score; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        return playerName + "," + score + "," + timestamp;
    }
}