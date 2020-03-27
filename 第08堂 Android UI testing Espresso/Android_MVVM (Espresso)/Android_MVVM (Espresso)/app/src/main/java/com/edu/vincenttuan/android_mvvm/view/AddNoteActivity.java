package com.edu.vincenttuan.android_mvvm.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.edu.vincenttuan.android_mvvm.R;

public class AddNoteActivity extends AppCompatActivity {
    public static final String EXTRA_TITLE = "com.edu.vincenttuan.android_mvvm.view.EXTRA_TITLE";
    public static final String EXTRA_DESCRIPTION = "com.edu.vincenttuan.android_mvvm.view.EXTRA_DESCRIPTION";
    public static final String EXTRA_PROPERTY = "com.edu.vincenttuan.android_mvvm.view.EXTRA_PROPERTY";

    private EditText editTextTitle;
    private EditText editTextDescription;
    private NumberPicker numberPickerProperty;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);

        editTextTitle = findViewById(R.id.edit_text_title);
        editTextDescription = findViewById(R.id.edit_text_description);
        numberPickerProperty = findViewById(R.id.number_picker_property);
        numberPickerProperty.setMinValue(1);
        numberPickerProperty.setMaxValue(10);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Note");
    }

    private void addNote() {
        String title = editTextTitle.getText().toString();
        String description = editTextDescription.getText().toString();
        int property = numberPickerProperty.getValue();

        if(title.trim().isEmpty() || description.trim().isEmpty()) {
            Toast.makeText(this, "Please input data !", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_TITLE, title);
        data.putExtra(EXTRA_DESCRIPTION, description);
        data.putExtra(EXTRA_PROPERTY, property);

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_note) {
            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
            addNote();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}
