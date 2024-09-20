package main

import kotlin.test.Test
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import kotlin.test.assertEquals

class WallServiceTest {

    @Test
    fun testAdd() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Тестовый пост", friendsOnly = false)
        val addedPost = WallService.add(post)

        assertTrue(addedPost.id > 0, "Пост должен получить уникальный идентификатор")
        assertEquals(post.text, addedPost.text, "Текст добавленного поста должен совпадать")
    }

    @Test
    fun testUpdateSuccess() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Тестовый пост", friendsOnly = false)
        val addedPost = WallService.add(post)

        val updatedPost = addedPost.copy(text = "Обновленный текст")
        val result = WallService.update(updatedPost)

        assertTrue(result, "Обновление должно быть успешным")
        assertEquals("Обновленный текст", WallService.getPosts().first().text, "Текст поста должен обновиться")
    }

    @Test
    fun testUpdateFail() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Тестовый пост", friendsOnly = false)
        WallService.add(post)

        val nonExistentPost = Post(id = 999, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Не существующий пост", friendsOnly = false)
        val result = WallService.update(nonExistentPost)

        assertFalse(result, "Обновление должно завершиться неудачей для несуществующего поста")
    }

    @Test
    fun testAddWithZeroId() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Пост с id 0", friendsOnly = false)
        val addedPost = WallService.add(post)

        assertTrue(addedPost.id > 0, "ID поста должен быть уникальным и больше 0")
    }

    @Test
    fun testUpdateNonExistentPost() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 999, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "Пост с несуществующим ID", friendsOnly = false)
        val result = WallService.update(post)

        assertFalse(result, "Обновление поста с несуществующим ID должно вернуть false")
    }

    @Test
    fun testAddWithEmptyText() {
        WallService.clear()  // Очищаем перед тестом
        val post = Post(id = 0, ownerId = 100, fromId = 200, createdBy = 1, date = 1663157200, text = "", friendsOnly = false)
        val addedPost = WallService.add(post)

        assertEquals("", addedPost.text, "Текст поста должен остаться пустым")
    }
}
