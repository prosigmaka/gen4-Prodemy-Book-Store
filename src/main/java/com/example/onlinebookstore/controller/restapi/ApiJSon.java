package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.RequestListOrderDTO;
import com.example.onlinebookstore.model.dto.RequestOrderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json")
public class ApiJSon {
    @Autowired
    private ModelMapper modelMapper;

    @GetMapping("/jsonArray")
    public RequestOrderDTO getJsonArray() {
        RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
        return requestOrderDTO;
    }

    @PostMapping("/jsonArray")
    public RequestOrderDTO postJsonArray(@RequestBody RequestListOrderDTO requestListOrderDTO) {
        RequestOrderDTO requestOrderDTO = modelMapper.map(requestListOrderDTO,RequestOrderDTO.class);
        return requestOrderDTO;
    }


}
