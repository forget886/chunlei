package com.wuxi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CircularReferA {

    @Autowired
    CircularReferB circularReferB;
}
