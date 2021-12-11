package mm.com.contact.ui.activities

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import mm.com.contact.R
import mm.com.contact.data.remote.response.User
import mm.com.contact.ui.adapters.UserAdapter
import mm.com.contact.ui.delegates.ContactDelegate
import mm.com.contact.viewmodels.UserViewModel


class MainActivity : BaseActivity(), ContactDelegate, SwipeRefreshLayout.OnRefreshListener {


    private lateinit var mAdapter: UserAdapter
    private lateinit var mViewModel: UserViewModel

    companion object {
        fun newIntent(context: Context): Intent {
            return Intent(context, MainActivity::class.java)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUpUI()
        getUser()
        observe()


    }

    private fun setUpUI() {


        mViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        mAdapter = UserAdapter(this, this)
        rvContact.apply {
            layoutManager = LinearLayoutManager(applicationContext)
            adapter = mAdapter
        }
        swipeRefresh.setOnRefreshListener(this)


    }

    private fun observe() {

        mViewModel.mResponseLd.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            val data: MutableList<User> = it.sortedBy { it.name } as MutableList<User>
            mAdapter.setNewData(data)
        })
        mViewModel.mErrorLD.observe(this, Observer {
            swipeRefresh.isRefreshing = false
            showPromptDialog(it)
        })
    }

    override fun onItemClick(user: User) {
        startActivity(ContactDetailActivity.newIntent(this, user))
    }

    private fun getUser() {
        swipeRefresh.isRefreshing = true
        mViewModel.getUser()
    }

    override fun onRefresh() {
        getUser()
    }
}