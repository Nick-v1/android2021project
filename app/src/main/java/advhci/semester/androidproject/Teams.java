package advhci.semester.androidproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

@Entity (tableName = "team",
        primaryKeys = {"team_id", "team_sport_id"},
        foreignKeys = {
        @ForeignKey(entity = Sports.class,
                    parentColumns = "sport_id",
                    childColumns = "team_sport_id",
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE)
                    }
        )
public class Teams {

    private int team_id;

    private String name;

    private String stadium;

    private String city;

    private String country;

    @ColumnInfo (name = "team_sport_id")
    private int sport_id;

    private String foundation_date;

    public int getTeam_id() {
        return team_id;
    }

    public void setTeam_id(int team_id) {
        this.team_id = team_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getSport_id() {
        return sport_id;
    }

    public void setSport_id(int sport_id) {
        this.sport_id = sport_id;
    }

    public String getFoundation_date() {
        return foundation_date;
    }

    public void setFoundation_date(String foundation_date) {
        this.foundation_date = foundation_date;
    }
}
