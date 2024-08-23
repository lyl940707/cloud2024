package com.lyl.outbound.service;


import java.util.Map;

public interface FirstFeign {
    String test(Map<String, Object> map);
}
