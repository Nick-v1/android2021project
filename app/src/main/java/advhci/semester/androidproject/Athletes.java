package advhci.semester.androidproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;

import java.util.Date;

@Entity (tableName = "athletes",
        primaryKeys = {"athlete_id", "sid"},
        foreignKeys = {
        @ForeignKey(entity = Sports.class,
                    parentColumns = "sport_id",
                    childColumns = "sid",
                    onDelete = ForeignKey.CASCADE,
                    onUpdate = ForeignKey.CASCADE) }
)
public class Athletes {

    private int athlete_id;

    private String firstname;

    private String lastname;

    private String city;

    private String country;

    @ColumnInfo (name = "sid")
    private int sport_id;

    private String birthdate;

    public int getAthlete_id() {
        return athlete_id;
    }

    public void setAthlete_id(int athlete_id) {
        this.athlete_id = athlete_id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }
}
