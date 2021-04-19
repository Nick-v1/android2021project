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


public class InsertSportFragment extends Fragment {

    EditText editText1, editText2;
    Button submitButton;
    Spinner spinnerType;
    Spinner spinnerGender;
    Spinner spinnerSportname;
    ArrayAdapter<CharSequence> adapter;

    public InsertSportFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_sport, container, false);

        final String[] queryArray = getResources().getStringArray(R.array.sporttype);

        spinnerType = view.findViewById(R.id.insertSportSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapter);

        spinnerGender = view.findViewById(R.id.insertGenderSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        spinnerSportname = view.findViewById(R.id.insertSportnameSpinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportname.setAdapter(adapter);

        editText1 = view.findViewById(R.id.textInputEditText1AddSport);

        submitButton = view.findViewById(R.id.submitsport);
        submitButton.setOnClickListener(this::addToDb);

        return view;
    }

    private void addToDb(View view) {
        int temp_var_sportid = 0;
        try {
            temp_var_sportid = Integer.parseInt(editText1.getText().toString());
        } catch (Exception e){
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_SHORT).show();
        }
        int var_sportid = temp_var_sportid;
        String Var_Name = spinnerSportname.getSelectedItem().toString();
        String Var_Type = spinnerType.getSelectedItem().toString();
        String Var_Gender = spinnerGender.getSelectedItem().toString();

        try {
            Sports sport = new Sports();
            sport.setId(var_sportid);
            sport.setName(Var_Name);
            sport.setType(Var_Type);
            sport.setGender(Var_Gender);
            FirstActivity.roomAPIdatabase.myDaoAdmin().addSport(sport);
            Toast.makeText(getActivity(), "Added Sport", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(getActivity(), "Taken Sport ID", Toast.LENGTH_SHORT).show();
        }
        editText1.setText("");

    }


}