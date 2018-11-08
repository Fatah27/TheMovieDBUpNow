package com.mobile.fatah.themoviedbupnow.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.fatah.themoviedbupnow.BuildConfig;
import com.mobile.fatah.themoviedbupnow.R;
import com.mobile.fatah.themoviedbupnow.activity.DetailActivity;
import com.mobile.fatah.themoviedbupnow.model.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AdapterMovie extends RecyclerView.Adapter<AdapterMovie.Holder> {
    Context context;
    List<ResultsItem> data;
    LayoutInflater inflater;

    public AdapterMovie(Context context, List<ResultsItem> data) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_movie, viewGroup, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, final int i) {
        final String title = data.get(i).getTitle();
        final String image = BuildConfig.URL_GAMBARKU + data.get(i).getPosterPath();
        holder.tvJudulMovie.setText(title);
        Glide.with(context)
                .load(image)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.imgMovie);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResultsItem data = new ResultsItem();
                data.setTitle(title);
                data.setPosterPath(image);
                Intent pass = new Intent(context, DetailActivity.class);
                pass.putExtra(DetailActivity.EXTRA_OBJECT, data);
                context.startActivity(pass);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.imgMovie)
        ImageView imgMovie;
        @BindView(R.id.tvJudulMovie)
        TextView tvJudulMovie;
        public Holder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
