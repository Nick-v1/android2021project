package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class UpdateSportFragment extends Fragment {

    EditText editText1,editText2;
    Button upbutton;
    Spinner spinnerGender;
    Spinner spinnerType;
    Spinner spinnerSportName;
    ArrayAdapter<CharSequence> adapter;

    public UpdateSportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_sport, container, false);

        spinnerType = view.findViewById(R.id.updateSportTypeSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        spinnerGender = view.findViewById(R.id.updateSportGenderSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        spinnerSportName = view.findViewById(R.id.updateSportnameSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportName.setAdapter(adapter);

        editText1 = view.findViewById(R.id.sportIDupdate);

        upbutton = view.findViewById(R.id.sportUPDATEBUTTON);
        upbutton.setOnClickListener(this::onClick);
        return view;
    }

    private void onClick(View view) {
        int var_sportid = 0;
        try{
            var_sportid = Integer.parseInt(editText1.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), "Select an id", Toast.LENGTH_SHORT).show();
        }
        String var_sportname = spinnerSportName.getSelectedItem().toString();
        String var_sportype = spinnerType.getSelectedItem().toString();
        String var_sportgender = spinnerGender.getSelectedItem().toString();

        Sports sport = new Sports();
        sport.setId(var_sportid);
        sport.setName(var_sportname);
        sport.setType(var_sportype);
        sport.setGender(var_sportgender);
        FirstActivity.roomAPIdatabase.myDaoAdmin().updateSport(sport);
        Toast.makeText(getActivity(), "One record updated!", Toast.LENGTH_LONG).show();

        editText1.setText("");


    }
}