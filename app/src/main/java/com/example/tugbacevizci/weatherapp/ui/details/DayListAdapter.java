package com.example.tugbacevizci.weatherapp.ui.details;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.data.remote.WeatherDayData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DayListAdapter extends RecyclerView.Adapter<DayListAdapter.ViewHolder> {

    private List<WeatherDayData> weatherDayData;
    private DayListAdapter.DayListener listener;

    public DayListAdapter(ArrayList<WeatherDayData> weatherDayData, DayListAdapter.DayListener mItemListener) {
        this.weatherDayData = weatherDayData;
        this.listener = mItemListener;
    }

    @NonNull
    @Override
    public DayListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DayListAdapter.ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_day_weather, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull DayListAdapter.ViewHolder holder, int position) {
        holder.tvAreaName.setText(weatherDayData.get(position).dt_txt);
        holder.tvWeather.setText(weatherDayData.get(position).weather.get(0).main);
    }

    @Override
    public int getItemCount() {
        return weatherDayData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_day_name)
        TextView tvAreaName;

        @BindView(R.id.txt_weather)
        TextView tvWeather;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }
    }

    public interface DayListener {
    }
}
