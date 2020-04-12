package com.nju.software.util;

import org.springframework.stereotype.Component;

@Component
public class UUID {
    public String getUUID() {
        return java.util.UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }
}
