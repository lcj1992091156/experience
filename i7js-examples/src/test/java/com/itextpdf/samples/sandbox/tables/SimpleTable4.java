/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie and Nishanthi Grashia in answer to the following question:
 * http://stackoverflow.com/questions/28073190/itext-maintain-identing-if-paragraph-takes-new-line-in-pdfpcell
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class SimpleTable4 extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/tables/simple_table4.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new SimpleTable4().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        // Here in itext7 there is only one way of adding paragraph to table. See itext5 example.
        Table table = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        Paragraph right = new Paragraph("This is right, because we create a paragraph with an indentation to the left and as we are adding the paragraph in composite mode, all the properties of the paragraph are preserved.");
        right.setMarginLeft(20);
        Cell rightCell = new Cell().add(right);
        table.addCell(rightCell);
        doc.add(table);

        doc.close();
    }
}
