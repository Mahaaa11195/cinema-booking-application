package com.movieapp.booking.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationModel {

    @Field("location_name")
    private String locationName;
    
    @Field("dates")
    private List<ShowDateModel> dates = new ArrayList<>();
    public List<ShowDateModel> getDates() {
        return dates;
    }

    public void setDates( List<ShowDateModel> dates) {
        if (dates == null) {
            this.dates = new ArrayList<>(); // Avoid null values
        } else {
            this.dates = dates;
        }
    }
//    @Field("shows")
//    private List<ShowModel> shows; // Each location has multiple showtimes
}

