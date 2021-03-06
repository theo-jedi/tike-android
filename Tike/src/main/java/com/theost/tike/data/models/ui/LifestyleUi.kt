package com.theost.tike.data.models.ui

import com.theost.tike.data.models.core.Lifestyle
import com.theost.tike.ui.interfaces.DelegateItem

data class LifestyleUi(
    val id: String,
    val text: String,
    val isSelected: Boolean
) : DelegateItem {
    override fun id(): Any = id
    override fun content(): Any = isSelected
}

fun Lifestyle.mapToLifestyleUi(): LifestyleUi {
    return LifestyleUi(
        id = id,
        text = text,
        isSelected = false
    )
}