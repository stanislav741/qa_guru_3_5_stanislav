package sample_test;

import io.github.eroshenkoam.allure.AllureInterceptor;
import io.github.eroshenkoam.allure.GitHubClient;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import org.junit.jupiter.api.Test;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitTest {

    @Test
    public void testGitHubRepos() throws Exception {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new AllureInterceptor());

        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create())
                .baseUrl("https://api.github.com/")
                .client(httpClient.build())
                .build();

        final GitHubClient gitHub = retrofit.create(GitHubClient.class);

        gitHub.listRepos("eroshenkoam").execute().body().forEach(repo -> {
            System.out.println(repo.getName());
        });
    }

}
