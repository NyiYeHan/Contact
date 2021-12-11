package mm.com.contact.ui.activities

import android.Manifest.permission.CALL_PHONE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.activity_contact_detail.*
import mm.com.contact.R
import mm.com.contact.data.remote.response.User


class ContactDetailActivity : BaseActivity(), View.OnClickListener {

    private lateinit var mData: User
    private var mPhoneNumber = ""
    private var mEmail = ""
    private var mUrl = ""

    companion object {
        const val KEY_USER = "USER"

        fun newIntent(context: Context, user: User): Intent {
            val intent = Intent(context, ContactDetailActivity::class.java)
            intent.putExtra(KEY_USER, user)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_detail)
        if (intent.hasExtra(KEY_USER)) {
            mData = intent.getSerializableExtra(KEY_USER) as User
            setUpUI(mData)
        }

    }

    private fun setUpUI(user: User) {

        supportActionBar?.title = getString(R.string.profile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (!user.website.startsWith("http://") && !user.website.startsWith("https://")) {
            val url = "http://${user.website}"
            tvWeb.text = url
        } else {
            tvWeb.text = user.website
        }
        tvFirstWord.text = user.name.first().toString()
        tvName.text = user.name
        tvNumber.text = user.phone
        tvEmailNo.text = user.email

        tvStreet.text = user.address.street
        tvSuite.text = user.address.suite
        tvCity.text = user.address.city
        tvZipCode.text = user.address.zipcode

        mPhoneNumber = user.phone
        mUrl = user.website
        mEmail = user.email

        ivPhone.setOnClickListener(this)
        ivWeb.setOnClickListener(this)
        ivEmail.setOnClickListener(this)

        tvPhone.setOnClickListener(this)
        tvNumber.setOnClickListener(this)

        tvEmailNo.setOnClickListener(this)
        tvEmail.setOnClickListener(this)

        tvWebNo.setOnClickListener(this)
        tvWeb.setOnClickListener(this)


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return false
    }

    private fun callPhone(phoneNumber: String) {
        val phoneIntent = Intent(Intent.ACTION_CALL)
        phoneIntent.data = Uri.parse("tel:$phoneNumber")
        if (ActivityCompat.checkSelfPermission(
                this,
                CALL_PHONE
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(CALL_PHONE), 1)
        } else {
            startActivity(phoneIntent)
        }

    }

    private fun link(url: String) {
        if (!url.startsWith("http://") && !url.startsWith("https://")) {
            val linkUrl = "http://$url"
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(linkUrl))
            startActivity(browserIntent)
        }


    }

    private fun sendEmail(email: String) {

        val addresses = arrayOf(email)
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, addresses)
            putExtra(Intent.EXTRA_SUBJECT, "subject")
            putExtra(Intent.EXTRA_TEXT, "text")

        }
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(
                this@ContactDetailActivity,
                "Require App is not installed",
                Toast.LENGTH_LONG
            ).show()
        }
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.ivPhone -> {
                callPhone(mPhoneNumber)
            }
            R.id.tvPhone -> {
                callPhone(mPhoneNumber)
            }
            R.id.tvNumber -> {
                callPhone(mPhoneNumber)
            }
            R.id.ivWeb -> {
                link(mUrl)
            }
            R.id.tvWebNo -> {
                link(mUrl)
            }
            R.id.tvWeb -> {
                link(mUrl)
            }
            R.id.ivEmail -> {
                sendEmail(mEmail)
            }
            R.id.tvEmailNo -> {
                sendEmail(mEmail)
            }

            R.id.tvEmail -> {
                sendEmail(mEmail)
            }
        }

    }
}