/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    For more information, please contact iText Software at this address:
    sales@itextpdf.com
 */
/**
 * These examples are written by Bruno Lowagie in the context of an article about fonts.
 */
package com.itextpdf.samples.sandbox.fonts.tutorial;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.licensekey.LicenseKey;
import com.itextpdf.samples.GenericTest;
import com.itextpdf.test.annotations.type.SampleTest;

import org.junit.experimental.categories.Category;

import java.io.File;

@Category(SampleTest.class)
public class F08_Unicode extends GenericTest {
    public static final String DEST = "./target/test/resources/sandbox/fonts/tutorial/f08_unicode.pdf";
    public static final String FONT = "./src/test/resources/font/FreeSans.ttf";

    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        new F08_Unicode().manipulatePdf(DEST);
    }

    @Override
    protected void manipulatePdf(String dest) throws Exception {
        LicenseKey.loadLicenseFile(System.getenv("ITEXT7_LICENSEKEY") + "/itextkey-multiple-products.xml");

        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(DEST));
        Document doc = new Document(pdfDoc);

        PdfFont font = PdfFontFactory.createFont(FONT, "Identity-H", false);

        doc.add(new Paragraph("Vous \u00eates d'o\u00f9?").setFont(font));
        doc.add(new Paragraph("\u00c0 tout \u00e0 l'heure. \u00c0 bient\u00f4t.").setFont(font));
        doc.add(new Paragraph("Je me pr\u00e9sente.").setFont(font));
        doc.add(new Paragraph("C'est un \u00e9tudiant.").setFont(font));
        doc.add(new Paragraph("\u00c7a va?").setFont(font));
        doc.add(new Paragraph("Il est ing\u00e9nieur. Elle est m\u00e9decin.").setFont(font));
        doc.add(new Paragraph("C'est une fen\u00eatre.").setFont(font));
        doc.add(new Paragraph("R\u00e9p\u00e9tez, s'il vous pla\u00eet.").setFont(font));
        doc.add(new Paragraph("Odkud jste?").setFont(font));
        doc.add(new Paragraph("Uvid\u00edme se za chvilku. M\u011bj se.").setFont(font));
        doc.add(new Paragraph("Dovolte, abych se p\u0159edstavil.").setFont(font));
        doc.add(new Paragraph("To je studentka.").setFont(font));
        doc.add(new Paragraph("V\u0161echno v po\u0159\u00e1dku?").setFont(font));
        doc.add(new Paragraph("On je in\u017een\u00fdr. Ona je l\u00e9ka\u0159.").setFont(font));
        doc.add(new Paragraph("Toto je okno.").setFont(font));
        doc.add(new Paragraph("Zopakujte to pros\u00edm.").setFont(font));
        doc.add(new Paragraph("\u041e\u0442\u043a\u0443\u0434\u0430 \u0442\u044b?")
                .setFont(font));
        doc.add(new Paragraph("\u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f \u0432 " +
                "\u043d\u0435\u043c\u043d\u043e\u0433\u043e. \u0423\u0432\u0438\u0434\u0438\u043c\u0441\u044f.")
                .setFont(font));
        doc.add(new Paragraph("\u041f\u043e\u0437\u0432\u043e\u043b\u044c\u0442\u0435 \u043c\u043d\u0435 " +
                "\u043f\u0440\u0435\u0434\u0441\u0442\u0430\u0432\u0438\u0442\u044c\u0441\u044f.")
                .setFont(font));
        doc.add(new Paragraph("\u042d\u0442\u043e \u0441\u0442\u0443\u0434\u0435\u043d\u0442.")
                .setFont(font));
        doc.add(new Paragraph("\u0425\u043e\u0440\u043e\u0448\u043e?")
                .setFont(font));
        doc.add(new Paragraph("\u041e\u043d \u0438\u043d\u0436\u0435\u043d\u0435\u0440. " +
                "\u041e\u043d\u0430 \u0434\u043e\u043a\u0442\u043e\u0440.")
                .setFont(font));
        doc.add(new Paragraph("\u042d\u0442\u043e \u043e\u043a\u043d\u043e.")
                .setFont(font));
        doc.add(new Paragraph("\u041f\u043e\u0432\u0442\u043e\u0440\u0438\u0442\u0435, " +
                "\u043f\u043e\u0436\u0430\u043b\u0443\u0439\u0441\u0442\u0430.")
                .setFont(font));

        doc.close();
    }
}
