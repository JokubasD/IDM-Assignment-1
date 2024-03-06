package tudelft.wis.idm_tasks.boardGameTracker.JDBC;

public class BoardGameJDBC implements tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame {
    private String name;
    private String bgg_url;

    public BoardGameJDBC(String name, String bggURL) {
        this.name = name;
        this.bgg_url = bggURL;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBGG_URL() {
        return bgg_url;
    }

    @Override
    public String toVerboseString() {
        return name + " (" + bgg_url + ")";
    }

    @Override
    public String toString() {
        return toVerboseString();
    }
}
