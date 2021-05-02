package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.KeyListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class UpdateAthleteFragment extends Fragment {


    public UpdateAthleteFragment() {
        // Required empty public constructor
    }

    Spinner spinnerGender;
    ArrayAdapter<CharSequence> adapter;
    TextView athleteid, athletename, athletelastname, athletecity, athletecountry, athletesportid, athletebirthdate, athleteperformanceview;
    TextInputLayout textInputLayout;
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
        athleteperformanceview = view.findViewById(R.id.updateAthletePerformance);

        textInputLayout =  view.findViewById(R.id.updateAthleteLayoutsportid);
        textInputLayout.setEnabled(false);

        bn = view.findViewById(R.id.updateAthleteButton);
        bn.setOnClickListener(this::updateAthlete);

        return view;
    }

    private void updateAthlete(View view) {
        int var_athleteid = 0;
        try {
            var_athleteid = Integer.parseInt(athleteid.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
        }
        String var_athletename = athletename.getText().toString();
        String var_athletelastname = athletelastname.getText().toString();
        String var_athletecity = athletecity.getText().toString();
        String var_athletecountry = athletecountry.getText().toString();
        int var_athlete_sport_id = 0;
        try {
            var_athlete_sport_id = Integer.parseInt(athletesportid.getText().toString());
        } catch (Exception e) {
            Toast.makeText(getActivity(), "" + e, Toast.LENGTH_SHORT).show();
        }
        String var_athlete_birthdate = athletebirthdate.getText().toString();
        String var_athletegender = spinnerGender.getSelectedItem().toString();
        double var_athleteperformance = Double.parseDouble(athleteperformanceview.getText().toString());

        try {
            Athletes athlete = new Athletes();
            athlete.setAthlete_id(var_athleteid);
            athlete.setFirstname(var_athletename);
            athlete.setLastname(var_athletelastname);
            athlete.setCity(var_athletecity);
            athlete.setCountry(var_athletecountry);
            athlete.setSport_id(var_athlete_sport_id);
            athlete.setBirthdate(var_athlete_birthdate);
            athlete.setGender(var_athletegender);
            athlete.setPerformance(var_athleteperformance);
            FirstActivity.roomDbBuilder.myDaoAdmin().updateAthlete(athlete);
            Toast.makeText(getActivity(), "Updated athlete", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Could not update", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        athleteid.setOnKeyListener(this::liveupdatetext);
    }


    private boolean liveupdatetext(View view, int i, KeyEvent keyEvent) {
        int athid = 0;
        int checkTextId = 0;
        String athname = "";
        String athlastname = "";
        String athcity = "";
        String athcountry = "";
        int athsportid = 0;
        String athbd = "";
        String athgender = "";
        String athleteperformance = "";

        List<Athletes> athletes = FirstActivity.roomDbBuilder.myDaoAdmin().getAthletes();

        try {
            checkTextId = Integer.parseInt(athleteid.getText().toString());
        }catch(NumberFormatException e){ }

        int counter = 0;
        for (Athletes ath : athletes) {
            if (ath.getAthlete_id() == checkTextId) {
                athid = ath.getAthlete_id();
                athname = ath.getFirstname();
                athlastname = ath.getLastname();
                athcity = ath.getCity();
                athcountry = ath.getCountry();
                athsportid = ath.getSport_id();
                athbd = ath.getBirthdate();
                athgender = ath.getGender();
                athleteperformance = String.valueOf(ath.getPerformance());
                counter++;
            }
        }

        if (counter > 1){
            textInputLayout.setEnabled(true);
        }
        else
            textInputLayout.setEnabled(false);

        String textvalueofsportid = String.valueOf(athsportid);

        athletename.setText(athname);
        athletelastname.setText(athlastname);
        athletecity.setText(athcity);
        athletecountry.setText(athcountry);
        athletesportid.setText(textvalueofsportid);
        athletebirthdate.setText(athbd);
        athleteperformanceview.setText(athleteperformance);

        if(athgender.equals("Female"))
            spinnerGender.setSelection(adapter.getPosition("Female"));
        else
            spinnerGender.setSelection(0);

        return false;
    }

}