package com.archeosbj.lifetarget;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;
import com.github.paolorotolo.appintro.model.SliderPage;

import static com.archeosbj.lifetarget.data.databaseContract.dataEntry.GENERAL_SETTINGS_NAME;

public class Appintro extends  AppIntro2 {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_appintro);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(GENERAL_SETTINGS_NAME, MODE_PRIVATE);
        boolean myb = sharedPref.getBoolean("FirstStart",true);

        if (sharedPref.getBoolean("FirstStart",true) == true){

        }else if (sharedPref.getBoolean("FirstStart",true) == false){
            Intent intent = new Intent(getApplicationContext(), startactivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
            finish();
        }

        SliderPage sliderPage1 = new SliderPage();
        sliderPage1.setTitle("Bienvenue chez LifeTarget");
        sliderPage1.setDescription("Découvrez comment cette application vas changez votre vie");
        sliderPage1.setImageDrawable(R.drawable.screen_i);
        sliderPage1.setTitleColor(getResources().getColor(R.color.Noir));
        sliderPage1.setDescColor(getResources().getColor(R.color.Noir));
        sliderPage1.setBgColor(getResources().getColor(R.color.white));
        addSlide(AppIntroFragment.newInstance(sliderPage1));

        SliderPage sliderPage2 = new SliderPage();
        sliderPage2.setTitle("Restaurant & Hotel");
        sliderPage2.setDescription("Explorer les restaurants & hotel  comme si vous y étiez, obtenez des bons de reduction, des reservations et beaucoup plus...");
        sliderPage2.setImageDrawable(R.drawable.rest_hot);
        sliderPage2.setTitleColor(getResources().getColor(R.color.Lavande));
        sliderPage2.setDescColor(getResources().getColor(R.color.Lavande));
        sliderPage2.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage2));

        SliderPage sliderPage3 = new SliderPage();
        sliderPage3.setTitle("Tourisme & découverte");
        sliderPage3.setDescription("Visiter à travers l'application des lieux unique, LT vous accompagne en tout point");
        sliderPage3.setImageDrawable(R.drawable.si_tran);
        sliderPage3.setTitleColor(getResources().getColor(R.color.Vert));
        sliderPage3.setDescColor(getResources().getColor(R.color.Vert));
        sliderPage3.setBgColor(Color.WHITE);
        addSlide(AppIntroFragment.newInstance(sliderPage3));

        SliderPage sliderPage4 = new SliderPage();
        sliderPage4.setTitle("Chercher à apprendre plus?");
        sliderPage4.setTitleColor(getResources().getColor(R.color.white));
        sliderPage4.setDescColor(getResources().getColor(R.color.white));
            sliderPage4.setDescription("Inscrivez vous à notre application et découvrez ses 56 fonctionnalités qui vont vous changez la vie");
        sliderPage4.setImageDrawable(R.drawable.icons_lt);
        sliderPage4.setBgColor(getResources().getColor(R.color.Bleu));
        addSlide(AppIntroFragment.newInstance(sliderPage4));
    }

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(GENERAL_SETTINGS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("FirstStart",false);
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), startactivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
        finish();
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences(GENERAL_SETTINGS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("FirstStart",false);
        editor.commit();
        editor.apply();
        Intent intent = new Intent(getApplicationContext(), startactivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_from_left,R.anim.slide_to_right );
        finish();
    }
}
