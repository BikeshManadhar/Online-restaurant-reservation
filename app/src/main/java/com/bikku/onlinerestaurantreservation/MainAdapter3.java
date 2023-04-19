package com.bikku.onlinerestaurantreservation;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;


public class MainAdapter3 extends FirebaseRecyclerAdapter <MainModel3,MainAdapter3.myViewHolder3>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter3(@NonNull FirebaseRecyclerOptions<MainModel3> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MainAdapter3.myViewHolder3 holder, int position, @NonNull MainModel3 model) {
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

        holder.img3.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.btnEdit3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img3.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup3))
                        .setExpanded(true, 1300)
                        .create();

                // dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText roomno = view.findViewById(R.id.txtRoomNo);
                EditText roomcapacity = view.findViewById(R.id.txtRoomCapacity);
                EditText roomtype = view.findViewById(R.id.txtRoomType);
                EditText rurl = view.findViewById(R.id.txtImageUrl3);

                Button btnUpdate3 = view.findViewById(R.id.btnUpdate3);

                roomno.setText(model.getRoomno());
                roomcapacity.setText(model.getRoomcapacity());
                roomtype.setText(model.getRoomtype());
                rurl.setText(model.getRurl());

                dialogPlus.show();

                btnUpdate3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("roomno",roomno.getText().toString());
                        map.put("roomcapacity",roomcapacity.getText().toString());
                        map.put("roomtype",roomtype.getText().toString());
                        map.put("rurl",rurl.getText().toString());

                        DatabaseReference ref = getRef(holder.getAdapterPosition());
                        FirebaseDatabase.getInstance().getReference().child("Room").child(ref.getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.roomno.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.roomtype.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete3
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.roomno.getContext());
                        builder.setTitle("Are you sure?");
                        builder.setMessage("Deleted Data can't be Undo");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference ref = getRef(holder.getAdapterPosition());
                                FirebaseDatabase.getInstance().getReference().child("Room").child(ref.getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.roomno.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });

    }


    @NonNull
    @Override
    public MainAdapter3.myViewHolder3 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item3,parent,false);
        return new MainAdapter3.myViewHolder3(view);
    }

    class myViewHolder3 extends RecyclerView.ViewHolder{

        ImageView img3;
        TextView roomno, roomcapacity, roomtype;
        Button btnEdit3, btnDelete3;


        public myViewHolder3(@NonNull View itemView) {
            super(itemView);
            img3 = (ImageView)itemView.findViewById(R.id.img3);
            roomno = (TextView) itemView.findViewById(R.id.roomNoText);
            roomcapacity = (TextView) itemView.findViewById(R.id.roomCapacityText);
            roomtype = (TextView) itemView.findViewById(R.id.roomTypeText);

            btnEdit3 = (Button)itemView.findViewById(R.id.btnEdit3);
            btnDelete3 = (Button)itemView.findViewById(R.id.btnDelete3);
        }

    }
}
