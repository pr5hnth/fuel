package com.example.fuelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class mila extends AppCompatActivity implements ex_diag.exdiaglist {

    EditText tc,td,fq;
    TextView milage,fp,cpk;
    Button cal,save,reset;
    TextInputLayout textInputLayout;
    public Integer dist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mila);
        td=findViewById(R.id.td);
        tc=findViewById(R.id.tcc);
        fq=findViewById(R.id.fq);

        milage=findViewById(R.id.milag);
        fp=findViewById(R.id.fp);
        cpk=findViewById(R.id.ck);

        cal=findViewById(R.id.cal);
        save=findViewById(R.id.save);
        reset=findViewById(R.id.rest);

        textInputLayout=findViewById(R.id.txip);

   textInputLayout.setEndIconOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           opendialog();

       }
   });


   cal.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
         String tds= td.getText().toString();
         String tcs=tc.getText().toString();
         String fqs= fq.getText().toString();


         milage.setText(Integer.parseInt(tds)/Float.parseFloat(fqs)+" KM/PL");
         fp.setText(Integer.parseInt(tcs)/Float.parseFloat(fqs)+" ₹");
         cpk.setText(Integer.parseInt(tcs)/Integer.parseInt(tds)+" ₹");

       }
   });


    }

    private void opendialog() {
        ex_diag exDiag=new ex_diag();
        exDiag.show(getSupportFragmentManager(),"ex");
    }

    @Override
    public void applytext(String start, String end) {
        dist= Integer.parseInt(end)-Integer.parseInt(start);
        td.setText(""+dist);
    }



}