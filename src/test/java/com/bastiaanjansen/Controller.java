package com.bastiaanjansen;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    public static final String ENDPOINT = "/users";

    @GetMapping(ENDPOINT)
    @ApiVersion("1")
    public ResponseEntity<Object> findUsersV1() {
        return ResponseEntity.ok("Version 1");
    }

    @GetMapping(ENDPOINT)
    @ApiVersion("2")
    public ResponseEntity<Object> findUsersV2() {
        return ResponseEntity.ok("Version 2");
    }

}
