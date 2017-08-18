package bitcoin.app.com.coinsafe.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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

import bitcoin.app.com.coinsafe.Home.Home;
import bitcoin.app.com.coinsafe.Home.SessionManager;
import bitcoin.app.com.coinsafe.Login.UserInfo;
import bitcoin.app.com.coinsafe.R;

public class User_Profle extends AppCompatActivity {
    String userid1, firstname1, lastname1, mobile1, dob1, email1, isactive1, udid1, uid1, address1, available_balance1, label1, network1;

    TextView userid, firstname, lastname, mobile, dob, email, password, unique_id, device_id, device_type, signup_from, OTP_id, network;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__profle);

        userid=(TextView)findViewById(R.id.user_id_detail);
        userid.setText(UserInfo.getUid());

        firstname=(TextView)findViewById(R.id.fname_detail);
        firstname.setText(UserInfo.getFirstname());

        lastname=(TextView)findViewById(R.id.lname_detail);
        lastname.setText(UserInfo.getLastname());

        mobile=(TextView)findViewById(R.id.mobile_detail);
        mobile.setText(UserInfo.getMobile());

        dob=(TextView)findViewById(R.id.dob_detail);
        dob.setText(UserInfo.getDob());

        email=(TextView)findViewById(R.id.email_detail);
        email.setText(UserInfo.getEmail());

        password=(TextView)findViewById(R.id.password_detail);
        password.setText(UserInfo.getPassword());

        unique_id=(TextView)findViewById(R.id.unique_id_detail);
        unique_id.setText(UserInfo.getUnique_id());

        device_id=(TextView)findViewById(R.id.device_id_detail);
        device_id.setText(UserInfo.getDevice_id());

        device_type=(TextView)findViewById(R.id.device_type_details);
        device_type.setText(UserInfo.getDevice_type());

        signup_from=(TextView)findViewById(R.id.signup_from_detail);
        signup_from.setText(UserInfo.getSignup_from());

        OTP_id=(TextView)findViewById(R.id.otp_id_detail);
        OTP_id.setText(UserInfo.getOtpid());







    }

}