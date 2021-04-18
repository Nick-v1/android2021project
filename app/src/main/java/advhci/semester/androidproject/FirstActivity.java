package advhci.semester.androidproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.room.Room;

import android.content.ClipData;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class FirstActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar topAppBar;
    FragmentManager fragmentManager;
    public static roomDb roomAPIdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavMenuView);

        navigationView.setNavigationItemSelectedListener(this::navItemSelected);

        View toolBarExitApp = findViewById(R.id.appbarExit);
        toolBarExitApp.setOnClickListener(this::endApp);              //toolbar exit button

        roomAPIdatabase = Room.databaseBuilder(getApplicationContext(), roomDb.class, "SportsDB").allowMainThreadQueries().build();

        topAppBar = findViewById(R.id.topAppBar);          //drawer menu button
        topAppBar.setNavigationOnClickListener(this::openDrawer);

        fragmentManager = getSupportFragmentManager();

        if(findViewById(R.id.fragment_container) != null){
            if(savedInstanceState != null){
                return;
            }
            fragmentManager.beginTransaction().add(R.id.fragment_container, new HomeFragment()).commit();
        }


    }

    private void openDrawer(View view) {
        drawerLayout.openDrawer(GravityCompat.START);           //method that opens drawer
    }

    public void endApp(View v){
        finish();
        Toast.makeText(getApplicationContext(), "Exited App", Toast.LENGTH_SHORT).show();
    }
//drawer menu effects and coding here
    public boolean navItemSelected(MenuItem item){

        item.setChecked(true);
        drawerLayout.closeDrawers();

        if (item.getItemId() == R.id.itemDrawerQueries){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new queries()).commit();
            return true;
        }
        else if(item.getItemId() == R.id.itemDrawerAddAthlete){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertAthleteFragment()).commit();
            return true;
        }

        else if(item.getItemId() == R.id.itemDrawerDeleteAthlete){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteAthleteFragment()).commit();
            return true;
        }

        else if (item.getItemId() == R.id.itemDrawerUpdateAthlete){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateAthleteFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerAddTeam){

            fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertTeamFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerDeleteTeam){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteTeamFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerUpdateTeam){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateTeamFragment()).commit();
            return true;
        }
        else if(item.getItemId() == R.id.itemDrawerAddSport){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new InsertSportFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerDeleteSport){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new DeleteSportFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerUpdateSport){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new UpdateSportFragment()).commit();
            return true;
        }
        else if(item.getItemId() == R.id.itemDrawerAddGame){
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerDeleteGame){
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerUpdateGame){
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerHome){
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.itemDrawerInfo){
//info fragment fragmentManager.beginTransaction().replace(R.id.fragment_container, new InfoFragment()).commit();
            return true;
        }
        else if (item.getItemId() == R.id.ExitAppDrawer){
            finish();
            Toast.makeText(getApplicationContext(), "Exited App", Toast.LENGTH_SHORT).show();
        }
        else{
            item.setChecked(false);
        }

        return false;
    }

}