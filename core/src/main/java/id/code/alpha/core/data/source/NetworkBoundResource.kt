package id.code.alpha.core.data.source

import id.code.alpha.core.data.Resource
import id.code.alpha.core.data.source.remote.network.ApiResponse
import id.code.alpha.core.utils.AppExecutors
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<ResultType, RequestType>(
    private val executors: AppExecutors
) {
    private val result: Flow<Resource<ResultType>> =
        flow {
            emit(Resource.Loading<ResultType>())
            val dbResource = loadFromDatabase().first()
            if (shouldFetchData(dbResource)) {
                emit(Resource.Loading<ResultType>())
                when (val apiResponse = createCall().first()) {
                    is ApiResponse.Success -> {
                        saveCallResult(apiResponse.data)
                        emitAll(loadFromDatabase().map {
                            Resource.Success(it)
                        })
                    }

                    is ApiResponse.Empty -> {
                        emitAll(loadFromDatabase().map {
                            Resource.Success(
                                it
                            )
                        })
                    }
                    is ApiResponse.Error -> {
                        onFetchFailed()
                        emit(Resource.Error<ResultType>(apiResponse.errorMessage))
                    }
                }
            } else {
                emitAll(loadFromDatabase().map {
                    Resource.Success(
                        it
                    )
                })
            }
        }

    protected open fun onFetchFailed() {}

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract fun shouldFetchData(it: ResultType?): Boolean

    protected abstract fun loadFromDatabase(): Flow<ResultType>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Resource<ResultType>> = result
}