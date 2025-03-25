package com.example.practuhv3.repository

import androidx.lifecycle.LiveData
import com.example.practuhv3.activity.Post

interface PostRepository
{
    fun get(): LiveData<Post>
    fun like()
    fun share()
}