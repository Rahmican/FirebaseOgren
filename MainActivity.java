package com.rahmican.firebaseogren;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText etMesaj;
    Button btnGonder, btnGoster;
    String a ;
    FirebaseDatabase database;
    DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etMesaj = (EditText) findViewById(R.id.etMesaj);
        btnGonder = (Button) findViewById(R.id.btnGonder);
        btnGoster = (Button) findViewById(R.id.btnGoster);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference dbref = database.getReference("Mesajlar");
        btnGonder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a = dbref.push().getKey();
                dbref.child("mesaj").child(a).setValue(etMesaj.getText().toString());
                etMesaj.setText("");
            }
        });
        btnGoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        String value = (String) dataSnapshot.child("-KgUOkXgFA6CawrlDkiH").getValue();
                        Log.d("Gelen değer", "Value is: " + value);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });


    }
}
