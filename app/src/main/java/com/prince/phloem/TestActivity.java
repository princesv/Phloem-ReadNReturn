package com.prince.phloem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class TestActivity extends AppCompatActivity {

    Button buttonAdd;
    Button buttonNull;
    TextView text;
    DatabaseReference dbr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        buttonAdd=findViewById(R.id.buttonadd);
        buttonNull=findViewById(R.id.buttonnull);
        text=findViewById(R.id.textView);
        Intent intent=getIntent();
        final String idNumber=intent.getStringExtra("ID");
        final String nameeee=intent.getStringExtra("NAME");
        dbr=FirebaseDatabase.getInstance().getReference("stateeeeeee").child(nameeee);
        text.setText(idNumber);






        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int s=1;
                Status stateData=new Status(idNumber,s);
               dbr.setValue(stateData);
                Toast.makeText(TestActivity.this, "update successful", Toast.LENGTH_SHORT).show();

            }
        });
        buttonNull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int s=0;
                Status stateData=new Status(idNumber,s);
                dbr.setValue(stateData);
                Toast.makeText(TestActivity.this, "saved successfully", Toast.LENGTH_SHORT).show();


            }
        });
    }
}
