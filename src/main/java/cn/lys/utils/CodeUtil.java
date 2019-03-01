package cn.lys.utils;

import com.code.QRCodeEncoder;

public class CodeUtil {
	//生成二维码
	public static void code(String content,String path) {
		QRCodeEncoder encoder = new QRCodeEncoder();
		encoder.encoderQRCode(
		content, //内容
		path, //文件路径 
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
	}
}
