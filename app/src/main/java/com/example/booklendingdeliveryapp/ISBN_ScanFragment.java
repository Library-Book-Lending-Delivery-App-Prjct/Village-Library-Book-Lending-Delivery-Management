package com.example.booklendingdeliveryapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

<<<<<<< Updated upstream
import androidx.fragment.app.Fragment;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ISBN_ScanFragment extends Fragment implements ZXingScannerView.ResultHandler{

=======
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ISBN_ScanFragment extends Fragment implements ZXingScannerView.ResultHandler{

>>>>>>> Stashed changes
    private ZXingScannerView scannerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_i_s_b_n__scan, container, false);

        scannerView = view.findViewById(R.id.scannerView);
        scannerView.setAspectTolerance(0.5f);
        scannerView.setResultHandler(ISBN_ScanFragment.this);
        scannerView.startCamera();

        return view;
<<<<<<< Updated upstream
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(),rawResult.getText(),Toast.LENGTH_LONG).show();
    }

=======
    }

    @Override
    public void handleResult(Result rawResult) {
        Toast.makeText(getActivity(),rawResult.getText(),Toast.LENGTH_LONG).show();
    }

>>>>>>> Stashed changes
}