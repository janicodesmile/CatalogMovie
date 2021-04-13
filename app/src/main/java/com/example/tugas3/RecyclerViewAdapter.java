package com.example.tugas3;

import android.app.Activity;
import android.content.Intent;
import android.telecom.Call;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {

    private List<Film> moviesList;
    private Activity mActivity;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout rl_layout;
        public TextView tv_judul, tv_sinopsis, tv_tahun, tv_ket;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            rl_layout = view.findViewById(R.id.rl);
            tv_judul = view.findViewById(R.id.judul);
            tv_sinopsis = view.findViewById(R.id.sinopsis);
            tv_ket = view.findViewById(R.id.keterangan);
            tv_tahun = view.findViewById(R.id.tahun);
            imageView = view.findViewById(R.id.gambar);
        }

    }

    public RecyclerViewAdapter(List<Film> moviesList, Activity activity) {
        this.moviesList = moviesList;
        this.mActivity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final Film movie = moviesList.get(position);
        holder.tv_judul.setText(movie.getJudul());
        holder.tv_sinopsis.setText(movie.getSinopsis());
        holder.tv_ket.setText(movie.getKeterangan());
        holder.tv_tahun.setText(movie.getTahun());
        Picasso.get()
                .load(movie.getImageUrl())
                .placeholder(R.drawable.img)
                .centerCrop()
                .fit()
                .into(holder.imageView);

        holder.rl_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goDetail = new Intent(mActivity, HalamanDetail.class);
                goDetail.putExtra("id", movie.getKey());
                goDetail.putExtra("judul", movie.getJudul());
                goDetail.putExtra("sinopsis", movie.getSinopsis());
                goDetail.putExtra("keterangan", movie.getKeterangan());
                goDetail.putExtra("tahun", movie.getTahun());
                goDetail.putExtra("imageUrl", movie.getImageUrl());
                mActivity.startActivity(goDetail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


}
