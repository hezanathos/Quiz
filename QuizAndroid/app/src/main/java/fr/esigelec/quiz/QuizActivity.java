package fr.esigelec.quiz;

import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Build;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gpillet on 06/01/2017.
 *
 * Main activity for the quiz, managing fragments and the websocket
 */
public class QuizActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "Option";

    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private TextView qNumber;
    private TextView qQuestion;
    private String aNumber;
    private String aQuestion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_quiz);
        // connect to the WebSocket
        connectWebSocket();
        // Initialise the fragment list for the pager with a default fragment while we wait
        // for the server to send us data
        List<Fragment> fList = new ArrayList<>();
        fList.add(new ResponseFragment());
        fList.add(StatFragment.newInstance("[{position:1,nom:'tortuga',points:10},{position:2,nom:'poulopoulopoulo',points:5}]"));

        // find the View objects
        qNumber = (TextView) findViewById(R.id.qNumber);
        qQuestion = (TextView) findViewById(R.id.qQuestion);
        mPager = (ViewPager) findViewById(R.id.pager);

        // set the pager
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fList);
        mPager.setAdapter(mPagerAdapter);
    }

    // Custom Pager adapter
    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        List<Fragment> fList;
        public ScreenSlidePagerAdapter(FragmentManager fm,List<Fragment> fList) {
            super(fm);
            this.fList = fList;
        }

        @Override
        public Fragment getItem(int position) {
            return fList.get(position);
        }

        @Override
        public int getCount() {
            return fList.size();
        }
    }

    private WebSocketClient mWebSocketClient;

    // Method to connect to the websocket and define the action listener
    private void connectWebSocket() {
        URI uri;
        try {
            //we get the server address from the SharedPreferences or use default value
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            String webSocketAdress = settings.getString("serverAdress","srvinfodev.esigelec.fr:8080/quiz");
            uri = new URI("ws://"+webSocketAdress);
        } catch (URISyntaxException e) {
            e.printStackTrace();
            return;
        }

        mWebSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake serverHandshake) {
                Log.i("Websocket", "Opened");
                mWebSocketClient.send("Hello from " + Build.MANUFACTURER + " " + Build.MODEL);
            }

            @Override
            public void onMessage(String s) {
                try{
                    final JSONObject message = new JSONObject(s);
                    final List<Fragment> fList = new ArrayList<>();
                    final int status = message.getInt("status");
                    switch(status) {
                        case 0:
                            fList.add(ResponseFragment.newInstance(status,s));
                            aNumber = message.getString("numero");
                            aQuestion = message.getString("question");
                            break;
                        case 1:
                            fList.add(ResponseFragment.newInstance(status,s));
                            break;
                        case 2:
                            fList.add(ResponseFragment.newInstance(status,s));
                            fList.add(StatFragment.newInstance(message.getJSONArray("classement").toString()));
                            break;
                        default:
                            break;
                    }

                    final String number = aNumber;
                    final String question = aQuestion;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (status == 0){
                                qNumber.setText(number);
                                qQuestion.setText(question);
                            }
                            mPager = (ViewPager) findViewById(R.id.pager);
                            mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fList);
                            mPager.setAdapter(mPagerAdapter);
                        }
                    });
                }catch(JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onClose(int i, String s, boolean b) {
                Log.i("Websocket", "Closed " + s);
            }

            @Override
            public void onError(Exception e) {
                Log.i("Websocket", "Error " + e.getMessage());
            }
        };
        mWebSocketClient.connect();
    }

    // public method to send text via WebSocket
    public void WebSocketSendText(String s){
        mWebSocketClient.send(s);
    }
}
