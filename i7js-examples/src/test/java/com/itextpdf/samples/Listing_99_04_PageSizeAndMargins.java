/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.samples;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.AreaBreak;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.FileNotFoundException;
import java.io.IOException;

@Category(SampleTest.class)
public class Listing_99_04_PageSizeAndMargins extends GenericTest {

    public static final String DEST = "./target/test/resources/Listing_99_03_PageSizeAndMargins/Listing_99_03_PageSizeAndMargins.pdf";


    public static void main(String args[]) throws IOException {
        new Listing_99_04_PageSizeAndMargins().manipulatePdf(DEST);
    }

    public void manipulatePdf(String dest) throws FileNotFoundException {
        //Initialize document with a certain page size.
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, new PageSize(100, 100));
        doc.setMargins(10, 10, 10, 10);

        //Add paragraph to the document
        doc.add(new Paragraph("Hello")).add(new Paragraph("World"));

        //Add new page with the default page size which is 100*100 and [10, 10, 10, 10] margins for this document.
        doc.add(new AreaBreak());

        //Add paragraph to the document
        doc.add(new Paragraph("Hello")).add(new Paragraph("World"));

        //Add new A4 page
        doc.add(new AreaBreak(PageSize.A4));

        //Add paragraph to the document
        doc.add(new Paragraph("Hello")).add(new Paragraph("World"));

        //Close document
        doc.close();
    }


}
