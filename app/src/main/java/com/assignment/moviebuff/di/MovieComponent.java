package com.assignment.moviebuff.di;

import com.assignment.moviebuff.movierepo.MovieRepository;
import com.assignment.moviebuff.view.MainActivity;
import dagger.Component;

@Component(modules = MovieModule.class)
public interface MovieComponent {

    void inject(MainActivity mainActivity);

    void inject(MovieRepository movieRepository);
}
