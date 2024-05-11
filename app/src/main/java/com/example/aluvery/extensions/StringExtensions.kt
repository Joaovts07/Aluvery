package com.example.aluvery.extensions


fun String.filterText(): String {
    val lines = this.split("\n")

    return if (lines.size > 1) {
        return lines.take(1).joinToString("\n") + "..."
    } else {
        this
    }
}
