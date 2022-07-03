package com.ganeshjitendra.passwordmanager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.ganeshjitendra.passwordmanager.data.DataBaseHandler;
import com.ganeshjitendra.passwordmanager.model.EncDec;
import com.ganeshjitendra.passwordmanager.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    public Button saveButton;
    public static EditText titleName;
    public static EditText keyName;
    public static EditText passName;
    public static DataBaseHandler dataBaseHandler;
    private List<Item> itemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dataBaseHandler = new DataBaseHandler(this);
        itemList = new ArrayList<>();

        itemList = dataBaseHandler.getAllItems();
        passByActivity();

        //List<Item> items = dataBaseHandler.getAllItems();
        //for (Item item : items){
        //   Log.d("Main", "onCreate: "+ dataBaseHandler.getItemsCount());
        //}

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createPopupDialog();
            }
        });
    }

    private void passByActivity() {
        if(dataBaseHandler.getItemsCount()>0) {
            startActivity(new Intent(MainActivity.this, ListActivity.class));
            finish();
        }

    }

    private void createPopupDialog() {
        builder = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.popup,null);

        titleName = view.findViewById(R.id.title_name);
        keyName = view.findViewById(R.id.key_name);
        passName = view.findViewById(R.id.pass_name);
        saveButton = view.findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!titleName.getText().toString().isEmpty() &&
                        !passName.getText().toString().isEmpty() &&
                        !keyName.getText().toString().isEmpty()) {
                    saveItem(v);
                }else {
                    Snackbar.make(v,"Empty Fields not Allowed",Snackbar.LENGTH_SHORT).show();
                }
            }
        });

        builder.setView(view);
        dialog = builder.create();
        dialog.show();
    }

    private void saveItem(View view) {
        EncDec.enc();
        Snackbar.make(view,"Password Saved",Snackbar.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.dismiss();
                Intent intent = new Intent(MainActivity.this, ListActivity.class);
                startActivity(intent);
                finish();
            }
        },1200);
    }

}
