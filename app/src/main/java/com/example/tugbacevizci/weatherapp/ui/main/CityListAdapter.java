package com.example.tugbacevizci.weatherapp.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tugbacevizci.weatherapp.R;
import com.example.tugbacevizci.weatherapp.data.local.City;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.ViewHolder> {

    private List<City> mCities;
    private CityListener listener;

    public CityListAdapter(ArrayList<City> cities, CityListener mItemListener) {
        this.mCities = cities;
        this.listener = mItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fav_city,
                        parent,
                        false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvAreaName.setText(mCities.get(position).cityName);
        holder.tvWeather.setText(mCities.get(position).weather);

    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.txt_area_name)
        TextView tvAreaName;

        @BindView(R.id.txt_weather)
        TextView tvWeather;

        private View view;

        public ViewHolder(View itemView) {
            super(itemView);
            this.view = itemView;
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.city_container)
        void onCityContainerClicked() {
            listener.onCityClick(mCities.get(getAdapterPosition()));
        }

    }

    public interface CityListener {

        void onCityClick(City clickedCity);
    }
}
