package id.ac.itn.latviewmodel;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BlogRepository {
    private ArrayList<Blog> blogList = new ArrayList<>();
    private MutableLiveData<List<Blog>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public BlogRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Blog>> getMutableLiveData() {

        RestApiService apiService = RetrofitInstance.getApiService();

        Call<BlogWrapper> call = apiService.getPopularBlog();

        call.enqueue(new Callback<BlogWrapper>() {
            @Override
            public void onResponse(Call<BlogWrapper> call, Response<BlogWrapper> response) {
                BlogWrapper mBlogWrapper = response.body();
                if (mBlogWrapper != null && mBlogWrapper.getBlog() != null) {
                    blogList = (ArrayList<Blog>) mBlogWrapper.getBlog();
                    mutableLiveData.setValue(blogList);
                    Log.d("load_data", "onResponse: Data ditemukan");
                }
            }

            @Override
            public void onFailure(Call<BlogWrapper> call, Throwable t) {
                    Log.d("load_data", "onFailure: Terjadi kesalahan " + t.getMessage());
            }
        });


        return mutableLiveData;
    }
}
