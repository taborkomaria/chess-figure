package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.TextLayout;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

import javax.swing.*;

public class Panel extends JPanel{
	//размер клетки
	public static final int SIZE = 50;
	//поля от края
	public static final int MOVE = 50;
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		setBackground(new Color(245,245,245)); 
		
		//белый фон поля
		Rectangle2D rectBag  = new Rectangle2D.Double(MOVE,MOVE, SIZE*8,SIZE*8);
		g2.setPaint(Color.white); 
		g2.fill(rectBag);
		g2.setPaint(Color.black); 
		g2.draw(rectBag);
		//черные клетки поля
		drawField(g2);
		
		//позиция королевы
		int x = 5;
		int y = 4;
		if(x > 0 && x < 9 && y > 0 && y < 9){
			//клетки, которые бьет королева
			queenLines(x, y, g2);
			//символ "Q" на клетке королевы
			queenPosition(x, y, g2);
		}
		else{
			Font font = new Font("Verdana", Font.BOLD, 18);
			g2.setFont(font);
			String text = "Указана неверная позиция";
			g2.setColor(Color.BLACK);
	        g2.drawString(text, MOVE, 30);
		}
	} 
	//отображение черных клеток поля
	public void drawField(Graphics2D g2){
		int horiz = MOVE;
		int vert = MOVE;
		//клетки черного цвета
		g2.setPaint(Color.black); 
		for(int i=0; i<4; i++){
			//рисование 4 пар клеток по диагонали (заполняется две строки)
			for(int k=0; k<4; k++){
				//квадрат в первой строке
				g2.fill(new Rectangle2D.Double(horiz+SIZE,vert, SIZE,SIZE));
				//квадрат во второй строке
				g2.fill(new Rectangle2D.Double(horiz,vert+SIZE, SIZE,SIZE));
				//сдвиг вправо на две клетки
				horiz += SIZE*2;
			}
			//новая пара строк заполняется от левого края
			horiz = MOVE;
			//сдвиг вниз на две строки
			vert += SIZE*2;
		}
	}
	//отображение символ "Q" на клетке, где стоит королева
	//x, y - позиция королевы на поле
	public void queenPosition(int x, int y, Graphics2D g2){
		//позиция королевы на поле
		int w = SIZE*x;
		int h = SIZE*y;
		Font font = new Font("Verdana", Font.BOLD, 24);
		g2.setFont(font);
		String text = "Q";
		FontMetrics fm = g2.getFontMetrics();
		//центрирование текста в клетке поля
        int textX = (int)((SIZE - fm.stringWidth(text)) / 2);
        int textY = (int)((SIZE - (fm.getHeight() - fm.getDescent())) / 2);
        textY += fm.getAscent() - fm.getDescent();
		g2.setColor(Color.BLACK);
		//отрисовка буквы на поле
        g2.drawString(text, w + textX, h + textY);
	}
	//показ клеток, которые бьет королева
	//x, y - позиция королевы на поле
	public void queenLines(int x, int y, Graphics2D g2){
		//позиция королевы на поле
		int w = SIZE*x;
		int h = SIZE*y;
		
		g2.setPaint(new Color(1f,0f,0f,.5f)); 
		//строка, на которой стоит королева
		g2.fill(new Rectangle2D.Double(MOVE,h, SIZE*8,SIZE));
		//столбец,в котором находится королева
		g2.fill(new Rectangle2D.Double(w,MOVE, SIZE,SIZE*8));	
		//диагонали
		drawDiagonals(w, h, g2);
	}
	//показ клеток, расположенных по диагонали от королевы
	//x, y - позиция королевы
	//w, h - позиция королевы на поле
	//size - размер клетки
	public void drawDiagonals(int w, int h, Graphics2D g2){
		int left = w - SIZE;
		int right = w + SIZE;
		int up = h - SIZE;
		int down = h + SIZE;
		g2.setPaint(new Color(1f,0f,0f,.5f));
		drawDiagonal2(left, up, -SIZE, -SIZE, g2);
		drawDiagonal2(left, down, -SIZE, SIZE, g2);
		drawDiagonal2(right, up, SIZE, -SIZE, g2);
		drawDiagonal2(right, down, SIZE, SIZE, g2);
	}
	//отрисовка двух клеток на диагонали
	//x, y - позиция первой закрашиваемой клетки
	//move1, move2 - сдвиг вправо/влево вверх/вниз
	public void drawDiagonal2(int x, int y, int move1, int move2, Graphics2D g2){
		int edge = SIZE*8;
		while(x >= MOVE && x <= edge && y <= edge && y >= MOVE){
			g2.fill(new Rectangle2D.Double(x,y, SIZE,SIZE));
			x+=move1;
			y+=move2;
		}
	}
	
}
