package com.example.framework.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.framework.mvp.IMvpView
import com.example.framework.mvp.IPresenter
import kotlin.reflect.KClass
import kotlin.reflect.full.isSubclassOf
import kotlin.reflect.full.primaryConstructor
import kotlin.reflect.jvm.jvmErasure

abstract class BaseActivity<out P : BasePresenter<BaseActivity<P>>> : AppCompatActivity(), IMvpView<P> {

    final override val presenter: P
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        initData()
    }

    abstract fun initData()

    abstract fun getLayoutId(): Int

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    init {
        presenter = createPresenter()
        presenter.view = this
    }

    private fun createPresenter(): P {
        sequence {
            var thisClass: KClass<*> = this@BaseActivity::class
            while (true) {
                yield(thisClass.supertypes)
                thisClass = thisClass.supertypes?.firstOrNull()?.jvmErasure ?: break
            }
        }.flatMap {
            it.flatMap { it.arguments }.asSequence()
        }.first {
            it.type?.jvmErasure?.isSubclassOf(IPresenter::class) ?: false
        }.let {
            return it.type?.jvmErasure!!.primaryConstructor!!.call() as P
        }
    }
}