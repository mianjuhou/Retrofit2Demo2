package cn.com.jtv.retrofit2demo2.retrofit2.convert.fastjson;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Created by fangdean on 2016/1/16.
 */
public class FastJsonConverterFactory<T> extends Converter.Factory {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private FastJsonConverterFactory() {
        super();
    }

    public static FastJsonConverterFactory create(){
        return new FastJsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new FastJsonResponseBodyConverter(type);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type,Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        return new FastJsonRequestBodyConverter();
    }

    class FastJsonRequestBodyConverter implements Converter<T, RequestBody>{
        @Override
        public RequestBody convert(T value) throws IOException {
            String requestString=JSON.toJSONString(value);
            return RequestBody.create(MEDIA_TYPE, requestString);
        }
    }

    class FastJsonResponseBodyConverter implements Converter<ResponseBody,T>{
        private Type mType;
        public FastJsonResponseBodyConverter(Type type) {
            mType=type;
        }
        @Override
        public T convert(ResponseBody value) throws IOException {
        	try{
                String stringResult=value.string();
                if(TextUtils.isEmpty(stringResult)){
                    throw new RuntimeException("请求结果为空");
                }
                T bean= JSON.parseObject(stringResult,mType);
                return bean;
            }catch (JSONException e){
                throw new RuntimeException("JSON解析发生异常",e);
            }
        }
    }
}
