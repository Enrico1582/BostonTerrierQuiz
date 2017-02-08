package de.enricoweinhold.bostonterrierquiz;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkAnswers(View view) {
        int score = 0;
        //Question 1:
        RadioButton r1_1 = (RadioButton) findViewById(R.id.radio1_1);
        RadioButton r1_2 = (RadioButton) findViewById(R.id.radio1_2);
        RadioButton r1_3 = (RadioButton) findViewById(R.id.radio1_3);
        if (r1_3.isChecked()) score++;

        //Question 2:
        RadioButton r2_1 = (RadioButton) findViewById(R.id.radio2_1);
        RadioButton r2_2 = (RadioButton) findViewById(R.id.radio2_2);
        RadioButton r2_3 = (RadioButton) findViewById(R.id.radio2_3);
        if (r2_1.isChecked()) score++;

        //Question 3:
        CheckBox c3_1 = (CheckBox) findViewById(R.id.check3_1);
        CheckBox c3_2 = (CheckBox) findViewById(R.id.check3_2);
        CheckBox c3_3 = (CheckBox) findViewById(R.id.check3_3);
        if (c3_1.isChecked()) score++;
        if (c3_2.isChecked()) score++;
        if (c3_3.isChecked()) score++;

        //Question 4:
        EditText et4 = (EditText) findViewById(R.id.edittext4);
        int et4Int = 0;
        try {
            et4Int = Integer.parseInt(et4.getText().toString());
        } catch (Exception e){
            Log.e("Exception", e.toString());
        }

        if (et4Int >= 38 && et4Int <= 43) score++;

        //Question 5:
        RadioButton r5_1 = (RadioButton) findViewById(R.id.radio5_1);
        RadioButton r5_2 = (RadioButton) findViewById(R.id.radio5_2);
        RadioButton r5_3 = (RadioButton) findViewById(R.id.radio5_3);
        if (r5_3.isChecked()) score++;

        //get the layout for dialog
        LayoutInflater li = LayoutInflater.from(this);
        View dialogLayout = li.inflate(R.layout.dialog_check, null);
        
        //build dialog
        android.app.AlertDialog.Builder myAlert = new android.app.AlertDialog.Builder(this);
        myAlert.setPositiveButton(R.string.button_close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .create();
        //get the textview from dialog layout
        TextView tvScoreHead = (TextView) dialogLayout.findViewById(R.id.textview_dialog_head);
        //message handling 
        if (score > 6) tvScoreHead.setText(R.string.scoretext_great);
        if (score > 4 && score <= 6) tvScoreHead.setText(R.string.scoretext_good);
        if (score <= 4) tvScoreHead.setText(R.string.scoretext_better);

        //get score dialog header
        TextView tvScoreText = (TextView) dialogLayout.findViewById(R.id.textview_dialog_score);
        tvScoreText.setText(String.format(getString(R.string.score_text), score));

        //put layout to the dialog
        myAlert.setView(dialogLayout);
        
        //show the dialog
        myAlert.show();
    }
}
