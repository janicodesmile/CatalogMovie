package com.example.tugas3;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.FilmCardViewHolder> {

    private ArrayList<Film> dataList;
    Activity activity;
    public CardViewAdapter(ArrayList<Film> filmArrayList, Activity activity) {
        this.dataList = filmArrayList;
        this.activity = activity;
    }


    @NonNull
    @Override
    public CardViewAdapter.FilmCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.activity_cardview,parent,false);
        return new FilmCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CardViewAdapter.FilmCardViewHolder holder, int position) {
        final Film movie = dataList.get(position);
        holder.tvjudul.setText(dataList.get(position).getJudul());
        holder.tvketerangan.setText(dataList.get(position).getKeterangan());
        holder.tvtahun.setText(dataList.get(position).getTahun());
        holder.tvsinopsis.setText(dataList.get(position).getSinopsis());

        Picasso.get()
                .load(dataList.get(position).getImageUrl())
                .placeholder(R.drawable.img)
                .centerCrop()
                .fit()
                .into(holder.srcGambar);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent goDetail = new Intent( activity,HalamanDetail.class);
                goDetail.putExtra("id", movie.getKey());
                goDetail.putExtra("judul", movie.getJudul());
                goDetail.putExtra("sinopsis", movie.getSinopsis());
                goDetail.putExtra("keterangan", movie.getKeterangan());
                goDetail.putExtra("tahun", movie.getTahun());
                goDetail.putExtra("imageUrl", movie.getImageUrl());
                activity.startActivity(goDetail);


            }
        });


        holder.btn_fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Favorite " +
                        dataList.get(holder.getAdapterPosition()).getJudul(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.btn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "Share " +
                        dataList.get(holder.getAdapterPosition()).getJudul(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0 ;
    }

    public class FilmCardViewHolder extends RecyclerView.ViewHolder {
        private TextView tvjudul, tvketerangan, tvtahun, tvsinopsis;
        private ImageView srcGambar;
        private Button btn_fav, btn_share;
        private CardView cardView;


        public FilmCardViewHolder(@NonNull View itemView) {
            super(itemView);
            srcGambar = (ImageView) itemView.findViewById(R.id.gambar);
            cardView = itemView.findViewById(R.id.card);
            tvjudul = itemView.findViewById(R.id.judul);
            tvketerangan = itemView.findViewById(R.id.keterangan);
            tvtahun = itemView.findViewById(R.id.tahun);
            tvsinopsis = itemView.findViewById(R.id.sinopsis);
            btn_fav = itemView.findViewById(R.id.btn1);
            btn_share = itemView.findViewById(R.id.btn2);
        }
    }


}
