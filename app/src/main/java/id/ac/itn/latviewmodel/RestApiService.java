package id.ac.itn.latviewmodel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("feed.json")
    Call<BlogWrapper> getPopularBlog();
}
