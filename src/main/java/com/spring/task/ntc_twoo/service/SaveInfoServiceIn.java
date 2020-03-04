package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface SaveInfoServiceIn {

     void wordWrite(List<Articles> list) throws FileNotFoundException;
     InputStream image(String url) throws IOException;
}
