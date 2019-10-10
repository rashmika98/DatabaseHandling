package Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "UserInfo.db";


    public DBHandler(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String SQL_CREATE_ENTRIES = "CREATE TABLE "+
                UserMaster.Users.TABLE_NAME+" ("+UserMaster.Users._ID+" INTEGER PRIMARY KEY, "+
                UserMaster.Users.COLUMN_NAME_USERNAME+" TEXT,"+
                UserMaster.Users.COLUMN_NAME_PASSWORD+" TEXT)";

        sqLiteDatabase.execSQL(SQL_CREATE_ENTRIES);

    }



    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addInfo(String username, String password) {

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_USERNAME, username);
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD, password);

        long rownum = db.insert(UserMaster.Users.TABLE_NAME, null, values);
    }


    public List readAllInfo() {
        SQLiteDatabase db =getReadableDatabase();

        String[] pro = {
                UserMaster.Users._ID,
                UserMaster.Users.COLUMN_NAME_USERNAME,
                UserMaster.Users.COLUMN_NAME_PASSWORD,


        };

        String sortOrder = UserMaster.Users.COLUMN_NAME_USERNAME + " DESC";

        Cursor cursor= db.query(UserMaster.Users.TABLE_NAME,
                pro,
                null,
                null,
                null,
                null,
                sortOrder);

        List username = new ArrayList<>();
        List password = new ArrayList<>();

        while (cursor.moveToNext()){
            String userName = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_USERNAME));
            String PassWord = cursor.getString(cursor.getColumnIndexOrThrow(UserMaster.Users.COLUMN_NAME_PASSWORD));


            username.add(userName);
            password.add(PassWord);

        }
        cursor.close();
        return username;


    }
    public int deleatInfo(String username)
    {
        SQLiteDatabase db =getReadableDatabase();

            String whereCondition  = UserMaster.Users.COLUMN_NAME_USERNAME + "=?";
            String[] args ={username};

            int result = db.delete(UserMaster.Users.TABLE_NAME,whereCondition,args);

            if (result>0)
            {
                return  1;
            }
            else
            {
                return  -1;
            }


    }

    public boolean updateInfo(String username,String password)
    {
        SQLiteDatabase db = getReadableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserMaster.Users.COLUMN_NAME_PASSWORD,password);

        String wherecondition = UserMaster.Users.COLUMN_NAME_USERNAME + "=?";
        String [] args = {username};
        int result = db.update(UserMaster.Users.TABLE_NAME,values,wherecondition,args);
        if (result > 0)
        {
            return true;
        }
        else
        {
            return false;
        }

    }

}
