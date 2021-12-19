package com.mrmarl.listview;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Main extends AppCompatActivity
        implements AdapterView.OnItemClickListener{
    EditText txt;
    ListView list;
    Button edit,del;
    private ArrayAdapter<String> arad;
    String str;
    Pos pos = new Pos();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_act);
        arad=new ArrayAdapter<String> (this,R.layout.el);
        txt = findViewById(R.id.txt);
        list = findViewById(R.id.list);
        edit = findViewById(R.id.edit);
        del = findViewById(R.id.del);
        edit.setClickable(false);
        del.setClickable(false);
        list.setAdapter(arad);
        list.setOnItemClickListener(this);
    }
    public void add(View view) {
        String addTxt = txt.getText().toString() + " (New)";
        //arad.remove(addTxt);
        arad.add(addTxt);
        edit.setClickable(true);
        del.setClickable(true);
    }
    public void del(){
        del(null);
    }
    public void del(View view) {
        unclick();
        arad.remove(str);
        if (arad.isEmpty()) {
            edit.setClickable(false);
            del.setClickable(false);
        }
    }
    public void edit(View view) {
        if (str != null && pos.x != -1){
            del();
        arad.insert(txt.getText().toString() + "(edited)", pos.x);
        str = txt.getText().toString();
    }}
    public void clear(View view) {
        arad.clear();
        edit.setClickable(false);
        del.setClickable(false);
    }
    private void unclick(){
        if (pos.view != null) {
            pos.view.setBackgroundColor(Color.BLACK);
            pos.view = null;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        unclick();
        view.setBackgroundColor(Color.GREEN);
        pos.x=(int)arad.getItemId(position);
        pos.view = view;
        str = arad.getItem(position);
        txt.setText(str);
    }
}
