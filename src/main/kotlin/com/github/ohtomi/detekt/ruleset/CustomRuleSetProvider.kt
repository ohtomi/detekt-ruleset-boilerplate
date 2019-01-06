package com.github.ohtomi.detekt.ruleset

import io.gitlab.arturbosch.detekt.api.Config
import io.gitlab.arturbosch.detekt.api.RuleSet
import io.gitlab.arturbosch.detekt.api.RuleSetProvider

class CustomRuleSetProvider : RuleSetProvider {

    override val ruleSetId: String = "custom"

    override fun instance(config: Config): RuleSet = RuleSet(
        ruleSetId,
        listOf(ValueArgumentListLineBreakRule())
    )
}
