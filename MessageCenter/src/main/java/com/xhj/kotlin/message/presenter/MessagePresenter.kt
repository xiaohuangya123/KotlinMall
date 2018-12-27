package com.xhj.kotlin.message.presenter

import com.xhj.kotlin.base.ext.execute
import com.xhj.kotlin.base.presenter.BasePresenter
import com.xhj.kotlin.base.rx.BaseObserver
import com.xhj.kotlin.message.data.protocol.Message
import com.xhj.kotlin.message.presenter.view.MessageView
import com.xhj.kotlin.message.service.MessageService
import javax.inject.Inject

/*
    消息列表 Presenter
 */
class MessagePresenter @Inject constructor() : BasePresenter<MessageView>() {

    @Inject
    lateinit var messageService: MessageService

    /*
        获取消息列表
     */
    fun getMessageList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        messageService.getMessageList().execute(object : BaseObserver<MutableList<Message>?>(mView) {
            override fun onNext(t: MutableList<Message>?) {
                mView.onGetMessageResult(t)
            }
        }, lifecycleProvider)

    }


}
