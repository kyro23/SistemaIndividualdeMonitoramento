package br.com.senai.sistemaindividualdemonitoramento;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;

public class EncarregadoActivity extends AppCompatActivity {

    public static final int CAMERA_PHOTO_CODE = 567;
    public static final int CAMERA_VIDEO_CODE = 890;
    private String photoPath;
    private String videoPath;

    private EditText fieldOs;
    private Spinner spnActivity;
    private EditText fieldGoal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encarregado);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");

        InitSidebar.fillSidebar(this, employer);

        String[] defaultSpnText = {"Dobra", "Cola", "Outra coisa sei la", "Não sou da grafica kkkjj"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.encarregado_sp_activity);
        spn.setAdapter(adapter);

        Button btnSalvar = (Button) findViewById(R.id.encarregado_button_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int osNumber = Integer.parseInt(fieldOs.getText().toString());
                int goal = Integer.parseInt(fieldGoal.getText().toString());
                String activity = (String) spnActivity.getSelectedItem();

                ServiceOrder os = new ServiceOrder();
                os.setId(osNumber);
                os.setFotoInstrucao(photoPath);
                os.setVideoInstrucao(videoPath);

                finish();
            }
        });

        Button btnPhoto = (Button) findViewById(R.id.encarregado_button_photo);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkPermission()){
                    getPermission();
                }

                Intent intentCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri photoURI = null;
                try {
                    photoURI = FileProvider.getUriForFile(EncarregadoActivity.this,
                            BuildConfig.APPLICATION_ID + ".provider", createImageFile());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(intentCamera, CAMERA_PHOTO_CODE);
            }
        });

        Button btnVideo = (Button) findViewById(R.id.encarregado_button_video);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkPermission()){
                    getPermission();
                    takePhoto();
                }else {
                    takePhoto();
                }
            }
        });
    }

    private void takePhoto() {
        Intent intentCamera = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        videoPath = getExternalFilesDir(null)+"/"+System.currentTimeMillis()+".mp4";
        File fileVideo = new File(videoPath);
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileVideo));
        startActivityForResult(intentCamera, CAMERA_VIDEO_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){
            if(requestCode == CAMERA_PHOTO_CODE){
                Toast.makeText(this, photoPath, Toast.LENGTH_SHORT).show();
            }else if(requestCode == CAMERA_VIDEO_CODE){
                Toast.makeText(this, videoPath, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkPermission(){
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED;
    }

    private void getPermission(){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 0);
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        photoPath = "file:" + image.getAbsolutePath();
        System.out.println(photoPath);
        return image;
    }
}
