package com.example.hikerapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder>{

    List<LogModel>log;
    Context context;
    DBHelper dbHelper;

    public LogAdapter(List<LogModel> log, Context context) {
        this.log = log;
        this.context = context;
        dbHelper = new DBHelper(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.log_item_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final LogModel logModel = log.get(position);
        holder.textViewID.setText(Integer.toString(logModel.getId()));
        holder.text_name.setText(logModel.getName());
        holder.text_location.setText(logModel.getLocation());
        holder.text_date.setText(logModel.getDate());
        holder.text_parking.setText(logModel.getParking());
        holder.text_length.setText(logModel.getLength());
        holder.text_level.setText(logModel.getLevel());
        holder.text_desc.setText(logModel.getDesc());

        //set event for edit button
        holder.btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stringName = holder.text_name.getText().toString();
                String stringLocation = holder.text_location.getText().toString();
                String stringDate = holder.text_date.getText().toString();
                String stringParking = holder.text_parking.getText().toString();
                String stringLength = holder.text_length.getText().toString();
                String stringLevel = holder.text_level.getText().toString();
                String stringDesc = holder.text_desc.getText().toString();

                //call update method
                dbHelper.updateLog(new LogModel(logModel.getId(),stringName,stringLocation,stringDate,stringParking,stringLength,stringLevel,stringDesc));
                notifyDataSetChanged();
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        //set event for delete button
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call delete method
                dbHelper.deleteLog(logModel.getId());
                log.remove(position);
                notifyDataSetChanged();
            }
        });



    }

    // count log
    @Override
    public int getItemCount() {
        return log.size();
    }

    //view
    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewID;
        EditText text_name, text_location, text_date,text_parking,text_length,text_level,text_desc;
        Button btn_delete, btn_edit;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewID = itemView.findViewById(R.id.text_id);
            text_name = itemView.findViewById(R.id.text_name);
            text_location = itemView.findViewById(R.id.text_location);
            text_date = itemView.findViewById(R.id.text_date);
            text_parking = itemView.findViewById(R.id.text_parking);
            text_length = itemView.findViewById(R.id.text_length);
            text_level = itemView.findViewById(R.id.text_level);
            text_desc = itemView.findViewById(R.id.text_desc);

            btn_edit = itemView.findViewById(R.id.btn_edit);
            btn_delete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
