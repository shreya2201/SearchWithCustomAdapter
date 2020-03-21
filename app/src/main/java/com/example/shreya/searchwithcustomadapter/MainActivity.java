package com.example.shreya.searchwithcustomadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView list;
    SchemeHeadingAdapter adapter;
    EditText editsearch;
    ArrayList<Schemes> schemes = new ArrayList<Schemes>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        schemes.add(new Schemes(R.string.heading_01,R.string.brief_01,R.string.eligibility_01,R.string.benefits_01, R.string.apply_01, R.string.more_01));
        schemes.add(new Schemes(R.string.heading_02,R.string.brief_02,R.string.eligibility_02,R.string.benefits_02, R.string.apply_02, R.string.more_02));
        schemes.add(new Schemes(R.string.heading_03,R.string.brief_03,R.string.eligibility_03,R.string.benefits_03, R.string.apply_03, R.string.more_02));
        schemes.add(new Schemes(R.string.heading_04,R.string.brief_04,R.string.eligibility_04,R.string.benefits_04, R.string.apply_04, R.string.more_02));
        schemes.add(new Schemes(R.string.heading_05,R.string.brief_05,R.string.eligibility_05,R.string.benefits_05, R.string.apply_05, R.string.more_02));

        adapter = new SchemeHeadingAdapter(this, schemes);
        list = (ListView) findViewById(R.id.list_view_search);


        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (EditText) findViewById(R.id.inputSearch);

        // Capture Text in EditText
        editsearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub
                String text = editsearch.getText().toString().toLowerCase(Locale.getDefault());
                adapter.filter(text);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2,
                                      int arg3) {
                // TODO Auto-generated method stub
            }
        });
    }
}