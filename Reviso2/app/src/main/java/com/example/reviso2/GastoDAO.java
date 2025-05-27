package com.example.reviso2;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface GastoDAO {

    @Insert
    void inserir(Gasto gasto);
    @Query("SELECT * FROM gastos")
    List<Gasto> buscarTodosGastos();
    @Query("SELECT * FROM gastos ORDER BY valor DESC LIMIT 1")
    Gasto buscarMaiorGasto();

}
