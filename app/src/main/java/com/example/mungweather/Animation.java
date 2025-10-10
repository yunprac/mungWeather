package com.example.mungweather;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

public class Animation {

    private ConstraintLayout bkSnow, bkRain, bkCloud, bkClear;

    public Animation(ConstraintLayout bkSnow, ConstraintLayout bkRain, ConstraintLayout bkCloud, ConstraintLayout bkClear) {
        this.bkSnow = bkSnow;
        this.bkRain = bkRain;
        this.bkCloud = bkCloud;
        this.bkClear = bkClear;
    }

    public void updateBackground(String weatherStatus) {
        // 날씨 상태에 따라 배경 & 애니메이션 업데이트
        if (weatherStatus.contains("clouds") || weatherStatus.contains("mist")) {
            setVisibility(View.VISIBLE, bkCloud);
            setVisibility(View.GONE, bkClear, bkRain, bkSnow);
        } else if (weatherStatus.contains("rain") || weatherStatus.contains("drizzle")) {
            setVisibility(View.VISIBLE, bkRain);
            setVisibility(View.GONE, bkSnow, bkCloud, bkClear);
        } else if (weatherStatus.equals("clear sky")) {
            setVisibility(View.VISIBLE, bkClear);
            setVisibility(View.GONE, bkSnow, bkRain, bkCloud);
        } else if (weatherStatus.contains("snow") || weatherStatus.contains("sleet")) {
            setVisibility(View.VISIBLE, bkSnow);
            setVisibility(View.GONE, bkClear, bkRain, bkCloud);
        }
    }

    private void setVisibility(int visibility, ConstraintLayout... layouts) {
        for (ConstraintLayout layout : layouts) {
            layout.setVisibility(visibility);
        }
    }
}
