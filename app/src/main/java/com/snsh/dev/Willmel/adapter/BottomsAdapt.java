package com.snsh.dev.Willmel.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.snsh.dev.Willmel.App;
import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by infoava on 10/19/2017.
 */

public class BottomsAdapt extends RecyclerView.Adapter<BottomsAdapt.MyViewHolder> {


    private List<BestDealModel> dataList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tv1, tv2;
        ImageView iv1;

        public MyViewHolder(View view) {
            super(view);
            tv1 = (TextView) view.findViewById(R.id.tv1);
            tv2 = (TextView) view.findViewById(R.id.tv2);
            iv1 = (ImageView) view.findViewById(R.id.iv1);
        }
    }

    public void addData(List<BestDealModel> dataList) {
        this.dataList = dataList;
        notifyData();
    }

    public void notifyData() {
        notifyDataSetChanged();
    }

    public BottomsAdapt(List<BestDealModel> accessoriesList) {
        this.dataList = accessoriesList;


    }

    @Override
    public BottomsAdapt.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_best_deal_home, parent, false);
        return new BottomsAdapt.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BottomsAdapt.MyViewHolder holder, int position) {
        BestDealModel data = dataList.get(position);
        holder.tv1.setText(data.getText1());
        holder.tv2.setText(data.getText2());
        Picasso.with(App.getAppContext())
                .load(data.getImageUrl())
                .error(R.mipmap.ic_launcher)
                .into(holder.iv1);
        /*Picasso.with(App.getAppContext())
                .load(movie.getImageUrl())
                .memoryPolicy(MemoryPolicy.NO_CACHE)
                .networkPolicy(NetworkPolicy.NO_CACHE)
                .resize(512, 512)
                .error(R.mipmap.ic_launcher)
                .noFade()
                .into(holder.iv1);*/
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
