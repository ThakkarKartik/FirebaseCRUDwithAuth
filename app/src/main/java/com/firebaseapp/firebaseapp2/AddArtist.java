package com.firebaseapp.firebaseapp2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddArtist extends AppCompatActivity {
    Button btnSave;
    EditText txtName;
    EditText txtGenre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);
        btnSave = findViewById(R.id.btnSave);
        DatabaseReference dr = FirebaseDatabase.getInstance().getReference();
        txtName = findViewById(R.id.txtName);
        txtGenre = findViewById(R.id.txtGenre);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ArtistID = dr.push().getKey();
                Artist artist = new Artist(ArtistID,txtName.getText().toString(),txtGenre.getText().toString());
                dr.child("Artist").child(ArtistID).setValue(artist);
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

    }
}