package fr.esigelec.quiz;

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

public class QuizActivity extends AppCompatActivity {

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
        connectWebSocket();
        setContentView(R.layout.activity_quiz);
        List<Fragment> fList = new ArrayList<>();
        fList.add(new ResponseFragment());
        qNumber = (TextView) findViewById(R.id.qNumber);
        qQuestion = (TextView) findViewById(R.id.qQuestion);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager(), fList);
        mPager.setAdapter(mPagerAdapter);
    }

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

    WebSocketClient mWebSocketClient;

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://websockethost:8080");
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
                            fList.add(ResponseFragment.newInstance(status,message.getString("prop1")
                                    ,message.getString("prop2"),message.getString("prop3")
                                    ,message.getString("prop4"),message.getLong("timeRemaining")
                                    ,message.getLong("timeTotal")));
                            aNumber = message.getString("number");
                            aQuestion = message.getString("question");
                            break;
                        case 1:
                            fList.add(ResponseFragment.newInstance(status));
                            break;
                        case 2:
                            fList.add(ResponseFragment.newInstance(status));
                            fList.add(new StatFragment());
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
}
