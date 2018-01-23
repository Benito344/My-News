package com.behague.benjamin.mynews.Controllers.Activities;

import android.app.DatePickerDialog;
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

import com.behague.benjamin.mynews.Models.SearchArticles.SearchArticlesDoc;
import com.behague.benjamin.mynews.Models.SearchArticles.SearchArticlesMain;
import com.behague.benjamin.mynews.R;
import com.behague.benjamin.mynews.Utils.NYTStreams;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener {

    private Disposable disposable;

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

        this.configureToolbar();

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
                    SearchActivity.this.executeHttpRequestWithoutDate();
                }
                else {
                    searchArticlesDoc = new ArrayList<>();
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
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onClick(View v) {
        if(v == beginDate) {
            beginDatePickerDialog();
        } else if(v == endDate) {
            endDatePickerDialog();
        }
    }

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

    private void executeHttpRequest(){

        sectionsChecked = sectionsChecked.deleteCharAt(sectionsChecked.length()-1);
        String finalSections = sectionsChecked.toString();
        String beginDate = inputBeginDate.replace("/", "");
        String endDate = inputEndDate.replace("/", "");

        this.disposable = NYTStreams.streamSearchArticles(inputTerms, beginDate
                , endDate, finalSections).subscribeWith(new DisposableObserver<SearchArticlesMain>(){

            @Override
            public void onNext(SearchArticlesMain searchArticlesMain){
                if(searchArticlesMain.getResponse().getDocs().isEmpty()){
                    Log.e("TAG", "Empty");
                }
                else{
                    searchArticlesDoc.addAll(searchArticlesMain.getResponse().getDocs());
                    Intent searchResultsActivity = new Intent(SearchActivity.this, SearchResultsActivity.class);
                    startActivity(searchResultsActivity);
                    }
            }

            @Override
            public void onError(Throwable e){
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onComplete(){
                Log.e("TAG","Completed");
            }
        });
    }

    private void executeHttpRequestWithoutDate(){

        sectionsChecked = sectionsChecked.deleteCharAt(sectionsChecked.length()-1);
        String finalSections = sectionsChecked.toString();

        this.disposable = NYTStreams.streamSearchArticlesWhitoutDate(inputTerms, finalSections)
                .subscribeWith(new DisposableObserver<SearchArticlesMain>(){

            @Override
            public void onNext(SearchArticlesMain searchArticlesMain){
                if(searchArticlesMain.getResponse().getDocs().isEmpty()){
                    Log.e("TAG", "Empty");
                }
                else{
                    searchArticlesDoc.addAll(searchArticlesMain.getResponse().getDocs());
                    Intent searchResultsActivity = new Intent(SearchActivity.this, SearchResultsActivity.class);
                    startActivity(searchResultsActivity);
                }
            }

            @Override
            public void onError(Throwable e){
                Log.e("TAG", e.getMessage());
            }

            @Override
            public void onComplete(){
                Log.e("TAG","Completed");
            }
        });
    }
}
