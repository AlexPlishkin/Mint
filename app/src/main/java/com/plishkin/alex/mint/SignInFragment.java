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
import android.widget.Toast;

import com.plishkin.alex.mint.Adapters.MyRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class SignInFragment extends Fragment {


    private RecyclerView myRecyclerView;
    private RecyclerView.Adapter myAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //String login = getActivity().getIntent().getStringExtra("login");

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        myRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_list);
        String[] data = {"1", "2", "3"};
        myRecyclerView.setAdapter(new MyRecyclerViewAdapter(data));
        myRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
