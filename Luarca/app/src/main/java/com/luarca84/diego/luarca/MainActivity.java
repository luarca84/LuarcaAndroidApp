package com.luarca84.diego.luarca;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClickButtonYoutube(View view)
    {
        goToUrl("https://www.youtube.com/watch?v=rAWI-MpvIZk");
    }

    public void onClickButtonGoogleMaps(View view)
    {
        goToUrl("https://www.google.es/maps/place/Puerto+de+Luarca/@43.5460176,-6.5337976,17.75z/data=!4m5!3m4!1s0x0:0xd6d413abd6b71be8!8m2!3d43.5469758!4d-6.532978?hl=en");
    }

    public void onClickButtonAyuntamiento(View view)
    {
        goToUrl("http://www.valdes.es/");
    }

    public void onClickButtonTurismoLuarca(View view)
    {
        goToUrl("http://www.turismoluarca.com/");
    }

    public void onClickButtonWikipedia(View view)
    {
        goToUrl("https://es.wikipedia.org/wiki/Luarca");
    }

    public void onClickButtonPuzzle(View view)
    {
        Intent intent = new Intent(this,PuzzleActivity.class);
        startActivity(intent);
    }

    private void goToUrl (String url) {
        Uri uriUrl = Uri.parse(url);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
        startActivity(launchBrowser);
    }
}
