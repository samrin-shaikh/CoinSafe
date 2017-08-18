package bitcoin.app.com.coinsafe.Trade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import bitcoin.app.com.coinsafe.R;

public class Trade_List extends AppCompatActivity implements View.OnClickListener {
    public static final String TRADELIST_URL = "http://35.154.116.102/coinsafe/index.php/api/trading/gettradelist";
Button buttonGet;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade__list);
        buttonGet = (Button) findViewById(R.id.buttonget);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
    }

    private void sendRequest(){

    }

    private void showJSON(String json){

    }

    @Override
    public void onClick(View v) {
        sendRequest();
    }
}
