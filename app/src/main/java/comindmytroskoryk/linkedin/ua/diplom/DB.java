package comindmytroskoryk.linkedin.ua.diplom;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Dem on 16.01.2017.
 */
/*
public class DB {

    // Переменные для хранения названия БД, таблицы, столбцов, версии БД и строки создания БД
    private static final String DB_NAME = "Diplom_DB";
    private static final int DB_VERSION = 1;
    private static final String DB_TABLE = "Diplom_TABLE";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_DESCRIPTION = "description";

    private static final String DB_CREATE =
            "create table " + DB_TABLE + "(" +
                    COLUMN_ID + " integer primary key autoincrement, " +
                    COLUMN_TITLE + " title, " + COLUMN_DESCRIPTION + " description" + ");";
    //

    // Объекты для работы с БД
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;
    private final Context mCtx;
    private ArrayList<Unswer> get_unswer = new ArrayList<>();
    ContentValues cv = new ContentValues();
    //

    // Конструктор
    public DB(Context mCtx) {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        this.mCtx = mCtx;
    }

    // Открыть подключение к БД
    public void open() {
        mDB = mDBHelper.getWritableDatabase();
    }
    //

    // Закрыть подключение
    public void close() {
        if (mDBHelper != null) mDBHelper.close();
    }
    //

    // Получить все данные из таблицы DB_TABLE
    public Cursor getAllData() {
        return mDB.query(DB_TABLE, null, null, null, null, null, null);
    }

    public void write_DB(ArrayList<Unswer> get_unswer) {
        for (Unswer in : get_unswer) {
            cv.put(COLUMN_TITLE, in.getTitle());
            cv.put(COLUMN_DESCRIPTION, in.getDescription());
            mDB.insert(DB_TABLE, null, cv);
        }
    }
    //



    // Вложенный класс по созданию и управлению БД
    private class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        // Создаем БД и заполняем ее полученными данными
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        }
    }
    //
}
*/