package com.example.mungweather;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ChangeDog {
    private ImageButton dogGold, dogWhite, dogSiba;
    private ImageView profile;
    private Button cloth, time;

    public ChangeDog(ImageButton dogGold, ImageButton dogSiba, ImageButton dogWhite, Button cloth, Button time, ImageView profile) {
        this.dogGold = dogGold;
        this.dogSiba = dogSiba;
        this.dogWhite = dogWhite;
        this.cloth = cloth;
        this.time = time;
        this.profile = profile;
    }

    public void changeButton(String userBreed, int temperature) {
        // 견종별 온도별 강아지 버튼(메뉴 버튼)의 이미지 리소스, 추천코디와 추천산책시간 버튼 텍스트 변경
        if (userBreed.contains("골든")) {
            setVisibility(View.VISIBLE, dogGold);
            setVisibility(View.GONE, dogWhite, dogSiba);
            if (temperature <= -6) {
                dogGold.setImageResource(R.drawable.dog_gold1);
                cloth.setText("패딩");
                time.setText("30분");
            }
            else if (temperature >= -5 && temperature <= 5) {
                dogGold.setImageResource(R.drawable.dog_gold2);
                cloth.setText("후드티");
                time.setText("1시간");
            }
            else if (temperature >= 6 && temperature <= 15) {
                dogGold.setImageResource(R.drawable.dog_gold0);
                cloth.setText("없음");
                time.setText("2시간");
            }
            else if (temperature >= 16 && temperature <= 24) {
                dogGold.setImageResource(R.drawable.dog_gold0);
                cloth.setText("없음");
                time.setText("90분");
            }
            else {
                dogGold.setImageResource(R.drawable.dog_gold0);
                cloth.setText("없음");
                time.setText("30분");
            }
        }
        else if (userBreed.contains("시바")) {
            setVisibility(View.VISIBLE, dogSiba);
            setVisibility(View.GONE, dogGold, dogWhite);
            if (temperature <= 0) {
                dogSiba.setImageResource(R.drawable.dog_siba1);
                cloth.setText("패딩");
                time.setText("20분");
            }
            else if (temperature >= 1 && temperature <= 8) {
                dogSiba.setImageResource(R.drawable.dog_siba2);
                cloth.setText("바람막이");
                time.setText("40분");
            }
            else if (temperature >= 9 && temperature <= 15) {
                dogSiba.setImageResource(R.drawable.dog_siba3);
                cloth.setText("후드티");
                time.setText("1시간");
            }
            else if (temperature >= 16 && temperature <= 20) {
                dogSiba.setImageResource(R.drawable.dog_siba4);
                cloth.setText("티셔츠");
                time.setText("1시간");
            }
            else {
                dogSiba.setImageResource(R.drawable.dog_siba0);
                cloth.setText("없음");
                time.setText("30분");
            }
        }
        else {
            setVisibility(View.VISIBLE, dogWhite);
            setVisibility(View.GONE, dogGold, dogSiba);
            if (temperature <= 5) {
                dogWhite.setImageResource(R.drawable.dog_white1);
                cloth.setText("패딩");
                time.setText("20분");
            }
            else if (temperature >= 6 && temperature <= 10) {
                dogWhite.setImageResource(R.drawable.dog_white2);
                cloth.setText("양털옷");
                time.setText("30분");
            }
            else if (temperature >= 11 && temperature <= 15) {
                dogWhite.setImageResource(R.drawable.dog_white3);
                cloth.setText("가디건");
                time.setText("1시간");
            }
            else if (temperature >= 16 && temperature <= 20) {
                dogWhite.setImageResource(R.drawable.dog_white4);
                cloth.setText("티셔츠");
                time.setText("1시간");
            }
            else {
                dogWhite.setImageResource(R.drawable.dog_white5);
                cloth.setText("끈나시");
                time.setText("20분");
            }
        }

    }

    public void changeInfo(String userBreed) {
        // 견종별 사용자 정보 창의 프로필 이미지 리소스 변경
        if (userBreed.contains("골든"))
            profile.setImageResource(R.drawable.profile_gold);
        else if (userBreed.contains("시바"))
            profile.setImageResource(R.drawable.profile_siba);
        else
            profile.setImageResource(R.drawable.profile_white);
    }

    private void setVisibility(int visibility, ImageButton ... buttons) {
        for (ImageButton button : buttons) {
            button.setVisibility(visibility);
        }
    }
}
