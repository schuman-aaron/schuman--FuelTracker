package schuman.as1;

import android.support.v7.app.AppCompatActivity;

import java.math.RoundingMode;
import java.text.DecimalFormat;

/**
 * Created by Caleb on 28/01/2016.
 */
public class carData  extends AppCompatActivity {
    private String dateInput;
    private String stationInput;
    private Double odometerInput;
    private String fuelGradeInput;
    private Double amountInput;
    private Double unitCostInput;
    private String costOutput;

    public void setDateInput(String input){
        this.dateInput = input;
    }

    public void setStationInput(String input){this.stationInput = input; }

    public void setOdometerInput(Double input){
        //code acquired on 16/1/28 from: http://stackoverflow.com/questions/153724/how-to-round-a-number-to-n-decimal-places-in-java
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        this.odometerInput = Double.parseDouble(df.format(input));
    }

    public void setFuelGradeInput(String input){
        this.fuelGradeInput = input;
    }

    public void setAmountInput(Double input){
        DecimalFormat df = new DecimalFormat("#.###");
        df.setRoundingMode(RoundingMode.HALF_UP);

        this.amountInput = Double.parseDouble(df.format((input)));
    }

    public void setUnitCostInput(String input){
        DecimalFormat df = new DecimalFormat("#.#");
        df.setRoundingMode(RoundingMode.HALF_UP);

        this.unitCostInput = Double.parseDouble(df.format(Double.parseDouble(input)));
    }

    public String getDateInput(){
        return this.dateInput;
    }

    public String getStationInput(){
        return this.stationInput;
    }

    public Double getOdometerInput(){
        return this.odometerInput;
    }

    public String getFuelGradeInput(){
        return this.fuelGradeInput;
    }
    public Double getAmountInput(){
        return this.amountInput;
    }

    public Double getUnitCostInput(){
        return this.unitCostInput;
    }

    public String getCost(){
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);

        return df.format(getAmountInput()*getUnitCostInput()/100);
    }

    public int getId(){
        return this.getId();
    }


}
