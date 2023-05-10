package com.emindev.schoolgadgets.library.common.helper

import com.emindev.schoolgadgets.library.common.helper.Helper.Companion.cleanBlanks
import org.junit.Test

class HelperTest {

    @Test
    fun cleanBlanks() {
        assert("deneme budur  ggg n 1265".cleanBlanks() == "denemebudurgggn1265")
    }
}