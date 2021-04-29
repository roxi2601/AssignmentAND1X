package com.example.assignmentand1x.offer;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.example.assignmentand1x.R;
import java.util.ArrayList;
import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.ViewHolder> {
    private List<Offer> offers = new ArrayList<>();
    Context context;


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
        viewHolder.photo.setImageResource(offers.get(position).getPhoto());


    }


    public int getItemCount() {
        return offers.size();
    }
    public void setOffers(List<Offer> offers)
    {
        this.offers = offers;
        notifyDataSetChanged();
    }
    class ViewHolder extends  RecyclerView.ViewHolder{
        TextView titleTextView;
        TextView dateTextView;
        TextView timeTextView;
        TextView localizationTextView;
        ImageView photo;
        ConstraintLayout constraintLayout;
        ViewHolder(View itemView){
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleOfferTextView);
            dateTextView = itemView.findViewById(R.id.dateOfferTextView);
            timeTextView = itemView.findViewById(R.id.timeOfferTextView);
            localizationTextView = itemView.findViewById(R.id.localizationOfferTextView);
            photo = itemView.findViewById(R.id.photoFragment);
            constraintLayout=(ConstraintLayout) itemView.findViewById(R.id.constLayout);


            // on item click
            itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(context, OfferActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
