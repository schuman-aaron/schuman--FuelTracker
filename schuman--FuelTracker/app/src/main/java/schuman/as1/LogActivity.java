package schuman.as1;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by Caleb on 26/01/2016.
 */
public class LogActivity extends Activity {
    private static final String FILENAME = "file.sav";
    private static final String POSITIONNAME = "name.sav";

    private ListView Log;
    private carData cardata;

    private ArrayList<carData> carDataArray = new ArrayList<carData>();
    private ArrayAdapter<carData> adapter;

    private Integer index = 0;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_file);

        Log = (ListView) findViewById(R.id.Log);

        adapter = new ArrayAdapter<carData>(this,
                R.layout.log_list, carDataArray);

        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        Button modifyButton = (Button) findViewById(R.id.modifyButton);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_OK);
                if(carDataArray.isEmpty()){
                    index = 0;
                }else {
                    index = carDataArray.size();
                }
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }

        });

        modifyButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

       Log.setOnItemClickListener(new OnItemClickListener() {
           public void onItemClick(AdapterView<?> parent, View v,
                                   int position, long id) {
               Integer carIndex = 0;
               for (carData clickCar : carDataArray) {
                   if (clickCar.getId() == v.getId()) {
                       index = carIndex;
                       saveInFile();
                       break;
                   }
                   carIndex++;
               }
           }
       });

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();

        //String[] tweets = loadFromFile();
        loadFromFile();
        adapter = new ArrayAdapter<carData>(this,
                R.layout.log_list, carDataArray);
       // Log = (ListView) findViewById(R.id.Log);
        Log.setAdapter(adapter);
    }

    /*
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
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    private void loadFromFile() {
        try {
            FileInputStream fis = openFileInput(FILENAME);
            BufferedReader in = new BufferedReader(new InputStreamReader(fis));
            Gson gson = new Gson();

            // Took from https://google-gson.googlecode.com/svn/trunk/gson/docs/javadocs/com/google/gson/Gson.html 01-19 2016
            Type listType = new TypeToken<ArrayList<carData>>() {
            }.getType();
            carDataArray = gson.fromJson(in, listType);

            FileInputStream fis2 = openFileInput(POSITIONNAME);
            BufferedReader in2 = new BufferedReader(new InputStreamReader(fis2));

            Type IntegerType = new TypeToken<Integer>() {
            }.getType();

            if(in2.toString().isEmpty()){
                index = 0;
            } else {
                index = gson.fromJson(in2, IntegerType);
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            Gson gson = new Gson();

            FileOutputStream fos2 = openFileOutput(POSITIONNAME, 0);
            BufferedWriter out2 = new BufferedWriter(new OutputStreamWriter(fos2));
            gson.toJson(index, out2);
            out2.flush();
            fos2.close();

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

}
