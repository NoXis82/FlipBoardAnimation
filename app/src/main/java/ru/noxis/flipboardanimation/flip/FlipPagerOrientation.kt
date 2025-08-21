package ru.noxis.flipboardanimation.flip

/**
 * Закрытый класс для определения нашей ориентации
 */
sealed class FlipPagerOrientation {
    data object Vertical : FlipPagerOrientation()
    data object Horizontal : FlipPagerOrientation()
}