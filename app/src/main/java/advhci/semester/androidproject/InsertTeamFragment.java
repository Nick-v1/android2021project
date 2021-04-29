package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class InsertTeamFragment extends Fragment {


    public InsertTeamFragment() {
        // Required empty public constructor
    }

    TextView teamid, teamname, teamstadium, teamcity, teamcountry, teamsportid, teamfoundationdate;
    Button bn;
    TextInputLayout teamsportlayout, teamidlayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_team, container, false);

        teamsportlayout = view.findViewById(R.id.teamsportlayout);
        teamidlayout = view.findViewById(R.id.teamidlayout);

        teamid = view.findViewById(R.id.insertTeamid);
        teamname = view.findViewById(R.id.insertTeamName);
        teamstadium = view.findViewById(R.id.insertTeamStadium);
        teamcity = view.findViewById(R.id.insertTeamCity);
        teamcountry = view.findViewById(R.id.insertTeamCountry);
        teamsportid = view.findViewById(R.id.insertTeamSportID);
        teamfoundationdate = view.findViewById(R.id.insertTeamFoundationDate);

        bn = view.findViewById(R.id.insertTeamSubmitButton);
        bn.setOnClickListener(this::addteamtoroomdb);

        return view;
    }

    private void addteamtoroomdb(View view) {
        int var_teamid = 0;
        int var_teamsportid = 0;
        try{
            var_teamid = Integer.parseInt(teamid.getText().toString());
            var_teamsportid = Integer.parseInt(teamsportid.getText().toString());
        }catch(NumberFormatException e){}

        String var_teamname = teamname.getText().toString();
        String var_teamstadium = teamstadium.getText().toString();
        String var_teamcity = teamcity.getText().toString();
        String var_teamccountry = teamcountry.getText().toString();
        String var_teamfoundationdate = teamfoundationdate.getText().toString();
        List<Sports> sport = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();

        for (Sports sp : sport){
            if (sp.getId() == var_teamsportid && sp.getType().equals("Team")){
                try{
                    teamsportlayout.setErrorEnabled(false);
                    teamidlayout.setErrorEnabled(false);
                    Teams team = new Teams();
                    team.setTeam_id(var_teamid);
                    team.setName(var_teamname);
                    team.setStadium(var_teamstadium);
                    team.setCity(var_teamcity);
                    team.setCountry(var_teamccountry);
                    team.setSport_id(var_teamsportid);
                    team.setFoundation_date(var_teamfoundationdate);
                    FirstActivity.roomDbBuilder.myDaoAdmin().addTeam(team);
                    Toast.makeText(getActivity(), "Team added", Toast.LENGTH_SHORT).show();
                    teamsportlayout.setErrorEnabled(false);
                    teamidlayout.setErrorEnabled(false);
                    teamid.setText("");
                    teamname.setText("");
                    teamstadium.setText("");
                    teamcity.setText("");
                    teamcountry.setText("");
                    teamfoundationdate.setText("");
                    teamsportid.setText("");
                }catch(Exception e){
                    List<Sports> sports = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();
                    int sportid = 0;
                    for (Sports s : sports){
                        sportid = s.getId();
                        if (var_teamsportid != sportid ) {
                            teamsportlayout.setError("Sport id was not found");
                            break;
                        }
                    }
                    List<Teams> teams = FirstActivity.roomDbBuilder.myDaoAdmin().getTeams();
                    int teamid = 0;
                    for (Teams t : teams){
                        teamid = t.getTeam_id();
                        if (var_teamid == teamid ){
                            teamidlayout.setError("Team id already exists");
                            break;
                        }
                    }
                }
            }
        }



    }
}