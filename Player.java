import java.util.Arrays;

class Player implements Comparable<Player> {

    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

    public Player(String name, int matchesPlayed,
                  double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    // Established player rule
    static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    // Newer player rule
    static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    // Fantasy points
    double fantasyPoints() {
        return battingAverage;
    }

    // Higher fantasy points first
    @Override
    public int compareTo(Player other) {
        return Double.compare(
            other.fantasyPoints(),
            this.fantasyPoints()
        );
    }

    static String draftAndRank(Player[] players) {

        Player[] draftable = new Player[players.length];
        int count = 0;

        for (Player player : players) {

            if (isDraftable(player.matchesPlayed) ||
                isDraftable(player.matchesPlayed, player.injured)) {

                draftable[count] = player;
                count++;
            }
        }

        Player[] result = new Player[count];

        for (int i = 0; i < count; i++) {
            result[i] = draftable[i];
        }

        // Java's built-in sort uses compareTo()
        Arrays.sort(result);

        String output = "";

        for (int i = 0; i < result.length; i++) {
            output += (i + 1) + ". " + result[i].name;

            if (i < result.length - 1) {
                output += " | ";
            }
        }

        return output;
    }

    public static void main(String[] args) {

        Player[] players = {
            new Player("Virat", 15, 48.0, false),
            new Player("Rahul", 7, 55.0, false),
            new Player("Sameer", 3, 60.0, false),
            new Player("Dev", 12, 20.0, true)
        };

        System.out.println(draftAndRank(players));
    }
}