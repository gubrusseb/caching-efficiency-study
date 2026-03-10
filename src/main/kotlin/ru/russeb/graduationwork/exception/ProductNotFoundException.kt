package ru.russeb.graduationwork.exception

class ProductNotFoundException(
    message: String = "Товар не найден"
) : RuntimeException(message)