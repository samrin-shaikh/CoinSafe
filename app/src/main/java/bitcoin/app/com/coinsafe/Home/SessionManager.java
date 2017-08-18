package bitcoin.app.com.coinsafe.Home;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.HashMap;

import bitcoin.app.com.coinsafe.Login.Login;
import bitcoin.app.com.coinsafe.Login.Login_Signup_tabbed;
import bitcoin.app.com.coinsafe.util.Config;
import bitcoin.app.com.coinsafe.util.Config;
public class SessionManager {

    SharedPreferences pref;
    Editor editor;
    Context _context;
    int PRIVATE_MODE = 0;
    public SessionManager(Context context){
        this._context = context;
        pref = _context.getSharedPreferences(Config.PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public void createLoginSession(String email, String pass) {

        editor.putBoolean(Config.IS_LOGIN, true);
        editor.putString(Config.KEY_EMAIL, email);
        editor.putString(Config.KEY_PASSWORD, pass);
        editor.commit();
    }

    public void checkLogin(){

        if(!this.isLoggedIn()){
            Intent i = new Intent(_context, Login_Signup_tabbed.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            _context.startActivity(i);
        }

    }

    public HashMap<String, String> getUserDetails(){
        HashMap<String, String> user = new HashMap<String, String>();
        user.put(Config.KEY_EMAIL, pref.getString(Config.KEY_EMAIL, null));
        user.put(Config.KEY_PASSWORD, pref.getString(Config.KEY_PASSWORD, null));
        return user;
    }

    public void logoutUser(){
        editor.clear();
        editor.commit();
        Intent i = new Intent(_context, Login_Signup_tabbed.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _context.startActivity(i);
    }
    public boolean isLoggedIn()
    {
        return pref.getBoolean(Config.IS_LOGIN, false);
    }
}