package com.bikku.onlinerestaurantreservation;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.text.DecimalFormat;

public class menuMainAdapter1 extends FirebaseRecyclerAdapter<MainModel1, menuMainAdapter1.myViewHolder1> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public menuMainAdapter1(@NonNull FirebaseRecyclerOptions<MainModel1> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull menuMainAdapter1.myViewHolder1 holder, int position, @NonNull MainModel1 model) {
        holder.name.setText(model.getName());
        DecimalFormat df = new DecimalFormat("0.##"); // Create DecimalFormat object with up to two decimal places
        holder.price.setText(df.format(model.getPrice())); //
        holder.description.setText(model.getDescription());

        Glide.with(holder.img1.getContext())
                .load(model.getFurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(holder.img1);

        holder.img1.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                Intent intent = new Intent(v.getContext(), FoodOrderActivity.class);
                intent.putExtra("furl", model.getFurl());
                intent.putExtra("name", model.getName());
                intent.putExtra("price", model.getPrice());
                intent.putExtra("description", model.getDescription());
                intent.putExtra("position", currentPosition);
                ((Activity) v.getContext()).startActivityForResult(intent, 1);
            }
        });






    }
    @NonNull
    @Override
    public menuMainAdapter1.myViewHolder1 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.foodmainitem,parent,false);
        return new menuMainAdapter1.myViewHolder1(view);
    }

    class myViewHolder1 extends RecyclerView.ViewHolder{

        ImageView img1;
        TextView name, price, description;

        public myViewHolder1(@NonNull View itemView) {
            super(itemView);

            img1 = (ImageView)itemView.findViewById(R.id.img1food);
            name = (TextView) itemView.findViewById(R.id.nameText);
            price = (TextView) itemView.findViewById(R.id.priceText);
            description = (TextView) itemView.findViewById(R.id.descriptionText);

        }
    }
}
