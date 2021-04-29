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

import java.util.List;


public class InsertAthleteFragment extends Fragment {


    public InsertAthleteFragment() {
        // Required empty public constructor
    }

    Spinner spinnerGender;
    ArrayAdapter<CharSequence> adapter;
    Button button;
    TextView textid, textname, textlastname, textcity, textcountry, textsportid, textbirthdate, textperformance;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_athlete, container, false);

        spinnerGender = view.findViewById(R.id.insertAthleteGender);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        textid = view.findViewById(R.id.insertAthleteidtext);
        textname = view.findViewById(R.id.insertAthleteFirstName);
        textlastname = view.findViewById(R.id.insertAthleteLastName);
        textcity = view.findViewById(R.id.insertAthleteCity);
        textcountry = view.findViewById(R.id.insertAthleteCountry);
        textsportid = view.findViewById(R.id.insertAthleteSportid);
        textbirthdate = view.findViewById(R.id.insertAthleteBirthdate);
        textperformance = view.findViewById(R.id.insertAthletePerformance);

        button = view.findViewById(R.id.insertAthleteButton);
        button.setOnClickListener(this::addAthlete);

        return view;
    }

    private void addAthlete(View view){

       int var_athleteid = 0;
       double var_performance = 0;
       try{
           var_athleteid = Integer.parseInt(textid.getText().toString());
       } catch (Exception e){
           Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
       }
       try{
           var_performance = Double.parseDouble(textperformance.getText().toString());
       }catch (Exception e){}

       String var_athletename = textname.getText().toString();
       String var_athletelastname = textlastname.getText().toString();
       String var_athletecity = textcity.getText().toString();
       String var_athletecountry = textcountry.getText().toString();

       int var_athlete_sport_id = 0;
       try {
           var_athlete_sport_id = Integer.parseInt(textsportid.getText().toString());
       }catch (Exception e){
           Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
       }
       String var_athlete_birthdate = textbirthdate.getText().toString();
       String var_athletegender = spinnerGender.getSelectedItem().toString();

        List<Sports> sport = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();

       try{
           for (Sports sp : sport) {
               if (var_athlete_sport_id == sp.getId() && var_athletegender.equals(sp.getGender()) && sp.getType().equals("Individual")) {
                   Athletes athlete = new Athletes();
                   athlete.setAthlete_id(var_athleteid);
                   athlete.setFirstname(var_athletename);
                   athlete.setLastname(var_athletelastname);
                   athlete.setCity(var_athletecity);
                   athlete.setCountry(var_athletecountry);
                   athlete.setSport_id(var_athlete_sport_id);
                   athlete.setBirthdate(var_athlete_birthdate);
                   athlete.setGender(var_athletegender);
                   athlete.setPerformance(var_performance);
                   FirstActivity.roomDbBuilder.myDaoAdmin().addAthlete(athlete);
                   Toast.makeText(getActivity(), "Added athlete", Toast.LENGTH_SHORT).show();
               }
           }

       } catch (Exception e){
           Toast.makeText(getActivity(), "Could not be added\nReason: Athlete with same id exists",Toast.LENGTH_LONG).show();
       }

    }
}