package SpriteTree;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JFrame;

import com.golden.gamedev.util.ImageUtil;


public class GraphicsTest
{
    BufferedImage img;


    public static BufferedImage loadImage (String ref)
    {
        BufferedImage img = null;
        try
        {
            img = ImageIO.read(new File(ref));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return img;
    }


    public void loadAndDisplayImage (JFrame frame)
    {
        BufferedImage loadImg = loadImage("src/resources/bodyParts/head.png");
        //this.img = rotate(loadImg,100);

        frame.setBounds(0, 0, this.img.getWidth(), this.img.getHeight());
        JImagePanel panel = new JImagePanel(this.img, 0, 0);
        frame.add(panel);
        frame.setVisible(true);
    }


//	public static BufferedImage rotate(BufferedImage img, int angle, double x, double y){
//		int w = img.getWidth();
//		int h = img.getHeight();
//		BufferedImage dimg = new BufferedImage(w+20,h+20,img.getType());
//		Graphics2D g = dimg.createGraphics();
//		g.rotate(Math.toRadians(angle),h/2,w/2);
//		g.drawImage(img,null, 0,0);
//		return dimg;
//	}
	
	  public static BufferedImage rotate(BufferedImage src, double angle) {
          int w = src.getWidth(), h = src.getHeight(), transparency = src
                  .getColorModel().getTransparency();

        BufferedImage image = ImageUtil.createImage(w,h, transparency);
           
          Graphics2D g = image.createGraphics();
          g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                  RenderingHints.VALUE_INTERPOLATION_BILINEAR);
          g.rotate(Math.toRadians(angle),  w/2, h/2);
          
          //for testing
//          g.setColor(Color.RED);
//          g.drawRect(0,0, image.getWidth(), image.getHeight());
          
          g.drawImage(src, 0, 0, null);
          g.dispose();
          
          return image;
  }

    public static BufferedImage horizFlip(BufferedImage img){
		int w = img.getWidth();
		int h = img.getHeight();
		BufferedImage dimg = new BufferedImage (w, h, img.getType());
		Graphics2D g = dimg.createGraphics();
		g.drawImage(img,0, 0, w, h, w,0,0, h, null);
		g.dispose();
		return dimg;
	}
	public static void main(String[] args){
		GraphicsTest lol = new GraphicsTest();
		JFrame frame = new JFrame("Image test");
		lol.loadAndDisplayImage(frame);

		
	}
	
}
