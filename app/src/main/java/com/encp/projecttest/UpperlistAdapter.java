package com.encp.projecttest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UpperlistAdapter extends BaseAdapter {

    private Context context;
    private List<Upperlist> upperlistList;

    public UpperlistAdapter(Context context, List<Upperlist> upperlistList) {
        this.context = context;
        this.upperlistList = upperlistList;
    }

    @Override
    public int getCount() {
        return upperlistList.size();
    }

    @Override
    public Object getItem(int position) {
        return upperlistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.upperuserlist,null);
        TextView UpperlistuserName = (TextView) v.findViewById(R.id.upperuserlistname);
        TextView Upperlisttitle = (TextView) v.findViewById(R.id.upperuserlisttitle);

        UpperlistuserName.setText(upperlistList.get(position).getUpperlistuserName());
        Upperlisttitle.setText(upperlistList.get(position).getUpperlisttitle());

        String upperlistdrafter = upperlistList.get(position).getUpperlistdrafter();
        String upperlistreview = upperlistList.get(position).getUpperlistreview();
        String upperlistpayment = upperlistList.get(position).getUpperlistpayment();
        String upperlistcontents = upperlistList.get(position).getUpperlistcontents();
        String upperlistuserID = upperlistList.get(position).getUpperlistuserID();
        String upperlistpjcName = upperlistList.get(position).getUpperlistpjcName();
        String upperlistpjcGroup = upperlistList.get(position).getUpperlistpjcGroup();
        String upperlistrecipient = upperlistList.get(position).getUpperlistrecipient();
        int upperlistdistributecount = upperlistList.get(position).getUpperlistdistributecount();

        v.setTag(upperlistList.get(position).getUpperlistuserName());
        return v;
    }

}
