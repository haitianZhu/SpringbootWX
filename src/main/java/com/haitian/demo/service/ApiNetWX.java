package com.haitian.demo.service;

import com.haitian.demo.model.message.MessageType;
import com.haitian.demo.model.netbean.GetTokenRequest;
import com.haitian.demo.model.netbean.GetTokenResponse;
import com.haitian.demo.model.netbean.UploadFileResponse;
import com.haitian.demo.net.RetrofitManager;
import com.haitian.demo.util.wechat.WeChatUtil;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.Url;

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

    public static UploadFileResponse uploadFile(String baseURL, File file, String mediaType, String
                                                token) {

        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part  和后端约定好Key，这里的partName是用image
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", file.getName(),
                requestFile);

        // 添加描述
        String descriptionString = "hello, 这是文件描述";
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"),
                descriptionString);

        UploadFileService uploadFileService = RetrofitManager.getRetrofit(baseURL).create
                (UploadFileService.class);

        Call<UploadFileResponse> call = uploadFileService.uploadFile(token, mediaType,
                description, body);

        UploadFileResponse uploadFileResponse = null;
        try {
            uploadFileResponse = call.execute().body();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uploadFileResponse;

    }

    public interface GetAccessTokenService {

        @GET(WeChatUtil.GET_ACCESS_TOKEN)
        Call<GetTokenResponse> getToken(@Query("grant_type") String grant_type, @Query("appid")
                String appid, @Query("secret") String secret);
    }

    public interface UploadFileService {

        @Multipart
        @POST(WeChatUtil.UPLOAD_MEDIA)
        Call<UploadFileResponse> uploadFile(@Query("ACCESS_TOKEN") String token, @Query("TYPE")
                String type, @Part("description") RequestBody description, @Part MultipartBody
                .Part file);
    }

}
