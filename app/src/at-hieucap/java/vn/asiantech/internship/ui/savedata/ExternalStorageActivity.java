package vn.asiantech.internship.ui.savedata;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import vn.asiantech.internship.R;

/**
 * Create external storage activity
 */
public class ExternalStorageActivity extends AppCompatActivity {
    private EditText mEdtSaveData;
    private Button mBtnSaveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share_preference);
        initView();
        readFileExternal();
        mBtnSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isExternalWritable()) {
                    File fileExternal = new File(Environment.getExternalStorageDirectory().getPath() + getResources().getString(R.string.folder_name));
                    // Make directory
                    fileExternal.mkdirs();
                    File abc = new File(fileExternal.getPath(), getResources().getString(R.string.file_name));
                    try {
                        FileOutputStream outputStream = new FileOutputStream(abc);
                        OutputStreamWriter bufferedOutputStream = new OutputStreamWriter(outputStream, "UTF-8");
                        bufferedOutputStream.write(mEdtSaveData.getText().toString());
                        bufferedOutputStream.flush();
                        bufferedOutputStream.close();
                        outputStream.close();
                    } catch (IOException writeData) {
                        Log.d(getResources().getString(R.string.text_error), writeData.getMessage());
                    }
                }
            }
        });
    }

    private void initView() {
        mEdtSaveData = findViewById(R.id.edtTextSharePreference);
        mBtnSaveData = findViewById(R.id.btnClick);
    }

    private boolean isExternalReadable() {
        String state = Environment.getExternalStorageState();
        return !Environment.MEDIA_UNMOUNTABLE.equals(state) || !Environment.MEDIA_UNMOUNTED.equals(state);
    }

    private boolean isExternalWritable() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    private void readFileExternal() {
        if (isExternalReadable()) {
            File fileRead = new File(Environment
                    .getExternalStorageDirectory().getPath() + getResources().getString(R.string.folder_name), getResources().getString(R.string.file_name));
            try {
                Scanner scanner = new Scanner(fileRead, "UTF-8");
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                mEdtSaveData.setText(builder.toString());
            } catch (IOException readData) {
                Log.d(getResources().getString(R.string.text_error), readData.getMessage());
            }
        }
    }
}
