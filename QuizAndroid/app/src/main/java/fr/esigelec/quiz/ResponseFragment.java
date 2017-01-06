package fr.esigelec.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by gpillet on 06/01/2017.
 *
 * Fragment managing the response part
 */

public class ResponseFragment extends Fragment {
    private static final String ARG_status = "status";
    private static final String ARG_prop1 = "prop1";
    private static final String ARG_prop2 = "prop2";
    private static final String ARG_prop3 = "prop3";
    private static final String ARG_prop4 = "prop4";
    private static final String ARG_stat1 = "stat1";
    private static final String ARG_stat2 = "stat2";
    private static final String ARG_stat3 = "stat3";
    private static final String ARG_stat4 = "stat4";
    private static final String ARG_timeRemaining = "timeRemaining";
    private static final String ARG_reponse= "reponse";

    private int mStatus;
    private int mStat1;
    private int mStat2;
    private int mStat3;
    private int mStat4;
    private String mProp1;
    private String mProp2;
    private String mProp3;
    private String mProp4;
    private String mReponse;
    private long mTimeRemaining;

    private LinearLayout btnGrpTop;
    private LinearLayout btnGrpBot;
    private LinearLayout txtGrpTop;
    private LinearLayout txtGrpBot;
    private TextView txt1;
    private TextView txt2;
    private TextView txt3;
    private TextView txt4;
    private Button btn1;
    private Button btn2;
    private Button btn3;
    private Button btn4;
    private ProgressBar time;
    private boolean vide =false ;


    public ResponseFragment() {
        // Required empty public constructor
        vide = true;
    }

