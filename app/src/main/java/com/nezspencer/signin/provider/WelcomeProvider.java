package com.nezspencer.signin.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.nezspencer.signin.helper.SelectionBuilder;


/**
 * Created by AGBOMA franklyn on 10/29/16.
 */
public class WelcomeProvider extends ContentProvider {

    WelcomeHelper dh;
    SQLiteDatabase dbr,dbw;

    private static final UriMatcher uriMatcher = buildUriMatcher();

    //Integers to match DataBase when searched
    private static final int SMACK_LIST = 2;
    private static final int SMACK_ID = 3;


    private Context context;

    @Override
    public boolean onCreate() {
        dh = new WelcomeHelper(getContext());
        dbr = dh.getReadableDatabase();
        dbw = dh.getWritableDatabase();

        return true;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        if(uri == WelcomeContact.BASE_CONTENT_URI){

            dh.close();
            WelcomeHelper.deleteDataHelper(getContext());
            dh = new WelcomeHelper(getContext());

            return 1;
        }

        final SelectionBuilder sBuilder = selectionBuilder(uri, uriMatcher.match(uri));
        int deleteValue = sBuilder.where(selection, selectionArgs).delete(dbw);
        notify(uri);

        return deleteValue;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        int match = uriMatcher.match(uri);
        final SelectionBuilder sBuilder =  selectionBuilder(uri, match);

        switch (match){

            case SMACK_LIST:
                if(TextUtils.isEmpty(sortOrder))
                    sortOrder = WelcomeContact.Smack.SORT_ORDER;
                break;

        }
        return sBuilder.where(selection, selectionArgs).query(dbr, projection, sortOrder);
    }

    @Nullable
    @Override
    public String getType(Uri uri) {

        int match = uriMatcher.match(uri);

        switch (match){

            case SMACK_LIST:
                return WelcomeContact.Smack.CONTENT_DIR;
            case SMACK_ID:
                return WelcomeContact.Smack.CONTENT_ITEM;

            default:
                throw new UnsupportedOperationException("uri not available "+ match);
        }
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {

        int match = uriMatcher.match(uri);
        long id = 0;

        switch (match){
            case SMACK_LIST:{
                id = dbw.insert(WelcomeHelper.Tables.SMACK, null, contentValues);
                notify(uri);
                return getUriFoldId(uri, id);
            }

            default:{
                throw new UnsupportedOperationException("Unknown uri Dir: "+ uri);
            }
        }
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        final SelectionBuilder sBuilder =  selectionBuilder(uri, uriMatcher.match(uri));

        int updated_value = sBuilder.where(selection, selectionArgs).update(dbw,values);
        notify(uri);

        return updated_value;
    }



    public static UriMatcher buildUriMatcher(){

        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);

        matcher.addURI(WelcomeContact.AUTHORITY, WelcomeContact.PATH_SMACK, SMACK_LIST);
        matcher.addURI(WelcomeContact.AUTHORITY, WelcomeContact.PATH_SMACK + "/#", SMACK_ID);


        return matcher;
    }

    private SelectionBuilder selectionBuilder(Uri uri, int match){

        final SelectionBuilder sBuilder = new SelectionBuilder();
        int setArgs = 1;

        switch (match){

            case SMACK_LIST: {
                return sBuilder.table(WelcomeHelper.Tables.SMACK);
            }
            case SMACK_ID:{
                final String idArgs = uri.getPathSegments().get(setArgs);
                return sBuilder.table(WelcomeHelper.Tables.SMACK)
                        .where(WelcomeContact.Smack._ID + "=?", idArgs);
            }

            default:{
                throw new UnsupportedOperationException("Unknown uri found: "+ match + ": " + uri);
            }
        }
    }


    private void notify(Uri uri){

        context = getContext();
        context.getContentResolver().notifyChange(uri, null);
    }


    private static Uri getUriFoldId(Uri uri, long id){

        if(id > 0){
            Uri newUri = ContentUris.withAppendedId(uri, id);
            return newUri;
        }

        throw new SQLException("Problem Appending Uri: " + uri);
    }
}
