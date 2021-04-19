package advhci.semester.androidproject;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;


public class queries extends Fragment {

    public queries() {
        // Required empty public constructor
    }

    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    TextView textView;
    Button bn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_queries, container, false);


        spinner = view.findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(getContext(), R.array.queries_array, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        textView = view.findViewById(R.id.textViewQueryResult);
        bn = view.findViewById(R.id.buttonSearch);
        bn.setOnClickListener(this::results);

        return view;
    }

    private void results(View view) {
        List<Sports> sports = FirstActivity.roomDbBuilder.myDaoAdmin().getSports();
        String result ="";
        for (Sports sport: sports) {
            int code = sport.getId();
            String name = sport.getName();
            String type = sport.getType();
            String gender = sport.getGender();
            result = result + "\nId: " + code + "\nName: " + name + "\nType: " + type +  "\nSport gender: "+gender + "\n";
        }
        textView.setText(result);
    }



}