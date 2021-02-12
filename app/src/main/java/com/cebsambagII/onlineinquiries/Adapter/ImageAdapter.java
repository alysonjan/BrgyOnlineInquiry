package com.cebsambagII.onlineinquiries.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cebsambagII.onlineinquiries.R;
import com.cebsambagII.onlineinquiries.model.Upload;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;


    public ImageAdapter(Context context, List<Upload>uploads) {
        mContext = context;
        mUploads = uploads;

    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item,parent,false);
        return new ImageViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getmCaption());
        holder.tv_date.setText(uploadCurrent.getDate());
        Picasso.get()
                .load(uploadCurrent.getmImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerInside()
                .into(holder.ann_imageView);

    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewName;
        public TextView tv_date;
        public ImageView ann_imageView;


        public ImageViewHolder( View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.tv_caption);
            tv_date = itemView.findViewById(R.id.tv_date);
            ann_imageView = itemView.findViewById(R.id.announcement_image);

        }
    }
}
