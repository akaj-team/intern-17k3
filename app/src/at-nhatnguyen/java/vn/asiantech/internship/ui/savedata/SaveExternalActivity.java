package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import vn.asiantech.internship.R;

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
        initListener();
    }

    private void initView() {
        mBtnInExternal = findViewById(R.id.btnSaveExternal);
        mEdtExternal = findViewById(R.id.edtExternal);
        File file = new File(Environment.getExternalStorageDirectory(), FOLDER_NAME);
        if (!file.exists()) {
            file.mkdirs();
        }
        mSDCard = Environment.getExternalStorageDirectory().getAbsolutePath();

        try {
            FileReader fileReader = new FileReader(mSDCard + FOLDER_NAME + TEXT_NAME_FILE);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            if (bufferedReader.readLine() != null) {
                mEdtExternal.setText(bufferedReader.readLine());
            } else {
                mEdtExternal.setText("abc");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initListener() {
        mBtnInExternal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                try {
                    FileWriter fileWriter = new FileWriter(mSDCard + FOLDER_NAME + TEXT_NAME_FILE);
                    if (!TextUtils.isEmpty(mEdtExternal.getText())) {
                        fileWriter.write(mEdtExternal.getText().toString());
                    } else {
                        Toast.makeText(SaveExternalActivity.this, mSDCard, Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
