package com.example.mungweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    Context context;

    int screens[]= {R.drawable.blank, R.drawable.blank, R.drawable.blank};  // 배열로 이미지 리소스 ID 저장

    public ViewPagerAdapter(Context context) {
        this.context = context;
    }

    public void setTemperature(String userBreed, int temperature) {
        // 견종별 온도별 슬라이드 뷰 이미지 리소스 변경
        if (userBreed.contains("골든")) {
            if (temperature < -5)
                screens = new int[]{R.drawable.cloth_gold1, R.drawable.blank, R.drawable.walk_gold1};
            else if (temperature >= -5 && temperature < 6)
                screens = new int[]{R.drawable.cloth_gold2, R.drawable.blank, R.drawable.walk_gold2};
            else if (temperature >= 6 && temperature < 16)
                screens = new int[]{R.drawable.cloth_gold3, R.drawable.blank, R.drawable.walk_gold3};
            else if (temperature >= 16 && temperature <= 24)
                screens = new int[]{R.drawable.cloth_gold4, R.drawable.blank, R.drawable.walk_gold4};
            else
                screens = new int[]{R.drawable.cloth_gold5, R.drawable.blank, R.drawable.walk_gold5};
        }
        else if (userBreed.contains("시바")) {
            if (temperature < 1)
                screens = new int[]{R.drawable.cloth_siba1, R.drawable.blank, R.drawable.walk_siba1};
            else if (temperature >= 1 && temperature < 9)
                screens = new int[]{R.drawable.cloth_siba2, R.drawable.blank, R.drawable.walk_siba2};
            else if (temperature >= 9 && temperature < 16)
                screens = new int[]{R.drawable.cloth_siba3, R.drawable.blank, R.drawable.walk_siba3};
            else if (temperature >= 16 && temperature <= 20)
                screens = new int[]{R.drawable.cloth_siba4, R.drawable.blank, R.drawable.walk_siba4};
            else
                screens = new int[]{R.drawable.cloth_siba5, R.drawable.blank, R.drawable.walk_siba5};
        }
        else {
            if (temperature < 6)
                screens = new int[]{R.drawable.cloth_white1, R.drawable.blank, R.drawable.walk_white1};
            else if (temperature >= 6 && temperature < 11)
                screens = new int[]{R.drawable.cloth_white2, R.drawable.blank, R.drawable.walk_white2};
            else if (temperature >= 11 && temperature < 16)
                screens = new int[]{R.drawable.cloth_white3, R.drawable.blank, R.drawable.walk_white3};
            else if (temperature >= 16 && temperature <= 20)
                screens = new int[]{R.drawable.cloth_white4, R.drawable.blank, R.drawable.walk_white4};
            else
                screens = new int[]{R.drawable.cloth_white5, R.drawable.blank, R.drawable.walk_white5};
        }
    }

    @Override
    public int getCount() {
        // 페이지 개수 반환
        return screens.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 뷰와 객체 간의 관계를 확인하여 뷰가 현재 페이지를 나타내는지 여부 반환
        return view == (LinearLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        // 페이지를 생성하고 뷰를 반환
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout, container, false);

        ImageView slideImage = (ImageView) view.findViewById(R.id.screen);

        slideImage.setImageResource(screens[position]);

        container.addView(view);

        return view;


    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        // 페이지 파괴 및 뷰 제거
        container.removeView((LinearLayout)object);
    }
}
