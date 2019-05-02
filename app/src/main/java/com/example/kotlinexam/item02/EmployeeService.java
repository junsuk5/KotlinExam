package com.example.kotlinexam.item02;

import com.example.kotlinexam.item02.models.Employee;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface EmployeeService {
    String BASE_URL = "http://10.0.2.2:3000/";

    @GET("employees")
    Call<List<Employee>> getEmployees(@Query("page") int page);
}
