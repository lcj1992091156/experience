/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to:
 * http://stackoverflow.com/questions/36259214
 */

package com.itextpdf.samples.sandbox.tables;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.borders.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.UnitValue;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class NestedTables4 extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/tables/nested_tables4.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new NestedTables4().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        // Note that it is not necessary to create new PageSize object,
        // but for testing reasons (connected to parallelization) we call constructor here
        Document doc = new Document(pdfDoc, new PageSize(PageSize.A4).rotate());

        Table table = new Table(UnitValue.createPercentArray(new float[]{1, 12, 8, 1}));
        table.setBorder(new SolidBorder(1));
        // first row
        Cell cell = new Cell(1, 4).add(new Paragraph("Main table"));
        cell.setBorder(Border.NO_BORDER);
        table.addCell(cell);
        // second row
        table.addCell(new Cell().setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("nested table 1")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().add(new Paragraph("nested table 2")).setBorder(Border.NO_BORDER));
        table.addCell(new Cell().setBorder(Border.NO_BORDER));
        // third row
        // third row cell 1
        table.addCell(new Cell().add(new Paragraph("")).setBorder(Border.NO_BORDER));
        // third row cell 2
        Table table1 = new Table(UnitValue.createPercentArray(1)).useAllAvailableWidth();
        table1.addCell("cell 1 of nested table 1");
        table1.addCell("cell 2 of nested table 1");
        table1.addCell("cell 3 of nested table 1");
        table.addCell(new Cell().add(table1).setBorder(Border.NO_BORDER));
        // third row cell 3
        Table table2 = new Table(UnitValue.createPercentArray(2)).useAllAvailableWidth();
        table2.addCell(new Cell().setMinHeight(10));
        table2.addCell(new Cell().setMinHeight(10));
        cell = new Cell(1, 2).add(new Paragraph("cell 2 of nested table 2")).setMinHeight(10);
        table2.addCell(cell);
        cell = new Cell(1, 2).add(new Paragraph("cell 3 of nested table 2")).setMinHeight(10);
        table2.addCell(cell);
        table.addCell(new Cell().add(table2).setBorder(Border.NO_BORDER));
        // third row cell 4
        table.addCell(new Cell().setBorder(Border.NO_BORDER));
        // fourth row
        cell = new Cell(1, 4);
        cell.setBorder(Border.NO_BORDER);
        cell.setMinHeight(16);
        table.addCell(cell);

        doc.add(table);
        doc.close();

    }
}
