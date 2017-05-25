package com.nezspencer.signin.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;

/**
 * Created by AGBOMA franklyn on 10/29/16.
 */

public class WelcomeHelper extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "net_id.db";
    private static final int DATABASE_VERSION = 1;
    private Context context;

    public WelcomeHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    public interface Tables {

        String SMACK = "smack";
    }


    private final String SQL_CREATE_SMACK_TABLE = "CREATE TABLE IF NOT EXISTS "
            + Tables.SMACK + "("
            + BaseColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + WelcomeContact.Smack.CHAT + " TEXT"
            + ")";

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(SQL_CREATE_SMACK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ");
        onCreate(sqLiteDatabase);

    }

    public static void deleteDataHelper(Context context){
        context.deleteDatabase(DATABASE_NAME);
    }

}
