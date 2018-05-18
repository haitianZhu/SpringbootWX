package com.haitian.demo.service;

import com.haitian.demo.model.netbean.GetTokenRequest;
import com.haitian.demo.model.netbean.GetTokenResponse;
import com.haitian.demo.net.RetrofitManager;
import com.haitian.demo.util.URLContract;

import org.springframework.stereotype.Service;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @Author: haitian
 * @Date: 2018/5/18 下午11:53
 * @Description: This is API for access wechat by retrofit.
 */
@Service("apiWXService")
public class ApiNetWX {

    public static GetTokenResponse getToken(String baseURL, GetTokenRequest getTokenRequest) {

        GetAccessTokenService getAccessTokenService = RetrofitManager.getRetrofit(baseURL).create
                (GetAccessTokenService.class);
        Call<GetTokenResponse> call = getAccessTokenService.getToken(getTokenRequest.getGrant_type(),
                getTokenRequest.getAppid(), getTokenRequest.getSecret());

        GetTokenResponse getTokenResponse = null;
        try {
            getTokenResponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return getTokenResponse;
    }

    public interface GetAccessTokenService{

        @GET(URLContract.GET_ACCESS_TOKEN)
        Call<GetTokenResponse> getToken(@Query("grant_type") String grant_type, @Query("appid")
                String appid, @Query("secret") String secret);
    }

}
