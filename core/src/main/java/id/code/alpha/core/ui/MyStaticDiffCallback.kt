package id.code.alpha.core.ui

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil

class MyStaticDiffCallback<Y>: DiffUtil.ItemCallback<Y>(){
    override fun areItemsTheSame(oldItem: Y, newItem: Y): Boolean =
        oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Y, newItem: Y): Boolean {
        return oldItem == newItem
    }
}