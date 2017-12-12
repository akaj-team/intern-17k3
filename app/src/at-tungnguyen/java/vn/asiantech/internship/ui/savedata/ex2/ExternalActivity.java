package vn.asiantech.internship.ui.savedata.ex2;

import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

import vn.asiantech.internship.R;

public class ExternalActivity extends AppCompatActivity {
    private EditText mEdtName;
    private Button mBtnResult;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);
        readFileExternal();
        initViews();
        initEvent();
    }

    private void initViews() {
        mEdtName = findViewById(R.id.edtName);
        mBtnResult = findViewById(R.id.btnResult);
    }

    private void initEvent() {
        mBtnResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeFileExternal(mEdtName.getText().toString());
            }
        });
    }

    private boolean isExternalReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    private boolean isExternalWriteable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    private void writeFileExternal(String value) {
        if (!isExternalWriteable()) {
            Toast.makeText(this, "Please use memory", Toast.LENGTH_SHORT).show();
        } else {
            File folderMemory = new File(Environment.getExternalStorageDirectory().getPath() + "/Tungnguyen");
            folderMemory.mkdirs();
            Log.d("VVVV", "WriteFileExternal: " + Environment.getExternalStorageDirectory().getPath());
            File fileMemory = new File(folderMemory.getPath(), "abc.txt");
            try {
                FileOutputStream fos = new FileOutputStream(fileMemory);
                Writer w = new BufferedWriter(new OutputStreamWriter(fos,"UTF-8"));

                w.write(value);

                w.flush();
                w.close();
            } catch (IOException e) {
                Log.d("Exception",e.getMessage());
            }
        }
    }

    private void readFileExternal() {
        if (!isExternalWriteable()) {
            Toast.makeText(this, "Please use memory", Toast.LENGTH_SHORT).show();
        } else {
            if (isExternalReadable()) {
            } else {
                File fileRead = new File(Environment.getExternalStorageDirectory().getPath() + "/Tungnguyen", "abc.txt");
                Log.d("abcc", "path" + Environment.getExternalStorageDirectory().getPath());
                try {
                    FileInputStream inputStream = new FileInputStream(fileRead);
                    InputStreamReader reader = new InputStreamReader(inputStream,"UTF-8");
                    BufferedReader bufferedReader = new BufferedReader(reader);
                    String data;
                    StringBuilder builder = new StringBuilder();
                    while ((data = bufferedReader.readLine()) != null) {
                        builder.append(data);
                    }
                    bufferedReader.close();
                    reader.close();
                    inputStream.close();
                    Log.d("DATA", builder.toString());
                } catch (IOException e) {
                    Log.d("abc",e.getMessage());
                }
            }
        }
    }
}
