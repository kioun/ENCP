package com.encp.projecttest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class UserListAdapter extends BaseAdapter {

    private Context context;
    private List<UserList> userList;

    public UserListAdapter(Context context, List<UserList> userList) {
        this.context = context;
        this.userList = userList;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.userlist,null);
        TextView userName = (TextView) v.findViewById(R.id.userName);
        TextView userPosition = (TextView) v.findViewById(R.id.userPosition);
        TextView userGroup = (TextView) v.findViewById(R.id.userGroup);
        TextView userRanking = (TextView) v.findViewById(R.id.userRanking);

        userName.setText(userList.get(position).getUserName());
        userPosition.setText(userList.get(position).getUserPosition());
        userGroup.setText(userList.get(position).getUserGroup());
        userRanking.setText(userList.get(position).getUserRanking());

        v.setTag(userList.get(position).getUserName());
        return v;
    }
}
