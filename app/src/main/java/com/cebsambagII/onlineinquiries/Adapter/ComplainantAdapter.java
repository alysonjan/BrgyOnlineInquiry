package com.cebsambagII.onlineinquiries.Adapter;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cebsambagII.onlineinquiries.R;
import com.cebsambagII.onlineinquiries.model.ComplainantModel;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ComplainantAdapter extends RecyclerView.Adapter<ComplainantAdapter.ViewHolder> {

    private Context mContext;
    private List<ComplainantModel> mComplainantModels;
    private SelectedComplainant selectedComplainant;

    public ComplainantAdapter(Context mContext, List<ComplainantModel> mComplainantModels, SelectedComplainant selectedComplainant) {
        this.mComplainantModels = mComplainantModels;
        this.mContext = mContext;
        this.selectedComplainant = selectedComplainant;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.complainant_single, parent, false);
        return new ComplainantAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ComplainantModel complainantModel = mComplainantModels.get(position);

        String nameprefix = complainantModel.getFullname().substring(0,1);
        holder.fullname.setText(complainantModel.getFullname());
        holder.date.setText(complainantModel.getDate());
        holder.prefix.setText(nameprefix);

    }

    @Override
    public int getItemCount() {
        return mComplainantModels.size();
    }

   public interface SelectedComplainant {
       void selectedComplainant(ComplainantModel complainantModel);
   }


   public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView fullname;
        public TextView prefix;
        public ImageView imIcon;
        public TextView date;

       public ViewHolder(@NonNull View itemView) {
           super(itemView);

           fullname = itemView.findViewById(R.id.single_fullname);
           date = itemView.findViewById(R.id.single_date);
           prefix = itemView.findViewById(R.id.prefix);
           imIcon = itemView.findViewById(R.id.imageIcon);

           itemView.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   selectedComplainant.selectedComplainant(mComplainantModels.get(getAdapterPosition()));
               }
           });


       }
   }
}
