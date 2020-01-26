package com.example.berkan.stoktakipuygulamasi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class register extends AppCompatActivity
{
    Button b1, b2;
    LocationManager locationmanager;
    EditText et1, et2, et3, et4;
    databasehelper db;
    String item="start";
    int count=0;
    FirebaseDatabase database;
    FirebaseAuth fauth;
    DatabaseReference reference;
    TextView tv;
    private static int RESULT_LOAD_IMAGE = 1;
    ImageView img;
    Uri imageuri,bitmapUri;
    Uri urionline=null;
    private StorageReference mstorage;
    private static final int gallery=2;
    Bitmap bitmap;
    String j;
    Spinner sp;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        et1 = (EditText) findViewById(R.id.editText3);
        et2 = (EditText) findViewById(R.id.editText4);
        et3 = (EditText) findViewById(R.id.editText5);

        mstorage= FirebaseStorage.getInstance().getReference();


        b1 = (Button) findViewById(R.id.button4);

        img=(ImageView)findViewById(R.id.imageView2);
        img.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {


                Intent inte=new Intent(Intent.ACTION_PICK);
                inte.setType("image/*");

                startActivityForResult(inte,gallery);
            }
        });

        database = FirebaseDatabase.getInstance();
        sp=(Spinner)findViewById(R.id.spinner);
        fauth = FirebaseAuth.getInstance();
        reference = database.getReference();

        Spinner spinner = (Spinner)findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                item = parent.getItemAtPosition(position).toString();
                if(count==0)
                {
                    count++;
                    return;
                }
                Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        final String[] items = new String[]{"Cinsiyetiniz :","Erkek","Kadın"};
        ArrayAdapter<String> spinneradapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(spinneradapter);

        b1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(item.equals("Cinsiyetiniz :"))
                {
                    Toast.makeText(register.this, "Please select a valid category", Toast.LENGTH_LONG).show();
                    return;
                }
                Task<AuthResult> result = fauth.createUserWithEmailAndPassword(et1.getText().toString(), et2.getText().toString());
                result.addOnCompleteListener(new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //Log.e("User Name","");
                        if (task.isSuccessful())
                        {
                            String N = et1.getText().toString();
                            String P = et2.getText().toString();
                            int A = Integer.parseInt(et3.getText().toString());
                            Toast.makeText(getApplicationContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                            String q = "active";
                            add a = new add(N, P, A, item, q);
                            String sp = task.getResult().getUser().getUid();
                            reference.child(sp).setValue(a);

                            Intent h = new Intent(register.this, MainActivity.class);
                            startActivity(h);
                            Toast.makeText(register.this, "Kaydınız Başarıyla Gerçekleşti ", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==gallery && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            StorageReference filepath=mstorage.child("photos").child(uri.getLastPathSegment());
            filepath.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>()
            {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot)
                {
                    Toast.makeText(register.this,"Upload done",Toast.LENGTH_SHORT).show();

                    //     Uri download=taskSnapshot.getDownloadUrl();
                    //     Picasso.with(register.this).load(download).fit().centerCrop().into(img);

                }
            });


        }
    }
}