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

    @Query("select DISTINCT firstname as fn, lastname as ln, performance as prfmnce from athlete where performance >= 7")
    public List<String2Double1> getHighPerformingAthletes();

    @Query("select DISTINCT a.firstname, a.lastname, s.sport_name as SportNameFemales FROM athlete a INNER JOIN sport s ON a.sid = s.sport_id WHERE a.gender = 'Female' ")
    public List<String3> getFemaleAthletesAndSportParticipating();

    @Query("select DISTINCT a.firstname as string2name, a.lastname from athlete a where exists(select * from sport s where a.sid = sport_id and s.sport_name = :inputsportname)")
    public List<String2> getAthletesParticipatingInXSport(String inputsportname);
}
