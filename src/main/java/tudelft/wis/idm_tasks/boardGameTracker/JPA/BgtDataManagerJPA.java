package tudelft.wis.idm_tasks.boardGameTracker.JPA;
import tudelft.wis.idm_tasks.boardGameTracker.BgtException;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BgtDataManager;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.PlaySession;
import tudelft.wis.idm_tasks.boardGameTracker.interfaces.Player;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class BgtDataManagerJPA implements BgtDataManager {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Player createNewPlayer(String name, String nickname) throws BgtException {
        PlayerJPA player = new PlayerJPA();
        player.setName(name);
        player.setNickname(nickname);
        entityManager.persist(player);
        return player;
    }

    @Override
    public Collection<Player> findPlayersByName(String name) throws BgtException {
        String findByNameJPA = "SELECT p FROM PlayerJPA p WHERE p.name LIKE :name";

        List<PlayerJPA> players = entityManager.createQuery(findByNameJPA, PlayerJPA.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();

        List<Player> playerInterfaces = new ArrayList<>();
        for (PlayerJPA playerJPA : players) {
            playerInterfaces.add(playerJPA);
        }

        return playerInterfaces;
    }

    @Override
    public BoardGame createNewBoardgame(String name, String bggURL) throws BgtException {
        BoardGameJPA boardGame = new BoardGameJPA();
        boardGame.setName(name);
        boardGame.setBggUrl(bggURL);
        entityManager.persist(boardGame);
        return boardGame;
    }

    @Override
    public Collection<BoardGame> findGamesByName(String name) throws BgtException {
        String findByNameJPA = "SELECT b FROM BoardGameJPA b WHERE b.name LIKE :name";
        List<BoardGameJPA> boardGames = entityManager.createQuery(findByNameJPA, BoardGameJPA.class)
                .setParameter("name", "%"+ name + "%")
                .getResultList();
        List<BoardGame> boardGameInterfaces = new ArrayList<>();
        for (BoardGameJPA boardGameJPA: boardGames) {
            boardGameInterfaces.add(boardGameJPA);
        }
        return boardGameInterfaces;
    }

    @Override
    public PlaySession createNewPlaySession(Date date, Player host, BoardGame game, int playtime,
                                            Collection<Player> players, Player winner) throws BgtException {
        PlaySessionJPA playSession = new PlaySessionJPA();
        playSession.setDate(date);
        playSession.setHost((PlayerJPA) host);
        playSession.setGame((BoardGameJPA) game);
        playSession.setPlaytime(playtime);
        Collection<PlayerJPA> playerJPAs = new ArrayList<>();
        for (Player player : players) {
            playerJPAs.add((PlayerJPA) player);
        }
        playSession.setAllPlayers(playerJPAs);
        playSession.setWinner((PlayerJPA) winner);

        entityManager.persist(playSession);
        return playSession;
    }


    @Override
    public Collection<PlaySession> findSessionByDate(Date date) throws BgtException {
        String findByDateJPA = "SELECT ps FROM PlaySessionJPA ps WHERE ps.date = :date";
        List<PlaySessionJPA> playSessions = entityManager.createQuery(findByDateJPA, PlaySessionJPA.class)
                .setParameter("date",date)
                .getResultList();
        List<PlaySession> sessionInterfaces = new ArrayList<>();
        for (PlaySessionJPA playSessionJPA: playSessions) {
            sessionInterfaces.add(playSessionJPA);
        }
        return sessionInterfaces;
    }

    @Override
    public void persistPlayer(Player player) throws BgtException {
        PlayerJPA playerJPA = (PlayerJPA) player;
        entityManager.persist(playerJPA);
    }

    @Override
    public void persistPlaySession(PlaySession session) {
        PlaySessionJPA playSessionJPA = (PlaySessionJPA) session;
        entityManager.persist(playSessionJPA);
    }

    @Override
    public void persistBoardGame(BoardGame game) {
        BoardGameJPA boardGameJPA = (BoardGameJPA) game;
        entityManager.persist(boardGameJPA);
    }
}
