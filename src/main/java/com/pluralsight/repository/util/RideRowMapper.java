package com.pluralsight.repository.util;

import com.pluralsight.model.Ride;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RideRowMapper implements RowMapper<Ride> {
    @Override
    public Ride mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Ride ride = new Ride();
        ride.setId(resultSet.getInt("id"));
        ride.setName(resultSet.getString("name"));
        ride.setDuration(resultSet.getInt("duration"));
        return ride;
    }
}
