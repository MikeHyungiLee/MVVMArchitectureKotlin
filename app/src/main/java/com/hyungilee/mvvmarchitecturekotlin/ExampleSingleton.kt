package com.hyungilee.mvvmarchitecturekotlin

import com.hyungilee.mvvmarchitecturekotlin.models.User

object ExampleSingleton {

    // 처음 singletonUser 속성이 호출이 되었을때, 초기화가 된다.(lazy)
    val singletonUser: User by lazy {
        User("mitchelltabian@gmail.com", "mitch", "image.png")
    }

}