package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class UpdateAthleteFragment extends Fragment {


    public UpdateAthleteFragment() {
        // Required empty public constructor
    }

    Spinner spinnerGender;
    ArrayAdapter<CharSequence> adapter;
    TextView athleteid, athletename, athletelastname, athletecity, athletecountry, athletesportid, athletebirthdate;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_athlete, container, false);

        spinnerGender = view.findViewById(R.id.updateAthleteGender);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        athleteid = view.findViewById(R.id.updateAthleteId);
        athletename = view.findViewById(R.id.updateAthleteName);
        athletelastname = view.findViewById(R.id.updateAthleteLastName);
        athletecity = view.findViewById(R.id.updateAthleteCity);
        athletecountry = view.findViewById(R.id.updateAthleteCountry);
        athletesportid = view.findViewById(R.id.updateAthleteSportID);
        athletebirthdate = view.findViewById(R.id.updateAthleteBirthdate);

        bn = view.findViewById(R.id.updateAthleteButton);
        bn.setOnClickListener(this::updateAthlete);

        return view;
    }

    private void updateAthlete(View view) {
        int var_athleteid = 0;
        try{
            var_athleteid = Integer.parseInt(athleteid.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
        }
        String var_athletename = athletename.getText().toString();
        String var_athletelastname = athletelastname.getText().toString();
        String var_athletecity = athletecity.getText().toString();
        String var_athletecountry = athletecountry.getText().toString();
        int var_athlete_sport_id = 0;
        try {
            var_athlete_sport_id = Integer.parseInt(athletesportid.getText().toString());
        }catch (Exception e){
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
        }
        String var_athlete_birthdate = athletebirthdate.getText().toString();
        String var_athletegender = spinnerGender.getSelectedItem().toString();

        try{
            Athletes athlete = new Athletes();
            athlete.setAthlete_id(var_athleteid);
            athlete.setFirstname(var_athletename);
            athlete.setLastname(var_athletelastname);
            athlete.setCity(var_athletecity);
            athlete.setCountry(var_athletecountry);
            athlete.setSport_id(var_athlete_sport_id);
            athlete.setBirthdate(var_athlete_birthdate);
            athlete.setGender(var_athletegender);
            FirstActivity.roomDbBuilder.myDaoAdmin().updateAthlete(athlete);
            Toast.makeText(getActivity(), "Updated athlete", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(getActivity(), "Could not update",Toast.LENGTH_LONG).show();
        }
    }
}