package com.example.shoponline.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shoponline.R;
import com.example.shoponline.interfac.ClickListener;
import com.example.shoponline.modelgson.LoaiSp;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class categoryAdapter extends RecyclerView.Adapter<categoryAdapter.ViewHolder> {
    ClickListener clickListener;
    private ArrayList<LoaiSp> danhmucMDS;
    private Context context;

    public categoryAdapter(ArrayList<LoaiSp> danhmucMDS, Context context, ClickListener clickListener) {
        this.danhmucMDS = danhmucMDS;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public categoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull categoryAdapter.ViewHolder holder, final int position) {
        LoaiSp danhmucMD = danhmucMDS.get(position);
        holder.view.setText(danhmucMD.getTenloaisp());
        Picasso.with(context).load(danhmucMD.getHinh())
                .placeholder(R.drawable.noimage)
                .error(R.drawable.eror)
                .into(holder.imageView);
        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickListener.onClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return danhmucMDS.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView view;
        LinearLayout linearLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_user);
            view = itemView.findViewById(R.id.txt_TileCategory);
            linearLayout = itemView.findViewById(R.id.ln_itemcate);
        }
    }
}
