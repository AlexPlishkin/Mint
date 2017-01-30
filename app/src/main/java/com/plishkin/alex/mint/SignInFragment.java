package com.plishkin.alex.mint;

import android.app.Fragment;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignInFragment extends Fragment {


    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager myLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //String login = getActivity().getIntent().getStringExtra("login");

        myRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_list);
        myRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));

        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
