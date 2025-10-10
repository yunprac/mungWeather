package com.example.mungweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;
import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Main extends AppCompatActivity {
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";   // 날씨 정보를 가져오기 위한 API의 기본 URL
    private static final String api_key = "e6bc8ec840d7f2de99571c46cadbd37b";   // API 키
    private final static int REQUEST_LOCATION = 101;    // 위치 권한 요청을 위한 상수
    FusedLocationProviderClient fusedLocationProviderClient;    // GooglePlay 서비스에서 제공하는 위치 정보 요청 및 관리
    public WeatherService weatherService;
    DrawerLayout drawerLayout;
    Animation animation;
    ChangeDog changeDog;
    ConstraintLayout bkSnow, bkRain, bkCloud, bkClear;
    ImageButton dogGold, dogSiba, dogWhite;
    Button cloth, time;
    ImageView menu, profile;
    LinearLayout logout, dotIndicator;
    TextView tvId, tvName, tvBreed, tvTem, locationView, tvTime, tvCloth, tvInfo, infoTilte;
    double latitude, longitude;
    ViewPagerAdapter viewPagerAdapter;
    ViewPager slideViewPager;
    TextView[] dots;
    int temperature;
    String userBreed;

    ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener() {
        // 슬라이드 화면을 위한 ViewPager 페이지 변경 리스너
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            // 페이지가 선택되었을 때
            setDotIndicator(position);
            changeDog.changeButton(userBreed, temperature);
            if (position != 1) {
                dogSiba.setVisibility(View.INVISIBLE);
                dogGold.setVisibility(View.INVISIBLE);
                dogWhite.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 위치 정보 및 날씨 API를 사용하기 위한 객체 초기화
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        weatherService = retrofit.create(WeatherService.class);

        dogGold = findViewById(R.id.dog_gold);
        dogWhite = findViewById(R.id.dog_white);
        dogSiba = findViewById(R.id.dog_siba);
        cloth = findViewById(R.id.cloth);
        time = findViewById(R.id.time);
        drawerLayout =findViewById(R.id.drawerLayout);
        menu = findViewById(R.id.menu);
        profile = findViewById(R.id.profile);
        logout = findViewById(R.id.logout);
        tvId = findViewById(R.id.profileId);
        tvName = findViewById(R.id.profileName);
        tvBreed = findViewById(R.id.profileBreed);
        tvTem = findViewById(R.id.tem);
        locationView = findViewById(R.id.locationView);
        bkSnow = findViewById(R.id.bk_snow);
        bkRain = findViewById(R.id.bk_rain);
        bkCloud = findViewById(R.id.bk_cloud);
        bkClear = findViewById(R.id.bk_clear);
        tvTime = findViewById(R.id.tv_time);
        tvCloth = findViewById(R.id.tv_cloth);
        slideViewPager = (ViewPager) findViewById(R.id.slideViewPager);
        dotIndicator = (LinearLayout) findViewById(R.id.dotIndicator);

        viewPagerAdapter = new ViewPagerAdapter(this);
        animation = new Animation(bkSnow, bkRain, bkCloud, bkClear);
        changeDog = new ChangeDog(dogGold, dogSiba, dogWhite, cloth, time, profile);

        showUserData();
        getMyLocation();

        cloth.setOnClickListener(new View.OnClickListener() {
            // 추천 코디 버튼 & 오른쪽으로 이동
            @Override
            public void onClick(View v) {
                if (getItem(0) > 0) {
                    slideViewPager.setCurrentItem(getItem(-1), true);
                }
            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            // 추천 산책시간 버튼 & 왼쪽으로 이동
            @Override
            public void onClick(View v) {
                slideViewPager.setCurrentItem(getItem(1), true);
            }
        });


        menu.setOnClickListener(new View.OnClickListener() {
            // 뼈다귀 버튼(사용자 프로필 창)
            @Override
            public void onClick(View v) {
                openDrawer(drawerLayout);

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            // 로그아웃 버튼 클릭 시 로그인 액티비티로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
                finish();
            }
        });

        dogSiba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v);
            }
        });
        dogGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v);
            }
        });
        dogWhite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomDialog(v);
            }
        });
    }

    public static void openDrawer(DrawerLayout drawerLayout) {
        drawerLayout.openDrawer(GravityCompat.START);
    }
    public static void closeDrawer(DrawerLayout drawerLayout) {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }

    public void onPause() {
        super.onPause();
        closeDrawer(drawerLayout);
    }

    public void showBottomDialog(View v) {
        // 강아지 버튼 클릭했을 때 아래 화면(견종별 주의사항) 표시
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.activity_info);
        tvInfo = dialog.findViewById(R.id.info);
        infoTilte = dialog.findViewById(R.id.infoTitle);
        infoTilte.setText(userBreed);

        if(v.getId() == R.id.dog_gold)
            tvInfo.setText(R.string.gold);
        else if(v.getId() == R.id.dog_siba)
            tvInfo.setText(R.string.siba);
        else
            tvInfo.setText(R.string.white);

        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void showUserData() {
        Intent intent = getIntent();
        String userId = intent.getStringExtra("아이디");
        String userName = intent.getStringExtra("이름");
        userBreed = intent.getStringExtra("견종");

        tvId.setText("아이디 : " + userId);
        tvName.setText("이름 : " + userName);
        tvBreed.setText("견종 : " + userBreed);
    }

    private void getWeatherData(double latitude, double longitude) {
        // 위도와 경도 값을 받아와서 해당 지역 날씨 얻어옴
        Call<Weather> call = weatherService.getWeather(latitude, longitude, api_key);
        call.enqueue(new Callback<Weather>() {
            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {
                if (response.isSuccessful()) {
                    float temperatureK = response.body().main.temp;
                    float temperatureC = temperatureK - 273.15f;
                    temperature = Math.round(temperatureC);
                    tvTem.setText(temperature + "°");
                    changeDog.changeButton(userBreed, temperature);
                    viewPagerAdapter.setTemperature(userBreed, temperature);
                    slideViewPager.setAdapter(viewPagerAdapter);
                    slideViewPager.setCurrentItem(1);
                    setDotIndicator(1);
                    changeDog.changeInfo(userBreed);
                    slideViewPager.addOnPageChangeListener(viewListener);
                    if (response.body() != null && response.body().weather.size() > 0) {
                        String weatherStatus = response.body().weather.get(0).description;
                        animation.updateBackground(weatherStatus);
                    }
                }
                else {
                    Toast.makeText(Main.this, "API 호출 실패", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                Log.e("Weather", "API 호출 실패", t);
                Toast.makeText(Main.this, "API 호출 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void getMyLocation() {
        // 사용자의 현재 위치를 위도와 경도로 불러옴
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {
                    if (location != null) {
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        try {
                            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            latitude = addresses.get(0).getLatitude();
                            longitude = addresses.get(0).getLongitude();
                            getWeatherData(latitude, longitude);
                            locationView.setText(addresses.get(0).getLocality());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {

                    }
                }
            });
        } else {
            askPermission();
        }
    }

    private void askPermission() {
        // 사용자 위치 권한 요청
        ActivityCompat.requestPermissions(this, new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_LOCATION);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        // 위치 권한 요청 결과 처리
        if (requestCode == REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getMyLocation();
            } else {
                Toast.makeText(this, "위치정보 권한 요청을 허용해주세요.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Main.this, Login.class);
                startActivity(intent);
                finish();
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    public void setDotIndicator(int position) {
        // 도트 인디케이터 처리
        dots = new TextView[3];
        dotIndicator.removeAllViews();

        for (int i=0; i<dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setTextSize(35);
            dots[i].setText(Html.fromHtml("&#8226;"));  // 도트 표시
            dots[i].setTextColor(getResources().getColor(R.color.gray, getApplicationContext().getTheme()));    // 그 외 페이지의 도트 색상
            dotIndicator.addView(dots[i]);
        }
        dots[position].setTextColor(getResources().getColor(R.color.white, getApplicationContext().getTheme()));    // 해당 페이지의 도트 색상
    }
    private int getItem ( int i) {
        return slideViewPager.getCurrentItem() + i;
    }

    private void restartApplication(Context context) {
        try {
            PackageManager packageManager = context.getPackageManager();
            Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);
            finishAffinity();
        } catch (Exception e) {
        }
    }
}