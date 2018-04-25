package com.vit.demoreadcontact;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

        initView();
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
