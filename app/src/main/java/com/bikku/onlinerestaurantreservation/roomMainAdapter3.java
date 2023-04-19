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

public class roomMainAdapter3 extends FirebaseRecyclerAdapter<MainModel3, roomMainAdapter3.myViewHolder3> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public roomMainAdapter3(@NonNull FirebaseRecyclerOptions<MainModel3> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull roomMainAdapter3.myViewHolder3 holder, int position, @NonNull MainModel3 model) {
        holder.roomno.setText(model.getRoomno());
        holder.roomcapacity.setText(model.getRoomcapacity());
        holder.roomtype.setText(model.getRoomtype());

        Glide.with(holder.img3.getContext())
                .load(model.getRurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(holder.img3);

        holder.img3.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentPosition = holder.getAdapterPosition();
                Intent intent = new Intent(v.getContext(), RoomOrderActivity.class);
                intent.putExtra("rurl", model.getRurl());
                intent.putExtra("roomno", model.getRoomno());
                intent.putExtra("roomcapacity", model.getRoomcapacity());
                intent.putExtra("roomtype", model.getRoomtype());
                intent.putExtra("position", currentPosition);
                ((Activity) v.getContext()).startActivityForResult(intent, 1);
            }
        });



    }
    @NonNull
    @Override
    public roomMainAdapter3.myViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roommainitem3,parent,false);
        return new roomMainAdapter3.myViewHolder3(view);
    }

    class myViewHolder3 extends RecyclerView.ViewHolder{

        ImageView img3;
        TextView roomno, roomcapacity, roomtype;

        public myViewHolder3(@NonNull View itemView) {
            super(itemView);

            img3 = (ImageView)itemView.findViewById(R.id.img3);
            roomno = (TextView) itemView.findViewById(R.id.roomNoText);
            roomcapacity = (TextView) itemView.findViewById(R.id.roomCapacityText);
            roomtype = (TextView) itemView.findViewById(R.id.roomTypeText);

        }
    }
}
