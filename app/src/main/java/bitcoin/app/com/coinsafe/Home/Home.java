package bitcoin.app.com.coinsafe.Home;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import bitcoin.app.com.coinsafe.Buy.buy;

import bitcoin.app.com.coinsafe.Login.UserInfo;
import bitcoin.app.com.coinsafe.Payment.Payment;
import bitcoin.app.com.coinsafe.Profile.Profile;
import bitcoin.app.com.coinsafe.Profile.User_Detail;
import bitcoin.app.com.coinsafe.Profile.User_Profle;
import bitcoin.app.com.coinsafe.R;
import bitcoin.app.com.coinsafe.Mobile_Verification.*;
import bitcoin.app.com.coinsafe.Verification.*;
public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //Demo toast by Bilal
        Toast.makeText(this, "Welcome Mr. "+ UserInfo.getFirstname() + " " + UserInfo.getLastname(), Toast.LENGTH_SHORT).show();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_payment) {
            Intent intent=new Intent(getApplicationContext(),Payment.class);
            startActivity(intent);

        }
        else if (id == R.id.nav_profile) {
            Intent intent=new Intent(getApplicationContext(),Profile.class);
            startActivity(intent);

        }else if (id == R.id.nav_buy) {
            Intent intent=new Intent(getApplicationContext(),buy.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(getApplicationContext(),contactUs.class);
            startActivity(intent);


        }
        else if (id == R.id.navacc_veri) {
            Intent intent=new Intent(getApplicationContext(),Account_verification.class);
            startActivity(intent);

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
