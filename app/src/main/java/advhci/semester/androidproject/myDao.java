package advhci.semester.androidproject;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface myDao {

    @Insert
    public void addSport(Sports sport);

    @Query("select * from sport")
    public List<Sports> getSports();

    @Delete
    public void deleteSport(Sports sport);

    @Update
    public void updateSport(Sports sport);

    @Insert
    public void addAthlete(Athletes athlete);

    @Query("select * from athlete")
    public List<Athletes> getAthletes();

    @Delete
    public void deleteAthlete(Athletes athlete);

    @Update
    public void updateAthlete(Athletes athlete);

    @Insert
    public void addTeam(Teams team);

    @Query("select * from team")
    public List<Teams> getTeams();

    @Delete
    public void deleteTeam(Teams team);

    @Update
    public void updateTeam(Teams team);
}
