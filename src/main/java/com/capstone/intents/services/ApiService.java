package com.capstone.intents.services;

import com.capstone.intents.model.ApiResponse;
import com.capstone.intents.model.Code;

import java.util.List;

public interface ApiService {
    List<ApiResponse> getSearch(Code code);
}
