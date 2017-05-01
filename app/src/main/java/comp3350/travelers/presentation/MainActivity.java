package comp3350.travelers.presentation;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import comp3350.travelers.R;
import comp3350.travelers.application.Main;

public class MainActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        copyDatabaseToDevice();

        Main.startUp();

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Main.shutDown();
    }

    private void copyDatabaseToDevice() {
        final String DB_PATH = "db";

        String[] assetNames;
        Context context = getApplicationContext();
        File dataDirectory = context.getDir(DB_PATH, Context.MODE_PRIVATE);
        AssetManager assetManager = getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.dbName);

        } catch (IOException ioe) {
            Messages.warning(this, "Unable to access application data: " + ioe.getMessage());
        }
    }

    public void copyAssetsToDirectory(String[] assets, File directory) throws IOException {
        AssetManager assetManager = getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];
            char[] buffer = new char[1024];
            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {
                InputStreamReader in = new InputStreamReader(assetManager.open(asset));
                FileWriter out = new FileWriter(outFile);

                count = in.read(buffer);
                while (count != -1) {
                    out.write(buffer, 0, count);
                    count = in.read(buffer);
                }

                out.close();
                in.close();
            }
        }
    }

    public void flightFormOnClick(View v) {
        Intent flightIntent = new Intent(MainActivity.this, FlightForm.class);
        MainActivity.this.startActivity(flightIntent);
    }

    // Not currently focusing on these specific features until later date
    // These methods need to be here so the app won't crash when the user
    // clicks either hotel or car buttons
    public void hotelFormOnClick(View v) {
        Toast toastMessage = Toast.makeText(this, "Hotels under construction", Toast.LENGTH_SHORT);
        toastMessage.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 180);
        toastMessage.show();
    }

    public void carFormOnClick(View v) {
        Toast toastMessage = Toast.makeText(this, "Car Rentals under construction", Toast.LENGTH_SHORT);
        toastMessage.setGravity(Gravity.BOTTOM | Gravity.CENTER_VERTICAL, 0, 180);
        toastMessage.show();
    }


}
