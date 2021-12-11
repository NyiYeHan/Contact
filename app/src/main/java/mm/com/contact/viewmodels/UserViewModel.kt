package mm.com.contact.viewmodels

import androidx.lifecycle.MutableLiveData
import mm.com.contact.data.remote.response.User
import mm.com.contact.models.ContactModel

class UserViewModel : BaseViewModel() {


    val mResponseLd: MutableLiveData<MutableList<User>>



    init {
        super.initViewModel()

        mResponseLd = MutableLiveData()


    }


    fun getUser() {
        mCompositeDisposable.add(
            ContactModel.getInstance().getUser(
                mResponseLd,
                mErrorLD
            )
        )
    }


}