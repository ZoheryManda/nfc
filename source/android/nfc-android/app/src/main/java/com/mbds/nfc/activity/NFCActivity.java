package com.mbds.nfc.activity;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mbds.nfc.R;
import com.mbds.nfc.app.AppConfig;
import com.mbds.nfc.app.AppController;
import com.mbds.nfc.helper.CustomPreferenceManager;
import com.mbds.nfc.model.Materiel;
import com.mbds.nfc.model.Utilisateur;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NFCActivity extends ActionBarActivity {
    public static final String TAG = "NFCReaderActivity";
    public static final int REQUEST_CODE = 1000;
    public final static String MESSAGE = "message";
    public final static String UIID = "uiid";

    // dtection de tag
    private NfcAdapter nfcAdapter = null;
    private PendingIntent mPendingIntent;
    private IntentFilter ndefDetected;

    private static String message = "";
    private static NdefMessage ndefMsg = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        nfcAdapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
    }

    private void login(final Utilisateur utilisateur, final String uiid) {

        JSONObject params = new JSONObject();
        try {
            params.put("idutilisateur", utilisateur.getIdutilisateur());
            params.put("nom", utilisateur.getNom());
            params.put("prenom", utilisateur.getPrenom());
            params.put("pwd", utilisateur.getPwd());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                AppConfig.BASE_URL + "/utilisateurs/login/",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(NFCActivity.this, "->uiid:" + uiid, Toast.LENGTH_LONG).show();
                        getMateriel(uiid);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NFCActivity.this, "login error -> " + error.networkResponse.statusCode, Toast.LENGTH_LONG).show();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(objectRequest);
    }

    private void getMateriel(String uiid) {
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET,
                AppConfig.BASE_URL + "/materiels/uiid/" + uiid,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Materiel materiel = new Materiel();
                        try {
                            materiel.setIdmateriel(response.getInt("idmateriel"));
                            materiel.setIdtype(response.getInt("idtype"));
                            materiel.setLibelle(response.getString("libelle"));
                            materiel.setCodemateriel(response.getString("codemateriel"));
                            materiel.setUiid("uiid");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(NFCActivity.this, "Materiel " + materiel.getIdmateriel(), Toast.LENGTH_LONG).show();

                        enregistrerMvt(new Date(), materiel.getIdmateriel());

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NFCActivity.this, "Erreur lors de la recuperation du materiel.", Toast.LENGTH_LONG).show();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(jsObjRequest);
    }

    private void enregistrerMvt(final Date date, final int idMateriel) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NFCActivity.this);
        Utilisateur utilisateur = CustomPreferenceManager.getUtilisateur(prefs);

        SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy HH:mm");

        JSONObject params = new JSONObject();
        try {
            params.put("idmateriel", idMateriel);
            params.put("idutilisateur", utilisateur.getIdutilisateur());
            params.put("dateemprunt", formatter.format(date));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(NFCActivity.this, "JSONObject " + params.toString(), Toast.LENGTH_LONG).show();

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.POST,
                AppConfig.BASE_URL + "/mouvements/materiel/",
                params,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(NFCActivity.this, "Mouvement inserer", Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(NFCActivity.this, "Enregistrement du mouvoument a echoué.", Toast.LENGTH_LONG).show();
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(objectRequest);
    }


    // NFC
    public String readUiid(Intent intent) {
        // recuperer le uiid
        String uiid = "";
        try {
            Tag tag = NFCActivity.this.getIntent().getParcelableExtra(NfcAdapter.EXTRA_TAG);
            if (tag != null) {
                uiid = bin2hex(tag.getId());
            }

        } catch (Exception e) {
            Toast.makeText(NFCActivity.this, "Probleme lors de la lecture du TAG!...",
                    Toast.LENGTH_LONG).show();
        }

        // Raffichage dans l'activit TagActivity
        Intent main = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(MESSAGE, "");
        main.putExtras(bundle);
        main.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT);
        main.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(main);
        finish();

        return uiid;
    }

    // Parsing binary to string
    static String bin2hex(byte[] data) {
        return String.format("%0" + (data.length * 2) + "X", new BigInteger(1, data));
    }


    // Mthode invoque lors de la dtection d'un priphrique NFC
    // (Beam)
    @Override
    public void onNewIntent(Intent intent) {
        //Dtection d'un priphrique NFC (tag ou autre)
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action) ||
                NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
            String uiid = readUiid(intent);
            seLogger(uiid);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // - soit un tag NFC a t dtect !
        mPendingIntent = PendingIntent.getActivity(NFCActivity.this, 0, new Intent(NFCActivity.this,
                getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        // Intent filters
        ndefDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        Intent intent = NFCActivity.this.getIntent();
        //Lecture/Ecriture?
        String uiid = readUiid(intent);

        seLogger(uiid);
    }

    /**
     * Cancellation: user has clicked the return button
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            setResult(RESULT_CANCELED);
            finish();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void seLogger(String uiid) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(NFCActivity.this);
        Utilisateur utilisateur = CustomPreferenceManager.getUtilisateur(prefs);

        Toast.makeText(NFCActivity.this, "Login -> id:" + utilisateur.getIdutilisateur()
                + " pwd: " + utilisateur.getPwd(), Toast.LENGTH_LONG).show();

        login(utilisateur, uiid);
    }
}
