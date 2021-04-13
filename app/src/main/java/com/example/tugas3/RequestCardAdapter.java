package com.example.tugas3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RequestCardAdapter extends RecyclerView.Adapter<RequestCardAdapter.RequestFilmViewHolder> {

    private List<Request> reqlist;
    private Activity activity;

    public class RequestFilmViewHolder extends RecyclerView.ViewHolder {
        public TextView tvjudul, tvnama, tvdate,tvtime;
        public RelativeLayout relativeLayout;

        public RequestFilmViewHolder(View view) {
            super(view);
            tvjudul = view.findViewById(R.id.judulReq);
            tvnama = view.findViewById(R.id.namaReq);
            tvdate = view.findViewById(R.id.dateReq);
            tvtime = view.findViewById(R.id.timeReq);
            relativeLayout = view.findViewById(R.id.relativeLayout);

        }
    }

    public RequestCardAdapter(List<Request> reqlist, Activity activity){
        this.reqlist = reqlist;
        this.activity = activity;
    }

    @Override
    public RequestFilmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_request_card, parent, false);

        return new RequestFilmViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RequestFilmViewHolder holder, final int position) {
        final Request request = reqlist.get(position);
        holder.tvjudul.setText(request.getJudul());
        holder.tvnama.setText(request.getNama());
        holder.tvdate.setText(request.getDate());
        holder.tvtime.setText(request.getTime());

    }

    @Override
    public int getItemCount() {
        return reqlist.size();
    }


}