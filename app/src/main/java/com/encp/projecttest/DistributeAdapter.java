package com.encp.projecttest;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.detail.DistributeDetail;

import java.util.List;

public class DistributeAdapter extends RecyclerView.Adapter<DistributeAdapter.UpperViewHolder> {

    private Context context;
    private List<UpperItem> upperItemList;
    private int distributecount5;

    public DistributeAdapter(Context context, List<UpperItem> upperItemList) {
        this.context = context;
        this.upperItemList = upperItemList;
    }

    public class UpperViewHolder extends RecyclerView.ViewHolder{
        public TextView UpperlistuserName;
        public TextView Upperlisttitle;
        public LinearLayout Upperlistlayout;
        public TextView Upperlistimg;

        public UpperViewHolder(View view) {
            super(view);
            this.UpperlistuserName = (TextView)view.findViewById(R.id.upperuserlistname);
            this.Upperlisttitle = (TextView) view.findViewById(R.id.upperuserlisttitle);
            this.Upperlistlayout = (LinearLayout) view.findViewById(R.id.upperuserlistlayout);
            this.Upperlistimg = (TextView) view.findViewById(R.id.upperuserlistbox4);
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
        holder.UpperlistuserName.setText(upperItemList.get(position).UpperlistuserName);
        holder.Upperlisttitle.setText(upperItemList.get(position).Upperlisttitle);

        distributecount5 = upperItemList.get(position).getUpperlistdistributecount();
        if (distributecount5 > 3){
            holder.Upperlistimg.setVisibility(View.VISIBLE);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                String drafter5 = upperItemList.get(position).getUpperlistdrafter();
                String pjcName5 = upperItemList.get(position).getUpperlistpjcName();
                String payment5 = upperItemList.get(position).getUpperlistpayment();
                String recipient5 = upperItemList.get(position).getUpperlistrecipient();
                String contents5 = upperItemList.get(position).getUpperlistcontents();
                String userName5 = upperItemList.get(position).getUpperlistuserName();
                String title5 = upperItemList.get(position).getUpperlisttitle();
                String pjcGroup5 = upperItemList.get(position).getUpperlistpjcGroup();
                String review5 = upperItemList.get(position).getUpperlistreview();
                distributecount5 = upperItemList.get(position).getUpperlistdistributecount();
                int noticenumber5 = upperItemList.get(position).getUpperlistnoticenumber();


                Intent intent1 = new Intent(view.getContext(), DistributeDetail.class);
                intent1.putExtra("drafter", drafter5);
                intent1.putExtra("pjcName", pjcName5);
                intent1.putExtra("review", review5);
                intent1.putExtra("payment", payment5);
                intent1.putExtra("recipient", recipient5);
                intent1.putExtra("contents", contents5);
                intent1.putExtra("userName", userName5);
                intent1.putExtra("title", title5);
                intent1.putExtra("pjcGroup", pjcGroup5);
                intent1.putExtra("distributecount", distributecount5);
                intent1.putExtra("noticenumber", noticenumber5);
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
