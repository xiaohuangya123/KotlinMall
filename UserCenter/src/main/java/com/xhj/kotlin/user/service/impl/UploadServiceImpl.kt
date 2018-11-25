package com.xhj.kotlin.user.service.impl

import com.xhj.kotlin.base.ext.convert
import com.xhj.kotlin.user.data.repository.UploadRepository
import com.xhj.kotlin.user.service.UploadService
import io.reactivex.Observable
import javax.inject.Inject

class UploadServiceImpl @Inject constructor() :UploadService{
    @Inject
    lateinit var repository : UploadRepository

    override fun getUploadToken(): Observable<String> {
        return repository.getUploadToken().convert()
    }


}