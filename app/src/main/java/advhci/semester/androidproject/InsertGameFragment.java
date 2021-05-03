package advhci.semester.androidproject;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class InsertGameFragment extends Fragment {


    public InsertGameFragment() {
        // Required empty public constructor
    }

    Spinner spinnerType;
    Spinner spinnerSportname;
    ArrayAdapter<CharSequence> adapter;
    ArrayAdapter<CharSequence> adaptertype;
    Button bn;
    EditText gamedate, gamecity, gamecountry, gameid, gameteam1, gameteam2, gameteam1score, gameteam2score, gamesportidview;
    TextInputLayout textInputLayoutgameid, textInputLayoutTeam1, textInputLayoutTeam2, textInputLayoutTeam1Score, textInputLayoutTeam2Score, textInputLayoutGameSportid;
    List<Sports> sport = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_game, container, false);

        spinnerType = view.findViewById(R.id.spinnerInsertGameType);
        adaptertype = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adaptertype.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adaptertype);

        spinnerSportname = view.findViewById(R.id.spinnerInsertGameName);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportname.setAdapter(adapter);

        gamedate = view.findViewById(R.id.insertGameDate);
        gamecity = view.findViewById(R.id.insertGameCity);
        gamecountry = view.findViewById(R.id.insertGameCountry);
        gameid = view.findViewById(R.id.insertGameid);
        gamesportidview = view.findViewById(R.id.insertGameSportid);

        gameteam1 = view.findViewById(R.id.team1idtext);
        gameteam2 = view.findViewById(R.id.team2idtext);

        gameteam1score = view.findViewById(R.id.team1scoretext);
        gameteam2score = view.findViewById(R.id.team2scoretext);

        bn = view.findViewById(R.id.insertgamebutton);
        bn.setOnClickListener(this::addGameToDB);

        textInputLayoutgameid = view.findViewById(R.id.gameidinputlayout);
        textInputLayoutTeam1 = view.findViewById(R.id.team1idlayout);
        textInputLayoutTeam2 = view.findViewById(R.id.team2idlayout);
        textInputLayoutTeam1Score = view.findViewById(R.id.team1scorelayout);
        textInputLayoutTeam2Score = view.findViewById(R.id.team2scorelayout);
        textInputLayoutGameSportid = view.findViewById(R.id.gamesportidlayout);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        gameid.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                textInputLayoutgameid.setError("Adding an existing id will just update the corresponding game. Be careful when adding a new game as it will overwrite the existing game");
                return false;
            }
        });

        spinnerType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String varsporttype = spinnerType.getSelectedItem().toString();
                if (varsporttype.equals("Team")) {
                    textInputLayoutTeam1.setVisibility(View.VISIBLE);
                    textInputLayoutTeam2.setVisibility(View.VISIBLE);
                    textInputLayoutTeam1Score.setVisibility(View.VISIBLE);
                    textInputLayoutTeam2Score.setVisibility(View.VISIBLE);
                    textInputLayoutGameSportid.setVisibility(View.GONE);
                }
                else{
                    textInputLayoutTeam1.setVisibility(View.GONE);
                    textInputLayoutTeam2.setVisibility(View.GONE);
                    textInputLayoutTeam1Score.setVisibility(View.GONE);
                    textInputLayoutTeam2Score.setVisibility(View.GONE);
                    textInputLayoutGameSportid.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        gamesportidview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                for (Sports sp : sport){
                    try {
                        if (sp.getId() == Integer.parseInt(gamesportidview.getText().toString()))
                            spinnerType.setSelection(adaptertype.getPosition(""+sp.getType()));
                    } catch (Exception e){}
                }
                return false;
            }
        });
    }




    private void addGameToDB(View view) {
        int var_gameid = 0;
        try{
            var_gameid = Integer.parseInt(gameid.getText().toString());
        } catch (NumberFormatException e){}


        String vardate = gamedate.getText().toString();
        String varcity = gamecity.getText().toString();
        String varcountry = gamecountry.getText().toString();
        String varsportname = spinnerSportname.getSelectedItem().toString();
        String varsporttype = spinnerType.getSelectedItem().toString();
        String info = "";
        int firstteamid = 0;
        int secondteamid = 0;
        int firstteamscore = 0;
        int secondteamscore = 0;
        int gamesportid = 0;
        List<Teams> team = FirstActivity.roomDbBuilder.myDaoAdmin().getTeams();

        List<Athletes> athlete = FirstActivity.roomDbBuilder.myDaoAdmin().getAthletes();

        if (varsporttype.equals("Team")){
            try {
                firstteamid = Integer.parseInt(gameteam1.getText().toString());
                secondteamid = Integer.parseInt(gameteam2.getText().toString());
                firstteamscore = Integer.parseInt(gameteam1score.getText().toString());
                secondteamscore = Integer.parseInt(gameteam2score.getText().toString());
            } catch (NumberFormatException e){}

            for (Teams t : team) {
                if (firstteamid == t.getTeam_id()){
                    for (Teams t1 : team){
                        if (secondteamid != firstteamid && secondteamid == t1.getTeam_id()){
                            if (t1.getSport_id() == t.getSport_id()) {
                                try {
                                    Games game = new Games();
                                    game.setGid(var_gameid);
                                    game.setDate_of_match(vardate);
                                    game.setGcity(varcity);
                                    game.setGcountry(varcountry);
                                    game.setGsport_name(varsportname);
                                    game.setGsport_type(varsporttype);
                                    info = "Team " + firstteamid + " vs " + secondteamid + " Team.  " + "  Score: " + firstteamscore + " - " + secondteamscore;
                                    game.setInfo(info);
                                    FirstActivity.firedb.collection("Games").document("" + var_gameid).set(game).addOnSuccessListener(this::onComplete);
                                } catch (Exception e) { }
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if (varsporttype.equals("Individual")){
            int athletescounter = 0;
            float sumperformance = 0;
            try{
                gamesportid = Integer.parseInt(gamesportidview.getText().toString());
            }catch(NumberFormatException e){}

            for (Sports sp : sport){
                if (sp.getId() == gamesportid)
                for (Athletes ath : athlete) {
                     if (ath.getSport_id() == gamesportid){
                        athletescounter++;
                        sumperformance += ath.getPerformance(); //how many athletes
                    }
                }
            } sumperformance = sumperformance / athletescounter;
            try{
                Games game = new Games();
                game.setGid(var_gameid);
                game.setDate_of_match(vardate);
                game.setGcity(varcity);
                game.setGcountry(varcountry);
                game.setGsport_name(varsportname);
                game.setGsport_type(varsporttype);
                game.setInfo(athletescounter+" athletes took part in with average performance: "+sumperformance);
                FirstActivity.firedb.collection("Games")
                        .document(""+var_gameid).set(game).addOnSuccessListener(this::onComplete);

            } catch (Exception e){
                Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }


    }

    private void onComplete(Void aVoid) {
        Toast.makeText(getActivity(), "Game added", Toast.LENGTH_SHORT).show();
        textInputLayoutgameid.setErrorEnabled(false);
    }


}