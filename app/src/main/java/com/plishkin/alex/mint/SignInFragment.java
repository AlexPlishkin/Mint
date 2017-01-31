package com.plishkin.alex.mint;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.plishkin.alex.mint.Adapters.MyRecyclerViewAdapter;
import com.plishkin.alex.mint.Entities.User;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignInFragment extends Fragment {

    @BindView(R.id.recycle_list)
    public RecyclerView myRecyclerView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //String login = getActivity().getIntent().getStringExtra("login");

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);

        Gson gson = new Gson();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(getActivity().getAssets().open("users.json")));
            ArrayList<User> userArrayList = new ArrayList<>();
            User[] users = gson.fromJson(jsonReader, User[].class);

            for (User user : users){
                userArrayList.add(user);
            }

            myRecyclerView.setAdapter(new MyRecyclerViewAdapter(userArrayList));
            myRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
