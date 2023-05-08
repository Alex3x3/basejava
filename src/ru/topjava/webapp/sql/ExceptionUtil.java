package ru.topjava.webapp.sql;

import org.postgresql.util.PSQLException;
import ru.topjava.webapp.exception.ExistStorageException;
import ru.topjava.webapp.exception.StorageException;

import java.sql.SQLException;

public class ExceptionUtil {
    private ExceptionUtil() {
    }

    public static StorageException convertException(SQLException e) {
        if (e instanceof PSQLException) {

            if (e.getSQLState().equals("23505")) {
                return new ExistStorageException(null);
            }
        }
        return new StorageException(e);
    }
}