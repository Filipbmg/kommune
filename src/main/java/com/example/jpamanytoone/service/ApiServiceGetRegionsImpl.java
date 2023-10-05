package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Region;
import com.example.jpamanytoone.repository.RegionRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetRegionsImpl implements ApiServiceGetRegions {
    private final RestTemplate restTemplate;
    private final RegionRepository regionRepository;
    public ApiServiceGetRegionsImpl(RestTemplate restTemplate, RegionRepository regionRepository) {
        this.restTemplate = restTemplate;
        this.regionRepository = regionRepository;
    }

    private void saveRegions(List<Region> regions) {
        regionRepository.saveAll(regions);
    }

    @Override
    public List<Region> getRegions() {

        String regionUrl = "https://api.dataforsyningen.dk/regioner";


        ResponseEntity<List<Region>> listResponseEntity =
                restTemplate.exchange(regionUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<>() {
                        });
        List<Region> regions = listResponseEntity.getBody();
        saveRegions(regions);
        return regions;
    }
}
