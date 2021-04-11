package advhci.semester.androidproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
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
import android.widget.Toast;

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

        navigationView.setNavigationItemSelectedListener(this::navItemSelected);

        TextView exittext = findViewById(R.id.Exit);
        exittext.setOnClickListener(this::endApp);
    }

    public void endApp(View v){
        finish();
    }
//drawer menu effects and coding here
    public boolean navItemSelected(MenuItem item){
        if(item.getItemId() == R.id.itemDrawerAdd){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            // code here
            return true;
        }else if (item.getItemId() == R.id.itemDrawerDelete){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            //
            return true;
        } else if (item.getItemId() == R.id.itemDrawerHome){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            //
            return true;
        } else if (item.getItemId() == R.id.itemDrawerInfo){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            //
            return true;
        } else if (item.getItemId() == R.id.itemDrawerQueries){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            //
            return true;
        } else if (item.getItemId() == R.id.itemDrawerUpdate){
            item.setChecked(true);
            drawerLayout.closeDrawers();
            //
            return true;
        }
        return false;
    }

}