package com.uraga.moviestmdb.di

import com.uraga.data_layer.network.MoviesApiServices
import com.uraga.data_layer.repository.MoviesRepositoryImpl
import com.uraga.domain.repository.MoviesRepository
import com.uraga.use_cases_layer.GetAccessToken
import com.uraga.use_cases_layer.GetListMovies
import com.uraga.use_cases_layer.GetRequestToken
import com.uraga.use_cases_layer.MoviesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
@InstallIn(SingletonComponent::class)
class MoviesModule {

    @Singleton
    @Provides
    fun providesMoviesApiService(retrofit: Retrofit): MoviesApiServices {
        return retrofit.create(MoviesApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideMoviesRepository(moviesApiServices: MoviesApiServices): MoviesRepository {
        return MoviesRepositoryImpl(moviesApiServices)
    }

    @Provides
    @Singleton
    fun providesMoviesUseCases(repository: MoviesRepository): MoviesUseCases {
        return MoviesUseCases(
            getRequestToken = GetRequestToken(repository),
            getAccessToken = GetAccessToken(repository),
            getListMovies = GetListMovies(repository)
        )
    }
}