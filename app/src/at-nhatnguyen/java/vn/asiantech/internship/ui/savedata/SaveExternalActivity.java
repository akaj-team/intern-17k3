package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import vn.asiantech.internship.R;

/**
 * Activity save data in external
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
        });
    }

    private void readData(String string) {
        try {
            Scanner scan = new Scanner(new File(string));
            StringBuilder data = new StringBuilder();
            while (scan.hasNext()) {
                data.append(scan.nextLine());
            }
            scan.close();
            mEdtExternal.setText(data.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void writeData(String string) {
        try {
            FileWriter fileWriter = new FileWriter(string);
            if (!TextUtils.isEmpty(mEdtExternal.getText())) {
                fileWriter.write(mEdtExternal.getText().toString());
            } else {
                Toast.makeText(SaveExternalActivity.this, mSDCard, Toast.LENGTH_SHORT).show();
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
