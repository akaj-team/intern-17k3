package vn.asiantech.internship.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import vn.asiantech.internship.R;

/**
 * Created by phongle on 11/12/2560.
 * ExternalStorageActivity
 */
public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtInputExternalStorage;
    private Button mBtnClickExternalStorage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        initViews();
        initListener();
        readData();
    }

    private void initViews() {
        mEdtInputExternalStorage = findViewById(R.id.edtInputExternalStorage);
        mBtnClickExternalStorage = findViewById(R.id.btnClickExternalStorage);
    }

    private void initListener() {
        mBtnClickExternalStorage.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        writeData();
        readData();
    }

    private void writeData() {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + getResources().getString(R.string.file_name);
        try {
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard), "UTF-8");
            writer.write(mEdtInputExternalStorage.getText() + "");
            writer.close();
        } catch (FileNotFoundException e) {
            Log.d("w", getResources().getString(R.string.edt_write_file_fail));
        } catch (IOException e) {
            Log.d("ww", getResources().getString(R.string.edt_write_file_fail));
        }
    }

    private void readData() {
        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + getResources().getString(R.string.file_name);
        try {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(sdcard), "UTF-8");
            BufferedReader bufferedreader = new BufferedReader(reader);
            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = bufferedreader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            mEdtInputExternalStorage.setText(stringBuilder);
        } catch (FileNotFoundException e) {
            Log.d("r", getResources().getString(R.string.edt_read_file_fail));
        } catch (IOException e) {
            Log.d("rr", getResources().getString(R.string.edt_read_file_fail));
        }
    }
}
