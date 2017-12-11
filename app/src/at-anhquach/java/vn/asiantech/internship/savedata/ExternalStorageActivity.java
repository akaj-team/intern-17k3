package vn.asiantech.internship.savedata;

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

/*
 * Class read and write file on external storage
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
        if (isExternalWriteable()) {
            File fileExternal = new File(Environment.getExternalStorageDirectory().getPath() + getResources().getString(R.string.folder_name_anhquach));
            //noinspection ResultOfMethodCallIgnored
            fileExternal.mkdirs();
            File abc = new File(fileExternal.getPath(), getResources().getString(R.string.file_name_txt));

            try {
                FileOutputStream outputStream = new FileOutputStream(abc);
                OutputStreamWriter bufferedOutputStream = new OutputStreamWriter(outputStream, "UTF-8");
                bufferedOutputStream.write(value);
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
                outputStream.close();
            } catch (IOException writeData) {
                Log.d(getResources().getString(R.string.tag_error), writeData.getMessage());
            }
        }
    }

    private void readFileExternal() {
        if (!isExternalReadable()) {
            File fileRead = new File(Environment
                    .getExternalStorageDirectory().getPath() + getResources().getString(R.string.folder_name_anhquach), getResources().getString(R.string.file_name_txt));
            try {
                Scanner scanner = new Scanner(fileRead, "UTF-8");
                StringBuilder builder = new StringBuilder();
                while (scanner.hasNext()) {
                    builder.append(scanner.nextLine());
                }
                scanner.close();
                mEdtSaveData.setText(builder.toString());
            } catch (IOException readData) {
                Log.d(getResources().getString(R.string.tag_error), readData.getMessage());
            }
        }
    }
}
