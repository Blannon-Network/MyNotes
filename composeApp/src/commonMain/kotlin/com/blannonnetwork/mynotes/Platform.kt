package com.blannonnetwork.mynotes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform