package cn.com.jtv.retrofit2demo2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import cn.com.jtv.retrofit2demo2.retrofit2.RetrofitUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private Call<ResultBean> call;
    private RetrofitUtil.GpsService service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        service =   RetrofitUtil.getService();

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call = service.uploadGps(new Coordinate("11.23", "212.22"));
                call.enqueue(new Callback<ResultBean>() {
                    @Override
                    public void onResponse(Call<ResultBean> call, Response<ResultBean> response) {
                        try {
                            ResultBean body = response.body();
                            String result = body.toString();
                            System.out.println(result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultBean> call, Throwable t) {
                        t.printStackTrace();
                        System.out.println("onFailure");
                    }
                });

//                call.enqueue(new Callback<ResponseBody>() {
//                    @Override
//                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                        try {
//                            ResponseBody body = response.body();
//                            String result = body.string();
//                            System.out.println(result);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<ResponseBody> call, Throwable t) {
//                        t.printStackTrace();
//                        System.out.println("onFailure");
//                    }
//                });
            }
        });

    }
}
