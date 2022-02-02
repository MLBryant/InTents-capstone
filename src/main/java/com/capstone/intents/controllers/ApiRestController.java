package com.capstone.intents.controllers;

import com.capstone.intents.model.ApiResponse;
import com.capstone.intents.model.Code;
import com.capstone.intents.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ApiRestController {

    private final ApiService apiService;

    @Autowired
    public ApiRestController(ApiService apiService) {
        this.apiService = apiService;
    }

    @PostMapping(value = "/search", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ApiResponse> getSearch(@RequestBody Code code) {
        return apiService.getSearch(code);
    };
}
