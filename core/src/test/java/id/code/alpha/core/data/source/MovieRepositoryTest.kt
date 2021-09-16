package id.code.alpha.core.data.source

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import id.code.alpha.core.data.Resource
import id.code.alpha.core.data.source.local.LocalDataSource
import id.code.alpha.core.data.source.local.entity.MovieEntity
import id.code.alpha.core.data.source.remote.RemoteDataSource
import id.code.alpha.core.data.source.remote.network.ApiResponse
import id.code.alpha.core.data.source.remote.response.popular.PopularMovie
import id.code.alpha.core.data.util.MainCoroutineScopeRule
import id.code.alpha.core.domain.model.Movie
import id.code.alpha.core.utils.AppExecutors
import junit.framework.TestCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MovieRepositoryTest : TestCase() {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    val mainCoroutineScopeRule = MainCoroutineScopeRule()

    @Mock
    lateinit var remoteDataSource: RemoteDataSource

    @Mock
    lateinit var localDataSource: LocalDataSource

    @Mock
    lateinit var appExecutor: AppExecutors

    private lateinit var repository: MovieRepository
    private val response: Flow<ApiResponse<List<PopularMovie>>> =
        flowOf(ApiResponse.Success(listOf(PopularMovie(id = 123))))
    private val responseDb: Flow<List<MovieEntity>> = flowOf(listOf(MovieEntity(id = 123)))
    private val resultData: Flow<Resource<List<Movie>>> =
        flowOf(Resource.Success(listOf(Movie(id = 123))))

    public override fun setUp() {
        super.setUp()
        MockitoAnnotations.initMocks(this)
        repository = MovieRepository(remoteDataSource, localDataSource, appExecutor)
    }

    public override fun tearDown() {}

    fun testGetAllMovies() = runBlockingTest {
        `when`(remoteDataSource.getAllMovies("upcoming", 1)).thenReturn(response)
        `when`(localDataSource.getAllMovies("upcoming", 1)).thenReturn(responseDb)
        val result = repository.getAllMovies("upcoming", 1).first()
        assert(result.data == resultData)
    }
}