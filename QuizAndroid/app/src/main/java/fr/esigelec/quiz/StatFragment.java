package fr.esigelec.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

/**
 * Created by gpillet on 06/01/2017.
 *
 * The fragment displaying the ranking
 */

public class StatFragment extends Fragment {
    private static final String ARG_classement = "classement";

    private ArrayList<ClassementRow> mClassement;

    private ListView classement;

    public StatFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param jsonArrayString the JSONArray which contain the ranking
     * @return a new instance of StatFragment with the arguments
     */
    public static StatFragment newInstance(String jsonArrayString) {
        StatFragment fragment = new StatFragment();
        Bundle args = new Bundle();
        args.putString(ARG_classement, jsonArrayString);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            try {
                // create the arraylist of ranking row from the JSONArray string get when we create a new instance of this Fragment
                JSONArray array = new JSONArray(getArguments().getString(ARG_classement));
                mClassement = new ArrayList<>();
                for (int i = 0;i<array.length();i++) {
                    mClassement.add(new ClassementRow(array.getJSONObject(i)));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_stat, container, false);

        // Create and populate the ListView
        classement = (ListView) v.findViewById(R.id.classement);
        ClassementAdapter adapter = new ClassementAdapter(getActivity(),mClassement);
        classement.setAdapter(adapter);

        return v;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
