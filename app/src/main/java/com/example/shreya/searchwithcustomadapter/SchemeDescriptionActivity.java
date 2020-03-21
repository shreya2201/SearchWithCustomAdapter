package com.example.shreya.searchwithcustomadapter;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.speech.tts.TextToSpeech;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Objects;

public class SchemeDescriptionActivity extends AppCompatActivity  implements
        TextToSpeech.OnInitListener {

    TextView headingText , briefText, eligibilityText, benefitsText, applyText, moreText;
    ImageView speaker, delete;
    private TextToSpeech tts;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheme_description);

        tts = new TextToSpeech(this, this);


//        Toolbar toolbar2 = (Toolbar) findViewById(R.id.toolbar2);
//        setSupportActionBar(toolbar2);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        headingText = (TextView) findViewById(R.id.desc_heading);
        briefText = (TextView) findViewById(R.id.desc_brief);
        eligibilityText = (TextView) findViewById(R.id.desc_eligibility);
        benefitsText = (TextView) findViewById(R.id.desc_benefits);
        applyText = (TextView) findViewById(R.id.desc_apply);
        moreText = (TextView) findViewById(R.id.desc_info);
        speaker = (ImageView) findViewById(R.id.heading_speak);
        delete = (ImageView) findViewById(R.id.heading_speak_cancel);


        Intent intent = getIntent();
        int heading = intent.getIntExtra("heading", -1);
        int brief = intent.getIntExtra("brief", -1);
        int eligibility = intent.getIntExtra("eligibility", -1);
        int benefits = intent.getIntExtra("benefits", -1);
        int apply = intent.getIntExtra("apply", -1);
        final int moreinfo = intent.getIntExtra("more", -1);

        Log.d("heading", String.valueOf(heading));

        headingText.setText(heading);
        briefText.setText(brief);
        eligibilityText.setText(eligibility);
        benefitsText.setText(benefits);
        applyText.setText(apply);
        moreText.setText(moreinfo);

        speaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
                Log.d("speaker clicked","true");
                speaker.setVisibility(View.GONE);
                delete.setVisibility(View.VISIBLE);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Destroy();
                speaker.setVisibility(View.VISIBLE);
                delete.setVisibility(View.GONE);
            }
        });

        Log.d("moreinfo", String.valueOf(moreinfo));
        moreText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse((String) moreText.getText()));
                startActivity(browserIntent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
            speaker.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(new Locale("hi"));
            tts.setSpeechRate((float) .85);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            } else {
                speaker.setEnabled(true);

            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }

    }

    private void speakOut() {

        String text = headingText.getText().toString()+ " . "+getString(R.string.head_brief)+ ". .  . . . "+briefText.getText().toString()
                + ".    "+getString(R.string.head_eligibility)+". "+ eligibilityText.getText().toString()
                + ".    . .   . . . "+getString(R.string.head_benefits)+"."+ benefitsText.getText().toString();

        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);


    }

    private  void Destroy() {


        if (tts != null) {
            tts.stop();

            speaker.setVisibility(View.VISIBLE);
            delete.setVisibility(View.GONE);
        }
    }
}
