# 🐶 mungWeather
> **반려견 보호자를 위한 맞춤형 날씨 & 산책 코디 추천 앱**

<p align="center">
  <img src="images/main.png" width="300" />
</p>

---

## 🌤️ 프로젝트 개요
**mungWeather**는 반려견 보호자를 위한 스마트 날씨 앱으로,  
사용자의 위치 기반 **현재 날씨**를 조회하고,  
**견종별 맞춤 산책 시간과 의상**을 추천해주는 서비스입니다.

---

## ✨ 주요 기능
| 기능 | 설명 |
|:---|:---|
| 🐾 **회원가입 / 로그인** | Firebase Authentication & Realtime Database를 이용한 사용자 관리 (이메일, 비밀번호, 이름, 견종 등) |
| ☀️ **날씨 정보 제공** | OpenWeatherMap API를 이용해 현재 위치 기반의 날씨 데이터를 표시 |
| 👕 **맞춤 산책/코디 추천** | 견종 + 온도 조건에 따라 최적의 산책 시간 & 의상 제안 |
| 🎨 **UI/UX 구성** | ViewPager 기반 추천 슬라이드, Drawer 메뉴, 도트 인디케이터 등 다양한 UI 요소 제공 |

---

## 🧩 주요 클래스 구성

| 클래스 | 역할 |
|:---|:---|
| `Main` | 메인 액티비티. 날씨 조회, 추천 표시, 프로필 메뉴 등 관리 |
| `Login` | Firebase 로그인 처리 및 사용자 인증 |
| `SignUp` | 회원가입 및 사용자 정보 DB 저장 |
| `ChangeDog` | 견종과 온도에 따른 추천 로직 구현 |
| `ViewPagerAdapter` | 추천 슬라이드(ViewPager) 어댑터 |

---

## 🛠️ 사용 기술 및 라이브러리

**언어:** Java (100%)  
**주요 라이브러리 및 API:**
- 🔹 AndroidX, ConstraintLayout, DrawerLayout, ViewPager  
- 🔹 Firebase Auth, Firebase Realtime Database  
- 🔹 OpenWeatherMap API (Retrofit2 + GsonConverterFactory)  
- 🔹 Google Play Location Services  

---

## 📱 실행 화면

<p align="center">
  <img src="assets/screenshots/screen1.gif" width="200" />
  <img src="assets/screenshots/screen2.gif" width="200" />
  <img src="assets/screenshots/screen3.gif" width="200" />
  <img src="assets/screenshots/screen4.gif" width="200" />
</p>
