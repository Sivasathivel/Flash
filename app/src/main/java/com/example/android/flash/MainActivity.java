package com.example.android.flash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {
    int points = 0;
    String prefix = "com_example_android_flash_";
    boolean first = true;
    int img_idx   = 0;
    //The quiz data
    Field [] drawables = R.drawable.class.getFields();
    String [] image_Names = getResourceNames();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*
            Disable Points and EditText until, begin is pressed.
            Adjust Layout Height
        */
        TextView points_view = (TextView) findViewById(R.id.scores);
        EditText answer_view = (EditText) findViewById(R.id.answer);
        TextView survey_1    = (TextView) findViewById(R.id.survey_1) ;
        TextView survey_2    = (TextView) findViewById(R.id.survey_2) ;
        CheckBox survey_1_R1    = (CheckBox) findViewById(R.id.survey_1_R1) ;
        CheckBox survey_1_R2    = (CheckBox) findViewById(R.id.survey_1_R2) ;
        CheckBox survey_1_R3    = (CheckBox) findViewById(R.id.survey_1_R3) ;
        CheckBox survey_1_R4    = (CheckBox) findViewById(R.id.survey_1_R4) ;
        RadioButton survey_2_R1 = (RadioButton) findViewById(R.id.survey_2_R1) ;
        RadioButton survey_2_R2 = (RadioButton) findViewById(R.id.survey_2_R2) ;
        Button complete_button = (Button) findViewById(R.id.button2_id) ;
        points_view.setVisibility(View.GONE);
        answer_view.setVisibility(View.GONE);
        survey_1.setVisibility(View.GONE);
        survey_1_R1.setVisibility(View.GONE);
        survey_1_R2.setVisibility(View.GONE);
        survey_1_R3.setVisibility(View.GONE);
        survey_1_R4.setVisibility(View.GONE);
        survey_2.setVisibility(View.GONE);
        survey_2_R1.setVisibility(View.GONE);
        survey_2_R2.setVisibility(View.GONE);
        complete_button.setVisibility(View.GONE);
    }
    private String[] getResourceNames(){
        int counts = 0;
        for(Field f:drawables){if(f.getName().startsWith(prefix)){counts++;}}
        String [] imgNames = new String [counts];
        int i = 0;
        for(Field f:drawables)
            if(f.getName().startsWith(prefix))
                imgNames[i++]=f.getName().toString();
        return imgNames;
    }

    public void proceed(View view){
        Button button = (Button) findViewById(R.id.button_id);
        TextView points_view = (TextView) findViewById(R.id.scores);
        EditText answer_view = (EditText) findViewById(R.id.answer);
        TextView question_view = (TextView) findViewById(R.id.question_id);
        if(first){
            first = false;
            button.setText(R.string.check);
            points_view.setVisibility(View.VISIBLE);
            answer_view.setVisibility(View.VISIBLE);
            question_view.setText(R.string.question);
            displayUpdatedPoints();
            displayNextImage();
        }else{
            String response = prefix + answer_view.getText().toString().toLowerCase();
            Log.i("Image Name: ",image_Names[img_idx]);
            Log.i("Resps Name: ",response);
            if(image_Names[img_idx].equals(response)){
                points++;
                Toast toast = Toast.makeText(this,"You nailed it...Congrats!",Toast.LENGTH_SHORT);
                toast.show();
            }
            else{
                Toast toast = Toast.makeText(this,image_Names[img_idx].replaceAll("com_example_android_flash_",""),Toast.LENGTH_SHORT);
                toast.show();
            }
            displayUpdatedPoints();
            displayNextImage();
        }
    }
    private void displayNextImage(){
        img_idx++;
        if(img_idx < image_Names.length){
            ImageView imgView = (ImageView) findViewById(R.id.image_id);
            int imageResource = getResources().getIdentifier("@drawable/" + image_Names[img_idx],
                    null, getPackageName());
            imgView.setImageDrawable(getResources().getDrawable(imageResource));
            EditText answer_view = (EditText) findViewById(R.id.answer);
            answer_view.setText("");
        }else{
            Button button = (Button) findViewById(R.id.button_id);
            ImageView imgView = (ImageView) findViewById(R.id.image_id);
            EditText answer_view = (EditText) findViewById(R.id.answer);
            TextView question_view = (TextView) findViewById(R.id.question_id);
            TextView survey_1    = (TextView) findViewById(R.id.survey_1) ;
            TextView survey_2    = (TextView) findViewById(R.id.survey_2) ;
            CheckBox survey_1_R1    = (CheckBox) findViewById(R.id.survey_1_R1) ;
            CheckBox survey_1_R2    = (CheckBox) findViewById(R.id.survey_1_R2) ;
            CheckBox survey_1_R3    = (CheckBox) findViewById(R.id.survey_1_R3) ;
            CheckBox survey_1_R4    = (CheckBox) findViewById(R.id.survey_1_R4) ;
            RadioButton survey_2_R1 = (RadioButton) findViewById(R.id.survey_2_R1) ;
            RadioButton survey_2_R2 = (RadioButton) findViewById(R.id.survey_2_R2) ;
            Button complete_button = (Button) findViewById(R.id.button2_id) ;
            button.setVisibility(View.GONE);
            imgView.setVisibility(View.GONE);
            answer_view.setVisibility(View.GONE);
            question_view.setVisibility(View.GONE);
            survey_1.setVisibility(View.VISIBLE);
            survey_1_R1.setVisibility(View.VISIBLE);
            survey_1_R2.setVisibility(View.VISIBLE);
            survey_1_R3.setVisibility(View.VISIBLE);
            survey_1_R4.setVisibility(View.VISIBLE);
            survey_2.setVisibility(View.VISIBLE);
            survey_2_R1.setVisibility(View.VISIBLE);
            survey_2_R2.setVisibility(View.VISIBLE);
            complete_button.setVisibility(View.VISIBLE);
            survey_1_R1.setActivated(false);
            survey_1_R2.setActivated(false);
            survey_1_R3.setActivated(false);
            survey_1_R4.setActivated(false);
            survey_2_R1.setActivated(false);
            survey_2_R2.setActivated(false);
        }
    }
    public void terminate(View view){
        TextView survey_1    = (TextView) findViewById(R.id.survey_1) ;
        TextView survey_2    = (TextView) findViewById(R.id.survey_2) ;
        CheckBox survey_1_R1    = (CheckBox) findViewById(R.id.survey_1_R1) ;
        CheckBox survey_1_R2    = (CheckBox) findViewById(R.id.survey_1_R2) ;
        CheckBox survey_1_R3    = (CheckBox) findViewById(R.id.survey_1_R3) ;
        CheckBox survey_1_R4    = (CheckBox) findViewById(R.id.survey_1_R4) ;
        RadioButton survey_2_R1 = (RadioButton) findViewById(R.id.survey_2_R1) ;
        RadioButton survey_2_R2 = (RadioButton) findViewById(R.id.survey_2_R2) ;
        Button complete_button = (Button) findViewById(R.id.button2_id) ;
        survey_1.setText("Bonus: Kakapo is ....");
        if(survey_1_R1.isChecked() && survey_1_R4.isChecked() && !survey_1_R2.isChecked() && !survey_1_R3.isChecked()){
            points++;
            displayUpdatedPoints();
            //survey_1.setText("You did great... Learn more!");
        }else{
            Toast.makeText(this, "Kakapo is a nocturnal, flightless parrot endemic to Newzealand",Toast.LENGTH_SHORT).show();
        }


        survey_2.setText("HagFish defence Mechanism is ");
        survey_2_R1.setText("Slime");
        survey_2_R2.setText("Electrocution");
        if(survey_2_R1.isChecked()){
            points++;
            displayUpdatedPoints();
            survey_2.setText("Thank You...!");
        }
        else {
            survey_2.setText("Thank you... We will thrive to make it better!");
        }
        Toast.makeText(this, "Total Score: " + points, Toast.LENGTH_SHORT);
        /*survey_1_R1.setVisibility(View.GONE);
        survey_1_R2.setVisibility(View.GONE);
        survey_1_R3.setVisibility(View.GONE);
        survey_1_R4.setVisibility(View.GONE);
        survey_2_R1.setVisibility(View.GONE);
        survey_2_R2.setVisibility(View.GONE);*/
        complete_button.setVisibility(View.GONE);
    }
    private void displayUpdatedPoints(){
        TextView points_view = (TextView) findViewById(R.id.scores);
        String score_str = this.getResources().getString(R.string.points);
        points_view.setText(score_str + ": " + points);
        Log.i("New Points: ", String.valueOf(points));
    }
}
