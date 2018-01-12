package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import vn.asiantech.internship.R;

/**
 * DownloadActivity save data in external
 */
public class SaveExternalActivity extends AppCompatActivity {
    private static final String FOLDER_NAME = "/nhatnguyen";
    private static final String TEXT_NAME_FILE = "/abc.txt";
    private Button mBtnInExternal;
    private EditText mEdtExternal;
    private String mSDCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_external);
        initView();
        initFile();
        initListener();
    }

    private void initView() {
        mBtnInExternal = findViewById(R.id.btnSaveExternal);
        mEdtExternal = findViewById(R.id.edtExternal);
        mSDCard = Environment.getExternalStorageDirectory().getAbsolutePath();
        readData(mSDCard + FOLDER_NAME + TEXT_NAME_FILE);
    }

    private void initFile() {
        File file = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
        if (!file.exists()) {
            //noinspection ResultOfMethodCallIgnored
            file.mkdirs();
        }
    }

    private void initListener() {
        mBtnInExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeData(mSDCard + FOLDER_NAME + TEXT_NAME_FILE);
            }

            private void writeData(String string) {
                try {
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(new FileOutputStream(string), "UTF-8");
                    if (!TextUtils.isEmpty(mEdtExternal.getText())) {
                        outputStreamWriter.write(mEdtExternal.getText().toString());
                    } else {
                        Toast.makeText(SaveExternalActivity.this, mSDCard, Toast.LENGTH_SHORT).show();
                    }
                    outputStreamWriter.close();
                } catch (IOException e) {
                    Log.d("", e.getMessage());
                }
            }
        });
    }

    private void readData(String string) {
        try {
            Scanner scan = new Scanner(new File(string), "UTF-8");
            StringBuilder data = new StringBuilder();
            while (scan.hasNext()) {
                data.append(scan.nextLine());
            }
            scan.close();
            mEdtExternal.setText(data.toString());
        } catch (FileNotFoundException e) {
            Log.d("", e.getMessage());
        }
    }
}
