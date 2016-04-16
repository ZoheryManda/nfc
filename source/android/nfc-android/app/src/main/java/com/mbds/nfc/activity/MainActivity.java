package com.mbds.nfc.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.mbds.nfc.R;
import com.mbds.nfc.app.AppConfig;
import com.mbds.nfc.app.AppController;
import com.mbds.nfc.helper.SessionManager;
import com.mbds.nfc.util.GCMClientManager;

import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends ActionBarActivity implements FragmentDrawer.FragmentDrawerListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private SessionManager session;
    private FragmentDrawer drawerFragment;
    private String baseUrl;

    private GCMClientManager pushClientManager;

    @Bind(R.id.toolbar)
    Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        baseUrl = "http://" + PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("wsurl", "") + "/nfc/rest";

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerFragment = (FragmentDrawer)
                getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);
        drawerFragment.setDrawerListener(this);

        gcmRegistration();

        displayView(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        if (id == R.id.action_search) {
            Toast.makeText(getApplicationContext(), "Search action is selected!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDrawerItemSelected(View view, int position) {
        displayView(position);
    }

    private void displayView(int position) {
        Fragment fragment = null;
        String title = getString(R.string.app_name);
        switch (position) {
            case 0:
                Log.i(TAG, "HomeFragment clicked");
                fragment = new HomeFragment();
                title = getString(R.string.title_home);
                break;
            case 1:
                Log.i(TAG, "HistoriqueFragment clicked");
                fragment = new HistoriqueFragment();
                title = getString(R.string.nav_historique);
                break;
            default:
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.container_body, fragment);
            fragmentTransaction.commit();

            // set the toolbar title
            getSupportActionBar().setTitle(title);
        }
    }

    public void gcmRegistration() {
        pushClientManager = new GCMClientManager(this, AppConfig.SENDER_ID);

        pushClientManager.registerIfNeeded(new GCMClientManager.RegistrationCompletedHandler() {
            @Override
            public void onSuccess(String registrationId, boolean isNewRegistration) {

                Toast.makeText(MainActivity.this, registrationId, Toast.LENGTH_SHORT).show();
                Log.i(TAG, "registering device (regId = " + registrationId + ")");
                String idUtilisateur = PreferenceManager.getDefaultSharedPreferences(MainActivity.this).getString("matricule", "");
                updateRegid(idUtilisateur, registrationId);
            }

            @Override
            public void onFailure(String ex) {
                super.onFailure(ex);
            }

        });
    }

    public void updateRegid(String idUtilisateur, String regid) {
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                baseUrl + "/utilisateurs/regid/" + idUtilisateur + "/" + regid,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        Log.i(TAG, "registering device (regId = " + regid + ")");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        );
        AppController.getInstance().addToRequestQueue(objectRequest);
    }
}