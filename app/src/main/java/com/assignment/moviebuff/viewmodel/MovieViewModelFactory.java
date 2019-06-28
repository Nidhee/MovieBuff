package com.assignment.moviebuff.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.assignment.moviebuff.movierepo.MovieRepository;
import javax.inject.Inject;

public class MovieViewModelFactory implements ViewModelProvider.Factory {

    @Inject
    MovieRepository movieRepository;

    @Inject
    MovieViewModelFactory(){ }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MovieViewModel.class)){
            return (T) new MovieViewModel(this.movieRepository);
         }
        throw new IllegalArgumentException("Wrong ViewModel class");
    }
}
