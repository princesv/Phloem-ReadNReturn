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

import java.util.List;

public class Adapter extends ArrayAdapter {
    Activity context;
    List<Order> orderList;
    List<Status> statusList;

    public Adapter(Activity context,List<Order> orderList,List<Status> statusList){
        super(context,R.layout.list_layout,orderList);
        this.context=context;
        this.orderList=orderList;
        this.statusList=statusList;

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
        for(int i=0;i<statusList.size();i++){
            Status objStatus=statusList.get(i);
            if (order.getName().equals(objStatus.getIdNum())){
                if(objStatus.getS()==1){
                    tvStatus.setText("Acknowledged");
                    tvStatus.setTextColor(Color.parseColor("#008577"));
                }
                else if(objStatus.getS()==2){
                    tvStatus.setText("Transaction completed");
                    tvStatus.setTextColor(Color.parseColor("#00574B"));}
            }

        }
        return listViewItem;
    }
}
