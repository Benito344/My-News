package com.behague.benjamin.mynews.controllers.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.utils.NYTStreams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<SearchArticlesDoc> searchArticlesDoc;

    private StringBuilder sectionsChecked;
    private String inputTerms;
    private String inputBeginDate;
    private String inputEndDate;
    private int mYear, mMonth, mDay;
    private CheckBox [] checkBoxs;

    @BindView(R.id.activity_edit_box_sn) EditText terms;
    @BindView(R.id.cb_Arts) CheckBox arts;
    @BindView(R.id.cb_Business) CheckBox businness;
    @BindView(R.id.cb_Entrepreneurs) CheckBox entrepreneurs;
    @BindView(R.id.cb_Politics) CheckBox politics;
    @BindView(R.id.cb_Sports) CheckBox sports;
    @BindView(R.id.cb_Travels) CheckBox travels;
    @BindView(R.id.et_begin_date) EditText beginDate;
    @BindView(R.id.et_end_date) EditText endDate;
    @BindView(R.id.button_search) Button validateSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        beginDate.setOnClickListener(this);
        endDate.setOnClickListener(this);

        //Configure layout
        this.configureToolbar();

        //Check user informations when user clicked on search button
        validateSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputTerms = terms.getText().toString();
                inputBeginDate = beginDate.getText().toString();
                inputEndDate = endDate.getText().toString();

                checkBoxs = new CheckBox[]{arts, businness, entrepreneurs, politics, sports, travels};

                sectionsChecked = new StringBuilder();

                for(CheckBox section : checkBoxs){
                    if(section.isChecked()){
                        sectionsChecked.append(section.getText().toString());
                        sectionsChecked.append(",");
                    }
                }

                if(inputTerms.isEmpty() || sectionsChecked.length() == 0
                        || inputBeginDate.isEmpty() != inputEndDate.isEmpty()){
                    Toast.makeText(SearchActivity.this, "Please enter all the information",Toast.LENGTH_SHORT).show();
                }
                else if(!inputTerms.isEmpty() && sectionsChecked.length( )> 0
                        && inputBeginDate.isEmpty() && inputEndDate.isEmpty()){
                    searchArticlesDoc = new ArrayList<>();
                    validateSearch.setEnabled(false);
                    SearchActivity.this.executeHttpRequestWithoutDate();
                }
                else {
                    searchArticlesDoc = new ArrayList<>();
                    validateSearch.setEnabled(false);
                    SearchActivity.this.executeHttpRequest();
                }

            }
        });
    }

    private void configureToolbar(){
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

    //Used for user click on date picker dialog
    @Override
    public void onClick(View v) {
        if(v == beginDate) {
            beginDatePickerDialog();
        } else if(v == endDate) {
            endDatePickerDialog();
        }
    }

    //It used for display Date picker dialog for begin date
    public void beginDatePickerDialog (){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        String str;
                        monthOfYear += 1;

                        if(monthOfYear<10){
                            str = year + "/0" + monthOfYear + "/" + dayOfMonth;
                        }else{
                            str = dayOfMonth + "/" + monthOfYear + "/" + year;
                        }

                        beginDate.setText(str);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    //It used for display Date picker dialog for end date
    public void endDatePickerDialog (){
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        String str;
                        monthOfYear += 1;

                        if(monthOfYear<10){
                            str = year + "/0" + monthOfYear + "/" + dayOfMonth;
                        }else{
                            str = dayOfMonth + "/" + monthOfYear + "/" + year;
                        }

                        endDate.setText(str);

                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }

    //It used for execute HTTP request when user input date and get the results
    private void executeHttpRequest(){

        sectionsChecked = sectionsChecked.deleteCharAt(sectionsChecked.length()-1);

        String beginDate = inputBeginDate.replace("/", "");
        String endDate = inputEndDate.replace("/", "");

        NYTStreams.streamSearchArticles(inputTerms, beginDate
                , endDate, sectionsChecked.toString()).subscribeWith(new DisposableObserver<SearchArticlesMain>(){

            @Override
            public void onNext(SearchArticlesMain searchArticlesMain){
                if(searchArticlesMain.getResponse().getDocs().isEmpty()){
                    AlertDialog.Builder popUp = new AlertDialog.Builder(SearchActivity.this);
                    popUp.setTitle("Sorry")
                            .setMessage("No result found ! ")
                            .setNeutralButton("Retry", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                        validateSearch.setEnabled(true);
                                }
                            })
                            .show();
                    Log.e("TAG", "Empty");
                }
                else{
                    searchArticlesDoc.addAll(searchArticlesMain.getResponse().getDocs());
                    Intent searchResultsActivity = new Intent(SearchActivity.this, SearchResultsActivity.class);
                    startActivity(searchResultsActivity);
                    }
                    validateSearch.setEnabled(true);
            }

            @Override
            public void onError(Throwable e){
                validateSearch.setEnabled(true);
            }

            @Override
            public void onComplete(){
                validateSearch.setEnabled(true);
            }
        });
    }

    //It used for execute HTTP request when user doesn't input date and get results
    private void executeHttpRequestWithoutDate(){

        sectionsChecked = sectionsChecked.deleteCharAt(sectionsChecked.length()-1);
        String finalSections = sectionsChecked.toString();

        NYTStreams.streamSearchArticlesWhitoutDate(inputTerms, finalSections)
                .subscribeWith(new DisposableObserver<SearchArticlesMain>(){

            @Override
            public void onNext(SearchArticlesMain searchArticlesMain){
                if(searchArticlesMain.getResponse().getDocs().isEmpty()){
                    AlertDialog.Builder popUp = new AlertDialog.Builder(SearchActivity.this);
                    popUp.setTitle("Sorry")
                            .setMessage("No result found ! ")
                            .setNeutralButton("Retry", new DialogInterface.OnClickListener(){
                                public void onClick(DialogInterface dialog, int which){
                                    validateSearch.setEnabled(true);
                                }
                            })
                            .show();
                }
                else{
                    searchArticlesDoc.addAll(searchArticlesMain.getResponse().getDocs());
                    Intent searchResultsActivity = new Intent(SearchActivity.this, SearchResultsActivity.class);
                    startActivity(searchResultsActivity);
                }
                validateSearch.setEnabled(true);
            }

            @Override
            public void onError(Throwable e){
                validateSearch.setEnabled(true);
            }

            @Override
            public void onComplete(){
                validateSearch.setEnabled(true);
            }
        });
    }
}
