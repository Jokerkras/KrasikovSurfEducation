package com.example.krasikovsurfeducation.repo

import com.example.krasikovsurfeducation.model.MemDto
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import javax.inject.Inject

class MemRepository @Inject constructor(val retrofit: Retrofit, val userStorage: UserStorage) {

    private val memApi = retrofit.create(MemApi::class.java)


    fun mem(onSuccess: (List<MemDto>) -> Unit,
            onError: (Throwable) -> Unit) {
        memApi.memes(userStorage.getAccessToken())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe({
                onSuccess(it)
            }, {
                onError(it)
            })

    }
}