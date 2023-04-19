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




public class tableMainAdapter2 extends FirebaseRecyclerAdapter <MainModel2, tableMainAdapter2.myViewHolder2>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public tableMainAdapter2(@NonNull FirebaseRecyclerOptions<MainModel2> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder2 holder, int position, @NonNull MainModel2 model) {
        holder.t_type.setText(model.getT_type());
        holder.t_capacity.setText(model.getT_capacity());
        holder.t_location.setText(model.getT_location());
        holder.table_description.setText(model.getTable_description());

        Glide.with(holder.img2.getContext())
                .load(model.getTurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(holder.img2);

        holder.img2.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                Intent intent = new Intent(v.getContext(), TableOrderActivity.class);
                intent.putExtra("turl", model.getTurl());
                intent.putExtra("t_type", model.getT_type());
                intent.putExtra("t_capacity", model.getT_capacity());
                intent.putExtra("t_location", model.getT_location());
                intent.putExtra("table_description", model.getTable_description());
                intent.putExtra("position", currentPosition);
                ((Activity) v.getContext()).startActivityForResult(intent, 1);
            }
        });



    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tablemainitem2,parent,false);
        return new myViewHolder2(view);
    }

    class myViewHolder2 extends RecyclerView.ViewHolder{

        ImageView img2;
        TextView t_type, t_capacity, t_location, table_description;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            img2 = (ImageView)itemView.findViewById(R.id.img2);
            t_type = (TextView) itemView.findViewById(R.id.typeText);
            t_capacity = (TextView) itemView.findViewById(R.id.capacityText);
            t_location = (TextView) itemView.findViewById(R.id.locationText);
            table_description = (TextView) itemView.findViewById(R.id.description1Text);

        }
    }


}
