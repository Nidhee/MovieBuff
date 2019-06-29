package com.assignment.moviebuff.di;

import com.assignment.moviebuff.view.MainActivity;

import dagger.Subcomponent;
@MovieScope
@Subcomponent(modules = MovieScreenModule.class)
public interface MovieScreenComponent {
    void inject(MainActivity mainActivity);
}
