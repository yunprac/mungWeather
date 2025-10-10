package com.example.mungweather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
// Retrofit을 사용하여 날씨 정보를 가져오기 위한 서비스 인터페이스
public interface WeatherService {
        /*
        GET 요청을 사용하여 날씨 정보를 가져오는 메소드
        @Query : 쿼리 매개변수 설정
        lat, lon, appid는 각각 위도, 경도, API 키를 저장함
         */

        @GET("weather")
        Call<Weather> getWeather(@Query("lat") double lat, @Query("lon") double lon, @Query("appid") String apiKey);
}

