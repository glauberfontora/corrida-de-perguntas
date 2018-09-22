package br.unibh.corridadeperguntas;

import android.annotation.TargetApi;
import android.nfc.Tag;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = null;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference perguntas, respostas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        perguntas = database.getReference("Pergunta");
        respostas = database.getReference("Resposta");

        perguntas.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot perguntaSnapshot) {
                String value = perguntaSnapshot.getValue(String.class);
                Log.d(TAG, value.toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        respostas.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot respostaSnapshot) {
                Iterable<DataSnapshot> respostaChildren = respostaSnapshot.getChildren();
                for (DataSnapshot resposta : respostaChildren) {
                    Object tituloResposta = resposta.getKey();
                    Object respostaCorreta = resposta.getValue();
                    Log.d(TAG, tituloResposta.toString());
                    Log.d(TAG, respostaCorreta.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        setContentView(R.layout.activity_main);
    }
}
