package com.github.ohtomi.detekt.ruleset

import io.gitlab.arturbosch.detekt.test.lint
import kotlin.test.Test
import kotlin.test.assertEquals

class ValueArgumentListLineBreakRuleTest {

    @Test
    fun testHasOneProblem() {
        val rule = ValueArgumentListLineBreakRule()
        val code = """
            fun fn(a: String, b: String): String = a + b
            val a = "a"
            val b = "b"
            fun example() {
                fn(a, b)
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(1, findings.size)
    }

    @Test
    fun testHasNoProblem() {
        val rule = ValueArgumentListLineBreakRule()
        val code = """
            fun fn(a: String, b: String): String = a + b
            val a = "a"
            val b = "b"
            fun example() {
                fn(a,
                b)
            }
        """.trimIndent()

        val findings = rule.lint(code)
        assertEquals(0, findings.size)
    }
}
