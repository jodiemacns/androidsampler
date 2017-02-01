package com.example.android.lacedemy;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by jodie on 24/01/17.
 */

public class TextEdit extends Activity {

    NumberPicker nomPicker = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_edit);
        ((ImageButton) findViewById(R.id.imgcmdtesteditbutton)).setImageResource(R.mipmap.ic_launcher);
        addButtonListeners();
        setTextCheckedStatus(isCheckboxChecked());

        // Set up the number picker.
        nomPicker = ((NumberPicker)findViewById(R.id.numberPickerTestEdit));
        nomPicker.setMaxValue(100);
        nomPicker.setMinValue(5);
        nomPicker.setWrapSelectorWheel(true);
    }

    public void addButtonListeners() {
        // Test a button
        ((ImageButton) findViewById(R.id.imgcmdtesteditbutton)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ((ImageButton) findViewById(R.id.imgcmdtesteditbutton)).setImageResource(R.mipmap.ic_launcher);
                playVideo();
            }
        });

        ((CheckBox) findViewById(R.id.checkBoxTextEdit)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean isChecked;
                isChecked = isCheckboxChecked();
                setTextCheckedStatus(isChecked);
            }
        });

        ((NumberPicker)findViewById(R.id.numberPickerTestEdit)).setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
        {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                ((TextView)findViewById(R.id.textViewNumberChosenTestEdit)).setText("Selected value: " + newVal);

            }

        });
    }

    public boolean isCheckboxChecked() {
        return (((CheckBox) findViewById(R.id.checkBoxTextEdit)).isChecked());
    }

    public void setTextCheckedStatus(boolean isChecked) {
        TextView statusTextView;
        statusTextView = (TextView) findViewById(R.id.textViewCheckedStatus);
        if (isChecked) {
            statusTextView.setText("It is Checked");
        } else {
            statusTextView.setText("It is Not Checked");
        }
    }

    public void playVideo() {
        Toast.makeText(getBaseContext(), "Playing video", Toast.LENGTH_LONG).show();
    }

}

