package com.xhj.kotlin.message.injection.module

import com.xhj.kotlin.message.service.MessageService
import com.xhj.kotlin.message.service.impl.MessageServiceImpl
import dagger.Module
import dagger.Provides

/*
    消息模块业务注入
 */
@Module
class MessageModule {

    @Provides
    fun provideMessageService(messageService: MessageServiceImpl): MessageService {
        return  messageService
    }

}
