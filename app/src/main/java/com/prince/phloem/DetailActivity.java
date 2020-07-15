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
    Button buttonNotDelivered;
    Button buttonDelivered;
    Button notRetrieved;
    Button retrieved;
    Button notAvailable;
    Button available;
    DatabaseReference statusDatabaseReference;


    TextView idd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        idd=findViewById(R.id.iddddd);
        buttonDelivered=findViewById(R.id.ldelivered);
        buttonNotDelivered=findViewById(R.id.lnotdelivered);
        notRetrieved=findViewById(R.id.lunretrieved);
        retrieved=findViewById(R.id.lretrieved);
        notAvailable=findViewById(R.id.lunavailable);
        available=findViewById(R.id.lavailable);
        physics=findViewById(R.id.lphysics_content);
        chemistry=findViewById(R.id.lchemistry_content);
        mathematics=findViewById(R.id.lmathematics_content);
        economics=findViewById(R.id.leconomics_content);
        accounts=findViewById(R.id.laccounts_content);
        bst=findViewById(R.id.lbst_content);
        fnf=findViewById(R.id.lfnf_content);
        competitiveBooks=findViewById(R.id.lcompetitive_content);
        others=findViewById(R.id.lothers_content);
        Intent intent=getIntent();
        final String iddd=intent.getStringExtra("ID");
        final String nameOrder=intent.getStringExtra("NAME");
        idd.setText(iddd);
        physics.setText(intent.getStringExtra("PHYSICS"));
        chemistry.setText(intent.getStringExtra("CHEMISTRY"));
        mathematics.setText(intent.getStringExtra("MATHEMATICS"));
        economics.setText(intent.getStringExtra("ECONOMICS"));
        accounts.setText(intent.getStringExtra("ACCOUNTS"));
        bst.setText(intent.getStringExtra("BST"));
        fnf.setText(intent.getStringExtra("FNF"));
        competitiveBooks.setText(intent.getStringExtra("COMPETITIVEBOOKS"));
        others.setText(intent.getStringExtra("OTHERS"));

        statusDatabaseReference=FirebaseDatabase.getInstance().getReference("Status").child(nameOrder);


        buttonNotDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=0;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });
        buttonDelivered.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=1;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });
        notRetrieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=2;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });
        retrieved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=3;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });
        notAvailable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=4;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });
        available.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int s=5;

                Status status=new Status(nameOrder,s);
                statusDatabaseReference.setValue(status);
                Toast.makeText(DetailActivity.this, "update successful", Toast.LENGTH_SHORT).show();
            }
        });


    }


}
