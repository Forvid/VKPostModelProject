package ru.netology

// Интерфейс для вложений
interface Attachment {
    val type: String
}

// Класс для фото
data class Photo(val id: Int, val ownerId: Int, val photo130: String, val photo604: String)

data class PhotoAttachment(val photo: Photo) : Attachment {
    override val type = "photo"
}

// Класс для видео
data class Video(val id: Int, val ownerId: Int, val title: String, val duration: Int)

data class VideoAttachment(val video: Video) : Attachment {
    override val type = "video"
}

// Прочие классы для комментариев, лайков, репостов и просмотров
data class Comments(val count: Int)
data class Likes(val count: Int)
data class Reposts(val count: Int)
data class Views(val count: Int)

// Класс Post с nullable полями и массивом вложений
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
    val views: Views? = null,
    val attachments: Array<Attachment>? = null
)

// Объект WallService для управления постами
object WallService {
    private var posts = emptyArray<Post>()
    private var nextId = 1

    // Метод добавления поста
    fun add(post: Post): Post {
        val newPost = post.copy(id = nextId++)
        posts += newPost
        return newPost
    }

    // Метод обновления поста
    fun update(post: Post): Boolean {
        for ((index, currentPost) in posts.withIndex()) {
            if (currentPost.id == post.id) {
                posts[index] = post.copy(ownerId = currentPost.ownerId, date = currentPost.date)
                return true
            }
        }
        return false
    }

    // Очистка для тестов
    fun clear() {
        posts = emptyArray()
        nextId = 1
    }
}
