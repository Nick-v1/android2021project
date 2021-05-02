package advhci.semester.androidproject;

import androidx.room.ColumnInfo;

public class String3 {
    private String firstname;
    private String lastname;

    @ColumnInfo (name = "SportNameFemales")
    private String sportname;

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

    public String getSportname() {
        return sportname;
    }

    public void setSportname(String sportname) {
        this.sportname = sportname;
    }
}
