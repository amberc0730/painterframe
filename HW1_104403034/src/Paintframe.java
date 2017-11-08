//周芷安  資管3  104403034//
import java.awt.BorderLayout; // 包含東西南北中   
import java.awt.GridLayout;   // rows & columns
import java.awt.Color;        
import java.awt.event.ItemEvent;    //combobox & radiobutton 使用//
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;  //button 使用//  
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;   //mouse 使用//
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;    
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JComboBox;


public class Paintframe extends JFrame{
	private JPanel conPanel;  //畫布//
	private JPanel square;    //下畫面//
	private JPanel all;      //左畫面//
	private JLabel label1;    //繪圖工具顯示//
	private JLabel label2;    //筆刷大小顯示//
	private JLabel label3;    //工具區顯示//   
	private JLabel conmouse;
	private JRadioButton small,medium,big;
	private ButtonGroup radioGroup;
	private JButton ground,background,clean;
	private JCheckBox full;
	private JLabel under;  //狀態列//
	private JLabel point;   
	private JComboBox list;   //繪圖卷軸//
	private static final String[] names ={"筆刷","直線","橢圓","矩形","圓角矩形"};
	private String pen="筆刷";
	private String check="no";
	private String size="大";
	
	public Paintframe() {
	       super("小畫家介面");
	       
	all = new JPanel();
	square =new JPanel();
	
	label1 =new JLabel("[繪圖工具]");
	all.add(label1);
	
	JComboBox jComboBox = new JComboBox(names);
	list =jComboBox;
	all.add(list);     //把繪圖工具選項匯出//
    
	label2 =new JLabel("[筆刷大小]");
	all.add(label2);
	
    small =new JRadioButton("小");
    all.add(small);   
    medium =new JRadioButton("中");
    all.add(medium);   
    big =new JRadioButton("大");
    all.add(big);   
    
    radioGroup =new ButtonGroup();
    radioGroup.add(big);
    radioGroup.add(medium);
    radioGroup.add(small);    //設為一個radiogroup  把小中大輸出//
    
    full=new JCheckBox("填滿");
	all.add(full);
    
	label3 =new JLabel("[工作區]");
	all.add(label3);
	
    JButton ground =new JButton("前景色");
	all.add(ground);
	JButton background =new JButton("背景色");
	all.add(background);
	JButton clean =new JButton("清除畫面");
	all.add(clean);
	
	
	full.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					if(full.isSelected()){
						check="yes";
						under.setText(String.format("填滿:%s",check));
					}
					else{
						check="no";
						under.setText(String.format("填滿:%s",check));
					}
				}
			}
	);      //用這個if else判斷再狀態列顯示出有無填滿//

    RadioButtonHandler handlerR = new RadioButtonHandler(); 
	  big.addItemListener((ItemListener) handlerR);
	  medium.addItemListener((ItemListener) handlerR);
	  small.addItemListener((ItemListener) handlerR);
	       //設定radiobuttonhandler,每個event會執行完所有handlers//
	  
	ButtonHandler handler1 = new ButtonHandler();
	  ground.addActionListener(handler1);
	  background.addActionListener(handler1);
	  clean.addActionListener(handler1);
	  //設定buttonhandler,每個event會執行完所有handlers//
	  
    conPanel =new JPanel();
    conPanel.setBackground(Color.WHITE); //畫不初設白色//
    
      point=new JLabel();
  	  square.add(point);
      
      under=new JLabel();
      square.add(under);
      
      conmouse =new JLabel("else condition");
      square.add(conmouse);    
      //在下畫面顯示出游標位置  有無填滿 和游標狀態//
    
    list.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						pen=names[list.getSelectedIndex()];
						JOptionPane.showMessageDialog(null, String.format("筆刷:%s ",pen));
					}
				}
			}
		);//如果有選繪圖工具,跳出對應訊息框//
		
      square.setLayout(new GridLayout(1,3));   //設定下層griglayout條件//
	  add(square,BorderLayout.SOUTH);
	  all.setLayout(new GridLayout(12,1));    //設定左畫面griglayout條件//   
	  add(all,BorderLayout.WEST);             //設定左borderlayout為西邊//
	  add(conPanel,BorderLayout.CENTER);   //設定主griglayout為中間//
		
	 MouseHandler handler11 = (MouseHandler) new MouseHandler(); 
		conPanel.addMouseListener(handler11);
		conPanel.addMouseMotionListener( handler11);
	}
	  //設定mousehandler,每個event會執行完所有handlers//
	
	 private class RadioButtonHandler implements ItemListener{
		public void itemStateChanged(ItemEvent event){
			if(big.isSelected())
				size="大";
			if(medium.isSelected())
				size="中";  
			if(small.isSelected())
				size="小";
			JOptionPane.showMessageDialog(null, String.format("筆刷:%s",size));
		 }
	 }    //根據所選筆刷大小,跳出對應訊息框//
	 
	private  class MouseHandler implements MouseListener, MouseMotionListener 
	{
		public void mouseDragged(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("拖曳"));
		}
		public void mouseMoved(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("移動"));
		}
		public void mouseClicked(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("點擊"));
		}
		public void mousePressed(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("按下"));
		}
		public void mouseReleased(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("放開"));	
		}
		public void mouseEntered(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("進入"));
		}
		public void mouseExited(MouseEvent e) {
			point.setText( String.format( "瀏覽位置: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("離開"));
		}	  
	}   //根據游標的移動狀態,跳出對應訊息框//
	
	 private class ButtonHandler implements ActionListener{

			public void actionPerformed (ActionEvent event){
					JOptionPane.showMessageDialog( Paintframe.this,String.format("canvas:%s", event.getActionCommand()));
				 }
	 }//根據工具區選擇,跳出對應訊息框//
 }