package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.List;


public class UpdateTeamFragment extends Fragment {



    public UpdateTeamFragment() {
        // Required empty public constructor
    }

    TextView teamid, teamname, teamstadium, teamcity, teamcountry, teamsportid, teamfoundationdate;
    TextInputLayout layout;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_team, container, false);

        teamid = view.findViewById(R.id.updateTeamid);
        teamname = view.findViewById(R.id.updateTeamName);
        teamstadium = view.findViewById(R.id.updateTeamStadium);
        teamcity = view.findViewById(R.id.updateTeamCity);
        teamcountry = view.findViewById(R.id.updateTeamCountry);
        teamsportid = view.findViewById(R.id.updateTeamSportId);
        teamfoundationdate = view.findViewById(R.id.updateTeamFoundationDate);
        layout = view.findViewById(R.id.updateTeamLayoutsportid);
        layout.setEnabled(false);
        bn = view.findViewById(R.id.updateTeamButton);
        bn.setOnClickListener(this::updateAteamRoomApi);

        return view;
    }

    private void updateAteamRoomApi(View view) {
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

        try{
            Teams team = new Teams();
            team.setTeam_id(var_teamid);
            team.setName(var_teamname);
            team.setStadium(var_teamstadium);
            team.setCity(var_teamcity);
            team.setCountry(var_teamccountry);
            team.setSport_id(var_teamsportid);
            team.setFoundation_date(var_teamfoundationdate);
            FirstActivity.roomDbBuilder.myDaoAdmin().updateTeam(team);
            Toast.makeText(getActivity(), "Team Updated", Toast.LENGTH_SHORT).show();
            teamid.setText("");
            teamname.setText("");
            teamstadium.setText("");
            teamcity.setText("");
            teamcountry.setText("");
            teamfoundationdate.setText("");
            teamsportid.setText("");
        }catch(Exception e){}
    }

    @Override
    public void onResume() {
        super.onResume();

        teamid.setOnKeyListener(this::updatefields);
    }

    private boolean updatefields(View view, int i, KeyEvent keyEvent) {

        List<Teams> team = FirstActivity.roomDbBuilder.myDaoAdmin().getTeams();
        int checkid = 0;
        int var_teamid = 0;
        try{
            var_teamid = Integer.parseInt(teamid.getText().toString());
        } catch (Exception e){}

        for (Teams t : team){
            if (var_teamid == t.getTeam_id()){
                teamname.setText(t.getName());
                teamstadium.setText(t.getStadium());
                teamcity.setText(t.getCity());
                teamcountry.setText(t.getCountry());
                teamfoundationdate.setText(t.getFoundation_date());
                String var_sportid = String.valueOf(t.getSport_id());
                teamsportid.setText(var_sportid);
            }
        }

        return false;
    }
}