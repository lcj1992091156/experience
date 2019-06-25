/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/37652181
 */
package com.itextpdf.samples.sandbox.annotations;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfTextAnnotation;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;
import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class AddMarked extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/annotations/add_marked.pdf";
    public static final String SRC = "./src/test/resources/pdfs/hello_sticky_note.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new AddMarked().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        PdfPage page = pdfDoc.getFirstPage();
        PdfAnnotation sticky = page.getAnnotations().get(0);
        Rectangle stickyRectangle = sticky.getRectangle().toRectangle();
        PdfAnnotation replySticky = new PdfTextAnnotation(stickyRectangle)
                .setOpen(false)
                .setStateModel(new PdfString("Marked"))
                .setState(new PdfString("Marked"))
                .setIconName(new PdfName("Comment"))
                .setInReplyTo(sticky)
                .setText(new PdfString("Bruno"))
                .setContents("Marked set by Bruno")
                .setFlags(sticky.getFlags() + PdfAnnotation.HIDDEN);
        pdfDoc.getFirstPage().addAnnotation(replySticky);
        pdfDoc.close();
    }
}
