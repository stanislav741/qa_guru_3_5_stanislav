package io.github.eroshenkoam.allure;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

import static io.qameta.allure.Allure.step;

public class AllureInterceptor implements Interceptor {
    @Override
    public Response intercept(final Chain chain) throws IOException {
        final Request request = chain.request();
        step("Open url " + request.url().toString());
        return chain.proceed(request);
    }
}
