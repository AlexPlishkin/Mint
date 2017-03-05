package com.plishkin.alex.mint.Tasks;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.RequiresApi;

import com.plishkin.alex.mint.Entities.Contact;

import java.util.ArrayList;
import java.util.List;


public class LoadContactsTask extends AsyncTask<Void, Integer, List<Contact>> {

    private volatile Context context = null;
    private ProgressDialog dialog;
    private AsyncResponseble delegate;
    private LoadContactsTask.ContactsTaskData data = null;

    public LoadContactsTask(Context context, AsyncResponseble responseble, LoadContactsTask.ContactsTaskData data) {
        this.context = context;
        this.delegate = responseble;
        this.data = data;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dialog = ProgressDialog.show(context, "Loading", "Please wait...", true);
    }

    @Override
    protected List<Contact> doInBackground(Void... params) {
        List<Contact> tmp = new ArrayList<>();

        Cursor contactCursor = null;

        contactCursor = data.getResolver().query(ContactsContract.Contacts.CONTENT_URI, null, null,null, null);

        if (contactCursor != null && contactCursor.getCount() > 0) {
            while (contactCursor.moveToNext()){
                Contact contact = new Contact();

                String contact_id = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts._ID));
                String contact_name = contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                int has_phone = Integer.parseInt(contactCursor.getString(contactCursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER))) ;

                if (has_phone > 0){
                    Cursor phoneCursor =  data.getResolver().query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{contact_id},
                            null);

                    while (phoneCursor.moveToNext()){
                        String phone_number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                        contact.setPhoneNumber(phone_number);
                    }

                    phoneCursor.close();
                }

                contact.setName(contact_name);
                tmp.add(contact);
            }

            contactCursor.close();
        }

        return tmp;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(List<Contact> contacts) {
        super.onPostExecute(contacts);
        dialog.dismiss();
        delegate.getResponse(contacts);
    }

    public static class ContactsTaskData{
        private ContentResolver resolver;

        public ContactsTaskData(ContentResolver resolver) {
            this.resolver = resolver;
        }

        public ContentResolver getResolver() {
            return resolver;
        }
    }
}
