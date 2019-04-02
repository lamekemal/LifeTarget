package com.archeosbj.lifetarget.data;

import android.provider.BaseColumns;

public final class databaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private databaseContract() {}

    public static class AppConfig {
        public static final String URL_POST_FAVORITE = "http://lifetarget.atwebpages.com/interact/api/client/setfav.php";
        // Server user login url
        public static String URL_LOGIN = "http://lifetarget.atwebpages.com/interact/api/client/login.php";

        // Server user register url
        public static String URL_REGISTER = "http://lifetarget.atwebpages.com/interact/api/client/register.php";
    }

    /* Inner class that defines the table contents */
    public static class dataEntry implements BaseColumns {

        //feed section
        public static final String FEED_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/feed/getfeed.php";
        public static final String SET_FEED_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/feed/setfeed.php";

        //data link
        public static final String HOTEL_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/gethotel.php";
        public static final String RESTO_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/getresto.php";
        public static final String TRANS_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/gettrans.php";
        public static final String INNOV_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/getinnov.php";
        public static final String SERLI_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/getserli.php";
        public static final String SITES_LINK_URL = "http://lifetarget.atwebpages.com/interact/api/service/getsites.php";
        public static final String BLANK_COOKIES_URL ="http://lifetarget.atwebpages.com/interact/api/service/getgetting.php";
        public static final String SERVER_IMGURL_API = "http://lifetarget.atwebpages.com/interact/uploads/";

        //distilled data resto
        public static final String TABLE_NAME_RESTO = "liferesto";
        public static final String TABLE_NAME_HOTEL = "lifehotel";
        public static final String TABLE_NAME_SITES = "lifesites";
        public static final String TABLE_NAME_TRANS = "lifetrans";
        public static final String TABLE_NAME_INNOV = "lifeinnov";
        public static final String TABLE_NAME_SERLI = "lifeserli";

        public static final String TABLE_NAME = "lifetest";
        public static final String TABLE_NAME_LIFE = "lifetest";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "lifedbase.db";

        //database JSOn Header
        public static final String FEED_JSON_CATEGORIES = "feedContract";
        public static final String HOTEL_JSON_CATEGORIES = "hotelContract";
        public static final String RESTO_JSON_CATEGORIES = "restoContract";
        public static final String SITES_JSON_CATEGORIES = "sitesContract";
        public static final String TRANS_JSON_CATEGORIES = "transContract";
        public static final String INNOV_JSON_CATEGORIES = "innovContract";
        public static final String SERLI_JSON_CATEGORIES = "serliContract";

        //directory
        public static final String DATA_DIRECTORI = "LifeTarget";
        public static final String GENERAL_SETTINGS_NAME = "Life_General_Settings";
        //test db form
        public static final String ID = "Id";
        public static final String COLUMN_NAME_TITLEs = "Title";
        public static final String COLUMN_NAME_ADRSS = "Adress";
        public static final String COLUMN_NAME_RAT = "Rating";
        public static final String COLUMN_NAME_DESC = "Description";

        //hotel-resto
        public static final String hID = "id";
        public static final String COLUMN_NAME_hTITLEs = "title";
        public static final String COLUMN_NAME_name = "name";
        public static final String COLUMN_NAME_contact = "contact";
        public static final String COLUMN_NAME_mail = "mail";

        public static final String COLUMN_NAME_price = "price";
        public static final String COLUMN_NAME_extras = "extras";
        public static final String COLUMN_NAME_localisation = "localisation";
        public static final String COLUMN_NAME_primpimage = "primpimage";
        public static final String COLUMN_NAME_reserveone = "reserveone";
        public static final String COLUMN_NAME_reservetwo = "reservetwo";
        public static final String COLUMN_NAME_plusStr = "plusStr";

        public static final String COLUMN_NAME_hADRSS = "adress";
        public static final String COLUMN_NAME_hRAT = "rating";
        public static final String COLUMN_NAME_hDESC = "description";
        public static final String COLUMN_NAME_payement = "payement";
        public static final String COLUMN_NAME_horaire = "horaire";
        public static final String COLUMN_NAME_siteweb = "siteweb";
        public static final String COLUMN_NAME_longdescription ="longdescription" ;
        public static final String COLUMN_NAME_uniqueid ="uniqueid" ;
        public static final String COLUMN_NAME_rating ="rating" ;
        public static final String COLUMN_NAME_zonelivre ="zonelivre" ;
        public static final String COLUMN_NAME_maxlivre ="maxlivre" ;
        public static final String COLUMN_NAME_service = "service";
        public static final String COLUMN_NAME_pointfort ="pointfort" ;
        public static final String COLUMN_NAME_pointfaible = "pointfaible";
        public static final String COLUMN_NAME_prinpimage ="prinpimage" ;
        public static final String COLUMN_NAME_galeryOne = "galeryOne";
        public static final String COLUMN_NAME_galerytwo = "galerytwo";
        public static final String COLUMN_NAME_galeryfor ="galeryfor" ;
        public static final String COLUMN_NAME_galeryfour ="galeryfour" ;
        public static final String COLUMN_NAME_galerytree ="galerytree" ;
        public static final String COLUMN_NAME_galeryseven ="galeryseven" ;
        public static final String COLUMN_NAME_galeryfive ="galeryfive" ;
        public static final String COLUMN_NAME_galerysix ="galerysix" ;
        public static final String COLUMN_NAME_mets = "mets";
        public static final String COLUMN_NAME_more = "more";
        public static final String COLUMN_NAME_modified ="modified" ;
        public static final String COLUMN_NAME_transline="transline" ;
        public static final String COLUMN_NAME_loanbus ="loanbus" ;
        public static final String COLUMN_NAME_maxcap ="maxcap" ;

        public static final String COLUMN_NAME_innovname = "innovname";
        public static final String COLUMN_NAME_video ="video" ;
        public static final String COLUMN_NAME_bibliot="bibliot" ;
        public static final String COLUMN_NAME_stectn ="stectn" ;
        public static final String COLUMN_NAME_historq ="historq" ;

        public static final String SQL_CREATE_ENTRIES_HOTEL =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_HOTEL+ " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_hTITLEs + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_hADRSS + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_payement + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_siteweb  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_longdescription  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_rating  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfort  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfaible  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_prinpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfor  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfive  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerysix  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_mets  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_hDESC + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";

        public static final String SQL_CREATE_ENTRIES_LIFE =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_LIFE + " (" +
                        databaseContract.dataEntry.ID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_TITLEs + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_ADRSS + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_RAT + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_DESC + " TEXT)";

        public static final String SQL_CREATE_ENTRIES_RESTO =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_RESTO + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_hTITLEs + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_hADRSS + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_horaire + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_siteweb  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_longdescription  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_rating  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfort  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfaible  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_prinpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfor  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfive  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerysix  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_mets  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_hDESC + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";

        public static final String SQL_CREATE_ENTRIES_SITES =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_SITES + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_name + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_contact + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_horaire + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_mail  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfort  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_price  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_primpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytree  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfour  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfive  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerysix  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryseven  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_localisation  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_more  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reserveone  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reservetwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_extras  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_plusStr + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";

        public static final String SQL_CREATE_ENTRIES_SERLI =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_SERLI + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_name + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_contact + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_horaire + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_siteweb  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_price  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_extras  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_payement  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfort  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_zonelivre  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_primpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytree  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfour  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfive  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerysix  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_maxlivre + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reserveone  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reservetwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_hDESC + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";

        public static final String SQL_CREATE_ENTRIES_TRANS =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_TRANS + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_name + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_contact + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_horaire + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_extras  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_pointfort  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_price  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_payement  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_mail  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_primpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytree  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_hDESC + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_loanbus  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_transline + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reserveone  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reservetwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_maxcap  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_siteweb + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";

        public static final String SQL_CREATE_ENTRIES_INNOV =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_INNOV + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_name + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_contact + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_service  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_innovname + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_video  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_mail  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_primpimage  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryOne  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfour + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galerytree  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_hDESC + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_bibliot  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_stectn + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_historq  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_reservetwo  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_galeryfive  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_modified  + " TIMESTAMP," +
                        databaseContract.dataEntry.COLUMN_NAME_galerysix + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_uniqueid +"))";
    }
}