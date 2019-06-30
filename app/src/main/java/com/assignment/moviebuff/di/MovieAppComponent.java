package com.assignment.moviebuff.di;


import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = MovieAppModule.class)
public interface MovieAppComponent {
    MovieScreenComponent addMovieScreenComponent(MovieScreenModule movieScreenModule);
}
