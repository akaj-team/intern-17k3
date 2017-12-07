package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
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
 * Created by vietphan on 07/12/2017
 * ExternalStorageActivity
 */
public class ExternalStorageActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEditText;
    private String mFolderName = "/vietphan";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external_storage);
        initViews();
        readData();
    }

    private void initViews() {
        Button btnWriteData = findViewById(R.id.btnWriteData);
        Button btnReadData = findViewById(R.id.btnReadData);
        mEditText = findViewById(R.id.edtName);
        btnWriteData.setOnClickListener(this);
        btnReadData.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnWriteData:
                writeData();
                Toast.makeText(this, R.string.saved, Toast.LENGTH_SHORT).show();
                break;
            case R.id.btnReadData:
                readData();
                break;
        }
    }

    public void writeData() {
        File f = new File(Environment.getExternalStorageDirectory(), mFolderName);
        if (!f.exists()) {
            //noinspection ResultOfMethodCallIgnored
            f.mkdirs();
        }
        //noinspection StatementWithEmptyBody
        if (isExternalWriteable()) {
            String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + mFolderName + "/abc.txt";
            try {
                OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(sdcard),"UTF-8");
                writer.write(mEditText.getText() + "");
                writer.close();
            } catch (FileNotFoundException e) {
                Log.d(getString(R.string.err), e.getMessage());
            } catch (IOException e) {
                Log.d(getString(R.string.err), e.getMessage());
            }
        }
    }

    public void readData() {
        //noinspection StatementWithEmptyBody
        if (!isExternalReadable()) {
            String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath() + mFolderName + "/abc.txt";
            try {
                Scanner scan = new Scanner(new File(sdcard),"UTF-8");
                StringBuilder data = new StringBuilder();
                while (scan.hasNext()) {
                    data.append(scan.nextLine()).append("\n");
                }
                scan.close();
                mEditText.setText(data);
            } catch (FileNotFoundException e) {
                Log.d(getString(R.string.err), e.getMessage());
            }
        }
    }

    private boolean isExternalReadable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED_READ_ONLY.equals(state);
    }

    private boolean isExternalWriteable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
}
