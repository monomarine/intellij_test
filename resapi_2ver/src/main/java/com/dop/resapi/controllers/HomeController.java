package com.dop.resapi.controllers;

import com.dop.resapi.models.TemplateModel;

import  org.springframework.http.HttpStatus;
import  org.springframework.http.ResponseEntity;

import  org.springframework.stereotype.Controller;
import  org.springframework.web.bind.annotation.RequestMapping;
import  org.springframework.web.bind.annotation.RequestMethod;
import  org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Date;

@Controller
@RequestMapping("/rest/home")
public class HomeController {
    @ResponseBody
    @RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<TemplateModel> response(){
        Date date = new Date();
        Timestamp time = new Timestamp(date.getTime());

        TemplateModel tm = new TemplateModel("JSON-ответ", time.toString());

        return ResponseEntity.ok(tm);

    }
}
