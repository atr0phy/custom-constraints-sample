package com.example.customconstraintssample

import jakarta.validation.Valid
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
@Validated
class RootController {
    @PostMapping("/")
    fun index(
        @RequestBody @Valid request: IndexRequest,
    ): String {
        return "ok"
    }
}
