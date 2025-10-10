package com.example.mungweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPW;
    Button login, signUp;
    FirebaseAuth firebaseAuth;
    String idFromDB, nameFromDB, breedFromDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextEmail = findViewById(R.id.email);
        editTextPW = findViewById(R.id.pw);
        login = findViewById(R.id.login);
        signUp = findViewById(R.id.signUp);
        firebaseAuth = FirebaseAuth.getInstance();  // FirebaseAuth 인스턴스 초기화
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users"); // FirebaseDatabase 인스턴스 초기화

        signUp.setOnClickListener(new View.OnClickListener() {
            //  회원가입 버튼 클릭 시 회원가입 액티비티로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, SignUp.class);
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            // 로그인 버튼 클릭 시 정보 확인 후 메인 액티비티로 이동
            @Override
            public void onClick(View v) {
                String email, password;
                email = String.valueOf(editTextEmail.getText());
                password = String.valueOf(editTextPW.getText());

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(Login.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Login.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                reference.addValueEventListener(new ValueEventListener() {
                    // Firebase 데이터베이스에서 사용자 정보를 가져오는 ValueEventListener 등록
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.exists()) {
                            String[] idAddress = email.split("@");
                            String id = idAddress[0];   // 이메일에서 @ 앞부분만 가져와서 id 변수에 저장
                            idFromDB = snapshot.child(id).child("아이디").getValue(String.class);
                            nameFromDB = snapshot.child(id).child("이름").getValue(String.class);
                            breedFromDB = snapshot.child(id).child("견종").getValue(String.class);
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError error) {
                        Toast.makeText(Login.this, "데이터베이스 연동 실패", Toast.LENGTH_SHORT).show();
                    }
                });

                firebaseAuth.signInWithEmailAndPassword(email, password)    // Firebase 인증을 사용하여 이메일과 비밀번호로 로그인
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    Toast.makeText(Login.this, "로그인 완료", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Login.this, Main.class);

                                    intent.putExtra("아이디", idFromDB);
                                    intent.putExtra("이름", nameFromDB);
                                    intent.putExtra("견종", breedFromDB);

                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(Login.this, "로그인에 실패했습니다. 다시 시도해주세요", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}