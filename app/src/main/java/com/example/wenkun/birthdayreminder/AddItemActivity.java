package com.example.wenkun.birthdayreminder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

/**
 * Created by 焜 on 2017/12/6.
 */

public class AddItemActivity extends AppCompatActivity {
    private PeopleDB peopleDB=new PeopleDB(AddItemActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additem);

        final TextView name=(TextView)findViewById(R.id.name);
        final TextView birthday=(TextView) findViewById(R.id.birthday);
        final TextView gift=(TextView)findViewById(R.id.gift);
        Button addtoDB=(Button) findViewById(R.id.addtoDB);
        addtoDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().equals("")) {
                    Toast.makeText(AddItemActivity.this,"姓名为空，请检查",Toast.LENGTH_SHORT).show();
                } else {
                    if(peopleDB.isExistence(name.getText().toString())==true) {
                        Toast.makeText(AddItemActivity.this,"名字重复啦，请检查",Toast.LENGTH_SHORT).show();
                        Log.e("name",name.getText().toString());
                    } else {
                        peopleDB.insert(name.getText().toString(),birthday.getText().toString(),gift.getText().toString());
                        setResult(2);
                        finish();
                    }

                }
            }
        });

    }
}
