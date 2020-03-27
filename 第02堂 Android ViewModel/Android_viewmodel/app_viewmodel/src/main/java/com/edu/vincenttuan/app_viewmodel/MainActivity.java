package com.edu.vincenttuan.app_viewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editHeight, editWeight;
    private TextView textResult, textResultList;
    private MyViewModel mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(MyViewModel.class);
        editHeight = findViewById(R.id.editHeight);
        editWeight = findViewById(R.id.editWeight);
        textResult = findViewById(R.id.textResult);
        textResultList = findViewById(R.id.textResultList);

        dataBinding();
    }

    private void dataBinding() {
        List<BMI> bmis = mViewModel.getBmis();
        if(bmis.size() > 0) {
            editHeight.setText(String.format("%.1f", bmis.get(bmis.size()-1).getHeight()));
            editWeight.setText(String.format("%.1f", bmis.get(bmis.size()-1).getWeight()));
            textResult.setText(String.format("%.2f", bmis.get(bmis.size()-1).getBmi()));
            // 放歷史資料
            //textResultList.setText(bmis+"");
            textResultList.setText("");
            bmis.stream().forEach(bmi -> textResultList.append(bmi.toString()));
        }
    }

    public void onClick(View view) {
        double h = Double.parseDouble(editHeight.getText().toString());
        double w = Double.parseDouble(editWeight.getText().toString());
        BMI bmi = new BMI(h, w);
        mViewModel.addBmi(bmi);
        dataBinding();
    }
}
