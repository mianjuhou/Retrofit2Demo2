package cn.com.jtv.retrofit2demo2.retrofit2;

import cn.com.jtv.retrofit2demo2.Coordinate;
import cn.com.jtv.retrofit2demo2.ResultBean;
import cn.com.jtv.retrofit2demo2.retrofit2.convert.gson.MyGsonConverterFactory;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by fangdean on 2016/3/4.
 */
public class RetrofitUtil {
    private static Retrofit retrofit;
    private static GpsService service;
    static {
        ////http://172.16.140.151:8080/RetrofitServer/servlet/gsonservlet
        retrofit = new Retrofit.Builder()//
                .baseUrl("http://172.16.140.151:8080/RetrofitServer/")//
                .addConverterFactory(MyGsonConverterFactory.create())//
                .build();
        service =   retrofit.create(GpsService.class);
    }

    public static Retrofit getRetrofit(){
        return retrofit;
    }

    public static GpsService getService(){
        return service;
    }

    public static interface GpsService {
        @POST("servlet/gsonservlet")
        Call<ResultBean> uploadGps(@Body Coordinate coordinate);
    }
}
