package com.snsh.dev.Willmel.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.snsh.dev.Willmel.App;
import com.snsh.dev.Willmel.R;
import com.snsh.dev.Willmel.activity.MainActivity;
import com.snsh.dev.Willmel.model.BestDealModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by infoava on 10/23/2017.
 */

public class BestDealHomeSeeMoreAdapt extends RecyclerView.Adapter<BestDealHomeSeeMoreAdapt.MyViewHolder> {

    private List<BestDealModel> dataList;
    //list
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtprice, txtsold;
        ImageView imgBestDealList;

        public MyViewHolder(View view) {
            super(view);
            txtprice = (TextView) view.findViewById(R.id.txtprice);
            txtsold = (TextView) view.findViewById(R.id.txtsold);
            imgBestDealList = (ImageView) view.findViewById(R.id.imgBestDealList);

            // for click card view for all category
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.e(TAG, "onClick: " );

                    //........
                }
            });
        }
    }

    public void addData(List<BestDealModel> dataList) {
        this.dataList = dataList;
        notifyData();
    }

    public void notifyData() {
        notifyDataSetChanged();
    }

    public BestDealHomeSeeMoreAdapt(List<BestDealModel> bestDealHomeList) {
        this.dataList = bestDealHomeList;
    }

    @Override
    public BestDealHomeSeeMoreAdapt.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_b_d_home_see_more, parent, false);
        return new BestDealHomeSeeMoreAdapt.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(BestDealHomeSeeMoreAdapt.MyViewHolder holder, int position) {
        BestDealModel data = dataList.get(position);
        holder.txtprice.setText(data.getText1());
        holder.txtsold.setText(data.getText2());
        Picasso.with(App.getAppContext())
                .load(data.getImageUrl())
                .error(R.mipmap.ic_launcher)
                .into(holder.imgBestDealList);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
