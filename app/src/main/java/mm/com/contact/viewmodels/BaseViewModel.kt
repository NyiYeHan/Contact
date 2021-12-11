package mm.com.contact.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel() {

    lateinit var mErrorLD: MutableLiveData<String>
    lateinit var mCompositeDisposable: CompositeDisposable

    open fun initViewModel() {
        mErrorLD = MutableLiveData()
        mCompositeDisposable = CompositeDisposable()
    }

    override fun onCleared() {
        super.onCleared()
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.dispose()
        }
    }
}