package com.example.berkan.stoktakipuygulamasi;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class cadapter  extends BaseAdapter
{

    LayoutInflater LI;
    ArrayList FriendsList;

    cadapter(Context con, ArrayList<chatt> al)
    {
        LI = (LayoutInflater) con.getSystemService(con.LAYOUT_INFLATER_SERVICE);
        FriendsList = al;
    }

    @Override
    public int getCount() {
        return FriendsList.size();
    }

    @Override
    public Object getItem(int i) {
        return FriendsList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View v;
        if (view == null)
        {
            v = LI.inflate(R.layout.customlist, null);
        }

        else
        {
            v = view;
        }
        TextView tvName = (TextView) v.findViewById(R.id.textView4);
        TextView tvMessage = (TextView) v.findViewById(R.id.textView5);
        TextView tvdate = (TextView) v.findViewById(R.id.textView11);

        Calendar c = Calendar.getInstance();
        System.out.println("Current time => "+c.getTime());

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = df.format(c.getTime());


        chatt f = (chatt) getItem(i);
        tvName.setText(f.getName());
        tvMessage.setText(f.getMessage());
        tvdate.setText("" + formattedDate);
        return v;
    }
}