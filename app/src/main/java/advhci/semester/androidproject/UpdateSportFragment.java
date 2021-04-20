package advhci.semester.androidproject;

import android.content.Intent;
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

import java.util.List;


public class UpdateSportFragment extends Fragment {

    EditText editText1;
    Button upbutton;
    Spinner spinnerGender;
    Spinner spinnerType;
    Spinner spinnerSportName;
    ArrayAdapter<CharSequence> adapterName;
    ArrayAdapter<CharSequence> adapterType;
    ArrayAdapter<CharSequence> adapterGender;

    public UpdateSportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update_sport, container, false);

        spinnerType = view.findViewById(R.id.updateSportTypeSpinner);
        adapterType = ArrayAdapter.createFromResource(getContext(), R.array.sporttype, R.layout.support_simple_spinner_dropdown_item);
        adapterType.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerType.setAdapter(adapterType);

        spinnerGender = view.findViewById(R.id.updateSportGenderSpinner);
        adapterGender = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapterGender.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapterGender);

        spinnerSportName = view.findViewById(R.id.updateSportnameSpinner);
        adapterName = ArrayAdapter.createFromResource(getContext(), R.array.sportnames, R.layout.support_simple_spinner_dropdown_item);
        adapterName.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerSportName.setAdapter(adapterName);

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

        try {
            Sports sport = new Sports();
            sport.setId(var_sportid);
            sport.setName(var_sportname);
            sport.setType(var_sportype);
            sport.setGender(var_sportgender);
            FirstActivity.roomDbBuilder.myDaoAdmin().updateSport(sport);
            Toast.makeText(getActivity(), "One record updated!", Toast.LENGTH_LONG).show();
        } catch (Exception e){
            Toast.makeText(getActivity(), ""+e, Toast.LENGTH_LONG).show();
        }
        editText1.setText("");
    }

    @Override
    public void onResume() {
        super.onResume();
        editText1.setOnKeyListener(this::liveupdates);
    }

    private boolean liveupdates(View view, int i, KeyEvent keyEvent) {
        int checksportid = 0;
        int sportid = 0;
        String sportname = "";
        String sportgender = "";
        String sporttype = "";

        List<Sports> sports = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();

        try{
            checksportid = Integer.parseInt(editText1.getText().toString());
        }catch (NumberFormatException e){}

        for (Sports sp : sports){
            if (sp.getId() == checksportid){
                sportid = sp.getId();
                sportname = sp.getName();
                sportgender = sp.getGender();
                sporttype = sp.getType();
            }
        }


        spinnerSportName.setSelection(adapterName.getPosition(sportname));
        spinnerGender.setSelection(adapterGender.getPosition(sportgender));
        spinnerType.setSelection(adapterType.getPosition(sporttype));

        return false;
    }


}