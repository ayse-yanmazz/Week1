package Week1;
import java.util.Scanner;

public class Week1 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] teams = {"Team A", "Team B", "Team C", "Team D"};
        int numTeams = teams.length;

        
        
        int[] matchesPlayed = new int[numTeams];
        int[] wins = new int[numTeams];
        int[] draws = new int[numTeams];
        int[] losses = new int[numTeams];
        int[] goalsFor = new int[numTeams];
        int[] goalsAgainst = new int[numTeams];
        int[] goalDifference = new int[numTeams];
        int[] points = new int[numTeams];

        
        int[][] matches = {
            {0, 1}, // Team A vs Team B
            {0, 2}, // Team A vs Team C
            {0, 3}, // Team A vs Team D
            {1, 2}, // Team B vs Team C
            {1, 3}, // Team B vs Team D
            {2, 3}  // Team C vs Team D
        };

        
        System.out.println("========================================");
        System.out.println("           TOURNAMENT FIXTURE           ");
        System.out.println("========================================");
        for (int i = 0; i < matches.length; i++) {
            System.out.println("Match " + (i + 1) + ": " + teams[matches[i][0]] + " vs " + teams[matches[i][1]]);
        }
        System.out.println("========================================");

        System.out.println("\nPlease enter the match scores:");

        for (int i = 0; i < matches.length; i++) {
            int team1Idx = matches[i][0];
            int team2Idx = matches[i][1];

            System.out.println("\n--- Match " + (i + 1) + ": " + teams[team1Idx] + " vs " + teams[team2Idx] + " ---");
            System.out.print(teams[team1Idx] + " goals: ");
            int score1 = scanner.nextInt();

            System.out.print(teams[team2Idx] + " goals: ");
            int score2 = scanner.nextInt();

            
            matchesPlayed[team1Idx]++;
            matchesPlayed[team2Idx]++;

            
            goalsFor[team1Idx] += score1;
            goalsAgainst[team1Idx] += score2;

            goalsFor[team2Idx] += score2;
            goalsAgainst[team2Idx] += score1;

            
            if (score1 > score2) {
                wins[team1Idx]++;
                points[team1Idx] += 3;
                losses[team2Idx]++;
            } else if (score2 > score1) {
                wins[team2Idx]++;
                points[team2Idx] += 3;
                losses[team1Idx]++;
            } else {
                draws[team1Idx]++;
                points[team1Idx] += 1;
                draws[team2Idx]++;
                points[team2Idx] += 1;
            }
        }

        
        for (int i = 0; i < numTeams; i++) {
            goalDifference[i] = goalsFor[i] - goalsAgainst[i];
        }

        System.out.println("\n==========================================================================");
        System.out.println("                             STANDINGS TABLE                              ");
        System.out.println("==========================================================================");
        System.out.printf("%-10s | %-2s | %-2s | %-2s | %-2s | %-3s | %-3s | %-2s | %-6s\n",
                "Team", "MP", "W", "D", "L", "GF", "GA", "GD", "Points");
        System.out.println("--------------------------------------------------------------------------");

        for (int i = 0; i < numTeams; i++) {
            System.out.printf("%-10s | %-2d | %-2d | %-2d | %-2d | %-3d | %-3d | %-2d | %-6d\n",
                    teams[i], matchesPlayed[i], wins[i], draws[i], losses[i],
                    goalsFor[i], goalsAgainst[i], goalDifference[i], points[i]);
        }

        int championIdx = 0;

        for (int i = 1; i < numTeams; i++) {
            if (points[i] > points[championIdx]) {
                championIdx = i;
            } else if (points[i] == points[championIdx]) {
                
                if (goalDifference[i] > goalDifference[championIdx]) {
                    championIdx = i;
                }
            }
        }

        System.out.println("==========================================================================");
        System.out.println("Tournament Champion: " + teams[championIdx]);
        System.out.println("==========================================================================");

        scanner.close();
    }
}
