package com.example.feature_main.presentation.adapter

import androidx.core.content.ContextCompat
import com.example.domain.models.Courses
import com.example.domain.util.formatRussianDate
import com.example.feature_auth.R
import com.example.feature_auth.databinding.ItemCourseBinding
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateViewBinding

fun courseAdapterDelegate(onLikeClick: (Courses) -> Unit) =
    adapterDelegateViewBinding<Courses, Courses, ItemCourseBinding>(
        { layoutInflater, root -> ItemCourseBinding.inflate(layoutInflater, root, false) }
    ) {
        bind {
            binding.tvTitle.text = item.title
            binding.tvPrice.text = getString(R.string.price_format, item.price)
            binding.tvStartDate.text = item.startDate.formatRussianDate()
            binding.tvRate.text = item.rate.toString()
            binding.tvDescription.text = item.text

            // Если лайк есть — зеленая иконка нифига пока не работает (
            val drawable = if (item.hasLike) {
                ContextCompat.getDrawable(binding.root.context, R.drawable.bookmark_green)
            } else {
                ContextCompat.getDrawable(binding.root.context, R.drawable.bookmark)
            }
            binding.ivLike.setImageDrawable(drawable)

            binding.ivLike.setOnClickListener { onLikeClick(item) }
        }
    }