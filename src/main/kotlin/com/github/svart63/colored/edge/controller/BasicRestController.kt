package com.github.svart63.colored.edge.controller

import com.github.svart63.colored.edge.CommWriter
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/basic")
class BasicRestController(private val commWriter: CommWriter) {
    @PostMapping("/one")
    fun writeOne() {
        commWriter.write("1".toByteArray())
    }

    @PostMapping("/two")
    fun writeTwo() {
        commWriter.write("1".toByteArray())
    }

}