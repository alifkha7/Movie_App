package com.hirocode.movieapp.di

import com.hirocode.movieapp.core.domain.usecase.MovieInteractor
import com.hirocode.movieapp.core.domain.usecase.MovieUseCase
import com.hirocode.movieapp.detail.DetailMovieViewModel
import com.hirocode.movieapp.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<MovieUseCase> { MovieInteractor(get()) }
}

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
}