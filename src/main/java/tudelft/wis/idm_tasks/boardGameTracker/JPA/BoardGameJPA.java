package tudelft.wis.idm_tasks.boardGameTracker.JPA;

import tudelft.wis.idm_tasks.boardGameTracker.interfaces.BoardGame;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "board_games")
public class BoardGameJPA implements BoardGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String bggUrl;
    public BoardGameJPA(String name, String bggUrl) {
        this.name = name;
        this.bggUrl = bggUrl;
    }
    public BoardGameJPA() {}


    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getBGG_URL() {
        return bggUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBggUrl(String bggUrl) {
        this.bggUrl = bggUrl;
    }

    @Override
    public String toVerboseString() {
        return "BoardGameJPA{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bggUrl='" + bggUrl + '\'' +
                '}';
    }
}
