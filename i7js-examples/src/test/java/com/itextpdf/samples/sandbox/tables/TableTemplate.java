/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/22093993/itext-whats-an-easy-to-print-first-right-then-down
 */
package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class TableTemplate extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/tables/table_template.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new TableTemplate().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));

        Table table = new Table(UnitValue.createPercentArray(15)).useAllAvailableWidth();
        table.setWidth(1500);
        Cell cell;
        for (int r = 'A'; r <= 'Z'; r++) {
            for (int c = 1; c <= 15; c++) {
                cell = new Cell();
                cell.setMinHeight(45);
                cell.add(new Paragraph(String.valueOf((char) r) + String.valueOf(c)));
                table.addCell(cell);
            }
        }

        PdfFormXObject tableTemplate = new PdfFormXObject(new Rectangle(1500, 1300));
        Canvas canvas = new Canvas(tableTemplate, pdfDoc);
        canvas.add(table);
        PdfFormXObject clip;
        for (int j = 0; j < 1500; j += 500) {
            for (int i = 1300; i > 0; i -= 650) {
                clip = new PdfFormXObject(new Rectangle(500, 650));
                new PdfCanvas(clip, pdfDoc).addXObject(tableTemplate, -j, 650 - i);
                new PdfCanvas(pdfDoc.addNewPage()).addXObject(clip, 36, 156);
            }
        }

        pdfDoc.close();
    }
}
