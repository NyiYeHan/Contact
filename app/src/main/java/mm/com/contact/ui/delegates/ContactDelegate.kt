package mm.com.contact.ui.delegates

import mm.com.contact.data.remote.response.User


interface ContactDelegate {
    fun onItemClick(user: User)
}