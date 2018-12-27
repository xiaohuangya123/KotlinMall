package com.xhj.kotlin.message.service.impl


import com.xhj.kotlin.base.ext.convert
import javax.inject.Inject

import com.xhj.kotlin.message.data.protocol.Message
import com.xhj.kotlin.message.data.repository.MessageRepository
import com.xhj.kotlin.message.service.MessageService
import io.reactivex.Observable

/*
   消息业务层
 */
class MessageServiceImpl @Inject constructor(): MessageService {

    @Inject
    lateinit var repository: MessageRepository

    /*
        获取消息列表
     */
    override fun getMessageList(): Observable<MutableList<Message>?> {
        return repository.getMessageList().convert()
    }

}
