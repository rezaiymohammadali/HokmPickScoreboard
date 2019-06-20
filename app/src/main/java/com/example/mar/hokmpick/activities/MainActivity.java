package com.example.mar.hokmpick.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mar.hokmpick.R;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;

    AutoCompleteTextView edt_mem1;
    AutoCompleteTextView edt_mem2;
    AutoCompleteTextView edt_mem3;
    AutoCompleteTextView edt_mem4;
    AutoCompleteTextView edt_mem5;
    AutoCompleteTextView edt_mem6;

    Button btnRandom;

    String[] members = {"حمید", "محسن", "مهدی", "رضا", "سعید", "جعفر", "امین", "علی"};
    boolean[] checkedItems = new boolean[members.length];
    ArrayList<String> choosedMembers = new ArrayList<>();

    AlertDialog ad;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);

        initViews();

        onClickFuncs();

    }

    private void initViews() {

        edt_mem1 = findViewById(R.id.edt_member1);
        edt_mem2 = findViewById(R.id.edt_member2);
        edt_mem3 = findViewById(R.id.edt_member3);
        edt_mem4 = findViewById(R.id.edt_member4);
        edt_mem5 = findViewById(R.id.edt_member5);
        edt_mem6 = findViewById(R.id.edt_member6);

        btnRandom = findViewById(R.id.randombtn);

        edt_mem1.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));
        edt_mem2.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));
        edt_mem3.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));
        edt_mem4.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));
        edt_mem5.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));
        edt_mem6.setAdapter(new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, members));

    }

    private void onClickFuncs() {

        edt_mem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem1.showDropDown();
            }
        });
        edt_mem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem2.showDropDown();
            }
        });
        edt_mem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem3.showDropDown();
            }
        });
        edt_mem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem4.showDropDown();
            }
        });
        edt_mem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem5.showDropDown();
            }
        });
        edt_mem6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edt_mem6.showDropDown();
            }
        });


        btnRandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edt_mem1.setText("");
                edt_mem2.setText("");
                edt_mem3.setText("");
                edt_mem4.setText("");
                edt_mem5.setText("");
                edt_mem6.setText("");

                choosedMembers = new ArrayList<>();
                checkedItems = new boolean[members.length];

                createDialog();

            }
        });
    }

    private void createDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("اعضای مورد نظر را انتخاب نمایید");
        builder.setMultiChoiceItems(members, checkedItems, new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean b) {
//                Toast.makeText(MainActivity.this, members[i], Toast.LENGTH_SHORT).show();
                choosedMembers.add(members[i]);
            }
        });

        builder.setPositiveButton("تایید", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (choosedMembers.size() >= 6)
                    getRandomString(choosedMembers, 0);
            }
        });
        ad = builder.create();
        ad.show();
    }

    private void getRandomString(ArrayList<String> arrayList, int i) {
        i++;
        int r = (int) (Math.random() * arrayList.size());
        String name = arrayList.get(r);
        switch (i) {
            case 1:
                edt_mem1.setText(name);
                break;
            case 2:
                edt_mem2.setText(name);
                break;
            case 3:
                edt_mem3.setText(name);
                break;
            case 4:
                edt_mem4.setText(name);
                break;
            case 5:
                edt_mem5.setText(name);
                break;
            case 6:
                edt_mem6.setText(name);
                break;
            default:
                break;
        }
        arrayList.remove(name);
        if (arrayList.size() > 0)
            getRandomString(arrayList, i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_next) {

            Intent intent = new Intent();
            intent.putExtra("member1", edt_mem1.getText().toString());
            intent.putExtra("member2", edt_mem2.getText().toString());
            intent.putExtra("member3", edt_mem3.getText().toString());
            intent.putExtra("member4", edt_mem4.getText().toString());
            intent.putExtra("member5", edt_mem5.getText().toString());
            intent.putExtra("member6", edt_mem6.getText().toString());
            intent.setClass(MainActivity.this, SecondActivity.class);
            startActivity(intent);

            return true;
        }else if (id == R.id.action_random) {

            edt_mem1.setText("");
            edt_mem2.setText("");
            edt_mem3.setText("");
            edt_mem4.setText("");
            edt_mem5.setText("");
            edt_mem6.setText("");

            choosedMembers = new ArrayList<>();
            checkedItems = new boolean[members.length];

            createDialog();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
