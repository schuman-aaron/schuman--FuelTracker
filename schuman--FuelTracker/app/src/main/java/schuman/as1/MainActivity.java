package schuman.as1;

//simplescreenrecorder

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

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

public class MainActivity extends Activity {

    private static final String FILENAME = "file.sav";
    private static final String POSITIONNAME = "name.sav";

   // private ListView myLog;
    private carData cardata = new carData();




    private ArrayList<carData> carDataArray = new ArrayList<carData>();
    //private ArrayAdapter<carData> adapter;

    private Integer index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //bodyText = (EditText) findViewById(R.id.dateText);


        Button saveButton = (Button) findViewById(R.id.saveButton);
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        // Button modifyButton = (Button) findViewById(R.id.modifyButton);
        Button logButton = (Button) findViewById(R.id.logButton);

        saveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                setResult(RESULT_OK);

                cardata = new carData();

                try {
                    cardata.setAmountInput(textID2Double(R.id.amountInput));
                    cardata.setDateInput(textID2String(R.id.dateInput));
                    cardata.setFuelGradeInput(textID2String(R.id.fuelGradeInput));
                    cardata.setOdometerInput(textID2Double(R.id.odometerInput));
                    cardata.setStationInput(textID2String(R.id.stationInput));
                    cardata.setUnitCostInput(textID2String(R.id.unitCostInput));
                    //adapter.notifyDataSetChanged();
                    carDataArray.add(index, cardata);
                    //adapter.notifyDataSetChanged();
                    saveInFile();
                    //saveInFile(text, new Date(System.currentTimeMillis()));
                    index = carDataArray.size();
                    //finish();
                } catch(NumberFormatException e){

                }

            }
        });

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

        logButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), LogActivity.class);
                //EditText editText = (EditText) findViewById(R.id.edit_message);
                //String message = editText.getText().toString();
                //intent.putExtra(EXTRA_MESSAGE, message);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        //String[] tweets = loadFromFile();
        loadFromFile();
       // myLog = (ListView) findViewById(R.id.log);
       // adapter = new ArrayAdapter<carData>(this,
               // R.layout.log_list, carDataArray);
       //myLog.setAdapter(adapter);
    }


    private void loadFromFile() {
        carDataArray = new ArrayList<carData>();
        index = 0;

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

            index = gson.fromJson(in2, IntegerType);

        } catch (FileNotFoundException e) {
            carDataArray = new ArrayList<carData>();
            index = 0;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
    }

    private void saveInFile() {
        try {
            FileOutputStream fos = openFileOutput(FILENAME,
                    0);
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(fos));
            Gson gson = new Gson();
            gson.toJson(carDataArray, out);
            out.flush();
            fos.close();

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

    private String textID2String(int textID) {
        return ((EditText) findViewById(textID)).getText().toString();
    }

    private Double textID2Double(int textID) {
        return Double.parseDouble(((EditText) findViewById(textID)).getText().toString());
    }
}
