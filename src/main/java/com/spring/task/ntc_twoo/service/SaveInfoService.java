package com.spring.task.ntc_twoo.service;

import com.spring.task.ntc_twoo.model.Articles;
import org.apache.log4j.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Service;

@Service
public class SaveInfoService implements SaveInfoServiceIn {

    private static final Logger logger = Logger.getLogger(SaveInfoService.class);

    NewsService newsService = new NewsService();

    public ByteArrayOutputStream wordWrite(List<Articles> list) throws IOException {
        ByteArrayOutputStream fos = new ByteArrayOutputStream();

        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph paragraph = doc.createParagraph();

        for (Articles articles : list) {
            XWPFRun run = paragraph.createRun();
            run.setText(articles.getTitle());
            run.addBreak();
            run.addBreak();
            run.setText("Автор: " + articles.getAuthor());
            run.addBreak();
            run.addBreak();
            run.setText("Источник: " + articles.getSource());
            run.addBreak();
            run.addBreak();
            if (articles.getImageUrl() == null) {
                run.setText("нет изображения");
            } else {
                try {
                    run.addPicture(image(articles.getImageUrl()), XWPFDocument.PICTURE_TYPE_JPEG, "", Units.toEMU(200), Units.toEMU(200));
                } catch (IOException | InvalidFormatException e) {
                    logger.error(e);
                    run.setText("нет изображения");
                }
            }
            run.addBreak();
            run.addBreak();
            run.setText(articles.getDescription());
            run.addBreak();
            run.addBreak();
            run.setText("Дата публикации: " + articles.getPublishedAt());
            run.addBreak();
            run.addBreak();
            run.addBreak();
        }
        doc.write(fos);
        fos.flush();
        fos.close();
        doc.close();
        return fos;
    }

    public ByteArrayOutputStream saveCountry(String url) throws IOException {
        return wordWrite(newsService.countrySearch(url));
    }

    public void saveCategory(String url1, String ur2) throws IOException {
        wordWrite(newsService.categorySearch(url1, ur2));
    }

    public InputStream image(String url) throws IOException {
        URL urll = new URL(url);
        URLConnection connection = urll.openConnection();
        InputStream pic = urll.openStream();
        connection.setRequestProperty("User-Agent", "Chrome");
        return pic;
    }
}
