package advhci.semester.androidproject;

import androidx.room.ColumnInfo;

public class String2Double1 {
    @ColumnInfo (name = "fn")
    private String FirstName;
    @ColumnInfo (name = "ln")
    private String LastName;
    @ColumnInfo (name = "prfmnce")
    private double Performance;

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public double getPerformance() {
        return Performance;
    }

    public void setPerformance(double performance) {
        Performance = performance;
    }
}
