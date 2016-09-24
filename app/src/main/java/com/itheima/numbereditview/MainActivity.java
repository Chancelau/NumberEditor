package com.itheima.numbereditview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.itheima.numbereditorlib.NumberEditorView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.number_editor)
    NumberEditorView BKNumberEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        BKNumberEditor.setOnNumberEditorClickListener(new NumberEditorView.OnNumberEditorClickListener() {
            @Override
            public void onAddButtonClick(int num) {
                Toast.makeText(MainActivity.this, num+"!!!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSubButtonClick(int num) {
                Toast.makeText(MainActivity.this, num+"!!!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onNumberViewClick() {
                Toast.makeText(MainActivity.this, "!!!", Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onFailed(int errorCode, int number) {

            }
        });
    }
}
