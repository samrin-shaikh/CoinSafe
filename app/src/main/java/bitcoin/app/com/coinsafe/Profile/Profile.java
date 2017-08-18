package bitcoin.app.com.coinsafe.Profile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import bitcoin.app.com.coinsafe.Login.UserInfo;
import bitcoin.app.com.coinsafe.R;

/**
 * Created by samrin shaikh on 18-08-2017.
 */

public class Profile extends AppCompatActivity implements View.OnClickListener{
    public static String USER_PROFILE = "http://35.154.116.102/coinsafe/index.php/api/users/getuserprofile";
    String uid, firstname, lastname, mobile, dob, email1, isactive, udid, uid1, address1, available_balance1, label1, network1;
    TextView  user_uid,first_name, last_name, mob, dobb, is_active, u_did, unique_id, device_id, device_type, signup_from, OTP_id, network;

   // TextView  firstname, lastname, mobile, dob, email, password, unique_id, device_id, device_type, signup_from, OTP_id, network;
EditText user_id;
    Button user_details;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profle);
        user_details=(Button)findViewById(R.id.user_id_detail);

        user_uid=(TextView)findViewById(R.id.uid_detail);
        //uid.setText(UserInfo.getFirstname());

        first_name=(TextView)findViewById(R.id.fname_detail);
       // firstname.setText(UserInfo.getFirstname());

        last_name=(TextView)findViewById(R.id.lname_detail);
       // lastname.setText(UserInfo.getLastname());

        mob=(TextView)findViewById(R.id.mobile_detail);
        //mobile.setText(UserInfo.getMobile());

        dobb=(TextView)findViewById(R.id.dob_detail);
        //dob.setText(UserInfo.getDob());
//
//
//        password=(TextView)findViewById(R.id.password_detail);
//        password.setText(UserInfo.getPassword());
//
//        unique_id=(TextView)findViewById(R.id.unique_id_detail);
//        unique_id.setText(UserInfo.getUnique_id());
//
//        device_id=(TextView)findViewById(R.id.device_id_detail);
//        device_id.setText(UserInfo.getDevice_id());
//
//        device_type=(TextView)findViewById(R.id.device_type_details);
//        device_type.setText(UserInfo.getDevice_type());
//
//        signup_from=(TextView)findViewById(R.id.signup_from_detail);
//        signup_from.setText(UserInfo.getSignup_from());
//
//        OTP_id=(TextView)findViewById(R.id.otp_id_detail);
//        OTP_id.setText(UserInfo.getOtpid());

        USER_PROFILE = USER_PROFILE + "?uid=" + user_id.getText().toString();
        user_details.setOnClickListener(this);




    }
    private void userdetails() {
        uid = user_uid.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_PROFILE,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject jsonObject1 = (JSONObject) jsonArray.get(0);
                            String stat = jsonObject1.getString("status");
                            JSONObject jsonObject = jsonObject1.getJSONObject("users");

                            User_Detail.setUid(jsonObject.getString("uid"));
                            User_Detail.setFirstname(jsonObject.getString("firstname"));
                            User_Detail.setLastname(jsonObject.getString("lastname"));
                            User_Detail.setMobile(jsonObject.getString("mobile"));
                            User_Detail.setDob(jsonObject.getString("dob"));
                            User_Detail.setEmail(jsonObject.getString("isactive"));
//                            UserInfo.setPassword(jsonObject.getString("password"));
//                            UserInfo.setUnique_id(jsonObject.getString("unique_id"));
//                            UserInfo.setDevice_id(jsonObject.getString("device_id"));
//                            UserInfo.setDevice_type(jsonObject.getString("device_type"));
//                            UserInfo.setSignup_from(jsonObject.getString("signup_from"));
//                            UserInfo.setOtpid(jsonObject.getString("otpid"));

                            if(Integer.parseInt(stat)==1){
                                Toast.makeText(getApplicationContext(),"Showing Details" ,Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(getApplicationContext(),"Data not present",Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_LONG ).show();
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AbstractMethodError {
                Map<String,String> map = new HashMap<>();
                map.put(uid,uid);
                map.put(firstname,firstname);
                map.put(lastname,lastname);
                map.put(mobile,mobile);
                map.put(dob,dob);
                return map;
            }
        };


        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
    @Override
    public void onClick(View view) {

        if (view.getId()==R.id.user_id_detail){
            userdetails();
        }
    }
}
