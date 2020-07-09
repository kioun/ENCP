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

import com.encp.projecttest.detail.Upper3Detail;

import java.util.List;

public class Upper3Adapter extends RecyclerView.Adapter<Upper3Adapter.UpperViewHolder> {

    private Context context;
    private List<UpperItem> upperItemList;
    private int distributecount2;

    public Upper3Adapter(Context context, List<UpperItem> upperItemList) {
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
            this.Upperlistimg = (TextView) view.findViewById(R.id.upperuserlistbox2);
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

        distributecount2 = upperItemList.get(position).getUpperlistdistributecount();
        if (distributecount2 > 1){
            holder.Upperlistimg.setVisibility(View.VISIBLE);
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
                int noticenumber2 = upperItemList.get(position).getUpperlistnoticenumber();

                Intent intent1 = new Intent(view.getContext(), Upper3Detail.class);
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
                intent1.putExtra("noticenumber", noticenumber2);
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
