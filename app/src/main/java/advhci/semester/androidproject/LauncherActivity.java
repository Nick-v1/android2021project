package advhci.semester.androidproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class LauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        Button button = findViewById(R.id.button);
        ImageButton imageButton = findViewById(R.id.imageButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity();      //both buttons do the same thing
                startActivity(intent);
                finish();                                      /** exits app**/
            }
        });

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getActivity();     //getActivity Method
                startActivity(intent);            //activates the intent to move to FirstActivity
                finish();                                     /** exits app**/
            }
        });
    }

    protected Intent getActivity(){                          /** getActivity returns an Intent (from this Activity LauncherActivity -> FirstActivity)**/
        return new Intent(this, FirstActivity.class);
    }


}