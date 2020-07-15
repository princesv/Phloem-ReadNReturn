package com.prince.phloem;

import android.app.Activity;
import android.content.Context;
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
        super(context,R.layout.list_laayout,orderList);
        this.context=context;
        this.orderList=orderList;
        this.statusList=statusList;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Order order=orderList.get(position);
        LayoutInflater inflater=context.getLayoutInflater();
        View listViewItem=inflater.inflate(R.layout.list_laayout,null,true);
        TextView tvName=listViewItem.findViewById(R.id.tvname);
        TextView tvContact=listViewItem.findViewById(R.id.tvcontact);
        TextView tvDelivered=listViewItem.findViewById(R.id.tvdelivered);
        TextView tvRetrieved=listViewItem.findViewById(R.id.tvretrieved);
        TextView tvAvailable=listViewItem.findViewById(R.id.tvunavailable);
        tvName.setText(order.getName());
        tvContact.setText(""+order.getContact());
        for(Status objStatus:statusList){
            if (order.getName().equals(objStatus.getIdNum())){
                if(objStatus.getS()==0){}
                if(objStatus.getS()==1){tvDelivered.setText("Delivered");}
                if(objStatus.getS()==2){}
                if(objStatus.getS()==3){tvRetrieved.setText("Retrieved");}
                if(objStatus.getS()==4){tvAvailable.setText("Not available");}
                if(objStatus.getS()==5){}
            }

        }
        return listViewItem;
    }
}
