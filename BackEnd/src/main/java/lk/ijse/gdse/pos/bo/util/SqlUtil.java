package lk.ijse.gdse.pos.bo.util;

import lk.ijse.gdse.pos.db.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUtil {
    private static PreparedStatement execute(String sql, Object... params) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement(sql);
        for (int i = 0; i < params.length; i++) {
            stm.setObject((i + 1), params[i]);
        }
        return stm;
    }

    public static ResultSet executeQuery(String sql, Object... params) throws SQLException, ClassNotFoundException {
        ResultSet resultSet = execute(sql, params).executeQuery();
       // System.out.println("Rst :"+resultSet.toString());
        return resultSet;

    }

    public static boolean executeUpdate(String sql, Object... params) throws SQLException, ClassNotFoundException {
        return execute(sql, params).executeUpdate() > 0;
    }

}
