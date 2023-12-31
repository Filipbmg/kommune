package com.example.jpamanytoone.service;

import com.example.jpamanytoone.model.Kommune;
import com.example.jpamanytoone.repository.KommuneRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner{

    private final RestTemplate restTemplate;
    private final KommuneRepository kommuneRepository;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate, KommuneRepository kommuneRepository){
        this.restTemplate = restTemplate;
        this.kommuneRepository = kommuneRepository;
    }

    private void saveKommuner(List<Kommune> kommuneList){
        kommuneList.forEach(kommune -> kommuneRepository.save(kommune));
    }

    @Override
    public List<Kommune> getKommuner() {

        String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

        ResponseEntity<List<Kommune>> listResponseEntity =
                restTemplate.exchange(kommuneUrl, HttpMethod.GET, null,
                        new ParameterizedTypeReference<List<Kommune>>() {
                        });
        List<Kommune> kommuneList = listResponseEntity.getBody();
        saveKommuner(kommuneList);
        return kommuneList;
    }
}