package com.pluralsight.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.pluralsight.model.Ride;
import com.pluralsight.service.RideService;

@Controller
public class RideController {

    @Autowired
    private RideService rideService;

    @RequestMapping(value = "/rides", method = RequestMethod.GET)
    public @ResponseBody
    List<Ride> getRides() {
        return rideService.getRides();
    }

    @RequestMapping(value = "/rides", method = RequestMethod.PUT)
    public @ResponseBody
    Ride createRides(@RequestBody Ride ride) {
        return rideService.createRide(ride);
    }

    @RequestMapping(value = "/ride/{id}", method = RequestMethod.GET)
    public @ResponseBody
    Ride getRide(@PathVariable(value = "id") Integer id) {
        return rideService.getRide(id);
    }

    @RequestMapping(value = "/ride", method = RequestMethod.PUT)
    public @ResponseBody
    Ride updateRide(@RequestBody Ride ride) {
        return rideService.updateRide(ride);
    }

    @RequestMapping(value = "/batch", method = RequestMethod.GET)
    public @ResponseBody
    Object batch() {
        rideService.batch();
        return null;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public @ResponseBody
    Object delete(@PathVariable(value = "id") Integer id) {

        rideService.deleteRide(id);
        return null;
    }


}
