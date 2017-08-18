package bitcoin.app.com.coinsafe.Verification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import bitcoin.app.com.coinsafe.R;

/**
 * Created by samrin shaikh on 11-08-2017.
 */

public class id_card extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.id_card_fragment,container,false);
        return view;
    }
}
