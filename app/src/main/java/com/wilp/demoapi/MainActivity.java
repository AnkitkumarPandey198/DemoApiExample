package com.wilp.demoapi;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String BASE_URL = "https://staging.greytrunk.cloudzmall.com/api/v1/launcherApi/";

    TextView emailTextView;
    TextView firstNameTextView;
    TextView lastNameTextView;
    TextView createdAtTextView;
    TextView rolesTextView;



    private TextView availableAssetsCountTextView, checkoutAssetsCountTextView, disposedAssetsCountTextView, lostAssetsCountTextView;
    private TextView totalAssetsCountTextView ,totalCostOfAssetsTextView ,assetCountLastYearTextView ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emailTextView = findViewById(R.id.email_value);
        firstNameTextView = findViewById(R.id.first_name_value);
        lastNameTextView = findViewById(R.id.last_name_value);
        createdAtTextView = findViewById(R.id.created_at_value);
        rolesTextView = findViewById(R.id.roles_value);

        availableAssetsCountTextView = findViewById(R.id.availableAssetsCountValue);
        checkoutAssetsCountTextView = findViewById(R.id.checkoutAssetsCountValue);
        disposedAssetsCountTextView = findViewById(R.id.disposedAssetsCountValue);
        lostAssetsCountTextView = findViewById(R.id.lostAssetsCountValue);

        totalAssetsCountTextView = findViewById(R.id.totalAssetsCountValue);
        totalCostOfAssetsTextView = findViewById(R.id.totalCostOfAssetsValue);
        assetCountLastYearTextView = findViewById(R.id.assetCountLastYearValue);

        new FetchUserDetailsData(this).execute();
        new FetchUserDataDetailsData(this).execute();


    }

    private static class FetchUserDetailsData extends AsyncTask<Void, Void, JSONObject> {

        @SuppressLint("StaticFieldLeak")
        MainActivity nMainActivity;

        public FetchUserDetailsData(MainActivity nMainActivity) {
            this.nMainActivity = nMainActivity;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            try {
                URL url = new URL(BASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Token", "berer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEwLCJpc3MiOiJodHRwczovL3d3dy5ncmV5dHJ1bmtyZmlkLmNvbS9hcGkvdjEvYXV0aCIsImlhdCI6MTY3OTU1MTMzMCwiZXhwIjoxNjgwODM1MzMwLCJuYmYiOjE2Nzk1NTEzMzAsImp0aSI6Im5sRE5BMkpmcndDbG1vbDgifQ.mir6a4iYtoaQ8MBKp_du5RXJUXMD3piW4HAKWjGNQwM");
                conn.setRequestProperty("Device-Type", "android");
                conn.setRequestProperty("api-key", "IqJit4XEaiM71D2tBPauHG6dS78BfuMLQuJVZiwsw6Y=");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    return jsonResponse.getJSONObject("userDetails");
                } else {
                    Log.e(TAG, "HTTP GET request failed, error code: " + responseCode);
                    return null;
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error during HTTP GET request", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject userDetails) {
            if (userDetails != null) {
                try {
                    String firstName = userDetails.getString("first_name");
                    String lastName = userDetails.getString("last_name");
                    String email = userDetails.getString("email");
                    String roles = userDetails.getString("roles");
                    String created_at = userDetails.getString("created_at");


                    nMainActivity.firstNameTextView.setText(firstName);
                    nMainActivity.lastNameTextView.setText(lastName);
                    nMainActivity.emailTextView.setText(email);
                    nMainActivity.rolesTextView.setText(roles);
                    nMainActivity.createdAtTextView.setText(created_at);

                    Log.d(TAG, "User details: " + firstName + " " + lastName + ", " + email);
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing JSON response", e);
                }
            }
        }
    }

    private static class FetchUserDataDetailsData extends AsyncTask<Void, Void, JSONObject> {

        @SuppressLint("StaticFieldLeak")
        MainActivity nMainActivity;

        public FetchUserDataDetailsData(MainActivity nMainActivity) {
            this.nMainActivity = nMainActivity;
        }

        @Override
        protected JSONObject doInBackground(Void... params) {
            try {
                URL url = new URL(BASE_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Token", "berer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOjEwLCJpc3MiOiJodHRwczovL3d3dy5ncmV5dHJ1bmtyZmlkLmNvbS9hcGkvdjEvYXV0aCIsImlhdCI6MTY3OTU1MTMzMCwiZXhwIjoxNjgwODM1MzMwLCJuYmYiOjE2Nzk1NTEzMzAsImp0aSI6Im5sRE5BMkpmcndDbG1vbDgifQ.mir6a4iYtoaQ8MBKp_du5RXJUXMD3piW4HAKWjGNQwM");
                conn.setRequestProperty("Device-Type", "android");
                conn.setRequestProperty("api-key", "IqJit4XEaiM71D2tBPauHG6dS78BfuMLQuJVZiwsw6Y=");

                int responseCode = conn.getResponseCode();
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    String inputLine;
                    StringBuilder response = new StringBuilder();
                    while ((inputLine = in.readLine()) != null) {
                        response.append(inputLine);
                    }
                    in.close();
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    return jsonResponse.getJSONObject("dashbaordData");
                } else {
                    Log.e(TAG, "HTTP GET request failed, error code: " + responseCode);
                    return null;
                }
            } catch (IOException | JSONException e) {
                Log.e(TAG, "Error during HTTP GET request", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(JSONObject dashbaordData) {
            if (dashbaordData != null) {
                try {
                    JSONObject assetCountByStatus = dashbaordData.getJSONObject("asset_count_by_status");
                    JSONObject assetCountData = dashbaordData.getJSONObject("asset_count_data");
                    String availableAssetsCount = String.valueOf(assetCountByStatus.getInt("available_assets_count"));
                    String checkoutAssetsCount = String.valueOf(assetCountByStatus.getInt("checkout_assets_count"));
                    String disposedAssetsCount = String.valueOf(assetCountByStatus.getInt("disposed_assets_count"));
                    String lostAssetsCount = String.valueOf(assetCountByStatus.getInt("lost_assets_count"));
                    String totalAssetsCount = String.valueOf(assetCountData.getInt("total_assets_count"));
                    String totalCostOfAssets = String.valueOf(assetCountData.getInt("total_cost_of_assets"));
                    String assetCountLastYear = String.valueOf(assetCountData.getInt("asset_count_last_year"));

                    nMainActivity.availableAssetsCountTextView.setText(availableAssetsCount);
                    nMainActivity.checkoutAssetsCountTextView.setText(checkoutAssetsCount);
                    nMainActivity.disposedAssetsCountTextView.setText(disposedAssetsCount);
                    nMainActivity.lostAssetsCountTextView.setText(lostAssetsCount);
                    nMainActivity.totalAssetsCountTextView.setText(totalAssetsCount);
                    nMainActivity.totalCostOfAssetsTextView.setText(totalCostOfAssets);
                    nMainActivity.assetCountLastYearTextView.setText(assetCountLastYear);


//                    Log.d(TAG, "Dashboard details: " + firstName + " " + lastName );
                } catch (JSONException e) {
                    Log.e(TAG, "Error parsing JSON response", e);
                }
            }
        }
    }




}