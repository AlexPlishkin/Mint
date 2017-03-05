package com.plishkin.alex.mint;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.plishkin.alex.mint.Adapters.ContactsRecyclerViewAdaper;
import com.plishkin.alex.mint.Entities.Contact;
import com.plishkin.alex.mint.Tasks.AsyncResponseble;
import com.plishkin.alex.mint.Tasks.LoadContactsTask;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactsFragment extends Fragment implements AsyncResponseble {

    public ContactsFragment() {

    }

    @BindView(R.id.contact_recycler_view)
    public RecyclerView contactRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_contacts, container, false);
        ButterKnife.bind(this, view);


        return view;

    }

    @OnClick(R.id.load_contacts_button)
    public void click(View v) {

        LoadContactsTask contactsTask = new LoadContactsTask(
                v.getContext(),
                this,
                new LoadContactsTask.ContactsTaskData(this.getActivity().getContentResolver())
        );

        contactsTask.execute();

    }

    @Override
    public void getResponse(Object response) {
        List<Contact> contacts = (List<Contact>) response;
        ContactsRecyclerViewAdaper adaper = new ContactsRecyclerViewAdaper((ArrayList<Contact>) contacts);
        contactRecyclerView.setAdapter(adaper);
        contactRecyclerView.setLayoutManager(new LinearLayoutManager(ContactsFragment.this.getActivity().getBaseContext()));
    }
}
