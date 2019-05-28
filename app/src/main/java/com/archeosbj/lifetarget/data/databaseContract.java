package com.archeosbj.lifetarget.data;

import android.provider.BaseColumns;

public final class databaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private databaseContract() {}

    public static class AppConfig {
        public static final String URL_POST_FAVORITE = "http://lifetargeteasy.com/server/interact/api/client/setfav.php";
        // Server user login url
        public static String URL_LOGIN = "http://lifetargeteasy.com/server/interact/api/client/login.php";
        public static final String SET_MSG_LINK_URL = "http://lifetargeteasy.com/server/interact/api/msg/setmsg.php";
        // Server user register url
        public static String URL_REGISTER = "http://lifetargeteasy.com/server/interact/api/client/register.php";
    }

    /* Inner class that defines the table contents */
    public static class dataEntry implements BaseColumns {

        //feed section
        public static final String FEED_LINK_URL = "http://lifetargeteasy.com/server/interact/api/feed/getfeed.php";
        public static final String SET_FEED_LINK_URL = "http://lifetargeteasy.com/server/interact/api/feed/setfeed.php";
        public static final String PROFILS_LINK_URL = "http://lifetargeteasy.com/server/interact/api/feed/getprofil.php";
        public static final String SET_PROFILS_LINK_URL = "http://lifetargeteasy.com/server/interact/api/feed/setprofil.php";
        //data link
        public static final String HOTEL_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/gethotel.php";
        public static final String RESTO_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/getresto.php";
        public static final String TRANS_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/gettrans.php";
        public static final String INNOV_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/getinnov.php";
        public static final String FAV_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/getfav.php";
        public static final String SERLI_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/getserli.php";
        public static final String SITES_LINK_URL = "http://lifetargeteasy.com/server/interact/api/service/getsites.php";
        public static final String GET_MSG_LINK_URL = "http://lifetargeteasy.com/server/interact/api/msg/getmsg.php";

        public static final String BLANK_COOKIES_URL ="http://lifetargeteasy.com/server/interact/api/service/getgetting.php";
        public static final String SERVER_IMGURL_API = "http://lifetargeteasy.com/server/interact/uploads/";
        public static final String SERVER_IMGURL_FEED = "http://lifetargeteasy.com/server/interact/uploads/feedimages/";
        public static final String SERVER_IMGURL_PROFILS = "http://lifetargeteasy.com/server/interact/uploads/useracc/";

        //distilled data resto/feedimages/
        public static final String TABLE_NAME_RESTO = "liferesto";
        public static final String TABLE_NAME_HOTEL = "lifehotel";
        public static final String TABLE_NAME_SITES = "lifesites";
        public static final String TABLE_NAME_TRANS = "lifetrans";
        public static final String TABLE_NAME_INNOV = "lifeinnov";
        public static final String TABLE_NAME_FAV = "ltappsfav";
        public static final String TABLE_NAME_SERLI = "lifeserli";
        public static final String TABLE_NAME_PROFILS = "ltappsprof";
        public static final String TABLE_NAME_MSG = "ltappsmsg";
        public static final String TABLE_NAME_FAVL = "localFavoritees";

        public static final String TABLE_NAME = "lifetest";
        public static final String TABLE_NAME_LIFE = "lifetest";
        public static final String COLUMN_NAME_TITLE = "title";
        public static final String COLUMN_NAME_SUBTITLE = "subtitle";
        public static final int DATABASE_VERSION = 1;
        public static final String DATABASE_NAME = "lifedbase.db";

        //database JSOn Header
        public static final String FEED_JSON_CATEGORIES = "feedContract";

        public static final String PROFILS_JSON_CATEGORIES = "phContract";
        public static final String MESSAGE_JSON_CATEGORIES = "msgContract";
        public static final String FAVST_JSON_CATEGORIES = "favstContract";

        public static final String HOTEL_JSON_CATEGORIES = "hotelContract";
        public static final String RESTO_JSON_CATEGORIES = "restoContract";
        public static final String SITES_JSON_CATEGORIES = "sitesContract";
        public static final String TRANS_JSON_CATEGORIES = "transContract";
        public static final String INNOV_JSON_CATEGORIES = "innovContract";
        public static final String FAV_JSON_CATEGORIES = "favContract";
        public static final String MSG_JSON_CATEGORIES = "msgContract";
        public static final String SERLI_JSON_CATEGORIES = "serliContract";

        private static final String TABLE_USER = "lfeappusers";

        // Login Table Columns names
        private static final String KEY_ID = "id";
        private static final String KEY_NAME = "name";
        private static final String KEY_PH = "settings";
        private static final String KEY_EMAIL = "email";
        private static final String KEY_UID = "uid";
        private static final String KEY_CREATED_AT = "created_at";
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

        public static final String COLUMN_NAME_formsg = "formsg";
        public static final String COLUMN_NAME_ofmsg = "ofmsg";
        public static final String COLUMN_NAME_msg = "msg";
        public static final String COLUMN_NAME_onmsg = "onmsg";

        public static final String COLUMN_NAME_foremail = "foremail";
        public static final String COLUMN_NAME_imgvar = "imgvar";
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

        public static final String COLUMN_NAME_unic ="uniqId" ;
        public static final String COLUMN_NAME_imurl ="urlimg" ;

        public static final String COLUMN_NAME_fvuniqid ="fvuniqid" ;
        public static final String COLUMN_NAME_byemail="byemail" ;
        public static final String COLUMN_NAME_boolvar ="boolvar" ;
        public static final String COLUMN_NAME_genre ="genre" ;
        public static final String COLUMN_NAME_created_at ="created_at" ;

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

        public static final String SQL_DELETE_ENTRIES_HOTEL =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_HOTEL;

        public static final String SQL_CREATE_ENTRIES_LIFE =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_LIFE + " (" +
                        databaseContract.dataEntry.ID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_TITLEs + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_ADRSS + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_RAT + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_DESC + " TEXT)";

        public static final String SQL_DELETE_ENTRIES_LIFE =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_LIFE;

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

        public static final String SQL_DELETE_ENTRIES_RESTO =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_RESTO;

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

        public static final String SQL_DELETE_ENTRIES_SITES =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_SITES;

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

        public static final String SQL_DELETE_ENTRIES_SERLI =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_SERLI;

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

        public static final String SQL_DELETE_ENTRIES_TRANS =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_TRANS;

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

        public static final String SQL_CREATE_ENTRIES_FAV =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_FAV + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_fvuniqid + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_byemail + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_boolvar + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_genre  + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_created_at + " TEXT)";
        public static final String SQL_DELETE_ENTRIES_FAV =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_FAV;

        public static final String CREATE_LOGIN_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_USER + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT,"
                + KEY_PH + " TEXT,"
                + KEY_EMAIL + " TEXT UNIQUE," + KEY_UID + " TEXT,"
                + KEY_CREATED_AT + " TEXT" + ")";

        public static final String SQL_DELETE_ENTRIES_INNOV =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_INNOV;

        public static final String SQL_DELETE_ENTRIES_PROFILS =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_PROFILS;

        public static final String SQL_DELETE_ENTRIES_MSG =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_MSG;

        public static final String SQL_DELETE_ENTRIES_FAVL =
                "DROP TABLE IF EXISTS " + dataEntry.TABLE_NAME_FAVL;

        public static final String SQL_CREATE_ENTRIES_PROFILS =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_PROFILS + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        dataEntry.COLUMN_NAME_foremail + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_imgvar + " TEXT, unique ("+
                        databaseContract.dataEntry.COLUMN_NAME_foremail +"))";

        public static final String SQL_CREATE_ENTRIES_MSG =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_MSG + " (" +
                        databaseContract.dataEntry.hID + " INTEGER PRIMARY KEY," +
                        dataEntry.COLUMN_NAME_formsg + " TEXT," +
                        dataEntry.COLUMN_NAME_ofmsg + " TEXT," +
                        dataEntry.COLUMN_NAME_msg + " TEXT," +
                        dataEntry.COLUMN_NAME_onmsg + " TEXT," +
                        dataEntry.COLUMN_NAME_created_at + " TEXT)";

        public static final String SQL_CREATE_ENTRIES_FAVLS =
                "CREATE TABLE IF NOT EXISTS " + dataEntry.TABLE_NAME_FAVL + " (" +
                        databaseContract.dataEntry.ID + " INTEGER PRIMARY KEY," +
                        databaseContract.dataEntry.COLUMN_NAME_TITLEs + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_ADRSS + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_RAT + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_unic + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_imurl + " TEXT," +
                        databaseContract.dataEntry.COLUMN_NAME_DESC + " TEXT)";


        public static final String DEFAULT_PREFS_SETTINGS_KEY_RESTO  = "GetedRestoSET";
        public static final String DEFAULT_PREFS_SETTINGS_KEY_SERLI  = "GetedSerliSET";
        public static final String DEFAULT_PREFS_SETTINGS_KEY_INNOV  = "GetedInnovSET";

        public static final String DEFAULT_PREFS_SETTINGS_KEY_HOTEL  = "GetedHotelSET";
        public static final String DEFAULT_PREFS_SETTINGS_KEY_TRANS  = "GetedTransSET";
        public static final String DEFAULT_PREFS_SETTINGS_KEY_SITES  = "GetedSitesSET";

        public static final String DEFAULT_PREFS_SETTINGS_KEY_PROFILS_NM = "PrfNomAcc";
    }
}