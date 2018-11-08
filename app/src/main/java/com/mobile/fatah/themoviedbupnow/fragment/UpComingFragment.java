package com.mobile.fatah.themoviedbupnow.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mobile.fatah.themoviedbupnow.BuildConfig;
import com.mobile.fatah.themoviedbupnow.R;
import com.mobile.fatah.themoviedbupnow.adapter.AdapterMovie;
import com.mobile.fatah.themoviedbupnow.model.ResponseMovie;
import com.mobile.fatah.themoviedbupnow.model.ResultsItem;
import com.mobile.fatah.themoviedbupnow.network.ApiClient;
import com.mobile.fatah.themoviedbupnow.network.ApiService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpComingFragment extends Fragment {
    RecyclerView recyclerViewMovie;
    @BindView(R.id.pdBar)
    ProgressBar pdBar;
    @BindView(R.id.id)
    TextView id;
    Unbinder unbinder;

    public UpComingFragment() {

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_now_playing, container, false);
        unbinder = ButterKnife.bind(this, view);

        recyclerViewMovie = (RecyclerView) view.findViewById(R.id.rv_rcyclerview);
        recyclerViewMovie.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        pdBar.setVisibility(View.VISIBLE);
        id.setVisibility(View.VISIBLE);
        getMovie();


        return view;

    }



    private void getMovie() {
        ApiService service = ApiClient.getClient().create(ApiService.class);
        Call<ResponseMovie> call = service.getUpComingMoviee(BuildConfig.API_KEY, BuildConfig.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                List<ResultsItem> dataMovie = response.body().getResults();
                Log.d(" ", "onResponse data: " + dataMovie);
                AdapterMovie adapterMovie = new AdapterMovie(getActivity(), dataMovie);
                pdBar.setVisibility(View.GONE);
                id.setVisibility(View.GONE);
                recyclerViewMovie.setAdapter(adapterMovie);

            }

            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: " + t.toString());


            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
