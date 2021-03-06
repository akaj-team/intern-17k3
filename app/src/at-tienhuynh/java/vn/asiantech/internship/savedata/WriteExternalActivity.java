package vn.asiantech.internship.savedata;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import vn.asiantech.internship.R;

public class WriteExternalActivity extends AppCompatActivity {
    private EditText mEdtValues;
    private Button mBtnSaveWriteToFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_external);
        initViews();
        if (readFromFile() != null) {
            mEdtValues.setText(readFromFile());
        }
        mBtnSaveWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeTxtFile(mEdtValues.getText().toString());
                startActivity(new Intent(WriteExternalActivity.this, ExerciseManagementActivity.class));
            }
        });
    }

    private void initViews() {
        mEdtValues = findViewById(R.id.edtValueWriteFile);
        mBtnSaveWriteToFile = findViewById(R.id.btnSaveWriteFileToExternal);
    }

    private String readFromFile() {
        String result = "";
        if (!isExternalWriteable()) {
            //no-op
        } else if (isExternalReadable()) {
            //no-op
        } else {
            File fileRead = new File(Environment.getExternalStorageDirectory().getPath()
                    + "/TienHuynh3", "abc.txt");
            try {
                String data;
                FileInputStream fileInputStream = new FileInputStream(fileRead);
                InputStreamReader reader = new InputStreamReader(fileInputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(reader);
                StringBuilder stringBuilder = new StringBuilder();
                while ((data = bufferedReader.readLine()) != null) {
                    stringBuilder.append(data);
                }
                bufferedReader.close();
                reader.close();
                result = stringBuilder.toString();
            } catch (FileNotFoundException e) {
                Log.d("FileNotFoundException", e.getMessage());
            } catch (IOException e) {
                Log.d("IOException", e.getMessage());
            }
        }
        return result;
    }

    /**
     * Save data to External Storge
     */
    private void writeTxtFile(String data) {
        if (!isExternalWriteable()) {
            //no-op
        } else if (isExternalReadable()) {
            //no-op
        } else {
            File folderExternal = new File(Environment.getExternalStorageDirectory()
                    .getPath() + "/TienHuynh3");
            folderExternal.mkdir();
            File fileExternal = new File(folderExternal.getPath(), "abc.txt");
            try {

                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(fileExternal), "UTF-8");
                writer.write(data);
                writer.close();
            } catch (FileNotFoundException e) {
                Log.d("FileNotFound", e.getMessage());
            } catch (IOException e) {
                Log.d("IO ", e.getMessage());
            }
        }
    }

    private boolean isExternalWriteable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }
}
