package com.uraga.domain.util

import com.google.gson.Gson
import com.uraga.domain.models.response.ErrorResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseRepo(){
    suspend fun <T> safeApiCall(apiToBeCalled: suspend() -> Response<T>): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
              val response: Response<T> = apiToBeCalled()
              if (response.isSuccessful) {
                  Resource.Success(data = response.body()!!)
              }  else {
                  val errorResponse = convertErrorBody(response.errorBody())
                  Resource.Error(errorMessage = errorResponse?.message ?: "Something went wrong")
              }
            } catch (e: HttpException){
                Resource.Error(errorMessage = e.message() ?: "Something went wrong")
            } catch (e: IOException) {
                Resource.Error(errorMessage = "Please check yout network connection")
            }catch (e: Exception) {
                Resource.Error(errorMessage = "Something went wrong")
            }
        }
    }

    private fun convertErrorBody(errorBody: ResponseBody?): ErrorResponse? {
        return try {
            errorBody?.let {
                Gson().fromJson(
                    it.charStream(),
                    ErrorResponse::class.java
                )
            }
        } catch (exception: Exception){
            null
        }
    }
}