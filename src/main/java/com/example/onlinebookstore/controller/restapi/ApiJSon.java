package com.example.onlinebookstore.controller.restapi;

import com.example.onlinebookstore.model.dto.RequestOrderDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/json")
public class ApiJSon {

    @GetMapping("/jsonArray")
    public RequestOrderDTO getJsonArray() {
        RequestOrderDTO requestOrderDTO = new RequestOrderDTO();
        return requestOrderDTO;
    }

    @PostMapping("/jsonArray")
    public RequestOrderDTO postJsonArray(@RequestBody RequestOrderDTO requestOrderDTO) {
        RequestOrderDTO requestOrderDTOS= new RequestOrderDTO();
        return requestOrderDTOS;
    }


}
