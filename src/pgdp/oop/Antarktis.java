package pgdp.oop;

public class Antarktis extends Maze {
    private static final Whale[] whales = new Whale[5];
    private static final LeopardSeal[] leopardSeals = new LeopardSeal[20];
    private static final Fish[] fishes = new Fish[500];
    private static Penguin lostPenguin;
    private static PlayerPenguin playerPenguin;
    private static boolean gameEnd;
    public static void main(String[] args) {
        int width = 41;
        int height = 41;
        antarktis = generateMaze(width, height);
        Animal.setAntarktis(antarktis);
        setupMaze();
        gameLoop();
        closeFrame();
    }

    private static void gameLoop() {
        while (playerPenguin.alive && lostPenguin.alive && !gameEnd) {
            draw();
            moveAll();
            currentEvent = NOTHING;
        }
        if (!playerPenguin.alive) {
            System.out.println("PlayerPenguin Died!");
        } else if (!lostPenguin.alive) {
            System.out.println("LostPenguin Died!");
        } else if (lostPenguin.x == playerPenguin.x && lostPenguin.y == playerPenguin.y) {
            System.out.println("Congratulations, You've won!");
        } else {
            System.out.println("You lent on illegal square!");
        }
    }

    private static void moveAll() {
        if (currentEvent == UP) {
            gameEnd = playerPenguin.move(playerPenguin.x, ((playerPenguin.y - 1) + 41) % 41);
        } else if (currentEvent == DOWN) {
            gameEnd = playerPenguin.move(playerPenguin.x, ((playerPenguin.y + 1) + 41) % 41);
        } else if (currentEvent == RIGHT) {
            gameEnd = playerPenguin.move((playerPenguin.x + 1) % 41, playerPenguin.y);
        } else if (currentEvent == LEFT) {
            gameEnd = playerPenguin.move(((playerPenguin.x - 1) + 41) % 41, playerPenguin.y);
        } else {
            currentEvent = NOTHING;
        }
        if (currentEvent != NOTHING) {
            for (Whale whale : whales) {
                whale.move();
            }
            for (LeopardSeal leopardSeal : leopardSeals) {
                leopardSeal.move();
            }
            if (!(lostPenguin.x == playerPenguin.x && lostPenguin.y == playerPenguin.y)) {
                lostPenguin.move();
            }
            for (Fish fish : fishes) {
                fish.move();
            }
        }
    }

    /**
     * Example Setup for easier Testing during development
     */
    private static void setupMaze() {
        int[] pos;
        playerPenguin = new PlayerPenguin(3, 3);
        antarktis[3][3] = playerPenguin;

        for (int i = 0; i < whales.length; i++) {
            pos = getRandomEmptyField();
            whales[i] = new Whale(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = whales[i];
        }

        for (int i = 0; i < leopardSeals.length; i++) {
            pos = getRandomEmptyField();
            leopardSeals[i] = new LeopardSeal(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = leopardSeals[i];
        }

        for (int i = 0; i < fishes.length; i++) {
            pos = getRandomEmptyField();
            fishes[i] = new Fish(pos[0], pos[1]);
            antarktis[pos[0]][pos[1]] = fishes[i];
        }
        antarktis[20][20] = new Penguin(20, 20);
        lostPenguin = (Penguin) antarktis[20][20];
    }
}
