package com.plishkin.alex.mint.Db;


import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.plishkin.alex.mint.Entities.Contact;

import java.sql.SQLException;
import java.util.List;

public class ContactDAO extends BaseDaoImpl<Contact, Integer>{

    protected ContactDAO(ConnectionSource connectionSource, Class<Contact> dataClass) throws SQLException {
        super(connectionSource, dataClass);
    }

    public List<Contact> getAll() throws SQLException {
        return this.queryForAll();
    }
}
