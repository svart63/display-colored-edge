package com.github.svart63.colored.edge.config

import com.github.svart63.colored.edge.CommApiFactory
import com.github.svart63.colored.edge.CommFactory
import com.github.svart63.colored.edge.CommWriter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AppConfig {
    @Bean
    fun commWriter(@Value("\${comm.port}") portName: String): CommWriter {
        val factory = apiFactory()
        val commWriter = factory.newWriter(portName)
        commWriter.init()
        return commWriter
    }

    @Bean
    fun apiFactory(): CommApiFactory = CommFactory()
}