package com.pocket.rocket.broken;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;


public class AndroidLauncher extends AndroidApplication {

    final AndroidLauncher context = this;

    //final AdMobImpl adMob;
    final GPGSImpl gpgs;
    final Main game;

    public AndroidLauncher() {
        //adMob = new AdMobImpl( "ca-app-pub-3433473599086980/5967988151" );
        gpgs = new GPGSImpl() {
            @Override
            public void onConnected(Bundle connectionHint) {
                game.gpgsStateChange = true;
            }

            @Override
            public void disconnect() {
                super.disconnect();
                game.gpgsStateChange = true;
            }

            @Override
            public void rateGame() {
                String str = "https://play.google.com/store/apps/details?id=com.pocket.rocket.broken";
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(str)));
            }
        };
        game = new Main(/* adMob, */gpgs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

        //adMob.init( context );
        gpgs.init(context);

        View gameView = initializeForView(game, new AndroidApplicationConfiguration());

        RelativeLayout layout = new RelativeLayout(this);
        layout.addView(gameView);
        //layout.addView( adMob.adView, adMob.adParams );
        setContentView(layout);
    }

    @Override
    public void onStart() {
        super.onStart();
        // Во время старта приложения, подключаемся к GPGS
        // Так рекомендует делать GOOGLE
        gpgs.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        gpgs.disconnect();
    }

    @Override
    public void onActivityResult(int request, int response, Intent intent) {
        super.onActivityResult(request, response, intent);
        gpgs.onActivityResult(request, response, intent);
    }
}
