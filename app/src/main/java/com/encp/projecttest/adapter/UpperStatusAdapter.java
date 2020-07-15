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
import com.encp.projecttest.item.UpperItem;
import com.encp.projecttest.detail.UpperStatusDetail;

import java.util.List;

public class UpperStatusAdapter extends RecyclerView.Adapter<UpperStatusAdapter.UpperViewHolder> {

    private Context context;
    private List<UpperItem> upperItemList;
    private int distributecount2;
    private int noticenumber2;

    public UpperStatusAdapter(Context context, List<UpperItem> upperItemList) {
        this.context = context;
        this.upperItemList = upperItemList;
    }

    public class UpperViewHolder extends RecyclerView.ViewHolder{
        public TextView UpperlistuserName;
        public TextView Upperlisttitle;
        public LinearLayout Upperlistlayout;
        public TextView Upperlistimg;
        public TextView Upperlistimg2;
        public TextView Upperlistimg3;
        public TextView Upperlistimg4;

        public UpperViewHolder(View view) {
            super(view);
            this.UpperlistuserName = (TextView)view.findViewById(R.id.upperuserlistname);
            this.Upperlisttitle = (TextView) view.findViewById(R.id.upperuserlisttitle);
            this.Upperlistlayout = (LinearLayout) view.findViewById(R.id.upperuserlistlayout);
            this.Upperlistimg = (TextView) view.findViewById(R.id.upperuserlistbox1);
            this.Upperlistimg2 = (TextView) view.findViewById(R.id.upperuserlistbox2);
            this.Upperlistimg3 = (TextView) view.findViewById(R.id.upperuserlistbox3);
            this.Upperlistimg4 = (TextView) view.findViewById(R.id.upperuserlistbox4);
        }
    }

    @Override
    public UpperViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View upperlayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.upperuserlist,parent,false);
        UpperViewHolder upperViewHolder = new UpperViewHolder(upperlayout);
        return upperViewHolder;
    }

    @Override
    public void onBindViewHolder(UpperViewHolder holder, final int position) {
        holder.UpperlistuserName.setText(upperItemList.get(position).getUpperlistuserName());
        holder.Upperlisttitle.setText(upperItemList.get(position).getUpperlisttitle());


        distributecount2 = upperItemList.get(position).getUpperlistdistributecount();
        if (distributecount2 == 1){
            holder.Upperlistimg.setVisibility(View.VISIBLE);
        } else if (distributecount2 == 2){
            holder.Upperlistimg2.setVisibility(View.VISIBLE);
        } else if (distributecount2 == 3){
            holder.Upperlistimg3.setVisibility(View.VISIBLE);
        } else if (distributecount2 > 3){
            holder.Upperlistimg4.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                String drafter2 = upperItemList.get(position).getUpperlistdrafter();
                String pjcName2 = upperItemList.get(position).getUpperlistpjcName();
                String payment2 = upperItemList.get(position).getUpperlistpayment();
                String recipient2 = upperItemList.get(position).getUpperlistrecipient();
                String contents2 = upperItemList.get(position).getUpperlistcontents();
                String userName2 = upperItemList.get(position).getUpperlistuserName();
                String title2 = upperItemList.get(position).getUpperlisttitle();
                String pjcGroup2 = upperItemList.get(position).getUpperlistpjcGroup();
                String review2 = upperItemList.get(position).getUpperlistreview();
                distributecount2 = upperItemList.get(position).getUpperlistdistributecount();
                noticenumber2 = upperItemList.get(position).getUpperlistnoticenumber();

                Intent intent1 = new Intent(view.getContext(), UpperStatusDetail.class);
                intent1.putExtra("drafter", drafter2);
                intent1.putExtra("pjcName", pjcName2);
                intent1.putExtra("review", review2);
                intent1.putExtra("payment", payment2);
                intent1.putExtra("recipient", recipient2);
                intent1.putExtra("contents", contents2);
                intent1.putExtra("userName", userName2);
                intent1.putExtra("title", title2);
                intent1.putExtra("pjcGroup", pjcGroup2);
                intent1.putExtra("distributecount", distributecount2);
                intent1.putExtra("noticenumber",noticenumber2);
                context.startActivity(intent1);
                ((Activity)context).finish();
            }
        });
    }
    @Override
    public int getItemCount() {
        return upperItemList.size();
    }

}
