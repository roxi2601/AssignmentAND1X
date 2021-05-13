package com.example.assignmentand1x.adapter;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignmentand1x.model.Offer;
import com.example.assignmentand1x.R;
import com.example.assignmentand1x.views.OfferActivity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private List<Offer> offers = new ArrayList<>();


    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_item_mainpage,parent,false);

        return new ViewHolder(view);
    }


    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        viewHolder.titleTextView.setText(offers.get(position).getTitle());
        viewHolder.dateTextView.setText(offers.get(position).getDate());
        viewHolder.timeTextView.setText(offers.get(position).getTime());
        viewHolder.localizationTextView.setText(offers.get(position).getLocalization());
        byte[] currentPhoto = offers.get(position).getPhoto();
        Bitmap bm = BitmapFactory.decodeByteArray(currentPhoto,0,currentPhoto.length);
        viewHolder.photo.setImageBitmap(bm);


    }
    public void setOffers(List<Offer> offers)
    {
        this.offers = offers;
        notifyDataSetChanged();
    }


    public int getItemCount() {
        return offers.size();
    }

    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView dateTextView;
        TextView timeTextView;
        TextView localizationTextView;
        ImageView photo;
        LinearLayout linearLayout;
        ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleOfferTextView);
            dateTextView = itemView.findViewById(R.id.dateOfferTextView);
            timeTextView = itemView.findViewById(R.id.timeOfferTextView);
            localizationTextView = itemView.findViewById(R.id.localizationOfferTextView);
            photo = itemView.findViewById(R.id.photoFragment);
            linearLayout= itemView.findViewById(R.id.linearrr);
            // on item click
            itemView.setOnClickListener(v -> {
                Intent intent= new Intent(itemView.getContext(), OfferActivity.class);
                intent.putExtra("id",offers.get(getAdapterPosition()).getId());
                intent.putExtra("offerAccountId",offers.get(getAdapterPosition()).getOfferAccountId());
                intent.putExtra("title",offers.get(getAdapterPosition()).getTitle());
                intent.putExtra("email",offers.get(getAdapterPosition()).getEmail());
                intent.putExtra("date",offers.get(getAdapterPosition()).getDate());
                intent.putExtra("time",offers.get(getAdapterPosition()).getTime());
                intent.putExtra("localization",offers.get(getAdapterPosition()).getLocalization());
                intent.putExtra("description",offers.get(getAdapterPosition()).getDescription());
                intent.putExtra("photo",offers.get(getAdapterPosition()).getPhoto());
                 v.getContext().startActivity(intent);
            });
        }


    }

}
