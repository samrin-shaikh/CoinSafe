package bitcoin.app.com.coinsafe.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import bitcoin.app.com.coinsafe.Home.Home;
import bitcoin.app.com.coinsafe.Home.SessionManager;
import bitcoin.app.com.coinsafe.Mobile_Verification.*;
import bitcoin.app.com.coinsafe.R;
import bitcoin.app.com.coinsafe.util.Config;

public class Login extends Fragment implements View.OnClickListener {
    Button login, dialogSkipBtn, dialogResetBtn;
    TextView forgetPassword,alertDialogForgetPassword;
    AlertDialog.Builder alertDialogBuilder;
    AlertDialog alertDialog;
    private static final String device_id = "1";
    private static final String device_type = "1";
    SessionManager session;
    EditText email_id, pwd,email_forget_password;
    public static String LOGIN_URL = "http://35.154.116.102/coinsafe/index.php/api/users/login";
    public static String FORGET_URL = "http://35.154.116.102/coinsafe/index.php/api/users/forgotpassword";

    public static final String KEY_USERNAME = "email";
    public static final String KEY_PASSWORD = "password";
    public static final String KEY_FORGET = "email";

    private String email;
    private String password;
    private String forget_email;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_login, container, false);
        email_id = (EditText) view.findViewById(R.id.email);
        pwd = (EditText)view. findViewById(R.id.password);
        email_forget_password=(EditText)view.findViewById(R.id.email_forget_password);
        forgetPassword = (TextView) view.findViewById(R.id.forgetPassword);



        login = (Button)view. findViewById(R.id.login_button);

        //http://35.154.116.102/coinsafe/index.php/api/users/login?email=samadhan.nirali@gmail.com&password=12345&device_id=1&device_type=1
        LOGIN_URL = LOGIN_URL + "?email=" + email_id.getText().toString() + "&password=" + pwd.getText().toString() + "&device_id=1&device_type=1";
        FORGET_URL = FORGET_URL + "?email=" + forgetPassword.getText().toString();

        login.setOnClickListener(this);

        forgetPassword.setOnClickListener(this);



        return view;

    }
    private void userLogin() {
        email = email_id.getText().toString().trim();
        password = pwd.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, LOGIN_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                            String msg = jsonObject1.getString("message");
                            String stat = jsonObject1.getString("status");
                            JSONObject jsonObject = jsonObject1.getJSONObject("profile");

                            UserInfo.setUid(jsonObject.getString("uid"));
                            UserInfo.setFirstname(jsonObject.getString("firstname"));
                            UserInfo.setLastname(jsonObject.getString("lastname"));
                            UserInfo.setMobile(jsonObject.getString("mobile"));
                            UserInfo.setDob(jsonObject.getString("dob"));
                            UserInfo.setEmail(jsonObject.getString("email"));
                            UserInfo.setPassword(jsonObject.getString("password"));
                            UserInfo.setUnique_id(jsonObject.getString("unique_id"));
                            UserInfo.setDevice_id(jsonObject.getString("device_id"));
                            UserInfo.setDevice_type(jsonObject.getString("device_type"));
                            UserInfo.setSignup_from(jsonObject.getString("signup_from"));
                            UserInfo.setOtpid(jsonObject.getString("otpid"));

                            if(Integer.parseInt(stat)==1){
                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                                openProfile();
                            }
                            else{
                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AbstractMethodError {
                Map<String,String> map = new HashMap<>();
                map.put(KEY_USERNAME,email);
                map.put(KEY_PASSWORD,password);
                return map;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(stringRequest);
    }

    private void openProfile(){
        Intent intent = new Intent(getContext(), Home.class);
        intent.putExtra(KEY_USERNAME, email);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        if (v.getId()==R.id.login_button){
            userLogin();
        }
        if (v.getId()==R.id.forgetPassword){
            forgetPasswordFun();
        }
        if (v.getId()==R.id.forgetPasswordBtnSkip){
            forgetPasswordBtnSkipFun();
        }
    }

            private void forgetPasswordBtnSkipFun() {
                alertDialog.dismiss();
            }

            private void forgetPasswordBtnResetFun() {
                Toast.makeText(getContext(), "Diialog", Toast.LENGTH_SHORT).show();
                forget_email = email_forget_password.getText().toString().trim();

                StringRequest stringRequestPasswdReset = new StringRequest(Request.Method.POST,FORGET_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                                    String msg = jsonObject1.getString("message");
                                    String stat = jsonObject1.getString("status");

                                    if(Integer.parseInt(stat)==1){
                                        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                                        alertDialog.dismiss();
                                    }
                                    else{
                                        Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG ).show();
                            }
                        }){
                    @Override
                    protected Map<String, String> getParams() throws AbstractMethodError {
                        Map<String,String> map = new HashMap<>();
                        map.put(KEY_FORGET,email);
                        return map;
                    }
                };

                RequestQueue requestQueue = Volley.newRequestQueue(getContext());
                requestQueue.add(stringRequestPasswdReset);

            }

            private void forgetPasswordFun() {
                alertDialogBuilder = new AlertDialog.Builder(getContext());
                LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View view = layoutInflater.inflate(R.layout.forgetpassword, null);
                alertDialogBuilder.setView(view);
                dialogSkipBtn = (Button) view.findViewById(R.id.forgetPasswordBtnSkip);
                dialogResetBtn = (Button) view.findViewById(R.id.forgetPasswordBtnReset);
                alertDialogForgetPassword = (TextView) view.findViewById(R.id.forgetPassword);
                dialogResetBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        forgetPasswordBtnResetFun();
                    }
                });
                dialogResetBtn.setOnClickListener(this);
                dialogSkipBtn.setOnClickListener(this);
                alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        }
