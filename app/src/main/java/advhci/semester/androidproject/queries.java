package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class queries extends Fragment {

    public queries() {
        // Required empty public constructor
    }

    Spinner spinner;
    Spinner spinner2;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adapter2;
    TextView QueryResultsText, v1;
    Button bn, bnsport;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queries, container, false);

        spinner = view.findViewById(R.id.Queryspinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner2 = view.findViewById(R.id.Queryspinner2);
        adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapter2.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner2.setAdapter(adapter2);

        v1 = view.findViewById(R.id.sporttitle);

        QueryResultsText = view.findViewById(R.id.textViewQueryResult);
        bn = view.findViewById(R.id.buttonSearch);
        bn.setOnClickListener(this::results);

        bn = view.findViewById(R.id.showallsports);
        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spinner.getSelectedItemPosition() == 7)
                    spinner2.setVisibility(View.VISIBLE);
            }
        });

        return view;
    }

    private void showspinner2(AdapterView<?> adapterView, View view, int i, long l) {
        if (spinner2.getSelectedItemPosition() == 7)
            spinner2.setVisibility(View.VISIBLE);
    }

    //Query Results
    private void results(View view) {
        CollectionReference collectionReference;
        spinner2.setVisibility(View.INVISIBLE);
        v1.setText(null);

        if (spinner.getSelectedItemPosition() == 0 ) {
            List<Sports> sports = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();
            String result = "";
            for (Sports sport : sports) {
                int code = sport.getId();
                String name = sport.getName();
                String type = sport.getType();
                String gender = sport.getGender();

                result = result + "Id: " + code + "\nName: " + name + "\nType: " + type + "\nSport gender: " + gender + "\n\n";
            }
            v1.setText("All Sports");
            Toast.makeText(getActivity(), "Total Sports: "+sports.size(), Toast.LENGTH_SHORT).show();
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 1){
            List<Athletes> athletes = FirstActivity.roomDbBuilder.myDaoAdmin().getAthletes();
            String result = "";
            for (Athletes ath : athletes){
                int athid = ath.getAthlete_id();
                String athname = ath.getFirstname();
                String athlastname = ath.getLastname();
                String athcity = ath.getCity();
                String athcountry = ath.getCountry();
                int athsportid = ath.getSport_id();
                String athbd = ath.getBirthdate();
                String athgender = ath.getGender();
                Double athperformance = ath.getPerformance();

                result += "Id: " + athid + "\nFirst name: " + athname + "\nLast Name: " + athlastname + "\nGender: " + athgender +
                        "\nSport id: " + athsportid + "\nPerformance: " + athperformance + "\nCity: " + athcity + "\nCountry: " + athcountry +  "\nBirthdate: "
                        + athbd +  "\n\n";

            }
            v1.setText("All Athletes");
            Toast.makeText(getActivity(), "Total Athletes: "+athletes.size(), Toast.LENGTH_SHORT).show();
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 2){
            List<Teams> teams = FirstActivity.roomDbBuilder.myDaoAdmin().getTeams();
            String result = "";
            for (Teams t : teams){
                int teamid = t.getTeam_id();
                String teamname = t.getName();
                String stadium = t.getStadium();
                String city = t.getCity();
                String country = t.getCountry();
                int teamsportid = t.getSport_id();
                String founddate = t.getFoundation_date();

                result += "Team ID: " + teamid + "\nTeam name: " + teamname + "\nTeam stadium: " + stadium + "\nParticipates on sport with id: " +teamsportid+ "\nFoundation date: "+founddate+"\n\n";

            }
            v1.setText("All Teams");
            Toast.makeText(getActivity(), "Total Teams: "+teams.size(), Toast.LENGTH_SHORT).show();
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 3){
            collectionReference = FirstActivity.firedb.collection("Games");
            collectionReference.get().addOnSuccessListener(this::getFirebaseGames);
            collectionReference.get().addOnFailureListener(this::getFirebaseGamesFailedToRead);
            v1.setText("All Games");
        }
        else if (spinner.getSelectedItemPosition() == 4){
            List<String2Double1> athletes = FirstActivity.roomDbBuilder.myDaoAdmin().getHighPerformingAthletes();
            String result = "";
            for (String2Double1 a : athletes){
                String firstname = a.getFirstName();
                String lastname = a.getLastName();
                double performance = a.getPerformance();

                result += "First Name: " + firstname + "\nLast Name: " + lastname + "\nPerformance: " + performance + "\n\n";
            }
            v1.setText("High Performing Athletes");
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 5){
            List<String3> femaleAthletesAndSport = FirstActivity.roomDbBuilder.myDaoAdmin().getFemaleAthletesAndSportParticipating();
            String result = "";
            for (String3 i : femaleAthletesAndSport){
                String firstname = i.getFirstname();
                String lastname = i.getLastname();
                String sportname = i.getSportname();

                result += "First Name: " + firstname + "\nLast Name: " + lastname + "\nSport: " + sportname + "\n\n";
            }
            v1.setText("Sports/Female Athletes");
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 6){
            collectionReference = FirstActivity.firedb.collection("Games");
            Query query = collectionReference.orderBy("date_of_match", Query.Direction.DESCENDING).limit(2);
            query.get().addOnSuccessListener(this::getFirebaseGames);
            v1.setText("Latest Games");
        }
        else if(spinner.getSelectedItemPosition() == 7){
            String result = "";
            String sportvalue = spinner2.getSelectedItem().toString();
            List<String2> s2 = FirstActivity.roomDbBuilder.myDaoAdmin().getAthletesParticipatingInXSport(sportvalue);

            v1.setText(sportvalue);
            for (String2 i : s2){
                result += "First Name: " + i.getName() + "\nLast Name: " + i.getLastname() +"\n\n";
            }
            QueryResultsText.setText(result);
        }
        else
            QueryResultsText.setText(null);
    }

    private void getFirebaseGamesFailedToRead(Exception e) {
        Toast.makeText(getActivity(), "Query operation failed.", Toast.LENGTH_SHORT).show();
    }

    private void getFirebaseGames(QuerySnapshot queryDocumentSnapshots) {
        String result = "";
        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
            Games game = documentSnapshot.toObject(Games.class);

            String dateofmatch = game.getDate_of_match();
            String gsportname = game.getGsport_name();
            String gsporttype = game.getGsport_type();
            String gcity = game.getGcity();
            String gcountry = game.getGcountry();
            Integer gameid = game.getGid();
            String gameinfo = game.getInfo();

            result += "Date of Game: " + dateofmatch + "\nLocation: " + gcity + ", " + gcountry +
                    "\nSport name: " + gsportname + "\nSport Type: " + gsporttype +
                    "\nGame id: " + gameid + "\nResults: " +gameinfo+ "\n\n";
        }
        QueryResultsText.setText(result);
    }



}