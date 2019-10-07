package tax.megakent.org.vergicontrol.Main.DatabaseHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME   ="VergiDB";
    private static final int SURUM =1;
    private static final String TABLE_PERSON = "PERSON";
    private String sicilNo=" ";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, SURUM);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql="CREATE TABLE "+ TABLE_PERSON + " (id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "personTC TEXT , " +
                "personPassword TEXT ,"+
                "personSicilNo TEXT "+")";
        Log.d("DBHelper","SQL :" + sql);//Hatayı tespit etmek için yazıldı

        try {
            db.execSQL(sql);//sql komutu çalıştırıldı.
        }catch (SQLException e){
            e.getCause();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXIST " + TABLE_PERSON);

        onCreate(db);
    }
    public String getSicilNo(String gelen_Tc){

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_PERSON,null,  "personTC=?",new String[]{gelen_Tc},null, null, null);
        if(cursor.getCount()<1){
            cursor.close();
            return "Not Exist";
        }
        else if(cursor.getCount()>=1 && cursor.moveToFirst()){

            sicilNo = cursor.getString(cursor.getColumnIndex("personSicilNo"));
            cursor.close();
        }
        return sicilNo;
    }
    public boolean Login(String userTC, String userPassword) throws SQLException
    {   SQLiteDatabase db = this.getReadableDatabase();
        Cursor mCursor = db.rawQuery("SELECT * FROM " + TABLE_PERSON + " WHERE personTC=? AND personPassword=?", new String[]{userTC,userPassword});
        if (mCursor != null) {
            if(mCursor.getCount() > 0)
            {
                return true;
            }
        }
        return false;
    }
    public void insertPerson( String tcKimlik, String password,String sicilNo) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("personTC" ,tcKimlik);
        contentValues.put("personPassword" , password);
        contentValues.put("personSicilNo",sicilNo);
        db.insert(TABLE_PERSON,null,contentValues);
        db.close();
    }
}
