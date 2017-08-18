package bitcoin.app.com.coinsafe.Login;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.koushikdutta.ion.Ion;

import bitcoin.app.com.coinsafe.HowIItWorks.How_It_Works;
import bitcoin.app.com.coinsafe.R;

public class SplashScreen extends AppCompatActivity {
    ImageView imgsplash;;

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    Thread splashTread;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);
        StartAnimations();
        //imgsplash=(ImageView)findViewById(R.id.image_gif);
       // Ion.with(imgsplash).load("http://bilalujede.com/BilalUjede/coinbasegify.gif");



    }
    private void StartAnimations() {
        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3000) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(SplashScreen.this,
                            How_It_Works.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    SplashScreen.this.finish();
                } catch (InterruptedException e) {
                } finally {
                    SplashScreen.this.finish();
                }
            }
        };
        splashTread.start();
    }

}

