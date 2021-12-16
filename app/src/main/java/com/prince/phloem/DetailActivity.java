package com.prince.phloem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DetailActivity extends AppCompatActivity {

    TextView physics;
    TextView chemistry;
    TextView mathematics;
    TextView economics;
    TextView accounts;
    TextView bst;
    TextView fnf;
    TextView competitiveBooks;
    TextView others;
    Button buttonAcknowledged;
    Button buttonComplete;
    DatabaseReference statusDatabaseReference;
    String incidenceKey;
    String incidenceId;
    ProgressBar progressBar;
    ScrollView orderDetails;
    MyTask runningTask;
    final String API_KEY="u+Bx-Rxtxjfp8XxaxR_Q";
    String emailAddress;
    RadioGroup radioGroup;
    int status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        SharedPreferences pref=this.getSharedPreferences("myPref",MODE_PRIVATE);
        radioGroup=findViewById(R.id.radioGroup);
        emailAddress=pref.getString("auth-id",null);
        physics=findViewById(R.id.lphysics_content);
        chemistry=findViewById(R.id.lchemistry_content);
        mathematics=findViewById(R.id.lmathematics_content);
        economics=findViewById(R.id.leconomics_content);
        accounts=findViewById(R.id.laccounts_content);
        bst=findViewById(R.id.lbst_content);
        fnf=findViewById(R.id.lfnf_content);
        competitiveBooks=findViewById(R.id.lcompetitive_content);
        others=findViewById(R.id.lothers_content);
        progressBar=findViewById(R.id.progress_circ);
        orderDetails=findViewById(R.id.orderDetalis);
        Intent intent=getIntent();
        incidenceKey=intent.getStringExtra("ID");
        final String nameOrder=intent.getStringExtra("NAME");
        physics.setText(intent.getStringExtra("PHYSICS"));
        chemistry.setText(intent.getStringExtra("CHEMISTRY"));
        mathematics.setText(intent.getStringExtra("MATHEMATICS"));
        economics.setText(intent.getStringExtra("ECONOMICS"));
        accounts.setText(intent.getStringExtra("ACCOUNTS"));
        bst.setText(intent.getStringExtra("BST"));
        fnf.setText(intent.getStringExtra("FNF"));
        competitiveBooks.setText(intent.getStringExtra("COMPETITIVEBOOKS"));
        others.setText(intent.getStringExtra("OTHERS"));
        status=intent.getIntExtra("state",0);
        setDefaultRadioButton(status);
        statusDatabaseReference=FirebaseDatabase.getInstance().getReference("Status").child(incidenceKey);
        getIdFromKey();
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int s=0;
                if(i==R.id.radioButton1){
                    s=0;
                }else if(i==R.id.radioButton2){
                    s=1;
                    sendRequestAcknowledged();
                }else if(i==R.id.radioButton3){
                    s=2;
                }else if(i==R.id.radioButton4){
                    s=3;
                    sendRequestResolved();
                }else if(i==R.id.radioButton5){
                    s=4;
                    sendRequestResolved();
                }
                Status status=new Status(incidenceKey,s);
                statusDatabaseReference.setValue(status);
            }
        });
    }
    void setDefaultRadioButton(int status){
        if(status==0){
            radioGroup.check(R.id.radioButton1);
        }else if(status==1){
            radioGroup.check(R.id.radioButton2);
        }else if(status==2){
            radioGroup.check(R.id.radioButton3);
        }else if(status==3){
            radioGroup.check(R.id.radioButton4);
        }else if(status==4){
            radioGroup.check(R.id.radioButton5);
        }
    }
    void sendRequestResolved(){

        final OkHttpClient client = new OkHttpClient();
        if(incidenceId==null){
            Toast.makeText(this, "Old response. Action not possible", Toast.LENGTH_SHORT).show();
            return;
        }
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody payload = RequestBody.create(mediaType, "{\n  \"incidents\": [\n    {\n      \"id\": \""+incidenceId+"\",\n      \"type\": \"incident_reference\",\n      \"status\": \"resolved\"\n    }\n  ]\n}");
        final Request request = new Request.Builder()
                .url("https://api.pagerduty.com/incidents")
                .put(payload)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/vnd.pagerduty+json;version=2")
                .addHeader("From", emailAddress)
                .addHeader("Authorization", "Token token="+API_KEY)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    String s1=response.body().string();
                    String s2=response.message();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void sendRequestAcknowledged(){
        final OkHttpClient client = new OkHttpClient();
        if(incidenceId==null){
            return;
        }
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody payload = RequestBody.create(mediaType, "{\n  \"incidents\": [\n    {\n      \"id\": \""+incidenceId+"\",\n      \"type\": \"incident_reference\",\n      \"status\": \"acknowledged\"\n    }\n  ]\n}");
        final Request request = new Request.Builder()
                .url("https://api.pagerduty.com/incidents")
                .put(payload)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/vnd.pagerduty+json;version=2")
                .addHeader("From", emailAddress)
                .addHeader("Authorization", "Token token="+API_KEY)
                .build();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = client.newCall(request).execute();
                    String s1=response.body().string();
                    String s2=response.message();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    void getIdFromKey(){
       if(runningTask!=null){
           runningTask.cancel(true);
       }
       runningTask=new MyTask();
       runningTask.execute();
    }
    private  class MyTask extends AsyncTask<Void,Void,Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            final OkHttpClient client = new OkHttpClient();

            final Request request = new Request.Builder()
                    .url("https://api.pagerduty.com/incidents?incident_key="+incidenceKey+"&time_zone=UTC")
                    .get()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/vnd.pagerduty+json;version=2")
                    .addHeader("Authorization", "Token token="+API_KEY)
                    .build();
            try {
                Response response = client.newCall(request).execute();
                String s1=response.body().string();
                JSONObject resObject=new JSONObject(s1);
                JSONArray incidences=resObject.getJSONArray("incidents");
                if(incidences.toString().equals("[]")){
                    return null;
                }
                JSONObject incidence0=incidences.getJSONObject(0);
                JSONObject logEntry=incidence0.getJSONObject("first_trigger_log_entry");
                String htmlUrl=logEntry.getString("html_url");
                String id="";
                int i=39;
                while(htmlUrl.charAt(i)!='/'){
                    id+=htmlUrl.charAt(i);
                    i++;
                }
                incidenceId=id;
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            progressBar.setVisibility(View.INVISIBLE);
            orderDetails.setVisibility(View.VISIBLE);
        }
    }
}
