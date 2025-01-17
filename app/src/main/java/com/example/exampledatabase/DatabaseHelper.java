package com.example.exampledatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "ContatosDB";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE contatos(id INTEGER PRIMARY KEY AUTOINCREMENT,nome Text, telefone TEXT,email TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS contatos");
        onCreate(db);
    }
    //METODO PARA INSERIR UM CONTATO


    public boolean inserirContato(String nome, String telefone, String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome",nome);
        values.put("telefone",telefone);
        values.put("email",email);

        long resultado = db.insert("contatos",null,values);
        db.close();

        return resultado != -1;
    }

    //METODO PARA LISTAR OS CONTATOS


    public Cursor listarContatos(){
        SQLiteDatabase db = this.getWritableDatabase();

        return db.rawQuery("SELECT * FROM contatos", null);
    }

    //METODO PARA ATUALIZAR UM CONTATO

    public boolean atualizarContato(int id, String nome, String telefone, String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome",nome);
        values.put("telefone",telefone);
        values.put("email",email);

        long resultado = db.update("contatos", values, "id=?", new String[]{String.valueOf(id)});
        db.close();

        return resultado > 0;
    }

    //METODO PARA EXCLUIR CONTATO

    public boolean excluirContato(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        long resultado = db.delete("contatos", "id=?",new String[]{String.valueOf(id)});
        db.close();

        return resultado > 0;

    }
}