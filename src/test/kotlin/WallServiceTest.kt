package ru.netology

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WallServiceTest {

    @Test
    fun testAddPost() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(
            id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200,
            text = "Тестовый пост", friendsOnly = false
        )
        val addedPost = WallService.add(post)
        assertEquals(1, addedPost.id)
    }

    @Test
    fun testUpdatePost() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(
            id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200,
            text = "Тестовый пост", friendsOnly = false
        )
        val addedPost = WallService.add(post)
        val updatePost = addedPost.copy(text = "Обновленный текст")
        val result = WallService.update(updatePost)
        assertTrue(result)
        assertEquals("Обновленный текст", updatePost.text)
    }

    @Test
    fun testAddPostWithAttachments() {
        WallService.clear()  // Очищаем перед тестом
        val photo = Photo(id = 1, ownerId = 100, photo130 = "https://link.to/photo130", photo604 = "https://link.to/photo604")
        val photoAttachment = PhotoAttachment(photo)

        val post = Post(
            id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200,
            text = "Пост с вложением", friendsOnly = false,
            attachments = arrayOf(photoAttachment)
        )
        val addedPost = WallService.add(post)

        assertEquals(1, addedPost.attachments?.size)
        assertEquals("photo", addedPost.attachments?.first()?.type)
    }

    @Test
    fun testAddPostWithMultipleAttachments() {
        WallService.clear()  // Очищаем перед тестом
        val photo = Photo(id = 1, ownerId = 100, photo130 = "https://link.to/photo130", photo604 = "https://link.to/photo604")
        val photoAttachment = PhotoAttachment(photo)

        val video = Video(id = 1, ownerId = 200, title = "Видео", duration = 120)
        val videoAttachment = VideoAttachment(video)

        val post = Post(
            id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200,
            text = "Пост с несколькими вложениями", friendsOnly = false,
            attachments = arrayOf(photoAttachment, videoAttachment)
        )
        val addedPost = WallService.add(post)

        assertEquals(2, addedPost.attachments?.size)
        assertEquals("photo", addedPost.attachments?.first()?.type)
        assertEquals("video", addedPost.attachments?.last()?.type)
    }
}
