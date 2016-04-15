package com.mbds.nfc.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.mbds.nfc.R;
import com.mbds.nfc.helper.CustomPreferenceManager;
import com.mbds.nfc.model.Utilisateur;

import butterknife.Bind;
import butterknife.OnClick;


public class HomeFragment extends Fragment {

    @Bind(R.id.scan)
    public Button scanButton;

    private OnFragmentInteractionListener mListener;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Initialiser l'adaptateur NFC
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_accueil, container, false);
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    @OnClick(R.id.scan)
    public void onClickScanButton(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        Utilisateur utilisateur = CustomPreferenceManager.getUtilisateur(prefs);

        Intent nfcReader = new Intent(getActivity().getBaseContext(), NFCActivity.class);
        startActivity(nfcReader);
    }
}
