package fr.esigelec.quiz;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ResponseFragment extends Fragment {
    private static final String ARG_status = "status";
    private static final String ARG_prop1 = "prop1";
    private static final String ARG_prop2 = "prop2";
    private static final String ARG_prop3 = "prop3";
    private static final String ARG_prop4 = "prop3";
    private static final String ARG_timeRemaining = "timeRemaining";
    private static final String ARG_timeTotal = "timeTotal";

    private int mStatus;


    public ResponseFragment() {
        // Required empty public constructor
    }

    public static ResponseFragment newInstance(int status) {
        ResponseFragment fragment = new ResponseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_status, status);
        fragment.setArguments(args);
        return fragment;
    }

    public static ResponseFragment newInstance(int status,String prop1,String prop2,String prop3,String prop4,long time,long timeTotal) {
        ResponseFragment fragment = new ResponseFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_status, status);
        args.putString(ARG_prop1, prop1);
        args.putString(ARG_prop2, prop2);
        args.putString(ARG_prop3, prop3);
        args.putString(ARG_prop4, prop4);
        args.putLong(ARG_timeRemaining, time);
        args.putLong(ARG_timeTotal, timeTotal);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mStatus = getArguments().getInt(ARG_status);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_response, container, false);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
