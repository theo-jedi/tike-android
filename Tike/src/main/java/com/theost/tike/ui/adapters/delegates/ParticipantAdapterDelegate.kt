package com.theost.tike.ui.adapters.delegates

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.theost.tike.R
import com.theost.tike.data.models.ui.ListParticipant
import com.theost.tike.databinding.ItemParticipantBinding
import com.theost.tike.ui.interfaces.AdapterDelegate
import com.theost.tike.ui.interfaces.DelegateItem

class ParticipantAdapterDelegate(private val clickListener: (participantId: String) -> Unit) : AdapterDelegate {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        val binding = ItemParticipantBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int,
        enabled: Boolean
    ) {
        (holder as ViewHolder).bind(item as ListParticipant, enabled)
    }

    override fun isOfViewType(item: DelegateItem): Boolean = item is ListParticipant

    class ViewHolder(
        private val binding: ItemParticipantBinding,
        private val clickListener: (participantId: String) -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(listParticipant: ListParticipant, enabled: Boolean) {
            binding.participantName.text = listParticipant.name
            binding.removeParticipantButton.setOnClickListener { clickListener(listParticipant.id) }

            Glide.with(binding.root)
                .load(listParticipant.avatar)
                .placeholder(R.color.blue)
                .error(R.color.blue)
                .into(binding.participantAvatar)

            binding.participantName.isEnabled = enabled
            binding.removeParticipantButton.isEnabled = enabled
        }

    }

}