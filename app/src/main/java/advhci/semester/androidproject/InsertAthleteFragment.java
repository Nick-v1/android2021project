package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class InsertAthleteFragment extends Fragment {


    public InsertAthleteFragment() {
        // Required empty public constructor
    }

    Spinner spinnerGender;
    ArrayAdapter<CharSequence> adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_insert_athlete, container, false);

        spinnerGender = view.findViewById(R.id.insertAthleteGender);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.gender, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinnerGender.setAdapter(adapter);

        return view;
    }
}