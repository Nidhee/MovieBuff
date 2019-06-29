package com.assignment.moviebuff.di;

import android.content.Context;

import com.assignment.moviebuff.BuildConfig;
import com.assignment.moviebuff.MyApplication;
import com.assignment.moviebuff.movierepo.MovieRepository;
import com.assignment.moviebuff.movierepo.local.MovieRoomDatabase;
import com.assignment.moviebuff.movierepo.remote.MovieService;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;


import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class MovieModule {

    private final MyApplication myApplication;

    public MovieModule(MyApplication myApplication) {
        this.myApplication = myApplication;
    }


    @Provides
    Context providesApplicationContext(){
        return this.myApplication;
    }

    @Provides

    MovieRepository providesMovieRepository(){
        return new MovieRepository(myApplication);
    }

    @Provides

    MovieService providesMovieService(Retrofit retrofit){
        return retrofit.create(MovieService.class);
    }
    @Provides
    MovieRoomDatabase providesMovieRoomDatabase(){
       return MovieRoomDatabase.getDatabase(myApplication.getApplicationContext());
    }

    @Provides
    Retrofit providesRetrofitClient(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient httpClient = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient)
                .build();
    }
}
