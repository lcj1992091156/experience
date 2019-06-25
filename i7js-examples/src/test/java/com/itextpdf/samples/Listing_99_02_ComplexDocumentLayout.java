/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.samples;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.IOException;

/**
 * Example demonstrates how to build complex layouts using layout manager
 */
@Category(SampleTest.class)
public class Listing_99_02_ComplexDocumentLayout extends GenericTest {

    public static final String DEST = "./target/test/resources/Listing_99_02_ComplexDocumentLayout/Listing_99_02_ComplexDocumentLayout.pdf";

    public static void main(String args[]) throws IOException {
        new Listing_99_02_ComplexDocumentLayout().manipulatePdf(DEST);
    }

    @Override
    public void manipulatePdf(String dest) throws IOException {
        //Initialize document
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc);

        //Set up renderer. The layout consist of 2 vertical stripes.
        Rectangle[] columns = {new Rectangle(100, 100, 100, 500), new Rectangle(400, 100, 100, 500)};
        doc.setRenderer(new ColumnDocumentRenderer(doc, columns));

        //Create paragraph and place it to layout
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < 200; i++) {
            text.append("A very long text is here...");
        }
        doc.add(new Paragraph(text.toString()).setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA)));

        //Close document
        doc.close();
    }

}
