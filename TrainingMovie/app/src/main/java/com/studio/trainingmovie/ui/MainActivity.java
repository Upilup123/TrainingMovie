package com.studio.trainingmovie.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.studio.trainingmovie.R;
import com.studio.trainingmovie.apiRetrofit.ApiService;
import com.studio.trainingmovie.apiRetrofit.CONSTANT;
import com.studio.trainingmovie.apiRetrofit.InstanceRetrofit;
import com.studio.trainingmovie.model.ResponseMovie;
import com.studio.trainingmovie.model.ResultsItem;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rvMovie)
    RecyclerView rvMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        loadData();
    }

    private void loadData() {
//        ApiService apiService = InstanceRetrofit.getInstance();
        ApiService apiService = InstanceRetrofit.getClient().create(ApiService.class);
        Call<ResponseMovie> call = apiService.getUpComingMovie(CONSTANT.APIKEY, CONSTANT.LANGUAGE);
        call.enqueue(new Callback<ResponseMovie>() {
            @Override
            public void onResponse(Call<ResponseMovie> call, Response<ResponseMovie> response) {

                if (response.isSuccessful()){
                    List<ResultsItem> responseMovie = response.body().getResults();
//               Log.d(" ", "onResponse data: " + responseMovie);
                    CustomAdapter adapter = new CustomAdapter(responseMovie, MainActivity.this);
                    rvMovie.setAdapter(adapter);
                    rvMovie.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                }
                else{
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }




            }


            @Override
            public void onFailure(Call<ResponseMovie> call, Throwable t) {
                Log.d("", "onFailure: " + t.toString());
            }
        });
    }


}
