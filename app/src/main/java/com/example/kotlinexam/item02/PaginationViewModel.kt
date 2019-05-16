package com.example.kotlinexam.item02

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinexam.item02.models.Employee
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.AnkoLogger
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PaginationViewModel : ViewModel(), AnkoLogger {
    private val service by lazy {
        newInstance()
    }

    var currentPage = 1

    val employees = MutableLiveData<List<Employee>>()

    private fun newInstance(): EmployeeService {
        val retrofit = Retrofit.Builder()
            .baseUrl(EmployeeService.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build()
        return retrofit.create(EmployeeService::class.java)
    }

    fun fetchEmployees(page: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                // Background 쓰레드
                val body = service.getEmployees(page).await()

                withContext(Dispatchers.Main) {
                    // Main 쓰레드
                    if (employees.value == null) {
                        employees.value = body
                    } else {
                        val newList = arrayListOf<Employee>()
                        newList.addAll(employees.value!!)
                        newList.addAll(body)
                        employees.value = newList
                    }
                    currentPage = page
                }
            } catch (err: Exception) {
                // 에러
            }
        }
    }
}