package com.pluralsight.repository;

import java.util.List;

import com.pluralsight.model.Ride;

public interface RideRepository {

    Ride getRide(Integer id);

    List<Ride> getRides();

    Ride createRide(Ride ride);

    Ride updateRide(Ride ride);

    void updateRides(List<Object[]> pairs);

    void deleteRide(Integer id);
}