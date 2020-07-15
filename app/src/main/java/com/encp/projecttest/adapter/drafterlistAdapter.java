package com.encp.projecttest.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.R;
import com.encp.projecttest.item.listitem;

import java.util.List;

public class drafterlistAdapter extends RecyclerView.Adapter<drafterlistAdapter.UpperViewHolder> {

    private Context context;
    private List<listitem> upperItemList;

    public drafterlistAdapter(Context context, List<listitem> upperItemList) {
        this.context = context;
        this.upperItemList = upperItemList;
    }

    public class UpperViewHolder extends RecyclerView.ViewHolder{
        public TextView listgp;
        public TextView listps;
        public TextView listnm;
        public LinearLayout Upperlistlayout;

        public UpperViewHolder(View view) {
            super(view);
            this.listgp = (TextView)view.findViewById(R.id.listgp);
            this.listps = (TextView) view.findViewById(R.id.listps);
            this.listnm = (TextView) view.findViewById(R.id.listnm);
            this.Upperlistlayout = (LinearLayout) view.findViewById(R.id.upperuserlistlayout);

        }
    }

    @Override
    public UpperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View upperlayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.drafterlist,parent,false);
        UpperViewHolder upperViewHolder = new UpperViewHolder(upperlayout);
        return upperViewHolder;
    }

    @Override
    public void onBindViewHolder(UpperViewHolder holder, final int position) {
        holder.listgp.setText(upperItemList.get(position).getListgroup());
        holder.listps.setText(upperItemList.get(position).getListposition());
        holder.listnm.setText(upperItemList.get(position).getListname());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                String listgroup = upperItemList.get(position).getListgroup();
                String listposition = upperItemList.get(position).getListposition();
                String listname = upperItemList.get(position).getListname();
                int listdraftercount = upperItemList.get(position).getListdraftercount();
                int listreviewcount = upperItemList.get(position).getListreviewcount();
                int listpaymentcount = upperItemList.get(position).getListpaymentcount();

                Intent intent = new Intent();
                intent.putExtra("review",listname);
                intent.putExtra("reviewgroup",listgroup);
                intent.putExtra("reviewposition",listposition);
                intent.putExtra("rvreviewcount",listreviewcount);

                intent.putExtra("drafter",listname);
                intent.putExtra("draftergroup", listgroup);
                intent.putExtra("drafterposition",listposition);
                intent.putExtra("dfdraftercount",listdraftercount);

                intent.putExtra("payment",listname);
                intent.putExtra("paymentgroup",listgroup);
                intent.putExtra("paymentposition",listposition);
                intent.putExtra("pmpaymentcount",listpaymentcount);

                intent.putExtra("recipient",listname);
                intent.putExtra("recipientgroup",listgroup);
                intent.putExtra("recipientposition",listposition);

                ((Activity)context).setResult(Activity.RESULT_OK,intent);
                ((Activity)context).finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return upperItemList.size();
    }

}
