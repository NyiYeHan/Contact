package mm.com.contact.ui.adapters

import android.content.Context
import android.view.ViewGroup
import mm.com.contact.R
import mm.com.contact.data.remote.response.User
import mm.com.contact.ui.delegates.ContactDelegate
import mm.com.contact.ui.viewholder.BaseViewHolder
import mm.com.contact.ui.viewholder.UserViewHolder


class UserAdapter(context: Context, private val delegate: ContactDelegate) :
    BaseRecyclerAdapter<UserViewHolder, User>(context) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<User> {
        val view = mLayoutInflator.inflate(R.layout.view_holder_user, parent, false)
        return UserViewHolder(view, delegate)
    }

}