package com.prince.phloem;
import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Adapter extends ArrayAdapter {
    Activity context;
    List<Order> orderList;
    HashMap<String, Integer> statusMap;

    public Adapter(Activity context, List<Order> orderList, HashMap<String , Integer> statusMap){
        super(context,R.layout.list_layout,orderList);
        this.context=context;
        this.orderList=orderList;
        this.statusMap=statusMap;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order=orderList.get(position);
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_layout,null,true);
        TextView tvName=listViewItem.findViewById(R.id.tvname);
        TextView tvContact=listViewItem.findViewById(R.id.tvcontact);
        TextView tvStatus=listViewItem.findViewById(R.id.status);
        tvName.setText(order.getName());
        tvContact.setText(""+order.getContact());
            if (statusMap.containsKey(order.getId().toString())){
                if(statusMap.get(order.getId().toString())==1){
                    tvStatus.setText("Acknowledged");
                    tvStatus.setTextColor(Color.parseColor("#008577"));
                }
                else if(statusMap.get(order.getId().toString())==2){
                    tvStatus.setText("Transaction completed");
                    tvStatus.setTextColor(Color.parseColor("#00574B"));
                }
            }
        return listViewItem;
    }
}
