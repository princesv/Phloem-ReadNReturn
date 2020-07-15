package com.prince.phloem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView orderList;
    DatabaseReference databaseReferenceOrder;
    DatabaseReference databaseReferenceStatus;
    List<Order> orders;
    List<Status> statuses;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        orderList=findViewById(R.id.list_order);
        orders=new ArrayList<>();
        statuses=new ArrayList<>();
        databaseReferenceOrder= FirebaseDatabase.getInstance().getReference("Mastersheet");
        databaseReferenceStatus=FirebaseDatabase.getInstance().getReference("Status");
        databaseReferenceStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                statuses.clear();
                for (DataSnapshot snapshotStatus:dataSnapshot.getChildren()){
                    Status status=snapshotStatus.getValue(Status.class);
                    statuses.add(status);
                }
                Adapter adapter=new Adapter(MainActivity.this,orders,statuses);
                orderList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        databaseReferenceOrder.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                orders.clear();
                for(DataSnapshot snapshotOrder:dataSnapshot.getChildren()){
                    Order order=snapshotOrder.getValue(Order.class);
                    orders.add(order);
                }
                Adapter adapter=new Adapter(MainActivity.this,orders,statuses);
                orderList.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        orderList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                  Order order=orders.get(position);
                String timeId=order.getId();
                String name=order.getName();
                String contact=""+order.getContact();
                String physics=""+order.getPhysics();
                String chemistry=""+order.getChemistry();
                String mathematics=""+order.getMathematics();
                String economics=""+order.getEconomics();
                String accounts=""+order.getAccounts();
                String bst=""+order.getBst();
                String fnf=""+order.getFnf();
                String competitiveBooks=""+order.getCompetitiveBooks();
                String others=""+order.getOthers();
                Intent intent=new Intent(MainActivity.this,DetailActivity.class);
                intent.putExtra("NAME",name);
                intent.putExtra("ID",timeId);
                intent.putExtra("PHYSICS",""+physics);
                intent.putExtra("CHEMISTRY",""+chemistry);
                intent.putExtra("MATHEMATICS",""+mathematics);
                intent.putExtra("ECONOMICS",""+economics);
                intent.putExtra("ACCOUNTS",""+accounts);
                intent.putExtra("BST",""+bst);
                intent.putExtra("FNF",""+fnf);
                intent.putExtra("COMPETITIVEBOOKS",""+competitiveBooks);
                intent.putExtra("OTHERS",""+others);
                startActivity(intent);


            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();




    }
}
