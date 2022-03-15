package com.example.stylism_fashion_ecommerce.utils

import junit.framework.TestCase
import org.junit.Test
import com.google.common.truth.Truth.assertThat

class CheckFormTest : TestCase() {

    @Test
    fun `empty email return false`() {
        val result = CheckForm.checkEmail("")
        assertThat(result).isFalse()
    }
}