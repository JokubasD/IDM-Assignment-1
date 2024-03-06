import tudelft.wis.idm_tasks.boardGameTracker.BoardGameSetup;

public class Main {
    public static void main(String[] args) {
        BoardGameSetup boardGameSetup = new BoardGameSetup();
        boardGameSetup.createPlayertable();
        boardGameSetup.createBoardgame();
    }
}
