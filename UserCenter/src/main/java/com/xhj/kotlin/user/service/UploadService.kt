package com.xhj.kotlin.user.service

import io.reactivex.Observable

interface UploadService {
    fun getUploadToken(): Observable<String>
}