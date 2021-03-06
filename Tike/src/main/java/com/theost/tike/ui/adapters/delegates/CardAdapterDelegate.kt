package com.theost.tike.ui.adapters.delegates

import android.view.LayoutInflater.from
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theost.tike.R
import com.theost.tike.data.models.ui.UserUi
import com.theost.tike.databinding.ItemCardBinding
import com.theost.tike.ui.extensions.load
import com.theost.tike.ui.extensions.loadWithPlaceholder
import com.theost.tike.ui.interfaces.AdapterDelegate
import com.theost.tike.ui.interfaces.DelegateItem

class CardAdapterDelegate(private val clickListener: (participantId: String) -> Unit) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemCardBinding.inflate(from(parent.context), parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
        enabled: Boolean
    ) {
        (holder as ViewHolder).bind(item as UserUi, enabled)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is UserUi

    class ViewHolder(
        private val binding: ItemCardBinding,
        private val clickListener: (participantId: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: UserUi, enabled: Boolean) {
            with(binding) {
                participantName.text = item.name
                participantName.isEnabled = enabled
                removeParticipantButton.isEnabled = enabled
                when (item.hasAccess) {
                    true -> participantAvatar.loadWithPlaceholder(item.avatar, R.color.blue)
                    false -> participantAvatar.load(R.drawable.ic_blocked)
                }
                when (enabled) {
                    true -> removeParticipantButton.setOnClickListener { clickListener(item.uid) }
                    else -> removeParticipantButton.setOnClickListener(null)
                }
            }
        }
    }
}
