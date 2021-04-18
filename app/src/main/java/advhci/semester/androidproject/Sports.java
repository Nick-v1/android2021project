package advhci.semester.androidproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity (tableName = "sport")
public class Sports {

    @ColumnInfo(name = "sport_id")
    @PrimaryKey
    private int id;

    @ColumnInfo(name = "sport_name")
    private String name;

    @ColumnInfo(name = "sport_type")
    private String type;

    @ColumnInfo(name = "sport_gender")
    private String gender;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}