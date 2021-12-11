package com.prince.phloem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView orderList;
    DatabaseReference databaseReferenceOrder;
    DatabaseReference databaseReferenceStatus;
    List<Order> orders;
    SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    HashMap<String, Integer> statusMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        statusMap=new HashMap<>();
        preferences=getApplicationContext().getSharedPreferences("myPref",0);
        editor=preferences.edit();
        if(preferences.getString("auth-id",null)==null){
            Intent intent=new Intent(MainActivity.this,AuthActivity.class);
            startActivity(intent);
        }
        orderList=findViewById(R.id.list_order);
        orders=new ArrayList<>();
        databaseReferenceOrder= FirebaseDatabase.getInstance().getReference("Mastersheet");
        databaseReferenceStatus=FirebaseDatabase.getInstance().getReference("Status");
        databaseReferenceStatus.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshotStatus:dataSnapshot.getChildren()){
                    Status status=snapshotStatus.getValue(Status.class);
                    statusMap.put(status.getIdNum(),status.getS());
                }
                Adapter adapter=new Adapter(MainActivity.this,orders,statusMap);
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
                Collections.reverse(orders);
                Adapter adapter=new Adapter(MainActivity.this,orders,statusMap);
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
                String timeId=order.getId().toString();
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case
                R.id.login_page:
                Intent intent=new Intent(MainActivity.this,AuthActivity.class);
            startActivity(intent);
            return true;
        }
        return true;
    }
}
