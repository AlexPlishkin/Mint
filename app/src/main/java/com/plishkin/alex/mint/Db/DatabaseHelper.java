package com.plishkin.alex.mint.Db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.plishkin.alex.mint.Entities.Fruit;

import java.sql.SQLException;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static String DATABASE_NAME = "database.db";
    private static int DATABASE_VERSION = 1;

    private FruitDAO fruitDAO = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(getConnectionSource(), Fruit.class);
        } catch (SQLException e) {
            throw new RuntimeException("Could not create database");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Fruit.class, true);
        } catch (SQLException e) {
            throw new RuntimeException("Could not upgrade db");
        }
    }

    @Override
    public void close() {
        super.close();
        fruitDAO = null;
    }

    public FruitDAO getFruitDAO() throws SQLException {
        if (fruitDAO == null)
            fruitDAO = new FruitDAO(getConnectionSource(), Fruit.class);
        return fruitDAO;
    }
}
