package com.github.svart63.colored.edge

import com.github.svart63.colored.edge.impl.CommLinux
import com.github.svart63.colored.edge.impl.CommWindows
import com.sun.jna.Platform

interface CommApi {
    fun init()
    fun tearDown()
}

interface CommWriter : CommApi {
    fun write(date: ByteArray)
}

interface CommReader : CommApi {
    fun read(): ByteArray
}

interface CommDevice : CommWriter, CommReader

interface CommApiFactory {
    fun newWriter(portName: String): CommWriter
}

class CommFactory : CommApiFactory {
    override fun newWriter(portName: String): CommWriter {
        if (Platform.isWindows()) {
            return CommWindows(portName)
        }
        if (Platform.isLinux()) {
            return CommLinux(portName)
        }
        throw IllegalStateException("Unknown platform: " + System.getProperties().getProperty("os.name"))
    }
}