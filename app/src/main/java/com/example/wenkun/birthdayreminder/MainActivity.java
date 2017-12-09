package com.example.wenkun.birthdayreminder;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private PeopleDB peopleDB=new PeopleDB(MainActivity.this);
    final List<Map<String,Object>> people_data=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        updateRecycleView();

        Button addItem=(Button)findViewById(R.id.addItem);
        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddItemActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }

    public void updateRecycleView() {
       // people_data.clear();
        Cursor cursor=peopleDB.findAllData();

        for(cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
            int nameColumn = cursor.getColumnIndex("name");
            int birthColumn = cursor.getColumnIndex("birth");
            int giftColumn = cursor.getColumnIndex("gift");
            String name = cursor.getString(nameColumn);
            String birth = cursor.getString(birthColumn);
            String gift = cursor.getString(giftColumn);
            Map<String,Object> temp=new LinkedHashMap<>();
            temp.put("name",name);
            temp.put("birth",birth);
            temp.put("gift",gift);

            //people_data.add(temp);
        }
//            final MyAdapter myAdapter=new MyAdapter<Map<String,Object>>(this,R.layout.people_item,people_data) {
//                @Override
//                public void convert(ViewHolder holder, Map<String,Object> o) {
//                    TextView name=holder.getView(R.id.name);
//                    name.setText(o.get("name").toString());
//                    TextView birthday=holder.getView(R.id.birthday);
//                    birthday.setText(o.get("birthday").toString());
//                    TextView gift=holder.getView(R.id.gift);
//                    gift.setText(o.get("gift").toString());
//                }
//
//            };
//
//            RecyclerView mRecyclerView=(RecyclerView) findViewById(R.id.recycler_view);
//            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//            mRecyclerView.setAdapter(myAdapter);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==2) {
            updateRecycleView();
        }
    }
}
