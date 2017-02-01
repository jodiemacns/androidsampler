package com.example.android.lacedemy;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.io.PrintStream;

public class MainActivity extends AppCompatActivity {

    Spinner cmbFormType = null;
    public static Intent browser = null;
    public static Intent callForm = null;
    private ProgressBar bar = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Reference to the combo box (spinner)
        cmbFormType = (Spinner)findViewById(R.id.cmbAM);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.formtypes, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        cmbFormType.setAdapter(adapter1);
        addKeypressButtonListeners();
        addButtonListeners();

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Reference to the combo box (spinner)
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.formtypes, android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void startThread()
    {
        bar = (ProgressBar)findViewById(R.id.barMainLoginProgress);
        bar.setProgress(0);
        new Thread(new Task()).start();
    }
    public void addButtonListeners() {
        // Progress Bar
        ((Button) findViewById(R.id.cmdMainStartProgress)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startThread();
                boolean boolLog = createErrorLog(new File("/mnt/sdcard/mynewerror1.log"));
                if (boolLog) {
                    Toast.makeText(getBaseContext(), " File Created", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getBaseContext(), " File Not Created!! or already exist", Toast.LENGTH_LONG).show();
                }
            }
        });

        // Take a photo
        ((Button) findViewById(R.id.cmdMainTakePhoto)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                takePhoto();
            }
        });
        // Start New form
        ((Button) findViewById(R.id.cmdMainCallForm)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callForm = new Intent("com.example.android.lacedemy.TEXTEDIT");
                startActivity(callForm);
            }
        });

        // Start the browser
        ((Button) findViewById(R.id.cmdMainStartBrowser)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                browser = new Intent("com.example.android.lacedemy.BROWSER");
                startActivity(browser);

            }
        });

        ((Button) findViewById(R.id.cmdMainOptions)).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    String textData = ((EditText) (findViewById(R.id.editTextUser))).getText().toString();
                    int intest = Integer.parseInt(textData);
                    Toast.makeText(getBaseContext(), textData, Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException nfe) {
                    Toast.makeText(getBaseContext(), "Please enter number", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getBaseContext(), "Unknown Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ((RadioButton) findViewById(R.id.rdioCheckbox1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) findViewById(R.id.rdioCheckbox2)).setChecked(false);
            }
        });
        ((RadioButton) findViewById(R.id.rdioCheckbox2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((RadioButton) findViewById(R.id.rdioCheckbox1)).setChecked(false);
            }
        });
    }

    public void addKeypressButtonListeners()
    {
        ((EditText)findViewById(R.id.textEditMainLoginPassword)).setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                Toast.makeText(getBaseContext(),"Pressed keys", Toast.LENGTH_LONG).show();
                return false;
            }
        });
    }
    private void takePhoto()
    {
        try
        {
            final Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(getTempFile(this)));
            // 1 = Back Camera
            startActivityForResult(intent, 1);
        }
        catch(Exception e)
        {

        }
    }

    private File getTempFile(Context context)
    {
       File imgPath = new File (Environment.getExternalStorageDirectory(), context.getPackageName());
        if(!imgPath.exists())
        {
           imgPath.mkdir();
        }
        imgPath = new File(imgPath, "image.tmp");
        return imgPath;
    }
    public class Task implements Runnable
    {
        @Override
        public void run() {
            for(int i = 0; i <= 10; i++)
            {
                final int value = i;

                try
                {
                    Thread.sleep(1000);
                }catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
                bar.setProgress(value * 10);
            }
        }
    }
    public boolean createErrorLog(File strLog)
    {
        File errorLog = strLog;
        try
        {
            errorLog.createNewFile();
            FileOutputStream fout = new FileOutputStream(errorLog, false);
            PrintStream prtStream = new PrintStream(fout);

            String strErrorHeader = "This is my error log";
            prtStream.println(strErrorHeader);
            fout.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
