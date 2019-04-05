package com.example.dhruvil.spit_it_out.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.dhruvil.spit_it_out.Models.ContactModel;
import com.example.dhruvil.spit_it_out.R;

import java.util.ArrayList;

/**
 * Created by Parsania Hardik on 11-May-17.
 */
public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ContactModel> contactModelArrayList;
    private ArrayList<ContactModel> contactListFiltered;



    public ArrayList<ContactModel> getContactModelArrayList() {
        return contactModelArrayList;
    }

    public void setContactModelArrayList(ArrayList<ContactModel> contactModelArrayList) {
        this.contactModelArrayList = contactModelArrayList;
    }

    public CustomAdapter(Context context, ArrayList<ContactModel> contactModelArrayList) {
        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
    }

    @Override
    public int getViewTypeCount() {
        return getCount();
    }

    @Override
    public int getItemViewType(int position) {

        return position;
    }

    @Override
    public int getCount() {
        return contactModelArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return contactModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.tvname = convertView.findViewById(R.id.name);
            holder.tvnumber = convertView.findViewById(R.id.number);
            holder.checkBox = convertView.findViewById(R.id.checkBox_selected);
            convertView.setTag(holder);
        } else {

            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvname.setText(contactModelArrayList.get(position).getName());
        holder.tvnumber.setText(contactModelArrayList.get(position).getNumber());
        holder.checkBox.setTag(position);
        holder.checkBox.setChecked(contactModelArrayList.get(position).isChecked());
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                contactModelArrayList.get(position).setChecked(isChecked);

            }
        });

        return convertView;
    }
    private class ViewHolder {

        public CheckBox checkBox;
        protected TextView tvname, tvnumber;

    }


}