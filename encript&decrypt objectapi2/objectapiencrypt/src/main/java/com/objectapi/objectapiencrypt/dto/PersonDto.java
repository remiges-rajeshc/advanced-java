package com.objectapi.objectapiencrypt.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDto {

  //  private Long id;
    private String name;
    private int age;
    private String cardDetails;

}
