package com.spring.task.ntc_twoo.model;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.net.URL;
import java.util.List;

import org.apache.poi.xwpf.usermodel.Document;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

public class Word {

    public static void create() throws IOException {
        FileOutputStream fos = new FileOutputStream(new File("save.docx"));
        XWPFDocument doc = new XWPFDocument();
        XWPFParagraph paragraphX = doc.createParagraph();
        XWPFRun run = paragraphX.createRun();
        run.setText("News");
        doc.write(fos);
        fos.flush();
        fos.close();
        doc.close();
    }

    public static void wordWrite(List<Articles> list) throws IOException, InvalidFormatException {
        CustomXWPFDocument document = new CustomXWPFDocument(new FileInputStream(new File("save.docx")));
        FileOutputStream fos = new FileOutputStream(new File("save.docx"));
        XWPFParagraph paragraph = document.createParagraph();

        for (Articles articles : list) {
            XWPFRun run = paragraph.createRun();
            run.setText(articles.getTitle());
            run.addBreak();
            run.setText(articles.getAuthor());
            run.addBreak();
            run.setText(articles.getSource());
            run.addBreak();
            InputStream input = new URL(articles.getImageUrl()).openStream();
            String blipId2 = paragraph.getDocument().addPictureData(input, Document.PICTURE_TYPE_PNG);
            document.createPicture(blipId2, document.getNextPicNameNumber(Document.PICTURE_TYPE_JPEG), 400, 400);
            run.addBreak();
            run.setText(articles.getDescription());
            run.addBreak();
            run.setText(articles.getPublishedAt());
            run.addBreak();
            run.addBreak();
        }
        document.write(fos);
        fos.flush();
        fos.close();
        document.close();
    }
}
