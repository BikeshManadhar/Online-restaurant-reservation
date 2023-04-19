package com.bikku.onlinerestaurantreservation;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartMainAdapter extends FirebaseRecyclerAdapter<CartMainModel, CartMainAdapter.myViewHolderCart> {

    /**
     * Initialize a {@link RecyclerView.Adapter} that listens to a Firebase query. See
     * {@link FirebaseRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public CartMainAdapter(@NonNull FirebaseRecyclerOptions<CartMainModel> options) {
        super(options);
    }
    @Override
    protected void onBindViewHolder(@NonNull CartMainAdapter.myViewHolderCart holder, int position, @NonNull CartMainModel model) {
        holder.CustomerName.setText(model.getName());
        holder.Email.setText(model.getEmail());
        holder.PhoneNo.setText(model.getPhone());
        holder.Quantity.setText(model.getQuantity());


        Glide.with(holder.imgCart.getContext())
                .load(model.getFurl())
                .placeholder(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark)
                .error(com.firebase.ui.database.R.drawable.common_google_signin_btn_icon_dark_normal)
                .override(600, 600)
                .fitCenter()
                .into(holder.imgCart);

        holder.imgCart.setScaleType(ImageView.ScaleType.FIT_XY);

        holder.cancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(holder.CustomerName.getContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Cancelled order can't be Undo");

                builder.setPositiveButton("cancel order", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DatabaseReference ref = getRef(holder.getAdapterPosition());
                        FirebaseDatabase.getInstance().getReference().child("Order").child(ref.getKey()).removeValue();
                        Toast.makeText(v.getContext(), "Order cancelled successfully", Toast.LENGTH_SHORT).show();

                    }
                });

                builder.show();
            }
        });



    }
    @NonNull
    @Override
    public CartMainAdapter.myViewHolderCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cartmain_item,parent,false);
        return new CartMainAdapter.myViewHolderCart(view);
    }

    class myViewHolderCart extends RecyclerView.ViewHolder{

        ImageView imgCart;
        TextView CustomerName, Email, PhoneNo, Quantity;
        Button cancelOrder;

        public myViewHolderCart(@NonNull View itemView) {
            super(itemView);

            imgCart = (ImageView)itemView.findViewById(R.id.imgCart);
            CustomerName = (TextView) itemView.findViewById(R.id.CustomerName);
            Email = (TextView) itemView.findViewById(R.id.Email);
            PhoneNo = (TextView) itemView.findViewById(R.id.PhoneNo);
            Quantity = (TextView) itemView.findViewById(R.id.Quantity);
            cancelOrder = (Button)itemView.findViewById(R.id.cancelOrder);

        }
    }
}