package advhci.semester.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toolbar;

import com.google.android.material.navigation.NavigationView;

import org.w3c.dom.Text;

public class FirstActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.NavMenuView);

//drawer menu effects and coding here
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if(item.getItemId() == R.id.itemDrawerAdd){
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    /**************
                     * ***********
                     * ************
                     * ***********/
                    return true;
                } else if (item.getItemId() == R.id.itemDrawerDelete){
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    /**************
                     * ***********
                     * ************
                     * ***********/
                    return true;
                } else if (item.getItemId() == R.id.itemDrawerHome){
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    /**************
                     * ***********
                     * ************
                     * ***********/
                    return true;
                } else if (item.getItemId() == R.id.itemDrawerInfo){
                    item.setChecked(true);
                    drawerLayout.closeDrawers();
                    /**************
                     * ***********
                     * ************
                     * ***********/
                    return true;
                }

                return false;
            }
        });

    /** Exit buttons*/
        ImageButton exitbutton = findViewById(R.id.btmBarExit);

        exitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();  /** kills app*/
            }
        });

        TextView exittext = findViewById(R.id.Exit);
        exittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}