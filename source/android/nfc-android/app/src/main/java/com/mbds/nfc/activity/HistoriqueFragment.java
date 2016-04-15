package com.mbds.nfc.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.mbds.nfc.R;
import com.mbds.nfc.adapter.CustomListAdapter;
import com.mbds.nfc.app.AppConfig;
import com.mbds.nfc.app.AppController;
import com.mbds.nfc.helper.CustomPreferenceManager;
import com.mbds.nfc.helper.SessionManager;
import com.mbds.nfc.model.Mouvement;
import com.mbds.nfc.model.Utilisateur;
import com.mbds.nfc.util.DateUtils;
import com.mbds.nfc.util.Dialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HistoriqueFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = HomeFragment.class.getSimpleName();


    private CustomListAdapter adapter;
    private SessionManager session;

    private ProgressDialog pDialog;
    private List<Mouvement> mvtList = new ArrayList<>();

    @Bind(R.id.list)
    ListView listView;
    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    public HistoriqueFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        session = new SessionManager(getActivity().getApplicationContext());

        //Setting the list
        adapter = new CustomListAdapter(getActivity(), mvtList);
        pDialog = new ProgressDialog(getActivity());
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

    }

    private void fetchMouvement() {
        // showing refresh animation before making http call
        swipeRefreshLayout.setRefreshing(true);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this.getActivity());
        Utilisateur utilisateur = CustomPreferenceManager.getUtilisateur(prefs);

        // Creating volley request obj
        JsonArrayRequest mvtReq = new JsonArrayRequest(
                AppConfig.BASE_URL + "/mouvements/utilisateur/" + utilisateur.getIdutilisateur(),
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        Dialog.hidePDialog(pDialog);

                        if (response.length() > 0) {
                            // Parsing json
                            for (int i = 0; i < response.length(); i++) {
                                try {

                                    JSONObject obj = response.getJSONObject(i);

                                    Mouvement mvt = new Mouvement();
                                    mvt.setDateemprunt(DateUtils.getDateFromString(obj.getString("dateemprunt")));
                                    mvt.setDateremise(DateUtils.getDateFromString(obj.getString("dateremise")));
                                    mvt.setIdmateriel(obj.getInt("idmateriel"));
                                    mvt.setIdmvt(obj.getInt("idmvt"));
                                    mvt.setIdutilisateur(obj.getInt("idutilisateur"));

                                    mvtList.add(0, mvt);

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }

                            // notifying list adapter about data changes
                            // so that it renders the list view with updated data
                            adapter.notifyDataSetChanged();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        VolleyLog.d(TAG, "Error: " + error.getMessage());
                        Dialog.hidePDialog(pDialog);

                    }
                });

        swipeRefreshLayout.setRefreshing(false);
        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(mvtReq);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.settings, container, false);
        ButterKnife.bind(this, view);

        listView.setAdapter(adapter);

        swipeRefreshLayout.setOnRefreshListener(this);
        swipeRefreshLayout.post(
                new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(true);
                        fetchMouvement();
                    }
                }
        );
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onRefresh() {
        mvtList = new ArrayList<>();
        fetchMouvement();
    }
}
