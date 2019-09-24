package com.jbarseg.conquistadores.com.jbarseg.conquistadores.com.jbarseg.conquistadores.verses;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jbarseg.conquistadores.R;

public class VersesFragment extends Fragment {

    public VersesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_verses, container, false);
    }
}
