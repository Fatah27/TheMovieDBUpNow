package com.mobile.fatah.themoviedbupnow.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.mobile.fatah.themoviedbupnow.R;
import com.mobile.fatah.themoviedbupnow.model.ResultsItem;

public class DetailActivity extends AppCompatActivity {
    TextView titleMovie;
    ImageView posterMovie;

    public static final String EXTRA_OBJECT = "OBJECT";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        titleMovie = findViewById(R.id.title_Movie);
        posterMovie = findViewById(R.id.image_Poster);

        ResultsItem get = getIntent().getParcelableExtra(EXTRA_OBJECT);
        Glide.with(DetailActivity.this)
                .load(get.getPosterPath())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(posterMovie);
        titleMovie.setText(get.getTitle());

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle(titleMovie.getText().toString());


    }
}
