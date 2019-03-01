package cn.lys.test;

import java.awt.Color;

import com.code.QRCodeDecoderHandler;
import com.code.QRCodeEncoder;

public class TestCode {
	//普通二维码
	public static void makeCode1() {
		String imgPath = "F:/1.png";//生成的二维码图像路径和名字
		String content = "http://www.baidu.com";//二维码内容
		QRCodeEncoder encoder = new QRCodeEncoder();
		encoder.encoderQRCode(
		content, //内容
		imgPath, //文件路径 
		"png",   //文件类型
		"UTF-8", //编码方式
		4,      //大小
		null,   //边框
		null, //前景色
		null, //背景色
		6,    //图标比例
		null, //图标路径 
		null  //动画图标路径
		);
		System.out.println("success");
	}
	
	//
	public static void makeCode2() {
		String imgPath = "F:/1.png";
		String content = "https://www.baidu.com";
		QRCodeEncoder encoder = new QRCodeEncoder();
		encoder.encoderQRCode(
		content, //内容
		imgPath, //文件路径 
		"png",   //文件类型
		"UTF-8", //编码方式
		4,      //大小
		null,   //边框
		Color.BLUE, //前景色
		Color.WHITE, //背景色
		6,    //图标比例
		null, //图标路径 
		null  //动画图标路径
		);
		System.out.println("success");
	}
	public static void makeCode3() {
		String imgPath = "F:/1.png";
		String logo = "F:/bg.jpg";
		String content = "哈哈哈哈";
		QRCodeEncoder encoder = new QRCodeEncoder();
		encoder.encoderQRCode(
			content, //内容
			imgPath, //文件路径 
			"png",   //文件类型
			"UTF-8", //编码方式
			4,      //大小
			null,   //边框
			null, //前景色
			null, //背景色
			6,    //图标比例
			logo, //图标路径 
			null  //动画图标路径
		);
		System.out.println("success");
	}
	
	public static void readCode() {
		QRCodeDecoderHandler handler = new QRCodeDecoderHandler();
		String imgPath = "F:/1.png";//二维码图像
		String decoderContent = handler.decoderQRCode(imgPath);
		System.out.println(decoderContent);
	}
	
	public static void main(String[] args) {
		makeCode3();
		//readCode();
	}
}
