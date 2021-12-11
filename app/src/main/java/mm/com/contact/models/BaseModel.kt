package mm.com.contact.models

import com.google.gson.Gson
import mm.com.contact.data.remote.ApiService
import mm.com.contact.utils.AppConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseModel {

    companion object {
        lateinit var mApiService: ApiService
    }

    init {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(60, TimeUnit.SECONDS)
        okHttpClient.readTimeout(60, TimeUnit.SECONDS)
        okHttpClient.writeTimeout(60, TimeUnit.SECONDS)
        okHttpClient.addInterceptor(loggingInterceptor)

        val retrofitService = Retrofit.Builder()
            .baseUrl(AppConstants.BASE_URL_SERVICE)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        mApiService = retrofitService.create(ApiService::class.java)
    }
}