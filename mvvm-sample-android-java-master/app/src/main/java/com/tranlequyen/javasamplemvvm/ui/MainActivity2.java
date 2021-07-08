package com.tranlequyen.javasamplemvvm.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tranlequyen.javasamplemvvm.R;
import com.tranlequyen.javasamplemvvm.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity {


    private static final String EITGUIDE_URL = "https://www.fullphimz.net/search?query=";
   // private WebView webView;
    private ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main2 );

        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
       //WebView webView = (WebView)findViewById ( R.id.webView );
        binding = ActivityMain2Binding.inflate(getLayoutInflater());
        binding.webView.setWebViewClient(new WebViewClient ());
        binding.webView.getSettings().setJavaScriptEnabled(true);
        Intent intent = getIntent();
        String titleM = intent.getStringExtra("title");
        binding.webView.loadUrl(EITGUIDE_URL+titleM.toString ());
        View view = binding.getRoot();
        setContentView(view);
      //Load url

        binding.webView.setOnKeyListener(new View.OnKeyListener ()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN)
                {
                    WebView webView = (WebView) v;

                    switch(keyCode)
                    {
                        case KeyEvent.KEYCODE_BACK:
                            if(webView.canGoBack())
                            {
                                webView.goBack();
                                return true;
                            }
                            break;
                    }
                }

                return false;
            }
        });
    binding.fab.setOnClickListener ( new View.OnClickListener () {
        @Override
        public void onClick(View v) {
            onBackPressed ();
        }
    } );
    }

}