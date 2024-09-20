package main

// Классы для различных объектов

data class Comments(
    val count: Int = 0,
    val canPost: Boolean = true,
    val groupsCanPost: Boolean = true,
    val canClose: Boolean = false,
    val canOpen: Boolean = true
)

data class Likes(
    val count: Int = 0,
    val userLikes: Boolean = false,
    val canLike: Boolean = true,
    val canPublish: Boolean = true
)

data class Reposts(
    val count: Int = 0,
    val userReposted: Boolean = false
)

data class Views(
    val count: Int = 0
)

data class Post(
    val id: Int,
    val ownerId: Int,
    val fromId: Int,
    val date: Int,
    val text: String,
    val friendsOnly: Boolean = false,
    val comments: Comments = Comments(),
    val likes: Likes = Likes(),
    val reposts: Reposts = Reposts(),
    val views: Views = Views(),
    val postType: String = "post",
    val isPinned: Boolean = false,
    val markedAsAds: Boolean = false,
    val isFavorite: Boolean = false
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[index] = post.copy(ownerId = existingPost.ownerId, date = existingPost.date)
                return true
            }
        }
        return false
    }

    fun getPosts(): Array<Post> = posts
}

fun main() {
    //  несколько постов
    val post1 = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "Первый пост!")
    val post2 = Post(id = 0, ownerId = 100, fromId = 300, date = 1663157250, text = "Второй пост!")

    //  посты в WallService
    val addedPost1 = WallService.add(post1)
    val addedPost2 = WallService.add(post2)

    println("Добавленные посты:")
    println(addedPost1)
    println(addedPost2)

    // Обновление пост
    val updatedPost = addedPost1.copy(text = "Обновленный текст для первого поста!")
    val updateResult = WallService.update(updatedPost)

    println("\nОбновление поста:")
    println("Успешно обновлено: $updateResult")

    // Выводим все посты
    println("\nВсе посты:")
    WallService.getPosts().forEach { println(it) }
}