    /**
     *
     * @param status the status we are in
     * @param s the JSON string which contain data
     * @return a new instance of ResponseFragment with the arguments
     */
    public static ResponseFragment newInstance(int status,String s) {
        ResponseFragment fragment = new ResponseFragment();
        Bundle args = new Bundle();
        try {
            JSONObject message = new JSONObject(s);
            args.putInt(ARG_status, status);
            args.putString(ARG_prop1, message.getString("prop1"));
            args.putString(ARG_prop1, message.getString("prop2"));
            args.putString(ARG_prop1, message.getString("prop3"));
            args.putString(ARG_prop1, message.getString("prop4"));
            if (status == 0){
                args.putLong(ARG_timeRemaining, message.getLong("timeRemaining"));
            }else{
                args.putInt(ARG_stat1, message.getInt("stat1"));
                args.putInt(ARG_stat2, message.getInt("stat2"));
                args.putInt(ARG_stat3, message.getInt("stat3"));
                args.putInt(ARG_stat4, message.getInt("stat4"));
            }
            if(status == 2){
                args.putString(ARG_reponse, message.getString("reponse"));
            }
            fragment.setArguments(args);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //we set the variable that will be used in this status
            mStatus = getArguments().getInt(ARG_status);
            mProp1 = getArguments().getString(ARG_prop1);
            mProp2 = getArguments().getString(ARG_prop2);
            mProp3 = getArguments().getString(ARG_prop3);
            mProp4 = getArguments().getString(ARG_prop4);
            if(mStatus == 0) {
                mTimeRemaining = getArguments().getLong(ARG_timeRemaining);
            }else{
                mStat1 = getArguments().getInt(ARG_stat1);
                mStat2 = getArguments().getInt(ARG_stat2);
                mStat3 = getArguments().getInt(ARG_stat3);
                mStat4 = getArguments().getInt(ARG_stat4);
            }
            if(mStatus == 2){
                mReponse = getArguments().getString(ARG_reponse);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_response, container, false);
        if(!vide) {
            btnGrpTop = (LinearLayout) v.findViewById(R.id.btnGrpTop);
            btnGrpBot = (LinearLayout) v.findViewById(R.id.btnGrpBot);
            txtGrpTop = (LinearLayout) v.findViewById(R.id.txtGrpTop);
            txtGrpBot = (LinearLayout) v.findViewById(R.id.txtGrpBot);

            txt1 = (TextView) v.findViewById(R.id.txt1);
            txt2 = (TextView) v.findViewById(R.id.txt2);
            txt3 = (TextView) v.findViewById(R.id.txt3);
            txt4 = (TextView) v.findViewById(R.id.txt4);
            if (mStatus != 0) {
                //if we are not in the "play" time we display the stats
                txt1.setText(mStat1 + "");
                txt2.setText(mStat2 + "");
                txt3.setText(mStat3 + "");
                txt4.setText(mStat4 + "");
                txtGrpTop.setVisibility(View.VISIBLE);
                txtGrpBot.setVisibility(View.VISIBLE);
            }

            btn1 = (Button) v.findViewById(R.id.btn1);
            btn2 = (Button) v.findViewById(R.id.btn2);
            btn3 = (Button) v.findViewById(R.id.btn3);
            btn4 = (Button) v.findViewById(R.id.btn4);
            // we set the 2 first proposition and hide the 2 next in case we only have 2 proposition
            btn1.setText(mProp1);
            btn2.setText(mProp2);
            btnGrpBot.setVisibility(View.INVISIBLE);
            btnGrpBot.setEnabled(false);
            if (!mProp3.equals("")) {
                // if we have 3 prop we enable and display the 3rd button but keep the  4th hidden
                btn3.setText(mProp3);
                btn4.setVisibility(View.INVISIBLE);
                btn4.setEnabled(false);
                btnGrpBot.setVisibility(View.VISIBLE);
                btnGrpBot.setEnabled(true);
            }
            if (!mProp4.equals("")) {
                // we have all 4 prop so we display all button
                btn4.setText(mProp4);
                btn4.setEnabled(true);
                btn4.setVisibility(View.VISIBLE);
            }
            // we set the OnClickListener
            btn1.setOnClickListener(new CustomOnClickListener());
            btn2.setOnClickListener(new CustomOnClickListener());
            btn3.setOnClickListener(new CustomOnClickListener());
            btn4.setOnClickListener(new CustomOnClickListener());
            if (mStatus == 2) {
                // if the status is 2 we display the good and bad responses
                btn1.setBackgroundColor(mReponse.equals("prop1") ? Color.parseColor("#05b31c") : Color.parseColor("#B32E05"));
                btn2.setBackgroundColor(mReponse.equals("prop2") ? Color.parseColor("#05b31c") : Color.parseColor("#B32E05"));
                btn3.setBackgroundColor(mReponse.equals("prop3") ? Color.parseColor("#05b31c") : Color.parseColor("#B32E05"));
                btn4.setBackgroundColor(mReponse.equals("prop4") ? Color.parseColor("#05b31c") : Color.parseColor("#B32E05"));
            }
            time = (ProgressBar) v.findViewById(R.id.time);
            if (mStatus == 0) {
                long countdownTime = 30000 - mTimeRemaining;
                new CountDownTimer(countdownTime, 300) {

                    public void onTick(long millisUntilFinished) {
                        //we make the progress bar to progress
                        time.setProgress((int) ((30000 - millisUntilFinished) / 30000 * 100));
                    }

                    public void onFinish() {
                        //when the timer is finished we disable the buttons
                        btnGrpTop.setEnabled(false);
                        btnGrpBot.setEnabled(false);
                    /*
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                    */
                    }
                }.start();
            } else {
                // initialise the fragment in readOnly
                btnGrpTop.setEnabled(false);
                btnGrpBot.setEnabled(false);
                time.setProgress(100);
            }
        }
        return v;
    }

    /**
     * OnClickListener used to manage the button action, it send to the server which proposition
     * the user as choose and disable all the button
     */
     class CustomOnClickListener implements View.OnClickListener{

         @Override
         public void onClick(View v) {
             switch(v.getId()){
                 case R.id.btn1:
                     send("prop1");
                     break;
                 case R.id.btn2:
                     send("prop2");
                     break;
                 case R.id.btn3:
                     send("prop3");
                     break;
                 case R.id.btn4:
                     send("prop4");
                     break;
                 default:
                     break;
             }
             btnGrpTop.setEnabled(false);
             btnGrpBot.setEnabled(false);
         }

         //shortener for the method WebSocketSendText
         private void send(String s){
             ((QuizActivity)getActivity()).WebSocketSendText(s);
         }
     }
}
