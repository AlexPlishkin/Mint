package com.plishkin.alex.mint;

import android.app.Application;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.plishkin.alex.mint.Adapters.MyRecyclerViewAdapter;
import com.plishkin.alex.mint.Db.DatabaseHelper;
import com.plishkin.alex.mint.Db.FruitDAO;
import com.plishkin.alex.mint.Db.HelperFactory;
import com.plishkin.alex.mint.Entities.Fruit;

import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class WelcomeFragment extends Fragment {

    public MyRecyclerViewAdapter myRecyclerViewAdapter;

    AlertDialog addFruitDialog;

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

        View view = inflater.inflate(R.layout.fragment_welcome, container, false);
        ButterKnife.bind(this, view);

        Gson gson = new Gson();
        try {
            JsonReader jsonReader = new JsonReader(new InputStreamReader(getActivity().getAssets().open("fruits.json")));
            ArrayList<Fruit> fruitArrayList = new ArrayList<>();
            Fruit[] fruits = gson.fromJson(jsonReader, Fruit[].class);

            for (Fruit fruit : fruits){
                fruitArrayList.add(fruit);
            }

            final FruitDAO fruitDAO = HelperFactory.getHelper().getFruitDAO();
            for (Fruit fruit : fruitDAO.getAllFruits()){
                fruitArrayList.add(fruit);
            }

            myRecyclerViewAdapter = new MyRecyclerViewAdapter(fruitArrayList);
            if (getActivity() instanceof SingInActivity){
                SingInActivity activity = (SingInActivity) getActivity();
                activity.adapter = myRecyclerViewAdapter;
            }

            myRecyclerView.setAdapter(myRecyclerViewAdapter);
            myRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext()));

            //Initialize alert dialog add
            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
            final View dialogView = inflater.inflate(R.layout.add_fruit, null);
            builder.setView(dialogView)
                    .setTitle("Add new fruit");

            addFruitDialog = builder.create();

            Button addFruit = (Button) dialogView.findViewById(R.id.add_new_fruit_button);
            addFruit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EditText text = (EditText) dialogView.findViewById(R.id.new_fruit_name_edit);
                    String fruitName = text.getText().toString();
                    if (!fruitName.equals("")){
                        Fruit fruit = new Fruit(fruitName);
                        myRecyclerViewAdapter.add(fruit);
                        try {
                            fruitDAO.create(fruit);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                    addFruitDialog.cancel();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return view;
    }

    @OnClick(R.id.floating_action_button)
    public void click(View view){
        addFruitDialog.show();
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }
}
