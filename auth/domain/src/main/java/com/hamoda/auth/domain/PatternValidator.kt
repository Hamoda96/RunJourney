package com.hamoda.auth.domain

interface PatternValidator {
    fun matches(value: String): Boolean
}