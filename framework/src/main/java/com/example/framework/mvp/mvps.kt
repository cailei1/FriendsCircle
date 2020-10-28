package com.example.framework.mvp

interface IPresenter<out V : IMvpView<IPresenter<V>>> : ILifecycle {
    val view: V
}


interface IMvpView<out P : IPresenter<IMvpView<P>>> : ILifecycle {
    val presenter: P
}