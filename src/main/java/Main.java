import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BgtDataManagerJDBC;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BoardGameJDBC;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.PlayerJDBC;
import tudelft.wis.idm_tasks.boardGameTracker.JPA.BgtDataManagerJPA;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

public class Main {
    public static void main(String[] args) throws BgtException {
//        BgtDataManagerJDBC bgtDataManagerJDBC = new BgtDataManagerJDBC();
////        bgtDataManagerJDBC.createNewPlayer("Noah", "Luigi");
////        bgtDataManagerJDBC.createNewBoardgame("Adrien", "google.com");
//        System.out.println(bgtDataManagerJDBC.findGamesByName("Adrien"));
//        System.out.println(bgtDataManagerJDBC.findPlayersByName("Noah"));
//        Player player = new PlayerJDBC("Theodora", "Imke");
//        bgtDataManagerJDBC.persistPlayer(player);
//        BoardGame boardGame = new BoardGameJDBC("Jacob", "helloKitkat.com");
//        bgtDataManagerJDBC.persistBoardGame(boardGame);
//
        BgtDataManagerJPA bgtDataManagerJPA = new BgtDataManagerJPA();
        bgtDataManagerJPA.createNewBoardgame("test", "google.com");
    }
}
