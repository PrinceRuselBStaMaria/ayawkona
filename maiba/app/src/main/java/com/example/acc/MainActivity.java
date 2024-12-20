package com.example.acc;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Context c = this;
    LinearLayout main;
    Button btn1, btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initialize();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void initialize() {
        main = findViewById(R.id.main);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setGravity(Gravity.START);

        LinearLayout.LayoutParams fullWidthParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout.LayoutParams spacingParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                70
        );

        LinearLayout.LayoutParams wrap = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        LinearLayout.LayoutParams login = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        login.gravity = Gravity.CENTER;

        // Title TextView
        TextView titleTextView = new TextView(c);
        titleTextView.setText("SIMPLE LOGIN APP");
        titleTextView.setTextSize(30);
        titleTextView.setTextColor(Color.parseColor("#000000"));
        titleTextView.setGravity(Gravity.CENTER);
        titleTextView.setPadding(0, 50, 0, 0);
        titleTextView.setLayoutParams(fullWidthParams);
        main.addView(titleTextView);

        // Add space below title
        main.addView(new View(c), spacingParams);

        // Username Label and EditText
        TextView usernameLabel = new TextView(this);
        usernameLabel.setText("USERNAME");
        usernameLabel.setTextSize(20);
        usernameLabel.setPadding(60, 0, 0, 60);
        usernameLabel.setLayoutParams(wrap);
        main.addView(usernameLabel);

        EditText et1 = new EditText(c);
        et1.setHint("Enter your username");
        et1.setTextSize(18);
        et1.setPadding(60, 0, 50, 50);
        et1.setInputType(InputType.TYPE_CLASS_TEXT);
        et1.setBackground(null); // Remove default underline
        main.addView(et1);

        View underline1 = new View(c);
        underline1.setLayoutParams(new LinearLayout.LayoutParams(600, 2));
        underline1.setBackgroundColor(Color.BLACK);
        ((LinearLayout.LayoutParams) underline1.getLayoutParams()).setMargins(50, 0, 50, 20);
        main.addView(underline1);

        // Password Label and EditText
        TextView passwordLabel = new TextView(this);
        passwordLabel.setText("PASSWORD");
        passwordLabel.setTextSize(20);
        passwordLabel.setPadding(60, 0, 0, 60);
        passwordLabel.setLayoutParams(fullWidthParams);
        main.addView(passwordLabel);

        EditText et2 = new EditText(c);
        et2.setHint("Enter your password");
        et2.setTextSize(18);
        et2.setPadding(50, 0, 50, 50);
        et2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        et2.setBackground(null); // Remove default underline
        main.addView(et2);

        // Custom underline for password
        View underline2 = new View(c);
        underline2.setLayoutParams(new LinearLayout.LayoutParams(600, 2));
        underline2.setBackgroundColor(Color.BLACK);
        ((LinearLayout.LayoutParams) underline2.getLayoutParams()).setMargins(50, 0, 50, 20);
        main.addView(underline2);

        // Add space before the login button
        main.addView(new View(c), spacingParams);

        // Login Button
        btn1 = new Button(c);
        btn1.setText("LOGIN");
        btn1.setTextSize(30);
        btn1.setPadding(225, 0, 225, 16);
        btn1.setTextColor(Color.parseColor("#FFFFFF"));
        btn1.setBackgroundColor(Color.parseColor("#000000"));
        btn1.setLayoutParams(login);
        main.addView(btn1);

        // Register TextView
        TextView registerLabel = new TextView(this);
        registerLabel.setText("Not yet Registered? Click Here");
        registerLabel.setTextSize(18);
        registerLabel.setPadding(16, 50, 16, 16);
        registerLabel.setGravity(Gravity.CENTER);
        registerLabel.setLayoutParams(fullWidthParams);
        main.addView(registerLabel);

        // Set OnClickListener for LOGIN Button
        btn1.setOnClickListener(v -> {
            String username = et1.getText().toString();
            String password = et2.getText().toString();

            if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(c, "Please Input Username and Password", Toast.LENGTH_SHORT).show();
            } else if ((username.equals("Jeff") && password.equals("12345")) ||
                    (username.equals("Joan") && password.equals("567890")) ||
                    (username.equals("Dani") && password.equals("ASDFGH"))) {
                Toast.makeText(c, "Login successful!", Toast.LENGTH_SHORT).show();
            } else {
                new AlertDialog.Builder(c)
                        .setTitle("Error")
                        .setMessage("Username or Password is incorrect.")
                        .setPositiveButton("OK", (dialogInterface, i) -> {
                            et1.setText("");  // Clear username field
                            et2.setText("");  // Clear password field
                        })
                        .show();
            }
        });

        // Set OnClickListener for Register Label
        registerLabel.setOnClickListener(v -> {
            Toast.makeText(c, "Going to Registration Page", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, MainActivity2.class));

        });
    }
}
