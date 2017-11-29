package com.example.bezierview.mybezierview;

import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class OtherActivity extends AppCompatActivity {
    private DrawCubicView cubicView;
    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        cubicView = (DrawCubicView) findViewById(R.id.cubicview);

        radioGroup = (RadioGroup) findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == group.getChildAt(0).getId()){
                    cubicView.moveLeft();
                }else if(checkedId == group.getChildAt(1).getId()){
                    cubicView.moveRight();
                }
            }
        });
    }
}
