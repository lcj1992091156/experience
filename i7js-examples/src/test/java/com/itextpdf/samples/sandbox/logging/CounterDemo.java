/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
package com.itextpdf.samples.sandbox.logging;

import com.itextpdf.kernel.log.CounterManager;
import com.itextpdf.kernel.log.ICounter;
import com.itextpdf.kernel.log.ICounterFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import org.junit.Assert;
import org.junit.experimental.categories.Category;

@Category(SampleTest.class)
public class CounterDemo extends GenericTest {
    public static final String SRC = "./target/test/resources/sandbox/logging/hello.pdf";
    public static final String DEST = "./target/test/resources/sandbox/logging/stamp.pdf";
    public static final String LOG_RESULT = "./target/test/resources/sandbox/logging/log.txt";
    public static final String CMP_LOG = "./src/test/resources/sandbox/logging/cmp_log.txt";
    public static final String CMP_LOG_LICENSED = "./src/test/resources/sandbox/logging/cmp_log_licensed.txt";
    public static final String CMP_LOG_RELEASE = "./src/test/resources/sandbox/logging/cmp_log_release.txt";

    MyCounterFactory myCounterFactory;

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new CounterDemo().manipulatePdf(DEST);
    }

    private void createPdf() throws FileNotFoundException {
        Document document = new Document(new PdfDocument(new PdfWriter(SRC)));
        document.add(new Paragraph("Hello World!"));
        document.close();
    }

    @Override
    public void manipulatePdf(String dest) throws IOException {
        myCounterFactory = new MyCounterFactory(getClass());
        CounterManager.getInstance().register(myCounterFactory);

        createPdf();
        PdfReader reader = new PdfReader(SRC);
        PdfDocument pdfDocument = new PdfDocument(reader, new PdfWriter(dest));
        Document document = new Document(pdfDocument).showTextAligned(new Paragraph("Stamped text"), 559, 806, TextAlignment.RIGHT);
        document.close();

        myCounterFactory.close();
        CounterManager.getInstance().unregister(myCounterFactory);

        compareLogs();
    }

    public static class MyCounterFactory implements ICounterFactory {

        protected FileWriter writer;
        protected String yourClass;

        public MyCounterFactory(Class<?> cls) {
            try {
                this.writer = new FileWriter(LOG_RESULT, false);
                yourClass = cls.getName();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        @Override
        public ICounter getCounter(Class<?> cls) {
            return new MyCounter(cls, yourClass, writer);
        }

        public void close() throws IOException {
            writer.close();
        }

        private static class MyCounter implements ICounter {

            protected FileWriter writer;
            protected String yourClass;
            protected String iTextClass;

            public MyCounter(Class<?> klass, String yourClass, FileWriter writer) {
                this.yourClass = yourClass;
                this.iTextClass = klass.getName();
                this.writer = writer;
            }

            @Override
            public void onDocumentRead(long size) {
                if (writer == null)
                    throw new RuntimeException("No writer defined!");
                try {
                    writer.write(String.format(
                            "[%s:%s] %s: %s read\n", yourClass, iTextClass, new Date().toString(), size));
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onDocumentWritten(long size) {
                if (writer == null)
                    throw new RuntimeException("No writer defined!");
                try {
                    writer.write(String.format(
                            "[%s:%s] %s: %s written\n", yourClass, iTextClass, new Date().toString(), size));
                    writer.flush();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void compareLogs() throws IOException {
        boolean expectedResult = fileToString(LOG_RESULT).equals(fileToString(CMP_LOG))
                || fileToString(LOG_RESULT).equals(fileToString(CMP_LOG_LICENSED))
                || fileToString(LOG_RESULT).equals(fileToString(CMP_LOG_RELEASE));
        Assert.assertTrue(expectedResult);
    }

    private String fileToString(String filePath) throws IOException {
        FileInputStream fin = new FileInputStream(filePath);
        BufferedReader myInput = new BufferedReader(new InputStreamReader(fin));
        StringBuilder sb = new StringBuilder();
        String thisLine;
        while ((thisLine = myInput.readLine()) != null) {
            int index = thisLine.lastIndexOf(": ");
            if (index > -1) {
                sb.append(thisLine, index + 2, thisLine.length()).append("\n");
            }
        }
        return sb.toString();
    }

}
