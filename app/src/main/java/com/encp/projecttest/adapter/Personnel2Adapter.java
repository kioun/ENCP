package com.encp.projecttest.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.encp.projecttest.Detail2;
import com.encp.projecttest.item.Personnel2Item;
import com.encp.projecttest.R;

import java.util.List;

public class Personnel2Adapter extends RecyclerView.Adapter<Personnel2Adapter.Personnel2ViewHolder> {

    private Context context;
    private List<Personnel2Item> person2list;

    public Personnel2Adapter(Context context, List<Personnel2Item> person2list) {
        this.context = context;
        this.person2list = person2list;
    }

    public class Personnel2ViewHolder extends RecyclerView.ViewHolder{
        public TextView personID;
        public TextView personName;
        public TextView personGroup;
        public TextView personPosition;

        public Personnel2ViewHolder(View view) {
            super(view);
            this.personID = (TextView)view.findViewById(R.id.personID);
            this.personName = (TextView) view.findViewById(R.id.personName);
            this.personGroup = (TextView) view.findViewById(R.id.personGroup);
            this.personPosition = (TextView) view.findViewById(R.id.personPosition);
        }
    }

    @Override
    public Personnel2ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View personnel2layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.personlist,parent,false);
        Personnel2ViewHolder personnel2ViewHolder = new Personnel2ViewHolder(personnel2layout);
        return personnel2ViewHolder;
    }

    @Override
    public void onBindViewHolder(Personnel2ViewHolder holder, final int position) {
        holder.personID.setText(person2list.get(position).getPersonnel2ID());
        holder.personName.setText(person2list.get(position).getPersonnel2Name());
        holder.personGroup.setText(person2list.get(position).getPersonnel2Group());
        holder.personPosition.setText(person2list.get(position).getPersonnel2Position());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context = view.getContext();
                Intent intent = new Intent(view.getContext(), Detail2.class);
                intent.putExtra("pid",person2list.get(position).getPersonnel2ID());
                intent.putExtra("pname",person2list.get(position).getPersonnel2Name());
                intent.putExtra("pposition",person2list.get(position).getPersonnel2Group());
                intent.putExtra("pgroup",person2list.get(position).getPersonnel2Position());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return person2list.size();
    }

}
