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

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class queries extends Fragment {

    public queries() {
        // Required empty public constructor
    }

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView QueryResultsText;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queries, container, false);

        spinner = view.findViewById(R.id.Queryspinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        QueryResultsText = view.findViewById(R.id.textViewQueryResult);
        bn = view.findViewById(R.id.buttonSearch);
        bn.setOnClickListener(this::results);

        return view;
    }

    //Query Results
    private void results(View view) {
        CollectionReference collectionReference;

        if (spinner.getSelectedItemPosition() == 0 ) {
            List<Sports> sports = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();
            String result = "";
            for (Sports sport : sports) {
                int code = sport.getId();
                String name = sport.getName();
                String type = sport.getType();
                String gender = sport.getGender();

                result = result + "Id: " + code + "\nName: " + name + "\nType: " + type + "\nSport gender: " + gender + "\n\n";

                Toast.makeText(getActivity(), "Total Sports: "+sports.size(), Toast.LENGTH_SHORT).show();
            }
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

                result += "Id: " + athid + "\nFirst name: " + athname + "\nLast Name: " + athlastname + "\nCity: " + athcity + "\nCountry: " + athcountry + "\nSport id: " + athsportid + "\nBirthdate: "
                        + athbd + "\nGender: " + athgender + "\n\n";

                Toast.makeText(getActivity(), "Total Athletes: "+athletes.size(), Toast.LENGTH_SHORT).show();
            }
            QueryResultsText.setText(result);
        }
        else if (spinner.getSelectedItemPosition() == 3){
            collectionReference = FirstActivity.firedb.collection("Games");
            collectionReference.get().addOnSuccessListener(this::getFirebaseGames);
            collectionReference.get().addOnFailureListener(this::getFirebaseGamesFailedToRead);
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

            result += "Date of Game: " + dateofmatch + "\nLocation: " + gcity + ", " + gcountry +
                    "\nSport name: " + gsportname + "\nSport Type: " + gsporttype +
                    "\nGame id: " + gameid + "\n\n";
        }
        QueryResultsText.setText(result);
    }



}