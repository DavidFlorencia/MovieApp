package com.dflorencia.movieapp.data.source

import com.dflorencia.movieapp.api.MoviePage
import com.dflorencia.movieapp.api.MovieTrailerPage
import com.dflorencia.movieapp.api.TmdbApi

class TmdbApiFake(var moviePage: MoviePage = MoviePage(),
                     var movieTrailerPage: MovieTrailerPage = MovieTrailerPage()):TmdbApi {

    override suspend fun getTopRatedMovies(apiKey: String) = moviePage

    override suspend fun getPopularMovies(apiKey: String) = moviePage

    override suspend fun searchMoviesFromNetwork(apiKey: String, query: String) = moviePage

    override suspend fun getMovieTrailers(movieId: String, apiKey: String) = movieTrailerPage

}