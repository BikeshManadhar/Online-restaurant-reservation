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


public class MainAdapter2 extends FirebaseRecyclerAdapter <MainModel2, MainAdapter2.myViewHolder2>{

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public MainAdapter2(@NonNull FirebaseRecyclerOptions<MainModel2> options) {
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

        holder.btnEdit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialogPlus = DialogPlus.newDialog(holder.img2.getContext())
                        .setContentHolder(new ViewHolder(R.layout.update_popup2))
                        .setExpanded(true, 1500)
                        .create();

                // dialogPlus.show();
                View view = dialogPlus.getHolderView();

                EditText t_type = view.findViewById(R.id.txtType);
                EditText t_capacity = view.findViewById(R.id.txtCapacity);
                EditText t_location = view.findViewById(R.id.txtLocation);
                EditText table_description = view.findViewById(R.id.txtDescription2);
                EditText turl = view.findViewById(R.id.txtImageUrl2);

                Button btnUpdate2 = view.findViewById(R.id.btnUpdate2);

                t_type.setText(model.getT_type());
                t_capacity.setText(model.getT_capacity());
                t_location.setText(model.getT_location());
                table_description.setText(model.getTable_description());
                turl.setText(model.getTurl());

                dialogPlus.show();

                btnUpdate2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Map<String,Object> map = new HashMap<>();
                        map.put("t_type",t_type.getText().toString());
                        map.put("t_capacity",t_capacity.getText().toString());
                        map.put("t_location",t_location.getText().toString());
                        map.put("table_description",table_description.getText().toString());
                        map.put("turl",turl.getText().toString());

                        DatabaseReference ref = getRef(holder.getAdapterPosition());
                        FirebaseDatabase.getInstance().getReference().child("Table").child(ref.getKey())
                                .updateChildren(map)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(holder.t_type.getContext(), "Data Updated Successfully", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(holder.t_type.getContext(), "Error While Updating.", Toast.LENGTH_SHORT).show();
                                        dialogPlus.dismiss();
                                    }
                                });
                    }
                });
            }
        });

        holder.btnDelete2
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(holder.t_type.getContext());
                        builder.setTitle("Are you sure?");
                        builder.setMessage("Deleted Data can't be Undo");

                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                DatabaseReference ref = getRef(holder.getAdapterPosition());
                                FirebaseDatabase.getInstance().getReference().child("Table").child(ref.getKey()).removeValue();
                            }
                        });

                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(holder.t_type.getContext(), "Cancelled", Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });

    }

    @NonNull
    @Override
    public myViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item2,parent,false);
        return new myViewHolder2(view);
    }

    class myViewHolder2 extends RecyclerView.ViewHolder{

        ImageView img2;
        TextView t_type, t_capacity, t_location, table_description;
        Button btnEdit2, btnDelete2;

        public myViewHolder2(@NonNull View itemView) {
            super(itemView);

            img2 = (ImageView)itemView.findViewById(R.id.img2);
            t_type = (TextView) itemView.findViewById(R.id.typeText);
            t_capacity = (TextView) itemView.findViewById(R.id.capacityText);
            t_location = (TextView) itemView.findViewById(R.id.locationText);
            table_description = (TextView) itemView.findViewById(R.id.description1Text);

            btnEdit2 = (Button)itemView.findViewById(R.id.btnEdit2);
            btnDelete2 = (Button)itemView.findViewById(R.id.btnDelete2);
        }
    }

}
