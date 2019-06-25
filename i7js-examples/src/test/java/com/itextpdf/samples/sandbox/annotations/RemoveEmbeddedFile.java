/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * This example was written by Bruno Lowagie in answer to the following question:
 * http://stackoverflow.com/questions/26648462/how-to-delete-attachment-of-pdf-using-itext
 * (This is part two, there's also a part one named AddEmbeddedFile)
 */
package com.itextpdf.samples.sandbox.annotations;

import com.itextpdf.kernel.pdf.*;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class RemoveEmbeddedFile extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/annotations/remove_embedded_file.pdf";
    public static final String SRC = "./src/test/resources/pdfs/hello_with_attachment.pdf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new RemoveEmbeddedFile().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfReader(SRC), new PdfWriter(DEST));
        PdfDictionary root = pdfDoc.getCatalog().getPdfObject();
        PdfDictionary names = root.getAsDictionary(PdfName.Names);
        PdfDictionary embeddedFiles = names.getAsDictionary(PdfName.EmbeddedFiles);
        PdfArray namesArray = embeddedFiles.getAsArray(PdfName.Names);
        namesArray.remove(0);
        namesArray.remove(0);
        pdfDoc.close();
    }
}
