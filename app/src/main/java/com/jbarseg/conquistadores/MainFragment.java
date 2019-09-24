package com.jbarseg.conquistadores;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

public class MainFragment extends Fragment {

    private ImageView imageView1, imageView2, imageView3;
    FirebaseFirestore mFirestoreDB = FirebaseFirestore.getInstance();

    public MainFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        imageView1 = (ImageView) view.findViewById(R.id.imageView1);
        imageView2 = (ImageView) view.findViewById(R.id.imageView2);
        imageView3 = (ImageView) view.findViewById(R.id.imageView3);

        update();

        return view;
    }

    public void update() {
        DocumentReference reference = mFirestoreDB.collection("FILES").document("images");
        reference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot doc = task.getResult();

                    StringBuilder img1 = new StringBuilder("");
                    img1.append(doc.get("image1"));
                    String image1url = img1.toString();
                    Picasso.get().load(image1url).into(imageView1);

                    StringBuilder img2 = new StringBuilder("");
                    img2.append(doc.get("image2"));
                    String image2url = img2.toString();
                    Picasso.get().load(image2url).into(imageView2);

                    StringBuilder img3 = new StringBuilder("");
                    img3.append(doc.get("image3"));
                    String image3url = img3.toString();
                    Picasso.get().load(image3url).into(imageView3);
                }
            }
        });
    }
}
