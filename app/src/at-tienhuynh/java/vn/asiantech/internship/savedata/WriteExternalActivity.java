package vn.asiantech.internship.savedata;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        mEdtValues.setText(readFromFile(this));
        mBtnSaveWriteToFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeTxtFile(mEdtValues.getText().toString(), getApplicationContext());
            }
        });
    }

    private void initViews() {
        mEdtValues = findViewById(R.id.edtValueWriteFile);
        mBtnSaveWriteToFile = findViewById(R.id.btnSaveWriteFileToExternal);
    }

    private String readFromFile(Context context) {
        String values = "";
        try {
            FileInputStream fileInputStream = openFileInput("tienhuynh3.txt");
            int c;
            while ((c = fileInputStream.read()) != -1) {
                values = values + Character.toString((char) c);
            }
            fileInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("err", "" + e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("err", "" + e);
        }
        return values;
    }

    private void writeTxtFile(String data, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("tienhuynh3.txt", context.MODE_PRIVATE));
            outputStreamWriter.write(data);
            outputStreamWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d("err", "" + e);
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("err", "" + e);
        }
    }
}
