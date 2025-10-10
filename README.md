# mungWeather

## 개요
**mungWeather**는 반려견 보호자를 위한 맞춤형 날씨 정보 및 산책/코디 추천 앱입니다. 사용자의 위치 정보를 바탕으로 OpenWeatherMap API에서 날씨 데이터를 가져오고, 반려견의 견종에 따라 추천 산책 시간과 의상 정보를 제공합니다. Firebase를 통한 회원가입/로그인과 사용자 정보 관리 기능을 지원합니다.

## 주요 기능
- **회원가입/로그인**: Firebase Authentication 및 Realtime Database 사용. 이메일, 비밀번호, 이름, 반려견 견종 등 사용자 정보 저장.
- **날씨 정보 제공**: OpenWeatherMap API를 이용해 현재 위치 기반의 날씨 정보를 받아옴.
- **맞춤 산책/코디 추천**: 견종과 온도에 따라 추천 산책 시간 및 의상 정보를 안내.
- **UI 및 사용자 경험**: ViewPager를 활용한 슬라이드 추천 화면, DrawerLayout의 프로필 메뉴, 도트 인디케이터 등 다양한 UI 제공.

## 주요 클래스 및 역할
- **Main**: 앱의 메인 액티비티. 날씨 정보 조회, 산책/코디 추천, 사용자 정보 표시, 뷰페이저 및 Drawer 메뉴 처리.
- **Login**: 로그인 화면. Firebase와 연동해서 사용자 인증 및 DB 정보 조회.
- **SignUp**: 회원가입 화면. 사용자 정보 입력 및 DB 저장 처리.
- **ChangeDog**: 견종 및 온도에 따라 추천 정보를 변경하는 로직.
- **ViewPagerAdapter**: 추천 슬라이드 화면을 위한 어댑터.

## 사용 기술 및 라이브러리
- **언어**: Java (100%)
- **주요 라이브러리**:
  - AndroidX, ConstraintLayout, DrawerLayout, ViewPager
  - Firebase Auth, Firebase Realtime Database
  - OpenWeatherMap API (Retrofit2 + GsonConverterFactory)
  - Google Play Location Services
