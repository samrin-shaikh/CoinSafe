package bitcoin.app.com.coinsafe.Verification;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import bitcoin.app.com.coinsafe.Login.Login;
import bitcoin.app.com.coinsafe.Login.Signup;

/**
 * Created by samrin shaikh on 10-08-2017.
 */

public class Pager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;

    //Constructor to the class
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                id_card id_card = new id_card();
                return id_card;
            case 1:
                adhar_card adhar_card = new adhar_card();
                return adhar_card;
            case 2:
                bank_details bank_details = new bank_details();
                return bank_details;

            default:
                return null;
        }
    }

    //Overriden method getCount to get the number of tabs
    @Override
    public int getCount() {
        return tabCount;
    }
}