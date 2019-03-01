package com.imooc.code;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import com.imooc.security.core.properties.SecurityProperties;
import com.imooc.security.core.validate.code.ImageCode;
import com.imooc.security.core.validate.code.ValidateCodeGenerator;

@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {

	 private Logger logger=LoggerFactory.getLogger(getClass());
	 
	 @Autowired
	 private SecurityProperties securityProperties;	
     @Override
	 public ImageCode generate(HttpServletRequest request) 
	 {
    	logger.info("execute.11111");
    	logger.info("execute.11111");
    	logger.info("execute.11111");
    	logger.info("execute.11111");
    	logger.info("execute.11111");
    	logger.info("execute.11111");
		int width=ServletRequestUtils.getIntParameter(request, "width", securityProperties.getCode().getImage().getWidth());
		int height=ServletRequestUtils.getIntParameter(request, "height",securityProperties.getCode().getImage().getHeight());
		BufferedImage image=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		Graphics g = image.getGraphics();
		Random random=new Random();
		g.setColor(getRandColor(200,250));
		g.fillRect(0, 0, width, height);
		g.setFont(new Font("Times New Roman",Font.ITALIC,20));
		g.setColor(getRandColor(160,200));
		for(int i=0;i<155;i++) 
		{
			int x=random.nextInt(width);
			int y=random.nextInt(height);
			int xl=random.nextInt(12);
			int yl=random.nextInt(12);
			g.drawLine(x, y, xl, yl);
		}
		String sRand="";
		int length=securityProperties.getCode().getImage().getLength();
		for(int i=0;i<length;i++) 
		{
			String rand=String.valueOf(random.nextInt(10));
			sRand+=rand;
			g.setColor(new Color(20+random.nextInt(110),20+random.nextInt(110),20+random.nextInt(110)));
			g.drawString(rand, 13*i+6,16);
		}
		g.dispose();
		int expired=securityProperties.getCode().getImage().getExpireIn();
		return new ImageCode(image,sRand,expired);
	 }
		
		private Color getRandColor(int fc, int bc)
		{
			Random random=new Random();
			if(fc>255) 
			{
				fc=255;
			}
			if(bc>255) 
			{
				bc=255;
			}
			int r=fc+random.nextInt(bc-fc);
			int g=fc+random.nextInt(bc-fc);
			int b=fc+random.nextInt(bc-fc);
			return new Color(r,g,b);
		}
}
