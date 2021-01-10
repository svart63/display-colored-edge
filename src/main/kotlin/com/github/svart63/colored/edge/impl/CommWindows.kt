package com.github.svart63.colored.edge.impl

import com.github.svart63.colored.edge.CommWriter
import com.sun.jna.platform.win32.Kernel32
import com.sun.jna.platform.win32.WinBase
import com.sun.jna.platform.win32.WinBase.DCB
import com.sun.jna.platform.win32.WinBase.SECURITY_ATTRIBUTES
import com.sun.jna.platform.win32.WinDef.BYTE
import com.sun.jna.platform.win32.WinDef.DWORD
import com.sun.jna.platform.win32.WinNT.*
import com.sun.jna.ptr.IntByReference

class CommWindows(val portName: String) : CommWriter {
    private lateinit var commHandle: HANDLE
    private val kernel32: Kernel32 = Kernel32.INSTANCE
    private val intByReference = IntByReference()

    override fun init() {
        val attributes = SECURITY_ATTRIBUTES()
        commHandle = kernel32.CreateFile(
            portName,
            GENERIC_READ or GENERIC_WRITE,
            0,
            attributes,
            OPEN_EXISTING,
            FILE_ATTRIBUTE_NORMAL,
            null
        )
        val serialParams = DCB()
        kernel32.GetCommState(commHandle, serialParams)
        serialParams.BaudRate = DWORD(WinBase.CBR_9600.toLong())
        serialParams.ByteSize = BYTE(8)
        serialParams.StopBits = BYTE(WinBase.ONESTOPBIT.toLong())
        serialParams.Parity = BYTE(WinBase.NOPARITY.toLong())
        kernel32.SetCommState(commHandle, serialParams)
    }

    override fun write(date: ByteArray) {
        kernel32.WriteFile(commHandle, date, date.size, intByReference, null)
    }

    override fun tearDown() {
        kernel32.CloseHandle(commHandle)
    }

}