package com.example.demo.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationCondition {

    private boolean isInUse;

    public RegistrationCondition(){
        isInUse = false;
    }

}
