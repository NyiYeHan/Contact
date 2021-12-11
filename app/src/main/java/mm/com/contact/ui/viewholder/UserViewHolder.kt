package mm.com.contact.ui.viewholder

import android.view.View
import kotlinx.android.synthetic.main.view_holder_user.view.*
import mm.com.contact.data.remote.response.User
import mm.com.contact.ui.delegates.ContactDelegate


class UserViewHolder(
    itemView: View,
    val delegate: ContactDelegate
) :
    BaseViewHolder<User>(itemView) {

    override fun setData(data: User) {
        mData = data
        itemView.tvFirstWord.text = mData.name.first().toString()
        itemView.tvEmail.text = mData.email
        itemView.tvName.text = mData.name
    }

    override fun onClick(v: View?) {
        delegate.onItemClick(mData)
    }

}