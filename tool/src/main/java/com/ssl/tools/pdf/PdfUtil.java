package com.rsr.common;

import com.alibaba.fastjson.JSONObject;
import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfTextFormField;
import com.itextpdf.io.font.FontProgram;
import com.itextpdf.io.font.FontProgramFactory;
import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.ssl.tools.PDFModel;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class PdfUtil {
    /**
     * 在pdf上创建表单域
     *
     * @param pdf
     * @param savePath
     * @param list：  [{
     *                 "page": "1",
     *                 "positionX": "x轴的距离",
     *                 "positionY": "y轴的距离",
     *                 "width": "长",
     *                 "height": "宽",
     *                 "font":"字体，ttf格式",
     *                 "fontSize": "字体大小",
     *                 "name":"表单域的名称",
     *                 "value":"表单域的值，可选"
     *                 },
     *                 {
     *                 "page": "1",
     *                 "positionX": "x轴的距离",
     *                 "positionY": "y轴的距离",
     *                 "width": "长",
     *                 "height": "宽",
     *                 "font":"字体，ttf格式",
     *                 "fontSize": "字体大小",
     *                 "name":"表单域的名称",
     *                 "value":"表单域的值，可选"
     *                 }
     *                 ]
     * @throws IOException
     */
    public static void createText(File pdf, String savePath,  List<PDFModel> list) throws IOException, InterruptedException {
        // 编辑后的文件
        PdfWriter pdfWriter = new PdfWriter(savePath);
        PdfDocument pdfDocument = new PdfDocument(new PdfReader(pdf), pdfWriter);
        PdfAcroForm pdfAcroForm = PdfAcroForm.getAcroForm(pdfDocument, true);

        //font

        FontProgram fontProgram;
        PdfFont font;


        PdfTextFormField pdfTextFormField;
        JSONObject field;
        Rectangle rectangle;
//    int count =1;
        Map<String,Integer>index=new HashMap<>();
        for (PDFModel pdfEntity : list) {

            String font1 = pdfEntity.getFont();
            // 读取ttf字体文件
            String path=getClasspath();
            fontProgram = FontProgramFactory.createFont(getClasspath()+"font\\STKAITI.TTF");
            // 编码使用 PdfEncodings.IDENTITY_H
            font = PdfFontFactory.createFont(fontProgram, PdfEncodings.IDENTITY_H, true);

            // 设置表单域的位置
            float positionX = pdfEntity.getPositionX();
            float positionY = pdfEntity.getPositionY();
            float width = pdfEntity.getWidth();
            float height = pdfEntity.getHeight();
            rectangle = new Rectangle(positionX, positionY, width,height);
            String name = pdfEntity.getName();
            String value = pdfEntity.getValue();
            //判断key是否 重
            if(index.containsKey(name)){
                Integer count=index.get(name);

                index.put(name,1+count);
                name=name+"#"+count;
                pdfTextFormField = PdfTextFormField.createText(pdfDocument, rectangle, name, value);
            }else{
                pdfTextFormField = PdfTextFormField.createText(pdfDocument, rectangle, name, value);
                index.put(name,0);
            }


            Integer page = pdfEntity.getPage();
            Integer fontSize = pdfEntity.getFontSize();

            pdfTextFormField.setBorderWidth(0).setReadOnly(true).setColor(ColorConstants.BLACK).setFontAndSize(font,fontSize);

//        pdfTextFormField.setPage(count);
            /** 将表单域加入pdf的指定页中 */
            pdfAcroForm.addField(pdfTextFormField, pdfDocument.getPage(page));
//        count++;
        }

        pdfDocument.close();
        pdfWriter.close();
    }

    public static void main(String[] args) throws IOException, InterruptedException {


        List<PDFModel> list =new ArrayList<>();
        PDFModel model =new  PDFModel();
        model.setFont("SIMYOU.TTF");
        model.setFontSize(18);
        model.setHeight(20F);
        model.setName("nametype");
        model.setPage(1);
        model.setPositionX(0F);
        model.setPositionY(10F);
        model.setValue("我是表单域值");
        model.setWidth(200F);
        list.add(model);
        PDFModel mode2 =new  PDFModel();
        mode2.setFont("SIMYOU.TTF");
        mode2.setFontSize(18);
        mode2.setHeight(20F);
        mode2.setName("nametype");
        mode2.setPage(2);
        mode2.setPositionX(0F);
        mode2.setPositionY(10F);
        mode2.setValue("我是表单域值2");
        mode2.setWidth(200F);
        list.add(mode2);

        PDFModel mode3 =new  PDFModel();
        mode3.setFont("SIMYOU.TTF");
        mode3.setFontSize(18);
        mode3.setHeight(20F);
        mode3.setName("nametype");
        mode3.setPage(3);
        mode3.setPositionX(0F);
        mode3.setPositionY(10F);
        mode3.setValue("我是表单域值3");
        mode3.setWidth(200F);
        list.add(mode3);
        PDFModel mode4 =new  PDFModel();
        mode4.setFont("SIMYOU.TTF");
        mode4.setFontSize(18);
        mode4.setHeight(20F);
        mode4.setName("nametype");
        mode4.setPage(4);
        mode4.setPositionX(0F);
        mode4.setPositionY(10F);
        mode4.setValue("我是表单域值4");
        mode4.setWidth(200F);
        list.add(mode4);

        PDFModel mode5 =new  PDFModel();
        mode5.setFont("SIMYOU.TTF");
        mode5.setFontSize(18);
        mode5.setHeight(20F);
        mode5.setName("nametttt");
        mode5.setPage(5);
        mode5.setPositionX(0F);
        mode5.setPositionY(10F);
        mode5.setValue("我是表单域值5");
        mode5.setWidth(200F);
        list.add(mode5);

        PDFModel mode6 =new  PDFModel();
        mode6.setFont("SIMYOU.TTF");
        mode6.setFontSize(22);
        mode6.setHeight(22F);
        mode6.setName("nametttt");
        mode6.setPage(5);
        mode6.setPositionX(11F);
        mode6.setPositionY(22F);
        mode6.setValue("我是表单域值6");
        mode6.setWidth(200F);
        list.add(mode6);

        //源文件
        File pdf = new File("F:\\合同.pdf");
        // 保存路径
        Random random  =new Random();

        String savePath = "C:\\Users\\lcj\\Desktop\\"+ random.nextInt(100000)+"new合同.pdf";
//        createText(pdf, savePath, (JSONArray) JSON.toJSON(list));
        createText(pdf, savePath, list);
        System.out.println("---------------------------------------------------------------");
    }

    public static String getClasspath() {
        String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
        if (path.startsWith("file:")) {
            path = path.substring(5);
        }
        return path;
    }


}
