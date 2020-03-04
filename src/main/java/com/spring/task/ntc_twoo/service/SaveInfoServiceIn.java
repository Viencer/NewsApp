package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface SaveInfoServiceIn {

    ByteArrayOutputStream wordWrite(List<Articles> list) throws IOException;

    InputStream image(String url) throws IOException;

    ByteArrayOutputStream saveCountry(String url) throws IOException;

    void saveCategory(String url1, String ur2) throws IOException;
}
