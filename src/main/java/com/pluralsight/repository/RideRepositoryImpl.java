package com.pluralsight.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pluralsight.repository.util.RideRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.pluralsight.model.Ride;

@Repository("rideRepository")
public class RideRepositoryImpl implements RideRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Ride createRide(Ride ride) {
//		jdbcTemplate.update("insert into Ride (name , duration) values ( ?, ? ) ", ride.getName(), ride.getDuration());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement preparedStatement = connection.prepareStatement("insert into Ride (name , duration) values ( ?, ? ) ",
                        new String[]{"id"});
                preparedStatement.setString(1, ride.getName());
                preparedStatement.setInt(2, ride.getDuration());
                return preparedStatement;
            }
        }, keyHolder);

        Number id = keyHolder.getKey();
        return getRide(id.intValue());
    }

    @Override
    public Ride getRide(Integer id) {
        Ride ride = jdbcTemplate.queryForObject("Select * from Ride where id = ?", new RideRowMapper(), id);
        return ride;
    }

    @Override
    public List<Ride> getRides() {
        List<Ride> rides = jdbcTemplate.query("select * from Ride", new RideRowMapper());
        return rides;
    }

    @Override
    public Ride updateRide(Ride ride) {
        jdbcTemplate.update("update Ride set name = ? , duration = ? where id = ?",
                ride.getName(), ride.getDuration(), ride.getId());
        return ride;
    }

    @Override
    public void updateRides(List<Object[]> pairs) {
        jdbcTemplate.batchUpdate("update Ride set ride_date = ? where id = ?", pairs);
    }

    @Override
    public void deleteRide(Integer id) {
        //jdbcTemplate.update("delete from Ride where id = ?", id);
        NamedParameterJdbcTemplate namedTemplate = new NamedParameterJdbcTemplate(jdbcTemplate);
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("id", id);
        namedTemplate.update("delete from Ride where id = :id", paramMap);

    }


}
