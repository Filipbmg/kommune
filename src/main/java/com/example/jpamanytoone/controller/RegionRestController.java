package com.example.jpamanytoone.controller;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.service.ApiServiceGetRegions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RegionRestController {
    @Autowired
    ApiServiceGetRegions apiServiceGetRegions;

    @GetMapping("/getregions")
    public List<Region> getRegions() {
        List<Region> lstRegions = apiServiceGetRegions.getRegions();
        return lstRegions;
    }
}
