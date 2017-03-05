package com.plishkin.alex.mint.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Entities.Contact;
import com.plishkin.alex.mint.R;

import java.util.ArrayList;


public class ContactsRecyclerViewAdaper extends RecyclerView.Adapter<ContactsRecyclerViewAdaper.ContactHolder> {

    public static class ContactHolder extends RecyclerView.ViewHolder{

        public TextView contact_name;

        public TextView phone;

        public Context context;

        public ContactHolder(final View itemView) {
            super(itemView);
            context = itemView.getContext();
            contact_name = (TextView) itemView.findViewById(R.id.contact_name);
            phone = (TextView) itemView.findViewById(R.id.contact_phone_number);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:"+phone.getText()));
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    private ArrayList<Contact> baseList;
    private ArrayList<Contact> currentList;

    public ContactsRecyclerViewAdaper(ArrayList<Contact> contacts) {
        this.baseList = new ArrayList<>(contacts);
        this.currentList = new ArrayList<>(contacts);
    }

    @Override
    public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_viw_row, null);
        return new ContactsRecyclerViewAdaper.ContactHolder(view);
    }

    @Override
    public void onBindViewHolder(ContactHolder holder, int position) {
        holder.contact_name.setText(currentList.get(position).getName());
        holder.phone.setText(currentList.get(position).getPhoneNumber());

    }

    @Override
    public int getItemCount() {
        return currentList.size();
    }



}
