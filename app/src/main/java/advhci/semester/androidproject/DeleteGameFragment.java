package advhci.semester.androidproject;

import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.Task;


public class DeleteGameFragment extends Fragment {



    public DeleteGameFragment() {
        // Required empty public constructor
    }

    Button bn;
    TextView gameid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_game, container, false);

        gameid = view.findViewById(R.id.deleteGameId);

        bn = view.findViewById(R.id.deleteGameButton);
        bn.setOnClickListener(this::deleteGameFromFireBase);

        return view;
    }

    private void deleteGameFromFireBase(View view) {
        int var_gameid = 0;

        try{
            var_gameid = Integer.parseInt(gameid.getText().toString());

        } catch (Exception e){}

        if (var_gameid != 0) {
            try {
                Games game = new Games();
                game.setGid(var_gameid);
                FirstActivity.firedb.collection("Games").document("" + var_gameid).delete().addOnCompleteListener(this::Completed);
            } catch (Exception e) {
            }
        }else
            Toast.makeText(getContext(), "Game ID can't be null or zero", Toast.LENGTH_SHORT).show();
    }

    private void Completed(Task<Void> voidTask) {
        Toast.makeText(getActivity(), "Game deleted", Toast.LENGTH_SHORT).show();
        gameid.setText(null);
    }
}