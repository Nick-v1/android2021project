package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class DeleteSportFragment extends Fragment {


    public DeleteSportFragment() {
        // Required empty public constructor
    }

    EditText deleteid;
    Button deletebutton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delete_sport, container, false);

        deleteid = view.findViewById(R.id.deletesportid);
        deletebutton = view.findViewById(R.id.deletesportbutton);
        deletebutton.setOnClickListener(this::removefromdb);
        return view;
    }

    private void removefromdb(View view) {

        int var_sportid = 0;
        try {
            var_sportid = Integer.parseInt(deleteid.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), "Select a sport id to delete", Toast.LENGTH_SHORT).show();
            return;
        }

            Sports sport = new Sports();
            sport.setId(var_sportid);
            FirstActivity.roomAPIdatabase.myDaoAdmin().deleteSport(sport);
            Toast.makeText(getActivity(), "Sport deleted", Toast.LENGTH_SHORT).show();
            deleteid.setText(null);

    }
}