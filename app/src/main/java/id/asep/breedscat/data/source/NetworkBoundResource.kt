package id.asep.breedscat.data.source

import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import id.asep.breedscat.data.model.Resource
import kotlinx.coroutines.*
import retrofit2.Response
import timber.log.Timber
import java.lang.Exception
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType>() {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading(null)
        }
        CoroutineScope(coroutineContext).launch(supervisorJob) {
            if (shouldFetch()){
                try {
                    fetchFromNetwork()
                } catch (e:Exception) {
                    val dbResult = loadFromDb()
                    var errorMessage = e.localizedMessage
                    if (errorMessage == null) {
                        errorMessage = e.toString()
                    }
                    setValue(Resource.error(errorMessage, dbResult))
                }
            } else {
                val dbResult = loadFromDb()
                setValue(Resource.success(dbResult, null))
            }
        }
        return this
    }

    private suspend fun fetchFromNetwork() {
        setValue(Resource.loading(null))
        val response = createCall()
        if (response.isSuccessful) {
            val body = response.body()
            if (body == null || response.code() == 204) {
                setValue(Resource.empty())
            } else {
                val resultResponse = withContext(Dispatchers.IO) {
                    processResponse(body)
                }
                setValue(Resource.success(resultResponse, null))
                try {
                    saveCallResult(resultResponse)
                } catch (e:Exception) {
                    Timber.e(e.localizedMessage)
                }
            }
        } else {
            val msg = response.errorBody()?.string()
            val errorMsg = if (msg.isNullOrEmpty()) {
                response.message()
            } else {
                msg
            }
            val dbResult = loadFromDb()
            setValue(Resource.error(errorMsg, dbResult))
        }
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response:RequestType): ResultType

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    @MainThread
    protected abstract suspend fun saveCallResult(item: ResultType)

    @MainThread
    abstract suspend fun createCall(): Response<RequestType>

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract suspend fun loadFromDb(): ResultType


}