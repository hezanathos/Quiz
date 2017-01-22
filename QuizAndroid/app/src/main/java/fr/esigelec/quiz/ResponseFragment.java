package fr.esigelec.quiz;

import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
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
    private static final String ARG_userResponce = "userResponce";
    private static final String ARG_timeRemaining = "timeRemaining";
    private static final String ARG_reponse= "reponse";

    private int mStatus;
    private int mUserResponce;
    private JSONObject mProp1;
    private JSONObject mProp2;
    private JSONObject mProp3;
    private JSONObject mProp4;
    private JSONObject mReponse;
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
    private boolean vide = true ;

    private CountDownTimer cdt;


    public ResponseFragment() {
        // Required empty public constructor
    }

    /**
     *
     * @param status the status we are in
     * @param s the JSON string which contain data
     * @return a new instance of ResponseFragment with the arguments
     */
    public static ResponseFragment newInstance(int status,String s) {
        return newInstance(status,s,-1);
    }

    /**
     *
     * @param status the status we are in
     * @param s the JSON string which contain data
     * @param userResponce id of the responce send by the user
     * @return a new instance of ResponseFragment with the arguments
     */
    public static ResponseFragment newInstance(int status,String s,int userResponce) {
        ResponseFragment fragment = new ResponseFragment();
        Bundle args = new Bundle();
        try {
            JSONObject message = new JSONObject(s);
            JSONArray propositions = message.getJSONArray("propositions");
            args.putInt(ARG_status, status);
            args.putString(ARG_prop1, propositions.getJSONObject(0).toString());
            args.putString(ARG_prop2, propositions.getJSONObject(1).toString());
            args.putString(ARG_prop3, propositions.getJSONObject(2).toString());
            args.putString(ARG_prop4, propositions.getJSONObject(3).toString());
            if (status == 0){
                args.putLong(ARG_timeRemaining, message.getLong("timeRemaining"));
            }
            if (status == 1){
                args.putInt(ARG_userResponce, userResponce);
            }
            if(status >= 2){
                args.putString(ARG_reponse, message.getJSONObject("reponse").toString());
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
            vide = false;
            //we set the variable that will be used in this status
            mStatus = getArguments().getInt(ARG_status);
            try {
                mProp1 = new JSONObject(getArguments().getString(ARG_prop1));
                mProp2 = new JSONObject(getArguments().getString(ARG_prop2));
                mProp3 = new JSONObject(getArguments().getString(ARG_prop3));
                mProp4 = new JSONObject(getArguments().getString(ARG_prop4));
                if (mStatus == 0) {
                    mTimeRemaining = getArguments().getLong(ARG_timeRemaining);
                }
                if (mStatus == 1) {
                    mUserResponce = getArguments().getInt(ARG_userResponce);
                }
                if (mStatus >= 2) {
                    mReponse = new JSONObject(getArguments().getString(ARG_reponse));
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onPause() {
        if(cdt != null){
            cdt.cancel();
        }
        super.onPause();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_response, container, false);
        if(!vide) {
            try {
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
                    txt1.setText(mProp1.getInt("stat") + "%");
                    txt2.setText(mProp2.getInt("stat") + "%");
                    txt3.setText(mProp3.getInt("stat") + "%");
                    txt4.setText(mProp4.getInt("stat") + "%");
                    txtGrpTop.setVisibility(View.VISIBLE);
                    txtGrpBot.setVisibility(View.VISIBLE);
                }

                btn1 = (Button) v.findViewById(R.id.btn1);
                if(mUserResponce == mProp1.getInt("id")){
                    btn1.setBackgroundColor(Color.parseColor("#4444AA"));
                }
                btn2 = (Button) v.findViewById(R.id.btn2);
                if(mUserResponce == mProp2.getInt("id")){
                    btn2.setBackgroundColor(Color.parseColor("#4444AA"));
                }
                btn3 = (Button) v.findViewById(R.id.btn3);
                if(mUserResponce == mProp3.getInt("id")){
                    btn3.setBackgroundColor(Color.parseColor("#4444AA"));
                }
                btn4 = (Button) v.findViewById(R.id.btn4);
                if(mUserResponce == mProp4.getInt("id")){
                    btn4.setBackgroundColor(Color.parseColor("#4444AA"));
                }
                // we set the 2 first proposition and hide the 2 next in case we only have 2 proposition
                String propLibele = mProp1.getString("libelle");
                btn1.setText(propLibele);
                propLibele = mProp2.getString("libelle");
                btn2.setText(propLibele);
                propLibele = mProp3.getString("libelle");
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                btnGrpBot.setVisibility(View.INVISIBLE);
                if (!propLibele.equals("")) {
                    // if we have 3 prop we enable and display the 3rd button but keep the  4th hidden
                    btn3.setText(propLibele);
                    btn4.setVisibility(View.INVISIBLE);
                    btn3.setEnabled(true);
                    btn4.setEnabled(false);
                    btnGrpBot.setVisibility(View.VISIBLE);
                }
                propLibele = mProp4.getString("libelle");
                if (!propLibele.equals("")) {
                    // we have all 4 prop so we display all button
                    btn4.setText(propLibele);
                    btn4.setEnabled(true);
                    btn4.setVisibility(View.VISIBLE);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            // we set the OnClickListener
            btn1.setOnClickListener(new CustomOnClickListener());
            btn2.setOnClickListener(new CustomOnClickListener());
            btn3.setOnClickListener(new CustomOnClickListener());
            btn4.setOnClickListener(new CustomOnClickListener());
            if (mStatus >= 2 ) {
                // if the status is 2 we display the good and bad responses
                try {
                    v.findViewById(R.id.classement_indicator).setVisibility(View.VISIBLE);
                    btn1.setBackgroundColor(mReponse.getInt("id") == mProp1.getInt("id") ? Color.parseColor("#7F05b31c") : Color.parseColor("#7FB32E05"));
                    btn2.setBackgroundColor(mReponse.getInt("id") == mProp2.getInt("id") ? Color.parseColor("#7F05b31c") : Color.parseColor("#7FB32E05"));
                    btn3.setBackgroundColor(mReponse.getInt("id") == mProp3.getInt("id") ? Color.parseColor("#7F05b31c") : Color.parseColor("#7FB32E05"));
                    btn4.setBackgroundColor(mReponse.getInt("id") == mProp4.getInt("id") ? Color.parseColor("#7F05b31c") : Color.parseColor("#7FB32E05"));
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
            if (mStatus == 3){
                Button homeButton = (Button) v.findViewById(R.id.home_button);
                homeButton.setVisibility(View.VISIBLE);
                homeButton.setOnClickListener(new CustomOnClickListener());
            }
            time = (ProgressBar) v.findViewById(R.id.time);
            if (mStatus == 0) {
                long countdownTime = 30000 - (30000 - mTimeRemaining);
                cdt = new CountDownTimer(countdownTime, 300) {

                    public void onTick(long millisUntilFinished) {
                        //we make the progress bar to progress
                        time.setProgress((int) ((30000 - millisUntilFinished) * 100 / 30000 ));
                    }

                    public void onFinish() {
                        //when the timer is finished we disable the buttons
                        time.setProgress(100);
                        btn1.setEnabled(false);
                        btn2.setEnabled(false);
                        btn3.setEnabled(false);
                        btn4.setEnabled(false);
                    }
                }.start();
            } else {
                // initialise the fragment in readOnly
                btn1.setEnabled(false);
                btn2.setEnabled(false);
                btn3.setEnabled(false);
                btn4.setEnabled(false);
                time.setProgress(100);
            }
        }else{
            v.findViewById(R.id.resp_layout).setVisibility(View.GONE);
            v.findViewById(R.id.resp_progress).setVisibility(View.VISIBLE);
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
             try {
                 switch (v.getId()) {
                     case R.id.btn1:
                         send(mProp1.getInt("id"));
                         btn1.setBackgroundColor(Color.parseColor("#4444AA"));
                         break;
                     case R.id.btn2:
                         send(mProp2.getInt("id"));
                         btn2.setBackgroundColor(Color.parseColor("#4444AA"));
                         break;
                     case R.id.btn3:
                         send(mProp3.getInt("id"));
                         btn3.setBackgroundColor(Color.parseColor("#4444AA"));
                         break;
                     case R.id.btn4:
                         send(mProp4.getInt("id"));
                         btn4.setBackgroundColor(Color.parseColor("#4444AA"));
                         break;
                     case R.id.home_button:
                         getActivity().finish();
                         break;
                     default:
                         break;
                 }
                 btn1.setEnabled(false);
                 btn2.setEnabled(false);
                 btn3.setEnabled(false);
                 btn4.setEnabled(false);
             }catch (JSONException e){
                 e.printStackTrace();
             }
         }

        /**
         * @param idProp id of the proposition selected by the user
         * shortener for the method WebSocketSendReponce
         */
         private void send(int idProp){
             ((QuizActivity)getActivity()).WebSocketSendReponce(idProp);
         }
     }
}
