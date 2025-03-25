package com.example.practuhv3.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.practuhv3.R
import com.example.practuhv3.databinding.ActivityMainBinding
import com.example.practuhv3.viewmodel.PostViewModel

data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 0,
    var shares: Int = 0,
    var likedByMe: Boolean = false
)

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.data.observe(this, Observer { post ->
            updateUI(post)
        })
        binding.like.setOnClickListener {
            Log.d("stuff", "like")
            viewModel.like()
        }

        // Обработка нажатия на кнопку "поделиться"
        binding.share.setOnClickListener {
            viewModel.share()
        }
    }

    private fun updateUI(post: Post) {
        with(binding) {
            textTitle.text = post.author
            textDate.text = post.published
            textContent.text = post.content
            like.setImageResource(if (post.likedByMe) R.drawable.icon_like_add else R.drawable.icon_like)
            likeCount.text = formatNumber(post.likes)
            sharecount.text = formatNumber(post.shares)
        }
    }

    private fun formatNumber(number: Int): String {
        return when {
            number >= 1_000_000 -> String.format("%.1fM", number / 1_000_000.0).replace(",", ".")
            number >= 1_000 -> String.format("%.0fK", number / 1_000.0)
            else -> number.toString()
        }
    }
}





