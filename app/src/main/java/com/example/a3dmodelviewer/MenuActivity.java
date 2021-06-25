package com.example.a3dmodelviewer;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.Locale;

public class MenuActivity extends ListActivity {

    private enum Action {
        LOAD_MODEL, EXIT, UNKNOWN
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.activity_menu_item,
                getResources().getStringArray(R.array.menu_items));
        setListAdapter(adapter);
        getListView().setOnItemClickListener(listener);
    }

    public AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            String selectedItem = (String) getListView().getItemAtPosition(position);
            String selectedAction = selectedItem.replace(' ', '_').toUpperCase(Locale.getDefault());
            Action action = Action.UNKNOWN;
            try {
                action = Action.valueOf(selectedAction);
            } catch (IllegalArgumentException ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
            try {
                switch (action) {
                    case LOAD_MODEL:
                        loadModel();
                        break;
                    case EXIT:
                        MenuActivity.this.finish();
                        break;
                    case UNKNOWN:
                        Toast.makeText(getApplicationContext(), "Unrecognized action '" + selectedAction + "'",
                                Toast.LENGTH_LONG).show();
                        break;
                }
            } catch (Exception ex) {
                Toast.makeText(getApplicationContext(), ex.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    };

    private void loadModel() {
        Intent intent = new Intent(MenuActivity.this, ListOfModel.class);
        startActivity(intent);
    }
}
