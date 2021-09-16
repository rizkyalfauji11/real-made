package id.code.alpha.core.data.source.remote

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.code.alpha.core.data.source.remote.network.ApiResponse
import id.code.alpha.core.data.source.remote.network.ApiService
import id.code.alpha.core.data.source.remote.response.popular.PopularMovie
import id.code.alpha.core.data.source.remote.response.popular.PopularMoviesResponse
import id.code.alpha.core.data.util.MainCoroutineScopeRule
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class RemoteDataSourceTest : TestCase() {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Mock
    lateinit var apiService: ApiService
    private lateinit var dataSource: RemoteDataSource

    val response = PopularMoviesResponse(page = 1, results = listOf(PopularMovie(id = 123)))

    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        dataSource = RemoteDataSource(apiService)
    }

    fun testGetAllMovies() = runBlockingTest {
        `when`(apiService.getMovieList("upcoming", "chashads", 1)).thenReturn(response)
        val result = dataSource.getAllMovies("upcoming", 1).first()
        assert((result as ApiResponse.Success).data == response.results)
    }
}