package com.example.mungweather;

import java.util.List;

public class Weather {
    public Main main;   // 주 날씨 정보를 나타내는 내부 클래스
    public List<WeatherCondition> weather;  // 다양한 날씨 조건을 나타내는 WeatherCondition 객체의 리스트

    public class Main {
        public float temp;  // 온도
    }

    public static  class WeatherCondition {
        public String description;  // 날씨 상태에 대한 설명
    }
}


