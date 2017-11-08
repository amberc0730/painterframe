//�P��w  ���3  104403034//
import java.awt.BorderLayout; // �]�t�F��n�_��   
import java.awt.GridLayout;   // rows & columns
import java.awt.Color;        
import java.awt.event.ItemEvent;    //combobox & radiobutton �ϥ�//
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;  //button �ϥ�//  
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;   //mouse �ϥ�//
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
	private JPanel conPanel;  //�e��//
	private JPanel square;    //�U�e��//
	private JPanel all;      //���e��//
	private JLabel label1;    //ø�Ϥu�����//
	private JLabel label2;    //����j�p���//
	private JLabel label3;    //�u������//   
	private JLabel conmouse;
	private JRadioButton small,medium,big;
	private ButtonGroup radioGroup;
	private JButton ground,background,clean;
	private JCheckBox full;
	private JLabel under;  //���A�C//
	private JLabel point;   
	private JComboBox list;   //ø�Ϩ��b//
	private static final String[] names ={"����","���u","���","�x��","�ꨤ�x��"};
	private String pen="����";
	private String check="no";
	private String size="�j";
	
	public Paintframe() {
	       super("�p�e�a����");
	       
	all = new JPanel();
	square =new JPanel();
	
	label1 =new JLabel("[ø�Ϥu��]");
	all.add(label1);
	
	JComboBox jComboBox = new JComboBox(names);
	list =jComboBox;
	all.add(list);     //��ø�Ϥu��ﶵ�ץX//
    
	label2 =new JLabel("[����j�p]");
	all.add(label2);
	
    small =new JRadioButton("�p");
    all.add(small);   
    medium =new JRadioButton("��");
    all.add(medium);   
    big =new JRadioButton("�j");
    all.add(big);   
    
    radioGroup =new ButtonGroup();
    radioGroup.add(big);
    radioGroup.add(medium);
    radioGroup.add(small);    //�]���@��radiogroup  ��p���j��X//
    
    full=new JCheckBox("��");
	all.add(full);
    
	label3 =new JLabel("[�u�@��]");
	all.add(label3);
	
    JButton ground =new JButton("�e����");
	all.add(ground);
	JButton background =new JButton("�I����");
	all.add(background);
	JButton clean =new JButton("�M���e��");
	all.add(clean);
	
	
	full.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent e){
					if(full.isSelected()){
						check="yes";
						under.setText(String.format("��:%s",check));
					}
					else{
						check="no";
						under.setText(String.format("��:%s",check));
					}
				}
			}
	);      //�γo��if else�P�_�A���A�C��ܥX���L��//

    RadioButtonHandler handlerR = new RadioButtonHandler(); 
	  big.addItemListener((ItemListener) handlerR);
	  medium.addItemListener((ItemListener) handlerR);
	  small.addItemListener((ItemListener) handlerR);
	       //�]�wradiobuttonhandler,�C��event�|���槹�Ҧ�handlers//
	  
	ButtonHandler handler1 = new ButtonHandler();
	  ground.addActionListener(handler1);
	  background.addActionListener(handler1);
	  clean.addActionListener(handler1);
	  //�]�wbuttonhandler,�C��event�|���槹�Ҧ�handlers//
	  
    conPanel =new JPanel();
    conPanel.setBackground(Color.WHITE); //�e����]�զ�//
    
      point=new JLabel();
  	  square.add(point);
      
      under=new JLabel();
      square.add(under);
      
      conmouse =new JLabel("else condition");
      square.add(conmouse);    
      //�b�U�e����ܥX��Ц�m  ���L�� �M��Ъ��A//
    
    list.addItemListener(
			new ItemListener(){
				public void itemStateChanged(ItemEvent e) {
					if(e.getStateChange()==ItemEvent.SELECTED){
						pen=names[list.getSelectedIndex()];
						JOptionPane.showMessageDialog(null, String.format("����:%s ",pen));
					}
				}
			}
		);//�p�G����ø�Ϥu��,���X�����T����//
		
      square.setLayout(new GridLayout(1,3));   //�]�w�U�hgriglayout����//
	  add(square,BorderLayout.SOUTH);
	  all.setLayout(new GridLayout(12,1));    //�]�w���e��griglayout����//   
	  add(all,BorderLayout.WEST);             //�]�w��borderlayout������//
	  add(conPanel,BorderLayout.CENTER);   //�]�w�Dgriglayout������//
		
	 MouseHandler handler11 = (MouseHandler) new MouseHandler(); 
		conPanel.addMouseListener(handler11);
		conPanel.addMouseMotionListener( handler11);
	}
	  //�]�wmousehandler,�C��event�|���槹�Ҧ�handlers//
	
	 private class RadioButtonHandler implements ItemListener{
		public void itemStateChanged(ItemEvent event){
			if(big.isSelected())
				size="�j";
			if(medium.isSelected())
				size="��";  
			if(small.isSelected())
				size="�p";
			JOptionPane.showMessageDialog(null, String.format("����:%s",size));
		 }
	 }    //�ھکҿﵧ��j�p,���X�����T����//
	 
	private  class MouseHandler implements MouseListener, MouseMotionListener 
	{
		public void mouseDragged(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("�즲"));
		}
		public void mouseMoved(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("����"));
		}
		public void mouseClicked(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("�I��"));
		}
		public void mousePressed(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("���U"));
		}
		public void mouseReleased(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("��}"));	
		}
		public void mouseEntered(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("�i�J"));
		}
		public void mouseExited(MouseEvent e) {
			point.setText( String.format( "�s����m: (%d, %d)", 
		            e.getX(), e.getY() ) );
			conmouse.setText(String.format("���}"));
		}	  
	}   //�ھڴ�Ъ����ʪ��A,���X�����T����//
	
	 private class ButtonHandler implements ActionListener{

			public void actionPerformed (ActionEvent event){
					JOptionPane.showMessageDialog( Paintframe.this,String.format("canvas:%s", event.getActionCommand()));
				 }
	 }//�ھڤu��Ͽ��,���X�����T����//
 }