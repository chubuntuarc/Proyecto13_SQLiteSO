package com.example.arciniega.proyecto13_sqliteso;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by arciniega on 15/07/16.
 */
public class MySQLiteHelper extends SQLiteOpenHelper{

    // Para la creación de la tabla comentario
    public static final String TABLE_COMENTARIO = "comentario";
    // Columnas de la tabla "comentario"
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_COMENTARIO = "comentario";
    // Para la creación de la base de datos
    public static final String DATABASE_NAME = "comentarios.db";
    // Construcción de la sentencia SQL para crear la tabla
    public static final String DATABASE_CREATE = "create table"
            + TABLE_COMENTARIO + "("
            + COLUMN_ID + " integer primary key autoincrement,"
            + COLUMN_COMENTARIO + " text not null);";
    public static final int DATABASE_VERSION = 1;

    // Constructor de la clase
    public MySQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        // Crea la base de datos al instalar la aplicación en el dispositivo móvil
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Actualizando la base de datos de la aplicación"
                );
        db.execSQL("DROP DATABASE IF EXISTS " + TABLE_COMENTARIO);
        onCreate(db);
    }
}
