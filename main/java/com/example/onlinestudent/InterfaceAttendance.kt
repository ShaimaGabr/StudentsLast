package com.example.onlinestudent

import com.example.onlinestudent.profilegeneral.DiplomaAttend
import com.example.onlinestudent.profilegeneral.Modelname
import com.example.onlinestudent.profilegeneral.course.AttendanceModel
import com.example.onlinestudent.profilegeneral.course.ModelnameCourse
import com.example.onlinestudent.scanegeneral.responce
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface InterfaceAttendance {
    @FormUrlEncoded
    @POST("TGA/hr/onecourse.php")
   suspend fun MyAttend(@Field("courseid")courseid:String,@Field("app_id")app_id:String):Response<AttendanceModel>
//    @FormUrlEncoded
//    @POST("fetchdat.php")
//   suspend fun somAttend(@Field("id")name:String,@Field("start")start:String,@Field("end")end:String):Response<AttendanceModel>
//    @FormUrlEncoded
//    @POST("TGA/hr/coursename.php")
//    suspend fun coursename(@Field("courseid")courseid:String):Response<Modelname>
// @FormUrlEncoded
// @POST("TGA/hr/coursename2.php")
// suspend fun coursenamec(@Field("courseid")courseid:String):Response<Modelname2>
    @FormUrlEncoded
    @POST("TGA/hr/diploma.php")
    suspend fun diplomacon(@Field("courseid")courseid:String):Response<Modelname>
//    @FormUrlEncoded
//    @POST("TGA/hr/attendd_diploma.php")
//    suspend fun diplomaAtend(@Field("app_id")app_id:String):Response<DiplomaAttend>
    @FormUrlEncoded
    @POST("TGA/hr/diploma_attend.php")
    suspend fun diplomaAtendsom(@Field("app_id")app_id:String,@Field("diploma_id")diploma_id:String):Response<DiplomaAttend>
    @FormUrlEncoded
    @POST("TGA/hr/coursescan.php")
    suspend fun scanCourse(@Field("app_id")app_id:String,@Field("data")data:String,@Field("course_id")course_id:String,@Field("go")go:String,@Field("time")time:String):Response<responce>
 @FormUrlEncoded
 @POST("TGA/hr/diplomascan.php")
 suspend fun scandiploma(@Field("app_id")app_id:String,@Field("data")data:String,@Field("go")go:String,@Field("time")time:String):Response<responce>
    @FormUrlEncoded
    @POST("TGA/hr/multicourse.php")
    suspend fun ccnam(@Field("st_id")st_id:String):Response<ModelnameCourse>
}