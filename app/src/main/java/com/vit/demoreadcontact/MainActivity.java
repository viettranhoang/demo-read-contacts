package com.vit.demoreadcontact;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView mLvContact;
    private ContactAdapter mContactAdapter;
    private List<Contact> mListContact;

    private ContactManager mContactManager;

    private final int RECORD_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestPermissions();


//        initView();

        setPhotoContact();
    }

    private void setPhotoContact() { // add , update contact using intent
        byte[] byteArrayImage = convertImageToByte(R.mipmap.ic_contact);

        ArrayList<ContentValues> data = new ArrayList<>();

        ContentValues row = new ContentValues();
        row.put(ContactsContract.Contacts.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE);
        row.put(ContactsContract.CommonDataKinds.Photo.PHOTO, byteArrayImage);
        data.add(row);

        Intent intent = new Intent(Intent.ACTION_INSERT_OR_EDIT, ContactsContract.Contacts.CONTENT_URI);
        intent.setType(ContactsContract.Contacts.CONTENT_ITEM_TYPE);
        intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, data);
        startActivity(intent);
    }

    private byte[] convertImageToByte(int imageResource) {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), imageResource); // your image

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();

        return byteArray;
    }

    private void requestPermissions() {
        int permission_write_contacts = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_CONTACTS);
        if (permission_write_contacts != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{
                    Manifest.permission.WRITE_CONTACTS}, RECORD_REQUEST_CODE);
        }
    }

    private void initView() {
        mContactManager = new ContactManager(this);
        mListContact = mContactManager.getListContact();


        mLvContact = findViewById(R.id.list_contact);
        mContactAdapter = new ContactAdapter(this, mListContact);
        mLvContact.setAdapter(mContactAdapter);

    }




}
