package com.hamoda.auth.domain.model

interface PatternValidator {
    fun matches(value: String): Boolean
}