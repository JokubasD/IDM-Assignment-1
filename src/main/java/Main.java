import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BgtDataManagerJDBC;

public class Main {
    public static void main(String[] args) throws BgtException {
        BgtDataManagerJDBC bgtDataManagerJDBC = new BgtDataManagerJDBC();
//        bgtDataManagerJDBC.createNewPlayer("Noah", "Luigi");
//        bgtDataManagerJDBC.createNewBoardgame("Adrien", "google.com");
        System.out.println(bgtDataManagerJDBC.findGamesByName("Adrien"));
    }
}
