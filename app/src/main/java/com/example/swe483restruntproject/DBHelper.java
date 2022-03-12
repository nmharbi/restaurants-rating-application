package com.example.swe483restruntproject;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.util.Log;

import com.example.swe483restruntproject.placeholder.CommentHolder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "Login.db";
    private ByteArrayOutputStream objectByteArrayInputStream;
    private byte[] imagebyte;
    public DBHelper(Context context) {
        super(context, "Login.db", null,1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table users (id INTEGER PRIMARY KEY AUTOINCREMENT,username TEXT,password TEXT,adminOf INTEGER)");
        MyDB.execSQL("CREATE TABLE restaurants (id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,descrption TEXT,speiclty TEXT,logo BLOB,latitude TEXT,longitude TEXT)");
        MyDB.execSQL("CREATE TABLE menus (id INTEGER PRIMARY KEY AUTOINCREMENT,item TEXT,price TEXT,restaurantid INTEGER)");
        MyDB.execSQL("CREATE TABLE ratings (id INTEGER PRIMARY KEY AUTOINCREMENT,rating REAL,comment TEXT,restaurantid INTEGER,usersid INTEGER)");
        MyDB.execSQL("CREATE TABLE photos (id INTEGER PRIMARY KEY AUTOINCREMENT,photoUrl BLOB,restaurantid INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists users ");
        MyDB.execSQL("drop Table if exists restaurant ");
        MyDB.execSQL("drop Table if exists menus ");
        MyDB.execSQL("drop Table if exists ratings ");
        MyDB.execSQL("drop Table if exists photos ");

    }

    public boolean insertUser(String username, String password,int adminOf){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password" , password);
        contentValues.put("adminOf" , adminOf);
        long result = MyDB.insert("users" , null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertRestaurants(String name , String descrption, String speiclty, Bitmap logo , String latitude , String longitude){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        objectByteArrayInputStream= new ByteArrayOutputStream();
        logo.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayInputStream);
        imagebyte = objectByteArrayInputStream.toByteArray();
        contentValues.put("name", name);
        contentValues.put("descrption" , descrption);
        contentValues.put("speiclty" , speiclty);
        contentValues.put("logo" , imagebyte);
        contentValues.put("latitude", latitude);
        contentValues.put("longitude" , longitude);
        long result = MyDB.insert("restaurants" , null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }

    public boolean updateLogo(Bitmap newlogo,String restName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        objectByteArrayInputStream= new ByteArrayOutputStream();
        newlogo.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayInputStream);
        imagebyte = objectByteArrayInputStream.toByteArray();
        contentValues.put("logo",imagebyte);
        MyDB.update("restaurants",contentValues,"name= '"+restName+"'",null);
        return true;
    }
    public boolean addPhoto(Bitmap photo,String restName){

        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        objectByteArrayInputStream= new ByteArrayOutputStream();
        photo.compress(Bitmap.CompressFormat.JPEG,100,objectByteArrayInputStream);
        imagebyte = objectByteArrayInputStream.toByteArray();
        contentValues.put("photoUrl",imagebyte);
        int restid= getRestaurantId(restName);
        contentValues.put("restaurantid",restid);
        MyDB.insert("photos",null,contentValues);
        return true;

    }
    public boolean insertMenuItem(String item , String price ,int restaurantid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("item", item);
        contentValues.put("price" , price);
        contentValues.put("restaurantid", restaurantid);
        long result = MyDB.insert("menus" , null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertRatings(double rating , String comment ,int restaurantid, int usersid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rating", rating);
        contentValues.put("comment" , comment);
        contentValues.put("restaurantid", restaurantid);
        contentValues.put("usersid", usersid);
        long result = MyDB.insert("ratings" , null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public boolean insertPhoto(String photoUrl , int restaurantid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("photoUrl", photoUrl);
        contentValues.put("restaurantid", restaurantid);
        long result = MyDB.insert("photos" , null, contentValues);
        if (result == -1)
            return false;
        else
            return true;
    }
    public String getResturantByName(String name){
        return null;
    }



    public Boolean checkusername (String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?" ,new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean checkusernamepassword (String username , String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ? and password = ?" ,new String[]{username,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public boolean checkRestaurantNameExist(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select * from restaurants where name = ?" ,new String[]{name});
        if(cursor.getCount() > 0 )
            return true;
        return false;
    }
    public void updateRestaurantNameExist(String oldName, String newName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int restId= getRestaurantId(oldName);
//        Cursor cursor = MyDB.rawQuery("UPDATE restaurants SET name = ? where name = ?" ,new String[]{oldName,newName});
        MyDB.execSQL("UPDATE restaurants SET name ='"+newName+"' where id = "+restId);
    }
    public int getUserId(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();


//        Cursor cursor =   MyDB.query("users",new String[]{"id","username","password","adminOf"},"username = '"+name+"'",null,null,null,null);
        String s[]= new String[1];
        s[0]= name;
        Log.e("ssssssss",s[0]);
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?" ,s);

       while(cursor.moveToNext())
        if(cursor.getCount() > 0)
            return cursor.getInt(0);
        return -1;
    }
    public int getRestaurantId(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        String s[]= new String[1];
        s[0]= name;
        Cursor cursor = MyDB.rawQuery("select * from restaurants where name = ?" ,s);
       while (cursor.moveToNext())
        if(cursor.getCount() > 0)
            return cursor.getInt(0);
        return -1;
    }
    public Cursor getComments(int restaurantid){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from ratings where restaurantid  = ?" ,new String[]{String.valueOf(restaurantid)});
        return cursor;
    }
    public Cursor getRestaurans(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from restaurants " ,new String[]{});
        return cursor;
    }
    public Cursor getRestauran(String restName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from restaurants where name = ? " ,new String[]{restName});
        return cursor;
    }
    public boolean hasRest(int userId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select adminOf from users where id = ?" ,new String[]{String.valueOf(userId)});
        while (cursor.moveToNext())
            if(cursor.getInt(0)>1)
                return true;
        return false;
    }
    public Cursor getAllRestAverageASC(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select name,speiclty,logo,avg(rating) from restaurants,ratings where restaurants.id =ratings.restaurantid  group by restaurants.id order by avg(rating) DESC " ,new String[]{});


        if(cursor.getCount() > 0)
            return cursor;
        return null;
    }
    public int getID(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from restaurants where name = ?" ,new String[]{name});
        while (cursor.moveToNext())
            return cursor.getInt(0);
        return -1;
    }
    public void makeAdmin(int userId , int restId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("UPDATE users SET adminof = ? WHERE id = ?" ,new String[]{String.valueOf(restId),String.valueOf(userId)});
        MyDB.execSQL("UPDATE users SET adminof = '"+restId+"' WHERE id = "+userId);
    }



    public boolean insertData (String comment){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("comment",comment);

        db.insert("MyData", null,contentValues);

        return true;
    }

    public Cursor getAllRestArabian(){
        SQLiteDatabase MyDB = this.getWritableDatabase();

        Cursor cursor = MyDB.rawQuery("select name,speiclty,logo,avg(rating) from restaurants,ratings where restaurants.id =ratings.restaurantid and speiclty = 'Arabic' group by restaurants.id " ,new String[]{});
        if(cursor.getCount() > 0)
            return cursor;
        return null;
    }
    public Cursor getAllRestBurgers(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select name,speiclty,logo,avg(rating) from restaurants,ratings where restaurants.id =ratings.restaurantid and speiclty = 'burgers' group by restaurants.id " ,new String[]{});
        if(cursor.getCount() > 0)
            return cursor;
        return null;
    }
    public Cursor getAllRestPizza(){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select name,speiclty,logo,avg(rating) from restaurants,ratings where restaurants.id =ratings.restaurantid and speiclty = 'pizza' group by restaurants.id " ,new String[]{});
        if(cursor.getCount() > 0)
            return cursor;
        return null;
    }
    public Cursor getRestByName(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
//        String cols[]={"id","name","descrption","speiclty","logo","latitude","longitude"};
//        Log.e("sssssssss",name);
//        Cursor cursor =   MyDB.query("restaurants",cols
//        ,"name ='"+name+"'",null,null,null,null);

        Cursor cursor = MyDB.rawQuery("select name,speiclty,logo,avg(rating) from restaurants,ratings where restaurants.id =ratings.restaurantid and name ='"+name+"' group by restaurants.id " ,new String[]{});

        Log.e("sssssssss",cursor.getCount()+"");
        if(cursor.getCount() > 0)
            return cursor;
        return null;
    }


    public Cursor getMenuItems(int restId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from menus where restaurantid = ?" ,new String[]{String.valueOf(restId)});
        return cursor;
    }
    public Cursor getPhotos(int restId){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from photos where restaurantid = ?" ,new String[]{String.valueOf(restId)});
        return cursor;
    }
    public void updateDec(String dec, String restName){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        int id = getRestaurantId(restName);
//        Cursor cursor = MyDB.rawQuery("UPDATE restaurants SET descrption = ? WHERE id = ?" ,new String[]{dec,String.valueOf(id)});
        MyDB.execSQL("UPDATE restaurants SET descrption = '"+dec+"' WHERE id = "+id);
    }
    public String getRestNameFromUser(String username){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where username = ?" ,new String[]{username});
        int restId = 0;
        while (cursor.moveToNext())
            restId=cursor.getInt(3);
        cursor = MyDB.rawQuery("select * from restaurants where id = ?" ,new String[]{String.valueOf(restId)});
        while (cursor.moveToNext())
            return cursor.getString(1);
        return "";
    }
    public String getName(int id){
        String name="";
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from users where id = ?" ,new String[]{String.valueOf(id)});
        while(cursor.moveToNext())
            return cursor.getString(1);
        return name;
    }





    @SuppressLint("Range") //added for error
    public Cursor getData(){
        ArrayList<CommentHolder> arrayList = new ArrayList<>();
        SQLiteDatabase db= this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from MyData", null);

        return res;
    }


    public boolean checkIfRestExist(String name){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("select * from restaurants where name = ?" ,new String[]{name});
        if(cursor.getCount() > 0)
            return true;
        return false;
    }
}
