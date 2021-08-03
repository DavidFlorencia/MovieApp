package com.dflorencia.movieapp.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.dflorencia.movieapp.getOrAwaitValue
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.Assert.assertTrue

class MovieDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var movieDao: MovieDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testMovies = listOf(
        MovieEntity(id = 11,title = "Movie1",overview = "Summary1"),
        MovieEntity(id = 22,title = "Movie2",overview = "Summary2"),
        MovieEntity(id = 33,title = "Movie3",overview = "Summary33")
    )

    @Before
    fun createDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        movieDao = database.movieDao()
    }

    @Test
    fun insertMoviesAndVerifyDifference(){
        val numberOfMoviesBeforeInsert = movieDao.getMovies().getOrAwaitValue().size

        movieDao.insertAll(testMovies)
        val numberOfMoviesAfterInsert = movieDao.getMovies().getOrAwaitValue().size

        val difference = numberOfMoviesAfterInsert - numberOfMoviesBeforeInsert

        assertTrue("Difference wrong", difference == testMovies.size)
    }

    @Test
    fun clearAllMoviesAndVerifyThatDatabaseIsEmpty(){
        movieDao.clear()
        val numberOfMoviesAfterDelete = movieDao.getMovies().getOrAwaitValue().size
        assertTrue("Database is not empty", numberOfMoviesAfterDelete == 0)
    }

    @Test
    fun clearAllMoviesThenInsertMoviesAndVerifyThatAllMoviesAreInserted(){
        movieDao.clear()
        movieDao.insertAll(testMovies)
        val moviesInserted = movieDao.getMovies().getOrAwaitValue()

        assertTrue("Not all movies were inserted", moviesInserted == testMovies)
    }

    @After
    fun closeDb() {
        database.close()
    }
}