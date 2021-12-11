package mm.com.contact.models


import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import mm.com.contact.data.remote.response.User

class ContactModel : BaseModel() {

    companion object {
        private var INSTANCE: ContactModel? = null
        fun getInstance(): ContactModel {
            if (INSTANCE == null) {
                INSTANCE = ContactModel()
            }
            val i = INSTANCE
            return i!!
        }
    }


    fun getUser(

        responseLD: MutableLiveData<MutableList<User>>,
        errorLd: MutableLiveData<String>
    ): Disposable {
        return mApiService.getUsers()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                {
                    if (it.isSuccessful) {
                        val response = it.body()
                        it.body()
                        response?.let {

                            responseLD.value = response
                        }
                    } else {
                        val response = it.body()
                        val message = it.message()
                        response?.let {

                            responseLD.value = response

                        }
                    }


                }, {
                    errorLd.value = it.localizedMessage
                }
            )

    }


}