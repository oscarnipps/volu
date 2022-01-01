package com.app.volu

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

/*
* custom rule that uses the trampoline scheduler for immediate execution of test cases
*/
class RxJavaSchedulerRule : TestRule {

    override fun apply(base: Statement, description: Description?): Statement {
        return RxJavaSchedulerRuleStatement(base)
    }

    class RxJavaSchedulerRuleStatement(private val base: Statement) : Statement() {
        override fun evaluate() {
            RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

            RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

            RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }

            RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }

            try {
                base.evaluate()
            } finally {
                RxJavaPlugins.reset()
                RxAndroidPlugins.reset()
            }
        }
    }
}