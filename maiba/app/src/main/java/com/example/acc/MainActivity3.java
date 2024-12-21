package com.example.colornoteclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity3 extends AppCompatActivity {
    private TextView named;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main3);
        
        named = findViewById(R.id.named);

        // Get the intent data
        String fullName = getIntent().getStringExtra("fullName");
        
        // Add null check and set text
        if (fullName != null && !fullName.isEmpty()) {
            named.setText(fullName);
        } else {
            named.setText("No name provided");
        }
        
        Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
        
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonGoToJavalysus = findViewById(R.id.button_go_to_javalysus);
        buttonGoToJavalysus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity3.this, JavalysusActivity.class);
                startActivity(intent);
            }
        });
    }
}