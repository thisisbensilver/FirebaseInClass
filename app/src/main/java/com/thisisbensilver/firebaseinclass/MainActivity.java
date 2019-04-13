package com.thisisbensilver.firebaseinclass;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase myDatabase;
    DatabaseReference myRef;
    DatabaseReference myRefChild;
    EditText etxt;
    String myKey;
    String myVal;
    String dbTable = "FirebaseHW";
    //delete?
    EditText keyField;
    EditText valueField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myDatabase = FirebaseDatabase.getInstance();
        //Setting a parent so I can keep multiple tables in one DB.
        myRef = myDatabase.getReference(dbTable);
        // myRefChild = myRef.child(dbTable);


    }

    public void goRead(View view) {

        update();
        myRefChild = myRef.child(myKey);

        myRefChild.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //  Log.i(TAG, dataSnapshot.getValue(String.class);
                //String a = dataSnapshot.getValue(String.class);
                if (dataSnapshot.exists()) {
                    etxt.setText(dataSnapshot.getValue(String.class));
                } else {
                    etxt.setText(null);
                    Toast.makeText(getBaseContext(), "NO CAN DO CHIEF", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getBaseContext(), "Error loading firebase", Toast.LENGTH_SHORT).show();
                etxt.setText(null);
            }
        });


    }

    public void goWrite(View view) {
        update();
        myRef.child(myKey).setValue(myVal);
    }

    public void update() {
        etxt = findViewById(R.id.etxt_Key);
        myKey = etxt.getText().toString();
        etxt = findViewById(R.id.etxt_Value);
        myVal = etxt.getText().toString();

        //Toast.makeText(this, myKey + " " + myVal, Toast.LENGTH_SHORT).show();

    }


}
