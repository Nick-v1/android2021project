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

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class UpdateGameFragment extends Fragment {



    public UpdateGameFragment() {
        // Required empty public constructor
    }

    Spinner spinnerType;
    Spinner spinnerSportname;
    ArrayAdapter<CharSequence> adapterType;
    ArrayAdapter<CharSequence> adapterName;
    Button bn;
    EditText gamedate, gamecity, gamecountry, gameidTextView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_game, container, false);

        spinnerType = view.findViewById(R.id.spinnerUpdateGameType);
        adapterType = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adapterType.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        spinnerSportname = view.findViewById(R.id.spinnerUpdateGameName);
        adapterName = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapterName.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportname.setAdapter(adapterName);

        gameidTextView = view.findViewById(R.id.updateGameId);
        gamedate = view.findViewById(R.id.updateGameDate);
        gamecity = view.findViewById(R.id.updateGameCity);
        gamecountry = view.findViewById(R.id.updateGameCountry);

        bn = view.findViewById(R.id.updategamebutton);
        bn.setOnClickListener(this::updateGameInFireBase);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        gameidTextView.setOnKeyListener(this::updatefields);
    }

    private boolean updatefields(View view, int i, KeyEvent keyEvent) {
        CollectionReference collectionReference;
        collectionReference = FirstActivity.firedb.collection("Games");
        collectionReference.get().addOnSuccessListener(this::getgameids);

        return false;
    }

    private void getgameids(QuerySnapshot queryDocumentSnapshots) {

        int checkingid = 0;
        try {
            checkingid = Integer.parseInt(gameidTextView.getText().toString());
        } catch (NumberFormatException e){}

        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
            Games game = documentSnapshot.toObject(Games.class);

            Integer gameid = game.getGid();
            String dateofmatch = game.getDate_of_match();
            String gsportname = game.getGsport_name();
            String gsporttype = game.getGsport_type();
            String gcity = game.getGcity();
            String gcountry = game.getGcountry();

            if (checkingid == gameid){
                gamedate.setText(dateofmatch);
                gamecity.setText(gcity);
                gamecountry.setText(gcountry);
                spinnerSportname.setSelection(adapterName.getPosition(""+gsportname));
                spinnerType.setSelection(adapterType.getPosition(""+gsporttype));
            }

        }

    }

    private void updateGameInFireBase(View view) {
        int var_gameid = 0;

        try{
            var_gameid = Integer.parseInt(gameidTextView.getText().toString());
        } catch (NumberFormatException e){}

        String vardate = gamedate.getText().toString();
        String varcity = gamecity.getText().toString();
        String varcountry = gamecountry.getText().toString();
        String varsportname = spinnerSportname.getSelectedItem().toString();
        String varsporttype = spinnerType.getSelectedItem().toString();


        try{
            Games game = new Games();
            game.setGid(var_gameid);
            game.setDate_of_match(vardate);
            game.setGcity(varcity);
            game.setGcountry(varcountry);
            game.setGsport_name(varsportname);
            game.setGsport_type(varsporttype);

            FirstActivity.firedb.collection("Games")
                    .document(""+var_gameid).set(game).addOnSuccessListener(this::successfull);

        } catch (Exception e){}
    }

    private void successfull(Void aVoid) {
        gamedate.setText(""); gamecity.setText("");gamecountry.setText("");
        gameidTextView.setText("");
        Toast.makeText(getActivity(), "Successfully updated", Toast.LENGTH_SHORT).show();
    }
}