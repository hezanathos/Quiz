package fr.esigelec.quiz;

/**
 * Created by Pierre on 06/01/2017.
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Paint;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.util.Linkify;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static fr.esigelec.quiz.LoginActivity.PREFS_NAME;

public class RegisterActivity extends Activity{

    private static final String DUMMY_CREDENTIALS = "user@test.com:hello";
    public static final String PREFS_NAME = "Option";

    private UserLoginTask userLoginTask = null;
    private View loginFormView;
    private View progressView;
    private AutoCompleteTextView emailTextView;
    private EditText passwordTextView;
    private TextView signUpTextView;
    private String serverAdress;
    private TextView nameTextView;
    private TextView fullnameTextView;
    private TextView optionTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);


        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        serverAdress = settings.getString("serverAdress","srvinfodev.esigelec.fr:8080/quiz");

        emailTextView = (AutoCompleteTextView) findViewById(R.id.email);
        //loadAutoComplete();

        fullnameTextView = (EditText) findViewById(R.id.surname);
        //loadAutoComplete()
       nameTextView = (EditText) findViewById(R.id.name);
        //loadAutoComplete()

        passwordTextView = (EditText) findViewById(R.id.password);
        passwordTextView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_NULL) {
                    initLogin();
                    return true;
                }
                return false;
            }
        });

        Button loginButton = (Button) findViewById(R.id.email_sign_in_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initLogin();
            }
        });

        loginFormView = findViewById(R.id.login_form);
        progressView = findViewById(R.id.login_progress);

        //adding underline and link to signup textview
        signUpTextView = (TextView) findViewById(R.id.signUpTextView);
        signUpTextView.setPaintFlags(signUpTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(signUpTextView, Linkify.ALL);
        optionTextView = (TextView) findViewById(R.id.optionTextView);
        optionTextView.setPaintFlags(optionTextView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        Linkify.addLinks(optionTextView, Linkify.ALL);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Sign In Activity activated.");
                //this is where you should start the signup Activity
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });

        optionTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("LoginActivity", "Option Activity activated.");
                RegisterActivity.this.startActivity(new Intent(RegisterActivity.this, OptionActivity.class));
            }
        });
    }


    /**
     * Validate Login form and authenticate.
     */
    public void initLogin() {
        if (userLoginTask != null) {
            return;
        }

        emailTextView.setError(null);
        passwordTextView.setError(null);
        nameTextView.setError(null);
        fullnameTextView.setError(null);

        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        String name = nameTextView.getText().toString();
        String fullname = fullnameTextView.getText().toString();


        boolean cancelLogin = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            passwordTextView.setError(getString(R.string.error_invalid_password));
            focusView = passwordTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(email)) {
            emailTextView.setError(getString(R.string.error_field_required));
            focusView = emailTextView;
            cancelLogin = true;
        } else if (!isEmailValid(email)) {
            emailTextView.setError(getString(R.string.error_invalid_email));
            focusView = emailTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(name)) {
            nameTextView.setError(getString(R.string.error_field_required));
            focusView = nameTextView;
            cancelLogin = true;
        }

        if (TextUtils.isEmpty(fullname)) {
            fullnameTextView.setError(getString(R.string.error_field_required));
            focusView = fullnameTextView;
            cancelLogin = true;
        }

        if (cancelLogin) {
            // error in login
            focusView.requestFocus();
        } else {
            // show progress spinner, and start background task to login
            showProgress(true);
            userLoginTask = new UserLoginTask(email, password, name, fullname);
            userLoginTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //add your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //add your own logic
        return password.length() > 2;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            loginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            progressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    progressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            progressView.setVisibility(show ? View.VISIBLE : View.GONE);
            loginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }


    /**
     * Async Login Task to authenticate
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String emailStr;
        private final String passwordStr;
        private final String nameStr;
        private final String fullnameStr;
        Global g = Global.getInstance();


        UserLoginTask(String email, String password, String name, String fullname) {
            emailStr = email;
            passwordStr = password;
            nameStr = name;
            fullnameStr = fullname;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            OkHttpClient client = new OkHttpClient();

            RequestBody formBody = new FormBody.Builder()
                    .add("name", nameStr)
                    .add("fullname", fullnameStr)
                    .add("email", emailStr)
                    .add("password", passwordStr)
                    .build();

            Request request = new Request.Builder()
                    .url("http://"+serverAdress+"/android/inscription")
                    .post(formBody)
                    .build();

            try{
                Response response = client.newCall(request).execute();
                String st = response.body().string();
                JSONObject json = new JSONObject(st);
                int id = json.getInt("id");

                response.close();

                if(json.getString("status").equals("OK"))
                {
                    g.setIdpersonne(id);
                    return true;
                }


            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }


            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            //using a local dummy credentials store to authenticate
            String[] pieces = DUMMY_CREDENTIALS.split(":");
            if (pieces[0].equals(emailStr) && pieces[1].equals(passwordStr)) {
                return true;
            } else {
                return false;
            }
        }



        @Override
        protected void onPostExecute(final Boolean success) {
            userLoginTask = null;
            //stop the progress spinner
            showProgress(false);


            if (success) {
                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(intent);
                //  login success and move to main Activity here.
            } else {
                // login failure
                emailTextView.setError("Email déja utilisé");
                // passwordTextView.setError(getString(R.string.error_invalid_password));
                emailTextView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            userLoginTask = null;
            showProgress(false);
        }
    }}