import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.test.assertFalse
import main.*

class WallServiceTest {

    @Test
    fun testAdd() {
        val post = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "Тестовый пост")
        val addedPost = WallService.add(post)

        assertTrue(addedPost.id > 0, "Пост должен получить уникальный идентификатор")
        assertEquals(post.text, addedPost.text, "Текст добавленного поста должен совпадать")
    }

    @Test
    fun testUpdateSuccess() {
        val post = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "Тестовый пост")
        val addedPost = WallService.add(post)

        val updatedPost = addedPost.copy(text = "Обновленный текст")
        val result = WallService.update(updatedPost)

        assertTrue(result, "Обновление должно быть успешным")
        assertEquals("Обновленный текст", WallService.getPosts().first().text, "Текст поста должен обновиться")
    }

    @Test
    fun testUpdateFail() {
        val post = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "Тестовый пост")
        WallService.add(post)

        val nonExistentPost = Post(id = 999, ownerId = 100, fromId = 200, date = 1663157200, text = "Не существующий пост")
        val result = WallService.update(nonExistentPost)

        assertFalse(result, "Обновление должно завершиться неудачей для несуществующего поста")
    }
}

@Test
fun testAddWithZeroId() {
    val post = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "Пост с id 0")
    val addedPost = WallService.add(post)

    assertTrue(addedPost.id > 0, "ID поста должен быть уникальным и больше 0")
}

@Test
fun testUpdateNonExistentPost() {
    val post = Post(id = 999, ownerId = 100, fromId = 200, date = 1663157200, text = "Пост с несуществующим ID")
    val result = WallService.update(post)

    assertFalse(result, "Обновление поста с несуществующим ID должно вернуть false")
}

@Test
fun testAddWithEmptyText() {
    val post = Post(id = 0, ownerId = 100, fromId = 200, date = 1663157200, text = "")
    val addedPost = WallService.add(post)

    assertTrue(addedPost.text.isEmpty(), "Текст поста должен остаться пустым")
}



