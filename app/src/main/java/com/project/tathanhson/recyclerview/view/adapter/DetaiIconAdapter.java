package com.project.tathanhson.recyclerview.view.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.tathanhson.recyclerview.MyApplication;
import com.project.tathanhson.recyclerview.R;
import com.project.tathanhson.recyclerview.model.IconModel;

import java.util.List;

public class DetaiIconAdapter extends RecyclerView.Adapter<DetaiIconAdapter.ViewHolder> {
    private Context context;
    private List<IconModel> list;

    public DetaiIconAdapter(Context context, List<IconModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_icon, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        IconModel data = list.get(position);
        Log.e("AAAAAA", "data in position = "+position );
        Log.e("AAAAAA", "data in position = "+data.getContent() );
        Log.e("AAAAAA", "data in position = "+data.getBitmapImg() );

        holder.tvIconDetail.setText(data.getContent());
        holder.ivIconDetail.setImageBitmap(data.getBitmapImg());

        MyApplication.getInstance().getStorage().iconModel.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIconDetail;
        TextView tvIconDetail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIconDetail = itemView.findViewById(R.id.ivIcon);
            tvIconDetail = itemView.findViewById(R.id.tvIcon);
        }
    }
}
