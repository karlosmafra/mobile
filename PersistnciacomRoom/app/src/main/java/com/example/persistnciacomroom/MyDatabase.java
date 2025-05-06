package com.example.persistnciacomroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Usuario.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract UsuarioDAO usuarioDAO();

}
