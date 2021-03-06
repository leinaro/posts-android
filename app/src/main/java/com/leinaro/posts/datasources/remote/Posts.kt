package com.leinaro.posts.datasources.remote

import java.io.Serializable

data class Posts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
) : Serializable {
    var isFavorite: Boolean = false
    var isRead: Boolean = false
}

/*
{
"userId": 1,
"id": 1,
"title": "sunt aut facere repellat provident occaecati excepturi optio reprehenderit",
"body": "quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"
}
 */