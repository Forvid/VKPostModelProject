package main

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
    val createdBy: Int,
    val date: Int,
    val text: String? = null,
    val replyOwnerId: Int? = null,
    val replyPostId: Int? = null,
    val friendsOnly: Boolean,
    val comments: Comments? = null,
    val likes: Likes? = null,
    val reposts: Reposts? = null,
    val views: Views? = null
)

object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

    // Метод добавления поста
    fun add(post: Post): Post {
        val postWithId = post.copy(id = nextId++)
        posts += postWithId
        return postWithId
    }

    // Метод обновления поста
    fun update(post: Post): Boolean {
        for ((index, existingPost) in posts.withIndex()) {
            if (existingPost.id == post.id) {
                posts[index] = post.copy(ownerId = existingPost.ownerId, date = existingPost.date)
                return true
            }
        }
        return false
    }

    // Получение всех постов
    fun getPosts(): Array<Post> = posts

    // Очистка постов (для тестов)
    fun clear() {
        posts = emptyArray()
        nextId = 1
    }
}

fun main() {
    WallService.clear()  // Очистим перед запуском

    val post1 = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Первый пост!", friendsOnly = false)
    val post2 = Post(id = 0, ownerId = 100, fromId = 300, createdBy = 1, date = 1663157250, text = "Второй пост!", friendsOnly = false)

    val addedPost1 = WallService.add(post1)
    val addedPost2 = WallService.add(post2)

    println("Добавленные посты:")
    println(addedPost1)
    println(addedPost2)

    val updatedPost = addedPost1.copy(text = "Обновленный текст для первого поста!")
    val updateResult = WallService.update(updatedPost)

    println("\nОбновление поста:")
    println("Успешно обновлено: $updateResult")

    println("\nВсе посты:")
    WallService.getPosts().forEach { println(it) }
}
