package bitcoin.app.com.coinsafe.Mobile_Verification;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import bitcoin.app.com.coinsafe.R;

public class mobile_verification extends AppCompatActivity {
    Button accept;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_verification);
        accept=(Button)findViewById(R.id.accept_and_continue);
        Intent intent=new Intent(getApplicationContext(),confirm_verification.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed()
    {
        // code here to show dialog
        super.onBackPressed();  // optional depending on your needs
    }
}
