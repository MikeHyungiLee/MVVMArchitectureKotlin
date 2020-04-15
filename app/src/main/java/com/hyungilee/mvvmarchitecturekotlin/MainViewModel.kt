package com.hyungilee.mvvmarchitecturekotlin

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.hyungilee.mvvmarchitecturekotlin.models.User
import com.hyungilee.mvvmarchitecturekotlin.repository.Repository

class MainViewModel : ViewModel() {

    // google sample code에서 변수 앞에 _를 붙여준다.
    private val _userId: MutableLiveData<String> = MutableLiveData()

    // Transformations - RxJava switch map / SwitchMap lecture
    // _userId에 변화가 생기면 .switchMap 이 trigger 된다. {}안에 실행
    val user: LiveData<User> = Transformations
        .switchMap(_userId){
            Repository.getUser(it)
        }

    fun setUserId(userId: String){
        val update = userId
        // 현재의 userId가 세팅되었다면 종료
        if(_userId.value == update){
            return
        }
        _userId.value = update
    }

    fun cancelJobs(){
        Repository.cancelJobs()
    }
}