package com.hyungilee.mvvmarchitecturekotlin.repository

import androidx.lifecycle.LiveData
import com.hyungilee.mvvmarchitecturekotlin.api.MyRetrofitBuilder
import com.hyungilee.mvvmarchitecturekotlin.models.User
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main

object Repository {

    var job: CompletableJob? = null

    fun getUser(userId: String): LiveData<User>{
        // Initialize the job
        job = Job()
        return object: LiveData<User>(){
            override fun onActive() {
                super.onActive()
                //check job
                //(var)?(check the variable whether it is null or not)
                job?.let {theJob ->
                    // job is not null, run the code below
                    // IO(dispatcher)
                    // create unique coroutineScope on the background of thread.
                    CoroutineScope(IO + theJob).launch {
                        val user = MyRetrofitBuilder.apiService.getUser(userId)
                        // set the live data value on the background thread
                        // live data를 background thread에서 실행해줄 수 없으므로,
                        // 우선 thread를 Main thraed로 전환한 뒤에 live data를 setting해줘야 한다.
                        // switch to the Main thread
                        withContext(Main){
                            value = user
                            theJob.complete()
                        }
                    }
                }
            }
        }
    }

    fun cancelJobs(){
        job?.cancel()
    }

}