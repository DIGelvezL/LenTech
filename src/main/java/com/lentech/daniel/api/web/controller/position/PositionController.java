package com.lentech.daniel.api.web.controller.position;

import com.lentech.daniel.api.delegate.position.PositionDelegate;
import com.lentech.daniel.api.web.dto.response.ILenTechResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/api/position")
@Api(value = "Position microservice", description = "This is Position's API")
@Validated
public class PositionController {

    private static final String API_KEY = "key";

    @Autowired
    private PositionDelegate positionDelegate;

    @RequestMapping(value = "/find", method = RequestMethod.GET, produces = "application/json", headers = API_KEY)
    @ApiOperation(value = "Find", notes = "Find Positions")
    public @ResponseBody
    ResponseEntity<ILenTechResponse> find(WebRequest webRequest) {

        return positionDelegate.findAll(webRequest.getHeader(API_KEY));
    }
}
