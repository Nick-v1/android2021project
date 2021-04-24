package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteAthleteFragment extends Fragment {


    public DeleteAthleteFragment() {
        // Required empty public constructor
    }
    TextView athid, athsportid;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_athlete, container, false);

        athid = view.findViewById(R.id.deleteAthleteid);
        athsportid = view.findViewById(R.id.deleteAthleteSportid);
        bn = view.findViewById(R.id.deleteAthleteButton);
        bn.setOnClickListener(this::deleteAnAthleteFromdb);

        return view;
    }

    private void deleteAnAthleteFromdb(View view) {

        int var_athleteID = 0;
        int var_athletesportid = 0;
        try{
            var_athleteID = Integer.parseInt(athid.getText().toString());
            var_athletesportid = Integer.parseInt(athsportid.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), "Fill both fields for deletion", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            Athletes athlete = new Athletes();
            athlete.setAthlete_id(var_athleteID);
            athlete.setSport_id(var_athletesportid);
            FirstActivity.roomDbBuilder.myDaoAdmin().deleteAthlete(athlete);
            Toast.makeText(getActivity(), "Deleted athlete", Toast.LENGTH_SHORT).show();
            athid.setText("");
            athsportid.setText("");
        } catch (Exception e){}
    }

}