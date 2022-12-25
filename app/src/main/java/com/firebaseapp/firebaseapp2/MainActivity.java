package com.firebaseapp.firebaseapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Artist> artists = new ArrayList<>();
    ListView lstArtist;
    Button btnNew;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String TAG = "KTTAG";
        if(mAuth.getCurrentUser() == null)
        {
            Intent intent = new Intent(MainActivity.this, UserRegister.class);
            startActivity(intent);
        }
        else
        {
            Log.e(TAG, "onCreate: "+mAuth.getCurrentUser().getEmail() );
        }

        // Data Add
        DatabaseReference DBRef = FirebaseDatabase.getInstance().getReference();
        DBRef = FirebaseDatabase.getInstance().getReference("Artist");
        artists.clear();
        DBRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot Snap : snapshot.getChildren())
                {
                    Artist artist = Snap.getValue(Artist.class);
                    String ID = Snap.getKey();
                    artists.add(artist);
                    Log.e("MyTag","Key - " + ID + " Artist - " + artist.getName());
                    lstArtist = findViewById(R.id.lstArtist);
                    ArtistAdapter adapter = new ArtistAdapter(artists, getApplicationContext());
                    lstArtist.setAdapter(adapter);
                    lstArtist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(getApplicationContext(),ArtistDetails.class);
                            intent.putExtra("ArtistID",artists.get(i).getArtistID());
                            startActivity(intent);
                        }
                    });
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnNew = findViewById(R.id.btnAddNew);

        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AddArtist.class);
                startActivity(intent);
            }
        });

    }
}