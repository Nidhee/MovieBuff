package com.assignment.moviebuff.di;

import com.assignment.moviebuff.movierepo.MovieRepository;
import dagger.Component;

@Component(modules = MovieAppModule.class)
public interface MovieAppComponent {

    void inject(MovieRepository movieRepository);

    MovieScreenComponent addMovieScreenComponent(MovieScreenModule movieScreenModule);
}
