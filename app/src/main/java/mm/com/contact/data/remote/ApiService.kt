package mm.com.contact.data.remote

import io.reactivex.Observable
import mm.com.contact.data.remote.response.User
import mm.com.contact.utils.AppConstants
import retrofit2.Response
import retrofit2.http.GET


interface ApiService {
    @GET(AppConstants.GET_USER_URL)
    fun getUsers(
    ): Observable<Response<MutableList<User>>>
}