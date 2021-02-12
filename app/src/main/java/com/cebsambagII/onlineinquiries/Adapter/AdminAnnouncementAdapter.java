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

import java.util.List;

public class AdminAnnouncementAdapter extends RecyclerView.Adapter<AdminAnnouncementAdapter.AdminAnnouncementViewHolder> {

    private Context mContext;
    private List<Upload> mUploads;
    private SelectedAnnouncement selectedAnnouncement;


    public AdminAnnouncementAdapter(Context context, List<Upload> uploads, SelectedAnnouncement selectedAnnouncement) {
        this.mContext = context;
        this.mUploads = uploads;
        this.selectedAnnouncement = selectedAnnouncement;

    }

    @NonNull
    @Override
    public AdminAnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.announcements_title_items,parent,false);
        return new AdminAnnouncementAdapter.AdminAnnouncementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdminAnnouncementViewHolder holder, int position) {
        Upload upload = mUploads.get(position);

        holder.textViewTitle.setText(upload.getmCaption());
    }

    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public interface SelectedAnnouncement {
        void selectedAnnouncement(Upload upload);
    }


    public class AdminAnnouncementViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle;

        public AdminAnnouncementViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.single_title);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    selectedAnnouncement.selectedAnnouncement(mUploads.get(getAdapterPosition()));
                }
            });
        }
    }



}
