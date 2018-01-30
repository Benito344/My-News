package com.behague.benjamin.mynews.controllers.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.AlarmReceiver;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.activity_edit_box_sn) EditText terms;
    @BindView(R.id.cb_Arts) CheckBox arts;
    @BindView(R.id.cb_Business) CheckBox businness;
    @BindView(R.id.cb_Entrepreneurs) CheckBox entrepreneurs;
    @BindView(R.id.cb_Politics) CheckBox politics;
    @BindView(R.id.cb_Sports) CheckBox sports;
    @BindView(R.id.cb_Travels) CheckBox travels;
    @BindView(R.id.switch_notification) Switch switchNotification;

    SharedPreferences notifParams;

    private final String KEY_SWITCH_STATE = "KEY_SWITCH_STATE";
    private final String KEY_SECTION_CHECKED = "KEY_SECTION_CHECKED";
    private final String KEY_TERMS = "KEY_TERMS";

    CheckBox [] checkBoxs;

    private PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        ButterKnife.bind(this);

        final String PARAMS = "PARAMS";
        notifParams = getSharedPreferences(PARAMS, Context.MODE_PRIVATE);

        checkBoxs = new CheckBox[] {arts, businness, entrepreneurs, politics, sports, travels};

        arts.setOnClickListener(this);
        businness.setOnClickListener(this);
        entrepreneurs.setOnClickListener(this);
        politics.setOnClickListener(this);
        sports.setOnClickListener(this);
        travels.setOnClickListener(this);

        this.configureToolbar();
        this.initUI();
        this.configureAlarmManager();

        terms.addTextChangedListener(textWatched);

        switchNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String inputTerms = terms.getText().toString();

                StringBuilder sectionsChecked = new StringBuilder();

                if (switchNotification.isChecked()){
                    for(CheckBox section : checkBoxs){
                        if(section.isChecked()){
                            sectionsChecked.append(section.getText().toString());
                            sectionsChecked.append(",");
                        }
                    }

                    if(inputTerms.length() == 0 || sectionsChecked.length() == 0){
                        switchNotification.setChecked(false);
                        Toast.makeText(NotificationActivity.this, "Please enter all informations", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sectionsChecked = sectionsChecked.deleteCharAt(sectionsChecked.length()-1);

                        notifParams.edit().putBoolean(KEY_SWITCH_STATE, true)
                                            .putString(KEY_SECTION_CHECKED, sectionsChecked.toString())
                                            .putString(KEY_TERMS, terms.getText().toString())
                                            .apply();
                        enableNotification();
                    }
                }
                else {
                    notifParams.edit().putBoolean(KEY_SWITCH_STATE, false).apply();
                    disableNotification();
                }
            }
        });
    }

    private void enableNotification() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        if(manager != null) {
            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, 5000,AlarmManager.INTERVAL_DAY, pendingIntent);
            Toast.makeText(this, "Notification activated", Toast.LENGTH_SHORT).show();
        }
        else{
            switchNotification.setChecked(false);
            Toast.makeText(this,"Please retry" , Toast.LENGTH_SHORT).show();
        }
    }

    private void disableNotification() {
        AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(manager != null){
            manager.cancel(pendingIntent);
            Toast.makeText(this, "Notification canceled", Toast.LENGTH_SHORT).show();
        }
        else{
            switchNotification.setChecked(false);
            Toast.makeText(this,"Please retry" , Toast.LENGTH_SHORT).show();
        }

    }


    private void configureToolbar() {
        //Get the toolbar (Serialise)
        Toolbar toolbar = findViewById(R.id.toolbarSearch);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        if(ab != null){
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void initUI(){

        if(notifParams.getBoolean(KEY_SWITCH_STATE, false)){
            switchNotification.setChecked(true);
            terms.setText(notifParams.getString(KEY_TERMS,""));

            String sectionChecked = notifParams.getString(KEY_SECTION_CHECKED, "");

            if(sectionChecked.contains(",")){
                String[] retrieveSection = notifParams.getString(KEY_SECTION_CHECKED, "").split(",");

                for(CheckBox checkBox : checkBoxs){
                    for(String section : retrieveSection){
                        if(checkBox.getText().toString().equals(section)){
                            checkBox.setChecked(true);
                        }
                    }
                }
            }
            else {
                for(CheckBox checkBox : checkBoxs){
                    if (sectionChecked.equals(checkBox.getText().toString())){
                        checkBox.setChecked(true);
                    }
                }
            }
        }
        else{
            switchNotification.setChecked(false);
        }
    }

    private void configureAlarmManager(){
        Intent alarmIntent = new Intent(NotificationActivity.this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(NotificationActivity.this, 0, alarmIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    private TextWatcher textWatched = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(switchNotification.isChecked()){
                notifParams.edit().putString(KEY_TERMS, terms.getText().toString()).apply();
            }
        }

        @Override
        public void afterTextChanged(Editable s) {}
    };

    @Override
    public void onClick(View v){
        if(switchNotification.isChecked() && v.getClass().getName().equals("android.support.v7.widget.AppCompatCheckBox")){
                if (((CheckBox)v).isChecked()){
                    String addSection = notifParams.getString(KEY_SECTION_CHECKED,"") + "," + ((CheckBox) v).getText();
                    notifParams.edit().putString(KEY_SECTION_CHECKED, addSection).apply();
                }
                else{
                    String sections = notifParams.getString(KEY_SECTION_CHECKED,"");
                    if(sections.contains(",")){
                        String [] removedSection = sections.split(",");
                        String newSection = removeSection(removedSection,((CheckBox) v).getText().toString());
                        notifParams.edit().putString(KEY_SECTION_CHECKED, newSection).apply();
                    }
                    else{
                        ((CheckBox) v).setChecked(true);
                        Toast.makeText(this,"You need one section minimum",Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    public static String removeSection(String[] input, String deleteMe) {

        StringBuilder result = new StringBuilder();

        for(String item : input){
            if(!deleteMe.equals(item)){
                result.append(item + ",");
            }
        }

        result.deleteCharAt(result.length()-1);

        return result.toString();
    }
}
