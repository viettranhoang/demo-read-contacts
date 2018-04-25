package com.vit.demoreadcontact;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ContactAdapter extends BaseAdapter {

    private Context mContext;
    private List<Contact> mListContact;
    private LayoutInflater mLayoutInflater;

    public ContactAdapter(Context mContext, List<Contact> mPersonList) {
        this.mContext = mContext;
        this.mListContact = mPersonList;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mListContact.size();
    }

    @Override
    public Object getItem(int position) {
        return mListContact.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_contact, null);

            holder = new ViewHolder();
            holder.imageAvatar = convertView.findViewById(R.id.image_avatar);
            holder.textName = convertView.findViewById(R.id.text_name);
            holder.textPhoneNumber = convertView.findViewById(R.id.text_phone_number);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Contact contact = mListContact.get(position);
        holder.textName.setText(contact.getName());
        holder.textPhoneNumber.setText(contact.getPhoneNumber());

        if (contact.getAvatar() != null) {
            holder.imageAvatar.setImageBitmap(contact.getAvatar());
        } else {
            holder.imageAvatar.setImageResource(R.mipmap.ic_contact);
        }

        return convertView;
    }

    class ViewHolder {
        ImageView imageAvatar;
        TextView textName;
        TextView textPhoneNumber;
    }

}
