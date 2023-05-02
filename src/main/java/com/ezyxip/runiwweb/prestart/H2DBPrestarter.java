package com.ezyxip.runiwweb.prestart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component("H2DBPrestarter")
public class H2DBPrestarter implements SourcePrestarter{

    static Logger logger = Logger.getLogger(H2DBPrestarter.class.getName());

    @Autowired
    DataSource dataSource;

    @Override
    public boolean check() {
        return false;
    }

    @Override
    public boolean create() {
        Connection con = null;
        Statement statement = null;
        try {
            con = dataSource.getConnection();
            statement = con.createStatement();
            statement.execute(
                    "create table if not exists users(\n" +
                            "    id INT NOT NULL AUTO_INCREMENT,\n" +
                            "    name VARCHAR (30) NOT NULL,\n" +
                            "    password VARCHAR(30) NOT NULL,\n" +
                            "    PRIMARY KEY (id)\n" +
                            ")");
            statement.execute(
                    "insert into users select 1, 'superuser', 'changeme' from dual " +
                    "where not exists(" +
                    "select * from users where id = '1'" +
                    ")");

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Не удалось выполнить запрос", e);
            return false;
        } finally {
            try {
                con.close();
                statement.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return true;
    }
}
