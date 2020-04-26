package com.farizma.themesample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RandomTheme extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();

        if(sharedPreferences.contains("theme"))
            setTheme(sharedPreferences.getInt("theme", 0));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_theme);
    }

    public void showDialog(View view) {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RadioGroup radioGroup = dialog.findViewById(R.id.radioGroup);
        Button submitBtn = dialog.findViewById(R.id.submit);
        Button cancelBtn = dialog.findViewById(R.id.cancel);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int itemChecked) {
                switch (itemChecked) {
                    case R.id.themeBlue:
                        editor.putInt("theme", R.style.BlueTheme);
                        editor.apply();
                        break;
                    case R.id.themeOrange:
                        editor.putInt("theme", R.style.OrangeTheme);
                        editor.apply();
                        break;
                    case R.id.themeGreen:
                        editor.putInt("theme", R.style.DefaultTheme);
                        editor.apply();
                        break;
                }
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                recreate();
            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.cancel();
            }
        });
        dialog.show();
    }
}
