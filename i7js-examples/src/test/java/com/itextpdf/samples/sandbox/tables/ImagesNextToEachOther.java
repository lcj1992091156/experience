/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/19700549/itextsharp-images-are-not-coming-next-to-one-another
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;
import java.net.MalformedURLException;

@Category(SampleTest.class)
public class ImagesNextToEachOther extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/tables/image_next_to_each_other.pdf";
    public static final String IMG1 = "./src/test/resources/img/javaone2013.jpg";
    public static final String IMG2 = "./src/test/resources/img/berlin2013.jpg";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new ImagesNextToEachOther().manipulatePdf(DEST);
    }

    public static Cell createImageCell(String path) throws MalformedURLException {
        Image img = new Image(ImageDataFactory.create(path));
        return new Cell().add(img.setAutoScale(true).setWidth(UnitValue.createPercentValue(100)));
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        Table table = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table.addCell(createImageCell(IMG1));
        table.addCell(createImageCell(IMG2));
        doc.add(table);

        doc.close();
    }
}
