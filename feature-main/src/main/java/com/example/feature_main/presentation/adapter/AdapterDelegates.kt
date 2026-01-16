package com.example.feature_main.presentation.adapter

import android.graphics.Color
import com.example.domain.models.Courses
import com.example.feature_auth.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(onLikeClick: (Courses) -> Unit) =
    adapterDelegateViewBinding<Courses, Courses, ItemCourseBinding>(
        { layoutInflater, root -> ItemCourseBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.tvTitle.text = item.title
            binding.tvPrice.text = item.price
            binding.tvRate.text = item.rate.toString()
            binding.tvDescription.text = item.text

            // Если лайк есть — зеленая иконка
            val color = if (item.hasLike) Color.GREEN else Color.GRAY
            binding.ivLike.setColorFilter(color)

            binding.ivLike.setOnClickListener { onLikeClick(item) }
        }
    }