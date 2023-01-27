package com.uraga.moviestmdb.use_cases

import com.uraga.domain.repository.MoviesRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class GetListMoviesTest {

    @RelaxedMockK
    private lateinit var moviesRepository: MoviesRepository

    lateinit var getListMovies: com.uraga.use_cases_layer.GetListMovies

    @Before
    fun obBefore() {
        MockKAnnotations.init(this)
        getListMovies = com.uraga.use_cases_layer.GetListMovies(moviesRepository)
    }

    @Test
    fun `when the api doesnt return anything`() = runBlocking {
        //Given
        coEvery {
            moviesRepository.getMovies(
                "2344f08f015e4a7ea9eb861d9626f615",
                "es-ES"
            )
        }
        //When

        //Then
        // coVerify(exactly = 1) {  }
    }

}