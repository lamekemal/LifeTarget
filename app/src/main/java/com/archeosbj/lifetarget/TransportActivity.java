package com.archeosbj.lifetarget;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.archeosbj.lifetarget.Adpter.DestinationAdapter;
import com.archeosbj.lifetarget.Model.Destination;
import com.bumptech.glide.Glide;
import com.snatik.storage.Storage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.DATA_DIRECTORI;

public class TransportActivity extends AppCompatActivity {

    String Id;
    String Name;
    String Contact;
    String Uniqueid;
    String Service;
    String Pointfort;
    String Price;
    String Mail;
    String Primpimage;
    String GaleryOne;
    String Galerytwo;
    String Galerytree;
    String Transline;
    String Loanbus;
    String Maxcap;
    String Horaire;
    String Reserveone;
    String Reservetwo;
    String Extras;
    String Description;
    String Payement;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transport);
        final Context cntx = this;
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsingToolBar);

        setSupportActionBar(toolbar);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.transportRecycler);

        Intent intent = getIntent();
        final String[] tranItm = intent.getStringArrayExtra("ITEM");
        Id = tranItm[1];
        Name = tranItm[2];
        Contact = tranItm[3];
        Uniqueid = tranItm[4];
        Service = tranItm[5];
        Pointfort = tranItm[6];
        Price = tranItm[7];
        Mail = tranItm[8];
        Primpimage = tranItm[9];
        GaleryOne = tranItm[10];
        Galerytwo = tranItm[11];
        Galerytree = tranItm[12];
        Transline = tranItm[13];
        Loanbus = tranItm[14];
        Maxcap = tranItm[15];
        Horaire = tranItm[16];
        Reserveone = tranItm[17];
        Reservetwo = tranItm[19];
        Extras = tranItm[20];
        Description = tranItm[21];
        Payement = tranItm[22];
        collapsingToolbarLayout.setTitle(Name);
        //---------
        ArrayList<String> Tempar;

        /* given string will be split by the argument delimiter provided. */
        List<String> itemsLine = Arrays.asList(Transline.split(";"));
        List<String> itemsPrix = Arrays.asList(Price.split(";"));

        ArrayList<Destination> destinations = new ArrayList<>();
        for(int i = 0; i <itemsLine.size(); i++)
        {
            if (i <itemsPrix.size() ){
                //destinations.add(new Destination("Parakou-Cotonou", "1200 XOF"));
                destinations.add(new Destination(itemsLine.get(i), itemsPrix.get(i)));
            }
        }

        DestinationAdapter adapter = new DestinationAdapter(this, destinations);
        ImageView ctrprimpimage = (ImageView) findViewById(R.id.primpimage);
        Storage storage = new Storage(this);
        String path = storage.getExternalStorageDirectory();
        String newDir = path + File.separator + DATA_DIRECTORI;
        String newDiri = newDir + File.separator + "images";
        String fileph = newDiri + File.separator + Primpimage;
        setBitmapImageFormSD(fileph,ctrprimpimage);

        TextView transcontact = (TextView) findViewById(R.id.transcontact);
        TextView transportmail = (TextView) findViewById(R.id.transportmail);
        TextView transportcap = (TextView) findViewById(R.id.transportcap);
        CheckBox chkBox = (CheckBox) findViewById(R.id.chkBox);
        TextView pointfortjstrans = (TextView) findViewById(R.id.pointfortjstrans);
        CheckBox resevationtrans = (CheckBox) findViewById(R.id.resevationtrans);

        transcontact.setText(Contact);
        transportmail.setText(Mail);
        transportcap.setText(Maxcap);
        pointfortjstrans.setText(Pointfort);

        if(Loanbus == "OUI"){
            chkBox.setChecked(true);
        }else if (Loanbus == " "){
            chkBox.setChecked(true);
        }

        if(Service == "OUI"){
            resevationtrans.setChecked(true);
        } else if (Service == "OUI "){
            resevationtrans.setChecked(true);
        }

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new
                LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        FloatingActionButton fabplus = findViewById(R.id.faba);

        /*fabplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(cntx, Reservation.class);
                intent.putExtra("ITEM",  tranItm);
                startActivity(intent);
            }
        });*/
    }
    void setBitmapImageFormSD(String filepath, ImageView ImgV){
        Glide.with(getApplicationContext())
                .load(Uri.fromFile(new File(filepath)))
                .into(ImgV);
    }
}
