/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * Example written by Bruno Lowagie in answer to
 * http://stackoverflow.com/questions/28554413/how-to-add-overlay-text-with-link-annotations-to-existing-pdf
 */
package com.itextpdf.samples.sandbox.annotations;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class AddLinkAnnotation2 extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/annotations/add_link_annotation2.pdf";
    public static final String SRC = "./src/test/resources/pdfs/hello.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddLinkAnnotation2().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);

        PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);

        Link link = new Link("The Best iText Questions on StackOverflow", PdfAction.createURI("http://pages.itextpdf.com/ebook-stackoverflow-questions.html"));
        Paragraph p = new Paragraph("Download ").add(link.setFont(bold)).add(" and discover more than 200 questions and answers.");

        doc.add(p.setFixedPosition(36, 700, 500));

        doc.close();
    }
}
