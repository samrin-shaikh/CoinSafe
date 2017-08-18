package bitcoin.app.com.coinsafe.Login;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

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

import bitcoin.app.com.coinsafe.Home.Validate;
import bitcoin.app.com.coinsafe.Mobile_Verification.*;
import bitcoin.app.com.coinsafe.Home.Home;
import bitcoin.app.com.coinsafe.R;
import bitcoin.app.com.coinsafe.util.Config;

/**
 * Created by samrin shaikh on 10-08-2017.
 */

public class Signup extends Fragment {

    private static final String device_type = "1";
    private static final String device_id = "1";
    EditText fname, lname, emailid, mob, pwd, cpwd;
    Button signup;
    public static String REGISTER_URL = "http://35.154.116.102/coinsafe/index.php/api/users/signup";
    public String firstname;
    public String lastname;
    public String email;
    public String mobile;
    public String password;

    public static final String KEY_MOBILE = "mobile";
    public static final String KEY_FIRST_NAME = "firstname";
    public static final String KEY_LAST_NAME = "lastname";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.signup, container, false);

        fname = (EditText) view.findViewById(R.id.fname);
        lname = (EditText) view.findViewById(R.id.lname);
        emailid = (EditText) view.findViewById(R.id.email);
        mob = (EditText) view.findViewById(R.id.mobbile);
        pwd = (EditText) view.findViewById(R.id.password);
        cpwd = (EditText) view.findViewById(R.id.cpassword);
        signup = (Button) view.findViewById(R.id.signup_button);

       // http://35.154.116.102/coinsafe/index.php/api/users/signup?firstname=simran&lastname=shaikh&email=ssamrin10@gmail.com&mobile=1231231234&password=12345&device_type=1&device_id=90ijfsf0sf9
        REGISTER_URL = REGISTER_URL
                + "?firstname="
                + fname.getText().toString().trim()
                + "&lastname="
                + lname.getText().toString()
                + "&mobile="
                + mob.getText().toString()
                + "&email="
                + emailid.getText().toString()
                + "&password="
                + pwd.getText().toString()
                +"&device_id=1&device_type=1";


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstname = fname.getText().toString().trim();
                lastname = lname.getText().toString().trim();
                email = emailid.getText().toString().trim();
                mobile = mob.getText().toString().trim();
                password = pwd.getText().toString().trim();

                StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {
                                    JSONArray jsonArray = new JSONArray(response);
                                    JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                                    String msg = jsonObject1.getString("message");
                                    String stat = jsonObject1.getString("status");
                                    JSONObject jsonObject = jsonObject1.getJSONObject("userprofile");

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
                                    }
                                    else{
                                        Toast.makeText(getContext(),stat,Toast.LENGTH_LONG).show();
                                    }
                                }catch (JSONException e)
                                {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getContext(), error.toString(), Toast.LENGTH_LONG).show();
                            }
                        }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();

                        params.put(KEY_FIRST_NAME, firstname);
                        params.put(KEY_LAST_NAME, lastname);
                        params.put(KEY_EMAIL, email);
                        params.put(KEY_MOBILE, mobile);
                        params.put(KEY_PASSWORD, password);

                        return params;
                    }

                };

                RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
                requestQueue.add(stringRequest);
            }


        });
        return view;
    }
}

//        //http://35.154.116.102/coinsafe/index.php/api/users/signup?firstname=simran&lastname=shaikh&email=ssamrin10@gmail.com&mobile=1231231234&password=12345&device_type=1&device_id=90ijfsf0sf9
//        REGISTER_URL = REGISTER_URL
//                + "?firstname="
//                + fname.getText().toString().trim()
//                + "&lastname="
//                + lname.getText().toString()
//                + "&mobile="
//                + mob.getText().toString()
//                + "&email="
//                + emailid.getText().toString()
//                + "&password="
//                + pwd.getText().toString()
//                +"&device_id=1&device_type=1";
//
//
//
//        signup.setOnClickListener(this);
//
//        return view;
//
//    }
//    private void registerUser() {
//       firstname = fname.getText().toString().trim();
//        lastname = lname.getText().toString().trim();
//          email = emailid.getText().toString().trim();
//        mobile = mob.getText().toString().trim();
//          password = pwd.getText().toString().trim();
//         String confirm = cpwd.getText().toString().trim();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, REGISTER_URL,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONArray jsonArray = new JSONArray(response);
//                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
//                           String msg = jsonObject1.getString("message");
//                            String stat = jsonObject1.getString("status");
//                            JSONObject jsonObject = jsonObject1.getJSONObject("userprofile");
//
//                            UserInfo.setUid(jsonObject.getString("uid"));
//                            UserInfo.setFirstname(jsonObject.getString("firstname"));
//                            UserInfo.setLastname(jsonObject.getString("lastname"));
//                            UserInfo.setMobile(jsonObject.getString("mobile"));
//                            UserInfo.setDob(jsonObject.getString("dob"));
//                            UserInfo.setEmail(jsonObject.getString("email"));
//                            UserInfo.setUnique_id(jsonObject.getString("unique_id"));
//                            UserInfo.setIsemailverified(jsonObject.getString("isemailverified"));
//                            UserInfo.setIsactive(jsonObject.getString("isactive"));
//                            UserInfo.setCreated_on(jsonObject.getString("created_on"));
//                            UserInfo.setRand_key(jsonObject.getString("rand_key"));
//                            UserInfo.setIsadmin(jsonObject.getString("isadmin"));
//                            UserInfo.setProfile_pic(jsonObject.getString("profile_pic"));
//                            UserInfo.setPassword(jsonObject.getString("password"));
//                            UserInfo.setDevice_id(jsonObject.getString("device_id"));
//                            UserInfo.setDevice_type(jsonObject.getString("device_type"));
//                            UserInfo.setSignup_from(jsonObject.getString("signup_from"));
//                            UserInfo.setOtpid(jsonObject.getString("otpid"));
//                            UserInfo.setUpdated_on(jsonObject.getString("updated_on"));
//
//                            if(Integer.parseInt(stat)==1){
//                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
//                                //openProfile();
//                            }
//                            else{
//                                Toast.makeText(getContext(),msg,Toast.LENGTH_LONG).show();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//                    }
//                },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Toast.makeText(getContext(),error.toString(),Toast.LENGTH_LONG ).show();
//                    }
//                }){
//        @Override
//        protected Map<String, String> getParams() throws AbstractMethodError {
//            Map<String,String> map = new HashMap<>();
//            map.put(firstname,firstname);
//            map.put(lastname,lastname);
//            map.put(email,email);
//            map.put(mobile,mobile);
//            map.put(password,password);
//            return map;
//        }
//    };
//
//       RequestQueue requestQueue = Volley.newRequestQueue(getContext());
//        requestQueue.add(stringRequest);
//    }
//
//    @Override
//    public void onClick(View v) {
//        if (v.getId()==R.id.signup_button){
//            registerUser();
//        }
//    }
//    private void openProfile(){
//        Intent intent = new Intent(getContext(), Home.class);
//        startActivity(intent);
//    }