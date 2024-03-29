package com.example.arciniega.proyecto13_sqliteso;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by arciniega on 15/07/16.
 */
public class DataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] columnas = {
            MySQLiteHelper.COLUMN_ID,
            MySQLiteHelper.COLUMN_COMENTARIO
    };

    // Constructor de la clase
    public DataSource(Context context){
        // Se instancia un objeto de la clase MySQLiteHelper para utilizar sus métodos
        dbHelper = new MySQLiteHelper(context);
    }

    // Método para abrir la base de datos
    public void open() throws SQLException{
        database = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    // Método recibe un cursos 'ResultSet' para transformar a la clase Comentario
    public Comentario cursorToComentario(Cursor cursor){
        Comentario comentario = new Comentario();
        comentario.setId(cursor.getInt(0));
        comentario.setComentario(cursor.getString(1));

        return comentario;
    }

    // Método que recibe como parámetro una cadena de la clase 'Comentario' y lo inserta en la tabla
    public Comentario crearComentario(String comentario){
        // Objeto para formar a colección de campos de la tabla
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_COMENTARIO, comentario);

        // En este momento se insertan los datos en la tabla, regresando el valor del nuevo id .. recordar que es autoincrement
        long id = database.insert(MySQLiteHelper.TABLE_COMENTARIO, null, values);

        // Consulta a la base de datos el ultimo registro insertado
        Cursor cursor = database.query(MySQLiteHelper.TABLE_COMENTARIO,
                columnas,
                MySQLiteHelper.COLUMN_ID + "=" + id,
                null, null, null, null);

        cursor.moveToFirst();

        Comentario nvocomentario = cursorToComentario(cursor);

        return nvocomentario;
    }

    // Método para eliminar un registro de la tabla
    public void eliminar(Comentario comentario){
        long id = comentario.getId();

        // Se elimina el registro tomando como base el id del objeto 'comentario'
        database.delete(MySQLiteHelper.TABLE_COMENTARIO,
                MySQLiteHelper.COLUMN_ID + "=" + id, null);

        // Confirmar en consola
        System.out.println("Registro eliminado " + id);
    }

    // Método para devolver un listado de todos los registros exostentes en la tabla
    public List<Comentario> listarComentarios(){

        List<Comentario> comentarios = new ArrayList<>();

        Cursor cursor = database.query(
          MySQLiteHelper.TABLE_COMENTARIO,columnas,null,null,null,null,null
        );

        // ..se debe convertir en un list
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Comentario comentario = cursorToComentario(cursor);
            comentarios.add(comentario);
            cursor.moveToNext();
        }

        cursor.close();

        // Se devuelve el objeto list
        return comentarios;
    }
}
