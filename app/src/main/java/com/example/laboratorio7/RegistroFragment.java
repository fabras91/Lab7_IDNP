package com.example.laboratorio7;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.FileOutputStream;
import java.io.PrintWriter;

public class RegistroFragment extends Fragment {
    private EditText editTextAuthor, editTextTitle, editTextTechnique, editTextCategory, editTextDescription, editTextYear;
    private Button buttonSave;


    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        editTextAuthor = view.findViewById(R.id.editTextAuthor);
        editTextTitle = view.findViewById(R.id.editTextTitle);
        editTextTechnique = view.findViewById(R.id.editTextTechnique);
        editTextCategory = view.findViewById(R.id.editTextCategory);
        editTextDescription = view.findViewById(R.id.editTextDescription);
        editTextYear = view.findViewById(R.id.editTextYear);
        buttonSave = view.findViewById(R.id.buttonSave);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String author = editTextAuthor.getText().toString();
                String title = editTextTitle.getText().toString();
                String technique = editTextTechnique.getText().toString();
                String category = editTextCategory.getText().toString();
                String description = editTextDescription.getText().toString();
                String year = editTextYear.getText().toString();

                saveToFile(author, title, technique, category, description, year);
                showVisualizacionFragment();//Añadimos la llamada aquí
            }
        });

        return view;
    }

    private void saveToFile(String author, String title, String technique, String category, String description, String year) {
        try {
            FileOutputStream fos = getActivity().openFileOutput("pintura.txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(author);
            writer.println(title);
            writer.println(technique);
            writer.println(category);
            writer.println(description);
            writer.println(year);
            writer.close();
            fos.close();
            Toast.makeText(getActivity(), "Datos guardados", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(getActivity(), "Error al guardar datos", Toast.LENGTH_SHORT).show();
        }
    }
    private void showVisualizacionFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new VisualizacionFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
