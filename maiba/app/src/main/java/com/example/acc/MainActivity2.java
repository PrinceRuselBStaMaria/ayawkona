package com.example.acc;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.format.DateFormat;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {

    private TextView dateEdt;
    private EditText regpassword;
    private EditText conpassword;
    private Button regbutton;
    private EditText reguser;
    private EditText fname;
    private EditText lname;
    private EditText email;
    private EditText address;
    private EditText contactNumber;
    private EditText otherGenderEditText;
    private RadioGroup gender;
    private CheckBox hb1;
    private CheckBox hb2;
    private CheckBox hb3;
    private CheckBox hb4;
    private CheckBox hb5;
    private CheckBox hb6;
    private CheckBox hb7;
    private CheckBox hb8;
    private CheckBox hb9;
    private CheckBox hb10;
    private Spinner spinner1;
    private Spinner spinner2;
    private Spinner spinner3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);

        try {
            initializeViews();
            setupDatePicker();
            setupSpinners();
            others();
            regbutton.setOnClickListener(view -> {
                if (!isFinishing()) {
                    validateForm();
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error initializing: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void others() {
        gender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radioButton3) {
                    otherGenderEditText.setVisibility(View.VISIBLE);
                } else {
                    otherGenderEditText.setVisibility(View.GONE);
                    otherGenderEditText.getText().clear();
                }
            }
        });
    }

    private void initializeViews() {
        try {
            dateEdt = findViewById(R.id.bday);
            regpassword = findViewById(R.id.regpassword);
            conpassword = findViewById(R.id.conpass);
            regbutton = findViewById(R.id.regbutton);
            reguser = findViewById(R.id.reguser);
            fname = findViewById(R.id.fname);
            lname = findViewById(R.id.lname);
            email = findViewById(R.id.email);
            address = findViewById(R.id.address);
            contactNumber = findViewById(R.id.contactNumber);
            gender = findViewById(R.id.gender);
            otherGenderEditText = findViewById(R.id.otherGenderEditText);

            spinner1 = findViewById(R.id.squestions);
            spinner2 = findViewById(R.id.squestions2);
            spinner3 = findViewById(R.id.squestions3);

            //checkboxes
            hb1 = findViewById(R.id.hb1);
            hb2 = findViewById(R.id.hb2);
            hb3 = findViewById(R.id.hb3);
            hb4 = findViewById(R.id.hb4);
            hb5 = findViewById(R.id.hb5);
            hb6 = findViewById(R.id.hb6);
            hb7 = findViewById(R.id.hb7);
            hb8 = findViewById(R.id.hb8);
            hb9 = findViewById(R.id.hb9);
            hb10 = findViewById(R.id.hb10);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error initializing views: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
    private void setupDatePicker() {
        dateEdt.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    MainActivity2.this,
                    (view, year1, monthOfYear, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year1;
                        dateEdt.setText(selectedDate);
                    },
                    year, month, day);
            datePickerDialog.show();
        });
    }

    private void setupSpinners() {
        spinner1 = findViewById(R.id.squestions);
        spinner2 = findViewById(R.id.squestions2);
        spinner3 = findViewById(R.id.squestions3);

        //questions
        String[] questionsWithDefault = new String[]{"Select a question", "First love?",
                "First kiss?", "Messi or Ronaldo?", "Favorite food?", "Mother Maiden's Name?"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, questionsWithDefault);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Track if initial setup is complete
        final boolean[] isInitialSetup = {true};


        spinner1.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                // Skip validation during initial setup
                if (isInitialSetup[0]) {
                    return;
                }

                // Skip validation if "Select a question" is chosen
                String selected = parent.getItemAtPosition(pos).toString();
                if (selected.equals("Select a question")) {
                    return;
                }

                // Check for duplicates
                if (hasDuplicateSelections(spinner1, spinner2, spinner3)) {
                    showAlert("Select only one");
                    // Reset the current spinner to default
                    ((Spinner)parent).setSelection(0);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        };


        spinner1.setOnItemSelectedListener(listener);
        spinner2.setOnItemSelectedListener(listener);
        spinner3.setOnItemSelectedListener(listener);

        //default option
        spinner1.setSelection(0);
        spinner2.setSelection(0);
        spinner3.setSelection(0);

        isInitialSetup[0] = false;
    }

    private boolean hasDuplicateSelections(Spinner... spinners) {
        for (int i = 0; i < spinners.length; i++) {
            String current = spinners[i].getSelectedItem().toString();
            if (current.equals("Select a question")) continue;

            for (int j = i + 1; j < spinners.length; j++) {
                String other = spinners[j].getSelectedItem().toString();
                if (current.equals(other)) {
                    return true;
                }
            }
        }
        return false;
    }


    private Boolean validatePasswords() {
        String password = regpassword.getText().toString();
        String confirmPassword = conpassword.getText().toString();

        if (!password.equals(confirmPassword)) {
            return false;
        } else {
            return true;
        }
    }

    private void showPasswordMismatchDialog() {
        showAlert("Passwords do not match!");
    }

    private void showAlert(String message) {
        if (!isFinishing()) {
            try {
                new AlertDialog.Builder(MainActivity2.this)
                        .setTitle("Alert")
                        .setMessage(message)
                        .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                        .create()
                        .show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    private void validateForm() {

        if (!validatePasswords()){
            showPasswordMismatchDialog();
            return;
        }

        if (!areFieldsFilled()) {
            showAlert("Please fill in all required fields");
            return;
        }
        else {
            display();
        }

    }


    private boolean areFieldsFilled() {
        return !isEmpty(reguser) &&
                !isEmpty(regpassword) &&
                !isEmpty(conpassword) &&
                !isEmpty(fname) &&
                !isEmpty(lname) &&
                !isEmpty(email) &&
                !isEmpty(address) &&
                !isEmpty(contactNumber) &&
                !dateEdt.getText().toString().equals("Input date") &&
                gender.getCheckedRadioButtonId() != -1 &&
                isAnyCheckboxChecked() &&
                areSpinnersSelected();
    }

    private boolean isEmpty(EditText editText) {
        return editText.getText().toString().trim().isEmpty();
    }

    private boolean isAnyCheckboxChecked() {
        return hb1.isChecked() || hb2.isChecked() || hb3.isChecked() ||
                hb4.isChecked() || hb5.isChecked() || hb6.isChecked() ||
                hb7.isChecked() || hb8.isChecked() || hb9.isChecked() ||
                hb10.isChecked();
    }

    private boolean areSpinnersSelected() {
        return !spinner1.getSelectedItem().toString().equals("Select a question") &&
                !spinner2.getSelectedItem().toString().equals("Select a question") &&
                !spinner3.getSelectedItem().toString().equals("Select a question");
    }

    private void display() {
        StringBuilder message = new StringBuilder();

        // Personal Details
        message.append("Username: ").append(reguser.getText().toString()).append("\n\n");
        message.append("Name: ").append(fname.getText().toString())
                .append(" ").append(lname.getText().toString()).append("\n");
        message.append("Email: ").append(email.getText().toString()).append("\n");
        message.append("Address: ").append(address.getText().toString()).append("\n");
        message.append("Contact: ").append(contactNumber.getText().toString()).append("\n");
        message.append("Birthday: ").append(dateEdt.getText().toString()).append("\n\n");

        // Gender
        int selectedId = gender.getCheckedRadioButtonId();
        if (selectedId == R.id.radioButton3) {
            String otherGender = otherGenderEditText.getText().toString();
            message.append("Gender: ").append(otherGender).append("\n\n");
        } else {
            message.append("Gender: ")
                    .append(((RadioButton) findViewById(selectedId)).getText().toString())
                    .append("\n\n");
        }

        // Hobbies
        message.append("Hobbies:\n");
        CheckBox[] hobbies = {hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9, hb10};
        boolean hasHobbies = false;
        for (CheckBox hobby : hobbies) {
            if (hobby.isChecked()) {
                message.append("- ").append(hobby.getText().toString()).append("\n");
                hasHobbies = true;
            }
        }
        if (!hasHobbies) {
            message.append("No hobbies selected\n");
        }
        message.append("\n");

        //spinner
        message.append("Security Questions:\n");
        message.append("1. ").append(spinner1.getSelectedItem().toString()).append("\n");
        message.append("2. ").append(spinner2.getSelectedItem().toString()).append("\n");
        message.append("3. ").append(spinner3.getSelectedItem().toString()).append("\n");


        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Account Details")
                .setMessage(message.toString())
                .setPositiveButton("OK", (dialog, which) -> {
                    dialog.dismiss();
                    newact();
                })
                .setNegativeButton("Edit", (dialog, which) -> dialog.dismiss())
                .show();
    }
    private void newact() {
        Intent intent = new Intent(MainActivity2.this, MainActivity3.class);
        intent.putExtra("fullName", fname.getText().toString() + " " + lname.getText().toString()); // Changed from "FULLNAME" to "fullName"
        startActivity(intent);
    }



}

/* bug fix for inital spinner setup
private boolean hasDuplicateSelections(Spinner... spinners) {
    for (int i = 0; i < spinners.length; i++) {
        String current = spinners[i].getSelectedItem().toString();
        if (current.equals("Select a question")) continue;

        for (int j = i + 1; j < spinners.length; j++) {
            String other = spinners[j].getSelectedItem().toString();
            if (current.equals(other)) {
                return true;
            }
        }
    }
    return false;
}
*/

/*    private void setupSpinners() {
        Spinner spinner = findViewById(R.id.squestions);
        Spinner spinner2 = findViewById(R.id.squestions2);
        Spinner spinner3 = findViewById(R.id.squestions3);

        // Track selected values
        final String[] selections = new String[3];

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, questions);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner2.setAdapter(adapter);
        spinner3.setAdapter(adapter);

        AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();

                // Determine which spinner was selected
                int spinnerIndex;
                if (parent.getId() == R.id.squestions) {
                    spinnerIndex = 0;
                } else if (parent.getId() == R.id.squestions2) {
                    spinnerIndex = 1;
                } else {
                    spinnerIndex = 2;
                }

                // Store selection
                selections[spinnerIndex] = selectedItem;

                // Check for duplicates
                boolean hasDuplicate = false;
                for (int i = 0; i < selections.length; i++) {
                    if (selections[i] != null) {
                        for (int j = i + 1; j < selections.length; j++) {
                            if (selections[i].equals(selections[j])) {
                                hasDuplicate = true;
                                break;
                            }
                        }
                    }
                }

                if (hasDuplicate) {
                    showDuplicateQuestionDialog();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        };

        spinner.setOnItemSelectedListener(listener);
        spinner2.setOnItemSelectedListener(listener);
        spinner3.setOnItemSelectedListener(listener);
    }*/