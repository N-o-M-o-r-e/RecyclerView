package com.project.tathanhson.recyclerview.view.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.project.tathanhson.recyclerview.R;
import com.project.tathanhson.recyclerview.model.IconModel;

import java.util.List;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.IconHolder> {
    private Context context;
    private List<IconModel> listStory;
    MutableLiveData<IconModel> storyLivaData = new MutableLiveData<>();


    public MutableLiveData<IconModel> getStoryLivaData() {
        return storyLivaData;
    }

    public IconAdapter(Context context, List<IconModel> listStory) {
        this.context = context;
        this.listStory = listStory;
    }

    /**
     * Từ item_view trong file res/layout, ánh xạ thành IconHolder (inflate)
     */
    @NonNull
    @Override
    public IconHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new IconHolder(view);
    }

    /**
     * Dựa cào số lượng item data , truyền dữ liệu tương ứng  của từng data vào IconHolder
     */
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull IconHolder holder, int position) {
        IconModel data = listStory.get(position);
        holder.tvIconName.setText(data.getContent());
        Bitmap bitmap = data.getBitmapImg();
        GlideHelper.loadBitmap(context, bitmap, holder.imageIcon);
        holder.tvIconName.setTag(data);
        if (data.isSelected()) {
            holder.backgroundView.setBackgroundResource(R.drawable.bg_selected);
        } else {
            holder.backgroundView.setBackgroundResource(R.drawable.bg_unselected);

        }
    }

    @Override
    public int getItemCount() {
        return listStory.size();
    }

    public class IconHolder extends RecyclerView.ViewHolder {
        TextView tvIconName;
        ImageView imageIcon;
        View backgroundView;

        public IconHolder(@NonNull View itemView) {
            super(itemView);
            tvIconName = itemView.findViewById(R.id.tvPhoto);
            imageIcon = itemView.findViewById(R.id.imgPhoto);
            backgroundView = itemView.findViewById(R.id.bgItem);

            itemView.setOnClickListener(v -> {
                clickItem((IconModel) tvIconName.getTag());

            });
        }

        private void clickItem(IconModel iconModel) {
            iconModel.setSelected(true);
            if (storyLivaData.getValue() != null) {
                storyLivaData.getValue().setSelected(false);

            }
            storyLivaData.postValue(iconModel);

            notifyItemRangeChanged(0, listStory.size());
        }
    }

    public static class GlideHelper {
        public static void loadBitmap(Context context, Bitmap bitmap, ImageView imageView) {
            RequestOptions requestOptions = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE) // Disable disk cache to load bitmap directly
                    .skipMemoryCache(true); // Disable memory cache
            Glide.with(context).asBitmap().load(bitmap).apply(requestOptions).into(imageView);
        }
    }
}
