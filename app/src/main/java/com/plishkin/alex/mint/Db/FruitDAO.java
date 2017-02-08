package com.plishkin.alex.mint.Db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.plishkin.alex.mint.Entities.Fruit;

import java.sql.SQLException;
import java.util.List;


public class FruitDAO extends BaseDaoImpl<Fruit, Integer> {

    protected FruitDAO(ConnectionSource connectionSource, Class<Fruit> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Fruit> getAllFruits() throws SQLException {
        return this.queryForAll();
    }
}
