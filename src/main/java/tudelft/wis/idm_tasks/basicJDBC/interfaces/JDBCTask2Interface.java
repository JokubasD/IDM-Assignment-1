package tudelft.wis.idm_tasks.basicJDBC.interfaces;

import java.sql.Connection;
import java.util.Collection;

/**
 * @TODO ADD SOME GOOD COMMENT HERE
 * 
 * @author chris
 */
public interface JDBCTask2Interface {
    
    /**
     * @TODO DESCRIPTION AND TASK!
     * @return  @TODO
     */
    public Connection getConnection();
    
    
    /**
     * @todo ...
     * @return 
     */
    public Collection<String> getTitlesPerYear(int year);
    
    
    /**
     * @todo ... ALso, the task description in the assignment is  a bit clumsily phrased.
     * @param searchString
     * @return 
     */
    public Collection<String> getJobCategoriesFromTitles(String searchString);
    
    
    /**
     * @todo
     * Also, I would rephrase this into average runtimes for a given genre?
     * @param genre
     * @return 
     */
    public int getAverageRuntimeOfGenre(String genre);
    
    /**
     * @todo
     * @param actorFullname
     * @return 
     */
    public Collection<String> getPlayedCharacters(String actorFullname);
    
    

    
    
}

