package com.lukefitness.lukegymbackend.dto.response.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SimpleUserQueryResponse {
    int id;
    String username;
    String email;
}
