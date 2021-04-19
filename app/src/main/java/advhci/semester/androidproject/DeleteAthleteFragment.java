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
    TextView athid;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_athlete, container, false);

        athid = view.findViewById(R.id.deleteAthleteid);
        bn = view.findViewById(R.id.deleteAthleteButton);
        bn.setOnClickListener(this::deleteAnAthleteFromdb);

        return view;
    }

    private void deleteAnAthleteFromdb(View view) {

        int var_athleteID = 0;
        try{
            var_athleteID = Integer.parseInt(athid.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
        }

            Athletes athlete = new Athletes();
            athlete.setAthlete_id(var_athleteID);
            FirstActivity.roomDbBuilder.myDaoAdmin().deleteAthlete(athlete);
            Toast.makeText(getActivity(), "Deleted athlete", Toast.LENGTH_SHORT).show();
//doesn't work ???
    }
}