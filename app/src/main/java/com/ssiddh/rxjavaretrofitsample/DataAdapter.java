package com.ssiddh.rxjavaretrofitsample;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ssiddh.rxjavaretrofitsample.model.AndroidVersion;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 2/28/18.
 */

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {


    private ArrayList<AndroidVersion> androidVersionList;

    public DataAdapter(ArrayList<AndroidVersion> androidVersionList){
        this.androidVersionList = androidVersionList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.versionName.setText(androidVersionList.get(position).getName());
        holder.versionNumber.setText(androidVersionList.get(position).getVer());
        holder.versionApi.setText(androidVersionList.get(position).getApi());
    }

    @Override
    public int getItemCount() {
        return androidVersionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView versionName, versionNumber, versionApi;

        public ViewHolder(View view){
            super(view);

            versionName = view.findViewById(R.id.version_name);
            versionNumber = view.findViewById(R.id.version_number);
            versionApi = view.findViewById(R.id.version_api_level);
        }
    }
}
