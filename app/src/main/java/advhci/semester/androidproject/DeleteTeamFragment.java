package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class DeleteTeamFragment extends Fragment {



    public DeleteTeamFragment() {
        // Required empty public constructor
    }

    TextView teamidview, teamsportidview;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_team, container, false);

        teamidview = view.findViewById(R.id.deleteTeamid);
        teamsportidview = view.findViewById(R.id.deleteTeamSportid);
        bn = view.findViewById(R.id.deleteTeambutton);
        bn.setOnClickListener(this::deleteteamfromroomapi);

        return view;
    }

    private void deleteteamfromroomapi(View view) {
        int var_id = 0;
        int var_sport_id = 0;
        try {
            var_id = Integer.parseInt(teamidview.getText().toString());
            var_sport_id = Integer.parseInt(teamsportidview.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), "Complete both fields to delete a team", Toast.LENGTH_SHORT).show();
            return;
        }
        try{
            Teams team = new Teams();
            team.setTeam_id(var_id);
            team.setSport_id(var_sport_id);
            FirstActivity.roomDbBuilder.myDaoAdmin().deleteTeam(team);
            Toast.makeText(getActivity(), "Removed team with id: "+var_id, Toast.LENGTH_SHORT).show();
            teamidview.setText("");
            teamsportidview.setText("");
        } catch (Exception e){}
    }
}