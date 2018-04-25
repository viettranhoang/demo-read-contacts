package com.vit.demoreadcontact;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.MediaStore;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContactManager {

    private Context mContext;

    private List<Contact> mListContact;

    public ContactManager(Context mContext) {
        this.mContext = mContext;
        getContactData();
        Collections.sort(mListContact);
    }

    public List<Contact> getListContact() {
        return mListContact;
    }

    private void getContactData() {
        mListContact = new ArrayList<>();

        String[] projections = {
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Phone.PHOTO_URI
        };

        Cursor phones = mContext.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                projections, null, null, null);

        int nameIndex = phones.getColumnIndex(projections[0]);
        int numberIndex = phones.getColumnIndex(projections[1]);
        int photoIndex = phones.getColumnIndex(projections[2]);

        phones.moveToFirst();

        while (phones.moveToNext()) {
            String name = phones.getString(nameIndex);
            String number = phones.getString(numberIndex);
            String photoURI = phones.getString(photoIndex);
            Bitmap photo = getPhotoFromUri(photoURI);

            mListContact.add(new Contact(name, number, photo));
        }
        phones.close();

    }

    private Bitmap getPhotoFromUri(String photoURI) {
        if(photoURI != null) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(mContext.getContentResolver(), Uri.parse(photoURI));
                return bitmap;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
