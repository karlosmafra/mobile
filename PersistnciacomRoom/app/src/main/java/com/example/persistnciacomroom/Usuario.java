package com.example.persistnciacomroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "tab_usuario")
public class Usuario {

    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo
    String login;
    @ColumnInfo
    String senha;

}
