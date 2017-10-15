package br.com.senai.sistemaindividualdemonitoramento;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
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
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.senai.sistemaindividualdemonitoramento.model.Employer;
import br.com.senai.sistemaindividualdemonitoramento.model.ServiceOrder;
import br.com.senai.sistemaindividualdemonitoramento.model.model.dao.ServiceOrderDAO;

public class EncarregadoActivity extends AppCompatActivity {

    public static final int CAMERA_PHOTO_CODE = 567;
    public static final int CAMERA_VIDEO_CODE = 890;
    private String photoPath;
    private String videoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encarregado);

        Intent intent = getIntent();
        Employer employer = (Employer) intent.getSerializableExtra("employer");
        final ServiceOrder os = (ServiceOrder) intent.getSerializableExtra("os");

        InitSidebar.fillSidebar(this, employer, os);

        String[] defaultSpnText = {"Dobra", "Cola", "Outra coisa sei la", "Não sou da grafica kkkjj"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, defaultSpnText);

        Spinner spn = (Spinner) findViewById(R.id.encarregado_sp_activity);
        spn.setAdapter(adapter);

        if(os.getId() != null){
            EditText fieldOs = (EditText) findViewById(R.id.encarregado_txt_os);
            EditText fieldGoal = (EditText) findViewById(R.id.encarregado_txt_meta);


            fieldOs.setText(String.valueOf(os.getId()));
            spn.setSelection(adapter.getPosition(os.getNome()));
            fieldGoal.setText(String.valueOf(os.getMetaPorHora()));
            videoPath = os.getVideoInstrucao();
            photoPath = os.getFotoInstrucao();
        }

        Button btnSalvar = (Button) findViewById(R.id.encarregado_button_salvar);
        btnSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (os.getId() == null) {
                    criaOs();
                }else{
                    EditText fieldOs = (EditText) findViewById(R.id.encarregado_txt_os);
                    Spinner spnActivity = (Spinner) findViewById(R.id.encarregado_sp_activity);
                    EditText fieldGoal = (EditText) findViewById(R.id.encarregado_txt_meta);

                    Long osNumber = Long.parseLong(fieldOs.getText().toString());
                    int goal = Integer.parseInt(fieldGoal.getText().toString());
                    String activity = (String) spnActivity.getSelectedItem();

                    if(osNumber.equals(os.getId())){
                        ServiceOrderDAO dao = new ServiceOrderDAO(EncarregadoActivity.this);
                        os.setFotoInstrucao(photoPath);
                        os.setVideoInstrucao(videoPath);
                        os.setNome(activity);
                        os.setMetaPorHora(goal);

                        dao.update(os);
                        Toast.makeText(EncarregadoActivity.this, "Salvo!", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        criaOs();
                    }
                }
            }

            private void criaOs() {
                EditText fieldOs = (EditText) findViewById(R.id.encarregado_txt_os);
                Spinner spnActivity = (Spinner) findViewById(R.id.encarregado_sp_activity);
                EditText fieldGoal = (EditText) findViewById(R.id.encarregado_txt_meta);

                Long osNumber = Long.parseLong(fieldOs.getText().toString());
                int goal = Integer.parseInt(fieldGoal.getText().toString());
                String activity = (String) spnActivity.getSelectedItem();

                ServiceOrder osToAdd = new ServiceOrder();
                osToAdd.setId(osNumber);
                osToAdd.setFotoInstrucao(photoPath);
                osToAdd.setVideoInstrucao(videoPath);
                osToAdd.setMetaPorHora(goal);
                osToAdd.setNome(activity);

                insertServiceOrder(osToAdd);

            }
        });

        Button btnPhoto = (Button) findViewById(R.id.encarregado_button_photo);
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkPermission()){
                    getPermission();
                }
                try {
                    dispatchTakePictureIntent();
                }catch (IOException ex){
                    ex.printStackTrace();
                }

            }
        });

        Button btnVideo = (Button) findViewById(R.id.encarregado_button_video);
        btnVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkPermission()){
                    getPermission();
                }
                try {
                    dispatchVideoIntent();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void insertServiceOrder(ServiceOrder os){
        ServiceOrderDAO dao = new ServiceOrderDAO(this);
        if(dao.create(os) < 0){
            Toast.makeText(this, "Ordem de serviço Criada com sucesso!", Toast.LENGTH_SHORT).show();

            finish();
        }else{
            Toast.makeText(this, "Ocorreu um erro ao criar ordem de serviço", Toast.LENGTH_SHORT).show();
        }
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
        System.out.println(image.getAbsolutePath());
        photoPath = image.getAbsolutePath();
        return image;
    }


    private File createVideoFile() throws IOException {
        // Create an video file name

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String videoFileName = "MP4_" + timeStamp + "_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_DCIM), "Camera");
        File video = File.createTempFile(
                videoFileName,  /* prefix */
                ".mp4",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        System.out.println(video.getAbsolutePath());
        videoPath = video.getAbsolutePath();
        return video;
    }

    private void dispatchTakePictureIntent() throws IOException{
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if(takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            }catch (IOException ex){
                return;
            }

            if(photoFile != null){
                Uri photoUri = Uri.fromFile(createImageFile());
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                startActivityForResult(takePictureIntent, CAMERA_PHOTO_CODE);
            }
        }
    }

    private void dispatchVideoIntent() throws IOException{
        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

        if(takeVideoIntent.resolveActivity(getPackageManager()) != null) {
            File videoFile = null;

            try {
                videoFile = createVideoFile();
            }catch (IOException ex){
                return;
            }

            if(videoFile != null){
                Uri videoURI = Uri.fromFile(createVideoFile());
                takeVideoIntent.putExtra(MediaStore.EXTRA_OUTPUT, videoURI);
                startActivityForResult(takeVideoIntent, CAMERA_VIDEO_CODE);
            }
        }
    }

}
