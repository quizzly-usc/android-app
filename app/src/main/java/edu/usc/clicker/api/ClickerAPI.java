package edu.usc.clicker.api;

import com.squareup.okhttp.ResponseBody;

import java.util.ArrayList;
import java.util.List;

import edu.usc.clicker.model.AnswerOptions;
import edu.usc.clicker.model.AnswerResponse;
import edu.usc.clicker.model.Course;
import edu.usc.clicker.model.CourseInfo;
import edu.usc.clicker.model.EnrollBody;
import edu.usc.clicker.model.LoginBody;
import edu.usc.clicker.model.QuizStatistics;
import edu.usc.clicker.model.RegisterBody;
import edu.usc.clicker.model.Section;
import edu.usc.clicker.model.QuizQuestion;
import edu.usc.clicker.model.StudentResponse;
import edu.usc.clicker.model.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

public interface ClickerAPI {
    @POST("/auth/signup/")
    Call<ResponseBody> register(@Body RegisterBody registerBody);

    @POST("/auth/login/")
    Call<ResponseBody> login(@Body LoginBody loginBody);

    @GET("/user/")
    Call<User> getUser(@Query("id") int userID);

    @GET("/student/getstudentcoursesbyemail/")
    Call<List<Section>> getUserSections(@Query("user") String email);

    @POST("/user/enroll/")
    Call<Section> enroll(@Body EnrollBody enrollBody);

    @POST("/user/unenroll/")
    Call<Section> unenroll(@Body EnrollBody enrollBody);

    @GET("/class/")
    Call<Course> getCourse(@Query("id") int courseID);

    @GET("/class/")
    Call<List<Course>> getCourses();

    @GET("/section/")
    Call<Section> getSection(@Query("id") int sectionID);

    @GET("/section/")
    Call<List<Section>> getSections();

    @POST("/question/answer/")
    Call<ResponseBody> answer(@Body AnswerResponse answer);

    @GET("/course/getCourseQuizzes")
    Call<List<QuizStatistics>> getStats(@Query("id")int id);

    @GET("/quiz/getQuizQuestions")
    Call<List<QuizQuestion>> getQuestions(@Query("id") int id);

    @GET("/question/getQuestionAndUserAnswer")
    Call<StudentResponse> getStudentResponse(@Query("question") int id, @Query("student") String email);

    @GET("/question/getQuestionAnswers")
    Call <List<AnswerOptions> > getAnswers (@Query("question") int id);
}
