package com.plishkin.alex.mint.Adapters;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.plishkin.alex.mint.Db.FruitDAO;
import com.plishkin.alex.mint.Db.HelperFactory;
import com.plishkin.alex.mint.Entities.Fruit;
import com.plishkin.alex.mint.R;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fruit_name;

        public ImageButton editButton;

        public ImageButton deleteButton;

        public Context context;

        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            fruit_name = (TextView) itemView.findViewById(R.id.fruit_name);
            deleteButton = (ImageButton) itemView.findViewById(R.id.delete_fruit_button);
            editButton = (ImageButton) itemView.findViewById(R.id.edit_fruit_button);
        }
    }



    private ArrayList<Fruit> currentList;
    private ArrayList<Fruit> baseList;

    public MyRecyclerViewAdapter(ArrayList<Fruit> currentList) {
        this.currentList = new ArrayList<>(currentList);
        this.baseList = new ArrayList<>(currentList);
    }

    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, null);
        return new MyRecyclerViewAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.fruit_name.setText(currentList.get(position).getName());
        //Delete item

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit = currentList.get(holder.getAdapterPosition());
                if (baseList.contains(fruit)){
                    baseList.remove(baseList.indexOf(fruit));
                }
                currentList.remove(holder.getAdapterPosition());

                if (fruit.getId() != 0){
                    try {
                        FruitDAO fruitDAO = HelperFactory.getHelper().getFruitDAO();
                        fruitDAO.delete(fruit);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                MyRecyclerViewAdapter.this.notifyItemRemoved(holder.getAdapterPosition());
            }
        });

        //Edit item
        LayoutInflater layoutInflater = LayoutInflater.from(holder.context);
        final View editView = layoutInflater.inflate(R.layout.edit_fruit, null);

        final EditText fruitName = (EditText) editView.findViewById(R.id.edit_fruit_name_edit);
        fruitName.setText(currentList.get(holder.getAdapterPosition()).getName());

        AlertDialog.Builder builder = new AlertDialog.Builder(holder.context);
        builder.setTitle("Edit fruit")
                .setView(editView);

        final AlertDialog dialog = builder.create();

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
            }
        });

        Button editButtonFruitDialog = (Button) editView.findViewById(R.id.edit_fruit_button_dialog);
        editButtonFruitDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fruit fruit = currentList.get(holder.getAdapterPosition());
                fruit.setName(fruitName.getText().toString());

                if (baseList.contains(fruit)){
                    baseList.get(baseList.indexOf(fruit)).setName(fruitName.getText().toString());
                }

                try {
                    FruitDAO fruitDAO = HelperFactory.getHelper().getFruitDAO();
                    fruitDAO.update(fruit);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                currentList.get(holder.getAdapterPosition()).setName(fruitName.getText().toString());
                MyRecyclerViewAdapter.this.notifyItemChanged(holder.getAdapterPosition());
                dialog.cancel();
            }
        });
    }

    @Override
    public int getItemCount() {
        return currentList.size();
    }

    public void add(Fruit fruit){
        currentList.add(fruit);
        baseList.add(fruit);
        this.notifyDataSetChanged();
    }

    public void filter(String sequence){
        currentList.clear();

        if (sequence.equals("")){
            for (Fruit fruit : baseList){
                currentList.add(fruit);
            }
        }else{
            String pattern = sequence.toLowerCase();

            for (Fruit fruit : baseList){
                if (fruit.getName().toLowerCase().contains(pattern.toLowerCase())){
                    currentList.add(fruit);
                }
            }
        }
        this.notifyDataSetChanged();
    }

}
