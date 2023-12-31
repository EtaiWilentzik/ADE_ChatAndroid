package com.example.ADE_Chat.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ADE_Chat.MyApplication;
import com.example.ADE_Chat.R;
import com.example.ADE_Chat.entities.Contact;
import com.example.ADE_Chat.entities.User;

import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {

    LayoutInflater inflater;

    public ContactAdapter(Context ctx, List<Contact> userArrayList) {
        super(ctx, R.layout.contact_list_item, userArrayList);
        this.inflater = LayoutInflater.from(ctx);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Contact contact = getItem(position);

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        ImageView imageView = convertView.findViewById(R.id.profile_image);
        TextView userName = convertView.findViewById(R.id.user_name);
        TextView lastMsg = convertView.findViewById(R.id.last_massage);
        TextView time = convertView.findViewById(R.id.time);

        User user = MyApplication.userDao.get(contact.getName());
        if (user != null && user.getImage() != null) {


        
        }
        else {
            /*imageView.setImageResource(R.drawable.icon_user_default);*/
        }

        String base64Data = contact.getProfilePic().substring(contact.getProfilePic().indexOf(",") + 1);

// Decode the Base64 encoded image data into a byte array
        byte[] decodedBytes = Base64.decode(base64Data, Base64.DEFAULT);

// Create a Bitmap from the decoded byte array
        Bitmap bitmap = BitmapFactory.decodeByteArray(decodedBytes, 0, decodedBytes.length);

// Set the bitmap on the ImageView
        imageView.setImageBitmap(bitmap);


        userName.setText(contact.getNickname());
        lastMsg.setText(contact.getLast());
        time.setText(contact.getLastdate());

        return convertView;
    }
}
