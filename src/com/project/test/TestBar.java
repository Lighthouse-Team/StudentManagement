package com.project.test;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;

import org.jbarcode.JBarcode;
import org.jbarcode.encode.EAN8Encoder;
import org.jbarcode.paint.EAN8TextPainter;
import org.jbarcode.paint.WidthCodedPainter;
import org.jbarcode.util.ImageUtil;

/**
 * @class:JbarcodeUtil
 * @descript:生成条形码最标准
 * @date:2016年11月25日 下午1:46:07
 * @version:V1.0 备注： 1.静态代码块的作用：当类被载入时，静态代码块被执行，且只被执行一次，静态块常用来执行类属性的初始化。
 *               2.常量条形码的高度和字体大小设置很重要，若是设置小了会看不到设置的文件
 */
public class TestBar {
	public static void main(String[] paramArrayOfString) {  
        try {  
            JBarcode localJBarcode = new JBarcode(EAN8Encoder.getInstance(),WidthCodedPainter.getInstance(),EAN8TextPainter.getInstance());  
            String str = "2219644";  
            BufferedImage localBufferedImage = localJBarcode.createBarcode(str);  
            
            saveToJPEG(localBufferedImage, "EAN8.jpg");
            //saveToGIF(localBufferedImage, "EAN8.jpg");
        }  
        catch (Exception localException) {  
            localException.printStackTrace();  
        }  
    }  
 
    static void saveToJPEG(BufferedImage paramBufferedImage, String paramString) {  
        saveToFile(paramBufferedImage, paramString, "jpeg");  
    }  
 
    static void saveToFile(BufferedImage paramBufferedImage, String paramString1, String paramString2) {  
        try {  
            FileOutputStream localFileOutputStream = new FileOutputStream("D:\\codeImg\\" + paramString1);  
            ImageUtil.encodeAndWrite(paramBufferedImage, paramString2, localFileOutputStream, 96, 96);  
            localFileOutputStream.close();  
        }  
        catch (Exception localException) {  
            localException.printStackTrace();  
        }  
    }
}
