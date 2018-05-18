package com.haitian.demo.net;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Author: haitian
 * @Date: 2018/5/18 下午10:34
 * @Description:
 */
public class RetrofitManager {

    public static Retrofit getRetrofit(String baseUrl) {

        SSLContext sc = null;
        try {
            sc = SSLContext.getInstance("TLS");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        X509TrustManager x509TrustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };

        try {
            sc.init(null, new TrustManager[]{x509TrustManager}, new SecureRandom());
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }

        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(5, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .sslSocketFactory(sc.getSocketFactory(), x509TrustManager)
                .hostnameVerifier(new HostnameVerifier() {

                    @Override
                    public boolean verify(String hostname, SSLSession session) {
                        return true;
                    }
                }).build();

        return new Retrofit.Builder().baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create()).
                client(okHttpClient).build();

    }
}
