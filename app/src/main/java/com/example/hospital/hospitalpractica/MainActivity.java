package com.example.hospital.hospitalpractica;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView textTargetUri;
    ImageView targetImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageButton buttonLoadImage = (ImageButton) findViewById(R.id.Fotopaciente);
        textTargetUri = (TextView) findViewById(R.id.targeturi);
        targetImage = (ImageView) findViewById(R.id.foto);

        Spinner spinner = (Spinner)findViewById(R.id.doctor_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.doctors_array, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        buttonLoadImage.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            Uri targetUri = data.getData();
            textTargetUri.setText(targetUri.toString());
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                targetImage.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this, adapterView.getItemAtPosition(i).toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(this, "selection disappears", Toast.LENGTH_SHORT).show();
    }

    @Override
   public void onPointerCaptureChangued(boolean hasCapture){

   }

    public void toastEvent(View v)
    {
        EditText uno = (EditText)findViewById(R.id.TE1);
        EditText dos = (EditText)findViewById(R.id.TE2);
        EditText tres = (EditText)findViewById(R.id.TE3);
        EditText cuatro = (EditText)findViewById(R.id.TE4);
        EditText cinco = (EditText)findViewById(R.id.TE5);
        EditText seis = (EditText)findViewById(R.id.TE6);
        EditText siete = (EditText)findViewById(R.id.TE7);
        EditText ocho = (EditText)findViewById(R.id.TE8);
        Spinner spinner = (Spinner) findViewById(R.id.doctor_spinner);
        Toast.makeText(MainActivity.this, "Nombre del paciente: " + uno.getText() + " \nEdad del paciente: " + dos.getText() + " \nCorreo del paciente: " + tres.getText() +
                        " \nTelefono del paciente: " + cuatro.getText() + " \nDirección del paciente: " + cinco.getText() + " \nPadecimiento del paciente: " + seis.getText() +
                        " \nFecha de cita: " + siete.getText() + " \nHora de cita: " + ocho.getText() + " \nDoctor que te atenderá: " + spinner.getSelectedItem().toString()
                ,Toast.LENGTH_LONG).show();

    }
}
