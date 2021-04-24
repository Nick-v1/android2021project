package advhci.semester.androidproject;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Sports.class, Athletes.class, Teams.class}, version = 1)
public abstract class roomDb extends RoomDatabase {
    public abstract myDao myDaoAdmin();
}
