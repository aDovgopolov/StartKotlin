package com.example.w.startkotlin.domain.commands


 interface Command<out T> {
    fun execute(): T
}