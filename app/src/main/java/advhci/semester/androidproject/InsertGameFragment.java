package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;


public class InsertGameFragment extends Fragment {


    public InsertGameFragment() {
        // Required empty public constructor
    }

    Spinner spinnerType;
    Spinner spinnerSportname;
    ArrayAdapter<CharSequence> adapter;
    Button bn;
    EditText gamedate, gamecity, gamecountry, gameid;
    TextInputLayout textInputLayout1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_game, container, false);

        spinnerType = view.findViewById(R.id.spinnerInsertGameType);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        spinnerSportname = view.findViewById(R.id.spinnerInsertGameName);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportname.setAdapter(adapter);

        gamedate = view.findViewById(R.id.insertGameDate);
        gamecity = view.findViewById(R.id.insertGameCity);
        gamecountry = view.findViewById(R.id.insertGameCountry);
        gameid = view.findViewById(R.id.insertGameid);

        bn = view.findViewById(R.id.insertgamebutton);
        bn.setOnClickListener(this::addGameToDB);

        textInputLayout1 = view.findViewById(R.id.gameidinputlayout);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        gameid.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                textInputLayout1.setError("Adding an existing id will just update the corresponding game. Be careful when adding a new game as it will overwrite the existing game");

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

        /**come back here after Teams class has added. **/

        try{
            Games game = new Games();
            game.setGid(var_gameid);
            game.setDate_of_match(vardate);
            game.setGcity(varcity);
            game.setGcountry(varcountry);
            game.setGsport_name(varsportname);
            game.setGsport_type(varsporttype);

            FirstActivity.firedb.collection("Games")
                    .document(""+var_gameid).set(game).addOnSuccessListener(this::onComplete);

        } catch (Exception e){}
    }

    private void onComplete(Void aVoid) {
        Toast.makeText(getActivity(), "Game added", Toast.LENGTH_SHORT).show();
    }

}