package com.capstone.intents.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse {
    private String agencyIcon;
    private String agencyName;
    private String contractId;
    private String contractType;
    private String facilityId;
    private String facilityName;
    private String facilityPhoto;
    private String favorite;
    private String latitude;
    private String listingOnly;
    private String longitude;
    private String regionName;
    private String shortName;
    private String sitesWithAmps;
    private String sitesWithPetsAllowed;
    private String sitesWithSewerHookup;
    private String sitesWithWaterHookup;
    private String sitesWithWaterfront;
    private String state;
}
