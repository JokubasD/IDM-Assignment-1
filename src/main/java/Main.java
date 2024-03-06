import tudelft.wis.idm_tasks.boardGameTracker.JDBC.BgtDataManagerJDBC;

public class Main {
    public static void main(String[] args) {
        BgtDataManagerJDBC bgtDataManagerJDBC = new BgtDataManagerJDBC();
        bgtDataManagerJDBC.createNewPlayer("Noah", "Luigi");
        bgtDataManagerJDBC.createNewBoardgame("Adrien", "google.com");
    }
}
