package com.example.user.helloworld.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.ByteArrayOutputStream;

/**
 * Created by USER on 2015-11-28.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DB_NAME = "testDB";
    public static final String TABLE_NAME1 = "tb1";
    public static final int DB_VERSION = 7;

    private SQLiteDatabase mDB;
    private static DBHelper mDBHelper;

    private Context mContext;


    //싱글톤
    public static DBHelper getInstance(Context context) {

        if(mDBHelper == null) {
            mDBHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
        }
        return mDBHelper;
    }

    //생성자
    private DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    //DB를 오픈한다.
    public boolean openDB() {
        getWritableDatabase();
        mDB = mContext.openOrCreateDatabase(DB_NAME,
                Context.MODE_WORLD_WRITEABLE, null);
        return true;
    }

    //테이블 생성
    public void createTable(SQLiteDatabase db) {
        try {
            db.execSQL("CREATE TABLE " + TABLE_NAME1
                            + "( _id integer PRIMARY KEY autoincrement, "
                            + " name text, "
                            + " age integer, "
                            + " phone text,"
                            + " img BLOB"
                            + ");"
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //DB가 오픈되면 자동으로 호출되는 메서드
        //getWritableDatabase() 호출후 데이터베이스가 생성되자 않았다면 한번 호출된다.
        createTable(db);

    }


    public void insertData() {
        mDB.execSQL("INSERT INTO " + TABLE_NAME1
                        + " (name, age, phone) VALUES "
                        + " ('Jhone', 20, '010-1234-5678'); "
        );

        mDB.execSQL("INSERT INTO " + TABLE_NAME1
                        + " (name, age, phone) VALUES "
                        + " ('Michael', 40, '010-1111-2222'); "
        );

        mDB.execSQL("INSERT INTO " + TABLE_NAME1
                        + " (name, age, phone) VALUES "
                        + " ('Jhone Resik', 30, '010-2222-3333'); "
        );
    }

    public void insertBitmap(Bitmap bmp) {

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, out); //기존 이미지 압축
        //압축실행 --> 압축된 데이터가 out 객체로 전달
        byte[] buffer = out.toByteArray();

        mDB.beginTransaction();

        //Bitmap 객체는 DB에 ContentValues 객체를 통해서 Insert 한다.
        ContentValues values = new ContentValues();
        values.put("name", "TestImg");
        values.put("age", 10);
        values.put("phone", "111-222-5555");
        values.put("img", buffer);

        try {
            mDB.insert(TABLE_NAME1, null, values);

            mDB.setTransactionSuccessful();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            mDB.endTransaction();
        }

    }

    //DB로부터 이미지를 들고온다.
    public Bitmap getBmpImg() {

        Bitmap bmp = null;

        Cursor c = rawQuery("SELECT * FROM " + TABLE_NAME1);

        int imgIdx = c.getColumnIndex("img");

        for(int i=0; i<c.getCount(); i++) {
            c.moveToNext();

            byte[] arrImg = c.getBlob(imgIdx);

            if(arrImg != null && arrImg.length > 0) {
                bmp = BitmapFactory.decodeByteArray(arrImg, 0, arrImg.length);
                break; //하나만 찾으면 for문을 빠져나간다.
            }
        }

        return bmp;
    }




    @Override
    public void onOpen(SQLiteDatabase db) {
        Log.i("TEST", DB_NAME + " 데이터베이스가 정상적으로 오픈됨");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("TEST", "DB가 업그레이드 되었음. oldVersion:" + oldVersion
                + "newVersion: " + newVersion);

        if(db != null) {
            try {
                db.execSQL("drop table " + TABLE_NAME1);
            } catch(Exception e) {
                e.printStackTrace();
            }

            try {
                createTable(db);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //select
    public Cursor rawQuery(String sql) {
        return rawQuery2(sql, null);
    }

    //select + where
    public Cursor rawQuery2(String sql, String[] args) {
        Cursor c = null;
        try {
            c = mDB.rawQuery(sql, args);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return c;
    }

}
