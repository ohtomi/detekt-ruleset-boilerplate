package com.github.ohtomi.detekt.ruleset

import io.gitlab.arturbosch.detekt.api.CodeSmell
import io.gitlab.arturbosch.detekt.api.Debt
import io.gitlab.arturbosch.detekt.api.Entity
import io.gitlab.arturbosch.detekt.api.Issue
import io.gitlab.arturbosch.detekt.api.Rule
import io.gitlab.arturbosch.detekt.api.Severity
import org.jetbrains.kotlin.com.intellij.psi.PsiWhiteSpace
import org.jetbrains.kotlin.lexer.KtTokens
import org.jetbrains.kotlin.psi.KtValueArgumentList
import org.jetbrains.kotlin.psi.psiUtil.allChildren

class ValueArgumentListLineBreakRule : Rule() {

    private val errorMessage = "should line break"

    override val issue: Issue = Issue(
        id = "value-argument-list-line-break",
        severity = Severity.Style,
        debt = Debt.FIVE_MINS,
        description = "should line break"
    )

    override fun visitValueArgumentList(list: KtValueArgumentList) {
        super.visitValueArgumentList(list)

        val isError = list.allChildren.any { child ->
            child.node.elementType == KtTokens.COMMA &&
                child.nextSibling is PsiWhiteSpace &&
                !child.nextSibling.textContains('\n')
        }
        if (isError) {
            report(CodeSmell(
                issue,
                Entity.from(list),
                errorMessage
            ))
        }
    }
}
