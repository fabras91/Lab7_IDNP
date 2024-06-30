package com.example.laboratorio7;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Objects;

public class RegistroFragment extends Fragment {
    private EditText eTxtAuthor, eTxtTitle, eTxtTechnique, eTxtCategory, eTxtDescription, eTxtYear;
    private Button saveB;



    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registro, container, false);

        eTxtAuthor = view.findViewById(R.id.editTextAuthor);
        eTxtTitle = view.findViewById(R.id.editTextTitle);
        eTxtTechnique = view.findViewById(R.id.editTextTechnique);
        eTxtCategory = view.findViewById(R.id.editTextCategory);
        eTxtDescription = view.findViewById(R.id.editTextDescription);
        eTxtYear = view.findViewById(R.id.editTextYear);
        saveB = view.findViewById(R.id.buttonSave);

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View vista) {

                String author = eTxtAuthor.getText().toString();
                String title = eTxtTitle.getText().toString();
                String technique = eTxtTechnique.getText().toString();
                String category = eTxtCategory.getText().toString();
                String description = eTxtDescription.getText().toString();
                String year = eTxtYear.getText().toString();

                saveToFile(author, title, technique, category, description, year);

                showVisualizacionFragment();
            }
        });

        return view;
    }

    private void saveToFile(String author, String title, String technique, String category, String description, String year) {
        try {
            FileOutputStream fos = getActivity().openFileOutput("paint.txt", Context.MODE_PRIVATE);
            PrintWriter writer = new PrintWriter(fos);
            writer.println(author);
            writer.println(title);
            writer.println(technique);
            writer.println(category);
            writer.println(description);
            writer.println(year);
            writer.close();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void showVisualizacionFragment() {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container, new VisualizacionFragment());
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
