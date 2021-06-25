package com.example.a3dmodelviewer;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ListOfModel extends AppCompatActivity {

    private List<Model> listOfModel = new ArrayList<>();
    Cursor c = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_model);

        DatabaseHelper myDbHelper = new DatabaseHelper(ListOfModel.this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
            throw sqle;
        }
        Toast.makeText(ListOfModel.this, "Successfully Imported", Toast.LENGTH_SHORT).show();
        c = myDbHelper.query("MODELS", null, null, null, null, null, null);
        if (c.moveToFirst()) {
            do {
                listOfModel.add(new Model(c.getString(1), c.getString(3)));
            } while (c.moveToNext());
        }


        ModelAdapter adapter = new ModelAdapter(this, listOfModel);
        ListView listView = new ListView(this);
        listView.setAdapter(adapter);
        this.setContentView(listView);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedName = adapter.getItem(position).name;
                Cursor mCursor = myDbHelper.rawQuery("select URL from MODELS where NAZWA = ? ", new String[]{selectedName});
                mCursor.moveToFirst();
                Intent sceneViewerIntent = new Intent(Intent.ACTION_VIEW);
                sceneViewerIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                sceneViewerIntent.setFlags(Intent.FLAG_GRANT_PERSISTABLE_URI_PERMISSION);
                sceneViewerIntent.setData(Uri.parse(mCursor.getString(0)));
                sceneViewerIntent.setPackage("com.google.android.googlequicksearchbox");
                startActivity(sceneViewerIntent);

            }
        });
    }
}