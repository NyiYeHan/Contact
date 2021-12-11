package mm.com.contact.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<W : Any>(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

    protected lateinit var mData: W

    init {
        itemView.setOnClickListener(this)
    }

    abstract fun setData(data: W)

}