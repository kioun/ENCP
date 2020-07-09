package com.encp.projecttest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CirculationlistAdapter extends BaseAdapter {

    private Context context;
    private List<Circulationlist> circulationlistList;

    public CirculationlistAdapter(Context context, List<Circulationlist> circulationlistList) {
        this.context = context;
        this.circulationlistList = circulationlistList;
    }

    @Override
    public int getCount() {
        return circulationlistList.size();
    }

    @Override
    public Object getItem(int position) {
        return circulationlistList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context,R.layout.circulationuserlist,null);
        TextView ccuserlisttitle = (TextView) v.findViewById(R.id.ccuserlist);
        TextView ccuserlistcc = (TextView) v.findViewById(R.id.ccuserlistcc);


        ccuserlisttitle.setText(circulationlistList.get(position).getCclisttitle());
        ccuserlistcc.setText(circulationlistList.get(position).getCclistcirculation1());

        String ccuerlistuserID = circulationlistList.get(position).getCclistuserID();
        String ccuerlistpjcName = circulationlistList.get(position).getCclistpjcName();
        String ccuerlistpjcGroup = circulationlistList.get(position).getCclistpjcGroup();
        String ccuerlistuserName = circulationlistList.get(position).getCclistuserName();
        String ccuerlistrecipient = circulationlistList.get(position).getCclistrecipient();
        String ccuerlistcontents = circulationlistList.get(position).getCclistcontents();

        v.setTag(circulationlistList.get(position).getCclisttitle());

        return v;
    }
}
