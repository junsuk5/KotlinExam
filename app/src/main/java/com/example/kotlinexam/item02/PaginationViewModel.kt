package com.example.kotlinexam.item02

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.kotlinexam.item02.models.Employee
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaginationViewModel : ViewModel(), AnkoLogger {
    private val service by lazy {
        newInstance()
    }

    val employees = MutableLiveData<List<Employee>>()

    private fun newInstance(): EmployeeService {
        val retrofit = Retrofit.Builder()
            .baseUrl(EmployeeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit.create(EmployeeService::class.java)
    }

    fun fetchEmployees(page: Int) {
        service.getEmployees(page).enqueue(object : Callback<List<Employee>> {
            override fun onFailure(call: Call<List<Employee>>, t: Throwable) {
                // 실패
                error { t.localizedMessage }
            }

            override fun onResponse(
                call: Call<List<Employee>>,
                response: Response<List<Employee>>
            ) {
                // 성공
                response.body()?.let { employees.value = it }
            }
        })
    }
}