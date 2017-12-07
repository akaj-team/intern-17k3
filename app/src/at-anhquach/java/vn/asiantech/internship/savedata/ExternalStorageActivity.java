package vn.asiantech.internship.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import vn.asiantech.internship.R;

public class ExternalStorageActivity extends AppCompatActivity {
    EditText mEdtSaveData;
    Button mBtnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initView();
        readFileExternal();
        mBtnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFileExternal(mEdtSaveData.getText().toString());
            }
        });
    }

    private void initView() {
        mEdtSaveData = findViewById(R.id.edtSaveData);
        mBtnSaveData = findViewById(R.id.btnSaveData);
    }

    private boolean isExternalReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private boolean isExternalWriteable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void writeFileExternal(String value) {
        if (!isExternalWriteable()) {
            // No-opp
        } else {
            File fileExternal = new File(Environment.getExternalStorageDirectory().getPath() + "/AnhQuach");
            fileExternal.mkdirs();
            File abc = new File(fileExternal.getPath(), "abc.txt");
            try {
                FileOutputStream outputStream = new FileOutputStream(abc);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
                bufferedOutputStream.write(value.getBytes());

                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                outputStream.close();
            } catch (IOException writeData) {
                writeData.printStackTrace();
            }
        }
    }

    private void readFileExternal() {
        if (!isExternalReadable()) {
            File fileRead = new File(Environment
                    .getExternalStorageDirectory().getPath() + "/AnhQuach", "abc.txt");
            try {
                FileInputStream inputStream = new FileInputStream(fileRead);
                InputStreamReader reader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(reader);
                String data;
                StringBuilder builder = new StringBuilder();
                while ((data = bufferedReader.readLine()) != null) {
                    builder.append(data);
                }
                bufferedReader.close();
                reader.close();
                inputStream.close();
                mEdtSaveData.setText(builder.toString());
            } catch (IOException readData) {
                readData.printStackTrace();
            }
        }
    }
}
