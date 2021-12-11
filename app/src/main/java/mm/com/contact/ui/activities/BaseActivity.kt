package mm.com.contact.ui.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {

    protected var isLoading = false
    private var mProgressBar: ProgressDialog? = null


    protected fun showLoading() {
        if (!isLoading) {
            mProgressBar = ProgressDialog.show(this, "", "loading...")
            if (mProgressBar != null) {
                mProgressBar!!.show()
            }
            isLoading = true
        }
    }

    protected fun showLoading(message: String) {
        if (!isLoading) {
            mProgressBar = ProgressDialog.show(this, "", message)
            if (mProgressBar != null) {

                mProgressBar!!.show()
            }
            isLoading = true
        }
    }



    protected fun hideLoading() {
        if (isLoading) {
            if (mProgressBar != null && mProgressBar!!.isShowing) {
                mProgressBar!!.dismiss()
            }
            isLoading = false
        }
    }

    fun getVersionName(): String {
        val pkInfo = packageManager.getPackageInfo(packageName, 0)
        return pkInfo.versionName
    }

    protected open fun showPromptDialog(errorMsg: String) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        alertDialog.show()
    }

    protected open fun showPromptDialog(errorMsg: String, ok: () -> Unit) {
        val alertDialog: AlertDialog? = this.let {
            val builder = AlertDialog.Builder(it)
            builder.apply {
                setPositiveButton(
                    "OK"
                ) { dialog, id ->
                    ok()
                    dialog.dismiss()
                }
            }
            builder.create()
        }
        alertDialog!!.setMessage(errorMsg)
        alertDialog.show()
    }

    protected fun hideSoftKeyboard(context: Context) {
        val imm = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

}