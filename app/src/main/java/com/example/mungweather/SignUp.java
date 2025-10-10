package com.example.mungweather;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUp extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference reference;
    EditText editTextEmail, editTextPW, editTextPWChk, editTextName;
    TextView breedText;
    Button check, backBtn;
    ImageButton small, siba, big;
    FirebaseAuth firebaseAuth;
    String dogName;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        breedText = findViewById(R.id.breed);
        editTextEmail = findViewById(R.id.email);
        editTextPW = findViewById(R.id.pw);
        editTextPWChk = findViewById(R.id.pwChk);
        editTextName = findViewById(R.id.name);
        check = findViewById(R.id.check);
        small = findViewById(R.id.small);
        siba = findViewById(R.id.siba);
        big = findViewById(R.id.big);
        backBtn = findViewById(R.id.backSignup);
        firebaseAuth = FirebaseAuth.getInstance();

        small.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogName = "말티즈";
                breedText.setText(dogName);
            }
        });

        siba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogName = "시바 이누";
                breedText.setText(dogName);
            }
        });

        big.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dogName = "골든 리트리버";
                breedText.setText(dogName);
            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            // 뒤로가기 버튼 클릭 시 로그인 액티비티로 이동
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, Login.class);
                startActivity(intent);
            }
        });

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Firebase Database 및 참조 객체 초기화
                database = FirebaseDatabase.getInstance();
                reference = database.getReference("users");

                String email = editTextEmail.getText().toString();
                String password = editTextPW.getText().toString();
                String pwChk = editTextPWChk.getText().toString();
                String name = editTextName.getText().toString();
                String breed = breedText.getText().toString();
                String[] idAddress = email.split("@");
                String id = idAddress[0];

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(SignUp.this, "이메일을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(SignUp.this, "비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pwChk)) {
                    Toast.makeText(SignUp.this, "비밀번호 확인을 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!password.equals(pwChk)) {
                    Toast.makeText(SignUp.this, "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(breed)) {
                    Toast.makeText(SignUp.this, "견종을 선택하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Firebase 인증을 사용하여 회원가입
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()) {
                                    reference.child(id).child("아이디").setValue(id);
                                    reference.child(id).child("이름").setValue(name);
                                    reference.child(id).child("견종").setValue(breed);
                                    Toast.makeText(SignUp.this, "회원가입 완료", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUp.this, Login.class);
                                    startActivity(intent);
                                    finish();
                                }
                                else {
                                    Toast.makeText(SignUp.this, "회원가입에 실패했습니다.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
    }
}