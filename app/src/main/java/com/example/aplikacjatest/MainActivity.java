package com.example.aplikacjatest;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Pytanie> pytaniaArraylista;
    private ImageView imageView;
    private Button buttonTak, buttonNie, buttonNastepny, buttonPodpowiedz;
    private TextView textViewPytanie;

    private int ktorePytanie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        wstawPytania();
        imageView = findViewById(R.id.imageView);
        textViewPytanie= findViewById(R.id.textView);
        buttonTak = findViewById(R.id.button3);
        buttonNie = findViewById(R.id.button4);
        buttonNastepny = findViewById(R.id.button);
        buttonPodpowiedz = findViewById(R.id.button2);
        wyswietlPytanie(0);

        buttonPodpowiedz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //intencja jawna, wiemy za pomoca czego ja wykonamy
                Intent intent = new Intent(MainActivity.this, PodpowiedzActivity.class);
                intent.putExtra("nrPytania", ktorePytanie);
                startActivity(intent);
            }
        });
        buttonNastepny.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ktorePytanie++;
                        if(ktorePytanie<pytaniaArraylista.size()) {
                            wyswietlPytanie(ktorePytanie);
                        }else {
                            buttonNastepny.setVisibility(INVISIBLE);
                            buttonNie.setVisibility(INVISIBLE);
                            buttonTak.setVisibility(INVISIBLE);
                            int punkty = 0;
                            for(Pytanie pytanie:pytaniaArraylista){
                                if (pytanie.isCzyPoprawnaOdpowiedz()){
                                    punkty++;
                                }
                            }
                            textViewPytanie.setText("Koniec testu otrzymano " + punkty + " punkty");
                        }
                    }
                }
        );
        buttonTak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ustawOdpowiedz(true);
            }
        });
        buttonNie.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ustawOdpowiedz(false);
            }
        }));
    }
    private void ustawOdpowiedz(boolean odp){
        if(odp == pytaniaArraylista.get(ktorePytanie).isOdpowiedz()){
            pytaniaArraylista.get(ktorePytanie).setCzyPoprawnaOdpowiedz(true);
        } else {
            pytaniaArraylista.get(ktorePytanie).setCzyPoprawnaOdpowiedz(false);
        }
    }
    private void wyswietlPytanie(int ktore){
        Pytanie pytanie = pytaniaArraylista.get(ktore);
        imageView.setImageResource(pytanie.getIdZdjecia());
        textViewPytanie.setText(pytanie.getTrescPytania());

    }
    private void wstawPytania(){
        pytaniaArraylista = new ArrayList<>();
        pytaniaArraylista.add(new Pytanie(R.drawable.ptak, "Czy ptaki to ssaki?", false));
        pytaniaArraylista.add(new Pytanie(R.drawable.jaszczur, "Czy jaszczurki zmieniają kolor skóry?", true));
        pytaniaArraylista.add(new Pytanie(R.drawable.sowa, "Czy to prawda, że sowa może obrócić głowę o 360 stopni?", false));
    }
}