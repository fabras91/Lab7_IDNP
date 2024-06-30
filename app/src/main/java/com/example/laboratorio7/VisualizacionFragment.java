package com.example.laboratorio7;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class VisualizacionFragment extends Fragment {
    private TextView textViewAuthor, textViewTitle, textViewTechnique, textViewCategory, textViewDescription, textViewYear;

     public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visualizacion, container, false);

        textViewAuthor = view.findViewById(R.id.textViewAuthor);
        textViewTitle = view.findViewById(R.id.textViewTitle);
        textViewTechnique = view.findViewById(R.id.textViewTechnique);
        textViewCategory = view.findViewById(R.id.textViewCategory);
        textViewDescription = view.findViewById(R.id.textViewDescription);
        textViewYear = view.findViewById(R.id.textViewYear);

        loadFromFile();

        return view;
    }

    private void loadFromFile() {
        try {
            FileInputStream fis = getActivity().openFileInput("paint.txt");
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            textViewAuthor.setText("El autor es " + reader.readLine());
            textViewTitle.setText("Título de la obra: " + reader.readLine());
            textViewTechnique.setText("Técnica " + reader.readLine());
            textViewCategory.setText("Categoría: " + reader.readLine());
            textViewDescription.setText("Descripción: " + reader.readLine());
            textViewYear.setText("Año: " + reader.readLine());
            reader.close();
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
