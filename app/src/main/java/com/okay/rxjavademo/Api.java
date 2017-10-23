package com.okay.rxjavademo;

import com.okay.rxjavademo.model.LoginRequest;
import com.okay.rxjavademo.model.LoginResponse;
import com.okay.rxjavademo.model.RegisterRequest;
import com.okay.rxjavademo.model.RegisterRespnse;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;

/**
 * Created by xmq on 2017/8/21.
 */

public interface Api {
    @GET
    Observable<LoginResponse> login(@Body LoginRequest request);

    @GET
    Observable<RegisterRespnse> register(@Body RegisterRequest request);
}