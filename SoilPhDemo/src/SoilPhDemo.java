	import java.awt.EventQueue;
	import java.awt.Graphics;
	import java.awt.Image;
	import java.awt.MediaTracker;
	import java.awt.Toolkit;
	
	import javax.swing.JFrame;
	import javax.swing.JComboBox;
	import javax.swing.JFileChooser;
	import javax.swing.DefaultComboBoxModel;
	import javax.swing.ImageIcon;
	
	import java.awt.event.ItemListener;
	import java.awt.image.BufferedImage;
	import java.io.File;
	import java.io.IOException;
	import java.sql.SQLException;
	import java.util.Random;
	import java.awt.event.ItemEvent;
	import javax.swing.JButton;
	import java.awt.event.ActionListener;
	import java.awt.event.ActionEvent;
	
	import javax.imageio.ImageIO;
	import javax.swing.Box;
	import javax.swing.border.SoftBevelBorder;
	import javax.swing.border.BevelBorder;
	import javax.swing.JPanel;
	import java.awt.Panel;
	import javax.swing.JTextArea;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JTextPane;
	import javax.swing.ProgressMonitor;
	
	import java.awt.Color;
	import javax.swing.border.TitledBorder;
	
	import javax.swing.border.MatteBorder;
	import java.awt.BorderLayout;
	import javax.swing.border.LineBorder;
	import java.awt.event.MouseAdapter;
	import java.awt.event.MouseEvent;
	import javax.swing.JTextField;
	import java.awt.Font;
	import javax.swing.SwingConstants;
	import javax.swing.SwingUtilities;
	import javax.swing.JProgressBar;
	
	public class SoilPhDemo {
		private JFrame frmSoilPh;
		private ImageToPixel ob;
		private Kmean ob1;
		private Kmean1 obj;
		private DBConnect conn;
		JButton create_segments = new JButton("Create Segments\r\n");
		JLabel lblNewLabel = new JLabel("");
		JLabel label1 = new JLabel("");
		JLabel label2 = new JLabel("");
		JLabel label3 = new JLabel("");
		JTextArea textArea = new JTextArea();
		BufferedImage image1;
		BufferedImage image2;
		BufferedImage image3;
		ImageIcon image_icon1;
		ImageIcon image_icon2;
		ImageIcon image_icon3;
		int count = 0;
		File f;
	
		/**
		 * Launch the application.
		 */
		public static void main(String[] args) {
			SoilPhDemo window = new SoilPhDemo();
	
			window.frmSoilPh.setVisible(true);
	
		}
	
		/**
		 * Create the application.
		 */
		public SoilPhDemo() {
	
			initialize();
		}
	
		/**
		 * Initialize the contents of the frame.
		 */
		//Design
		private void design(){
		//main image
			JPanel panel = new JPanel();
			panel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			panel.setBounds(32, 135, 479, 344);
			frmSoilPh.getContentPane().add(panel);
			panel.setLayout(null);
			lblNewLabel.setBounds(0, 0, 479, 344);
			panel.add(lblNewLabel);
			lblNewLabel.setBackground(Color.WHITE);
			
			//segment1
			JPanel panel_1 = new JPanel();
			panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_1.setBounds(547, 133, 306, 234);
			frmSoilPh.getContentPane().add(panel_1);
			panel_1.setLayout(null);
			label1.setBounds(0, 0, 306, 234);
			panel_1.add(label1);
			label1.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ph_result(image1);
	
				}
			});
			
			//segment2
			JPanel panel_2 = new JPanel();
			panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_2.setBounds(869, 135, 306, 234);
			frmSoilPh.getContentPane().add(panel_2);
			panel_2.setLayout(null);
			label2.setBounds(0, 0, 306, 234);
			panel_2.add(label2);
			label2.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					ph_result(image2);
	
			}
			});
			
			
			
			//segment3
			JPanel panel_3 = new JPanel();
			panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel_3.setBounds(737, 378, 306, 234);
			frmSoilPh.getContentPane().add(panel_3);
			panel_3.setLayout(null);
			label3.setBounds(0, 0, 306, 234);
			panel_3.add(label3);
			label3.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ph_result(image3);
	
			}		});
			
			
			
			//result panel
			JPanel panel5 = new JPanel();
			panel5.setBackground(Color.WHITE);
			panel5.setBorder(new LineBorder(new Color(0, 0, 0)));
			panel5.setBounds(35, 517, 491, 141);
			frmSoilPh.getContentPane().add(panel5);
			panel.setLayout(null);
			textArea.setFont(new Font("Times New Roman", Font.BOLD, 26));
			textArea.setEditable(false);
			textArea.setBounds(6, 11, 479, 119);
			panel5.add(textArea);
			
			create_segments.setBounds(424,56,144,39);frmSoilPh.getContentPane().add(create_segments);
			JLabel lblOriginalImage = new JLabel("ORIGINAL SOIL IMAGE");
			lblOriginalImage.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblOriginalImage.setBounds(32, 105, 196, 29);
			frmSoilPh.getContentPane().add(lblOriginalImage);
			
			JLabel lblNewLabel_1 = new JLabel("SEGMENTS");
			lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblNewLabel_1.setBounds(555, 106, 144, 29);
			frmSoilPh.getContentPane().add(lblNewLabel_1);
			
			JLabel lblNewLabel_2 = new JLabel("RESULT");
			lblNewLabel_2.setFont(new Font("Times New Roman", Font.BOLD, 17));
			lblNewLabel_2.setBounds(32, 490, 124, 20);
			frmSoilPh.getContentPane().add(lblNewLabel_2);
			
			DefaultComboBoxModel name = new DefaultComboBoxModel();
			name.addElement("");
			name.addElement("Select an image");
			JComboBox comboBox = new JComboBox(name);
			comboBox.setSelectedIndex(0);
			comboBox.setBounds(32, 56, 380, 39);
			frmSoilPh.getContentPane().add(comboBox);
			
			JLabel lblNewLabel_3 = new JLabel("Measurement of soil pH using RGB color space");
			lblNewLabel_3.setForeground(Color.BLACK);
			lblNewLabel_3.setFont(new Font("Times New Roman", Font.BOLD, 24));
			lblNewLabel_3.setBounds(347, 11, 506, 34);
			frmSoilPh.getContentPane().add(lblNewLabel_3);
	
			comboBox.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(comboBox.getSelectedIndex()==1){
					JFileChooser fc=new JFileChooser();
					int i=fc.showOpenDialog(frmSoilPh);
					if(i==JFileChooser.APPROVE_OPTION)
					{
						f=fc.getSelectedFile();
						String fname=f.getPath();
						comboBox.addItem(fname);
						comboBox.setSelectedItem(fname);
						ImageIcon img= new ImageIcon(new ImageIcon(fname).getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(), Image.SCALE_SMOOTH));
						lblNewLabel.setIcon(img);
				}
				}
				}
			});
			
			
		}
		void ph_result(BufferedImage im){
			float[] array=new float[4];
			ob.MeanRGB(im,array);
			
			Color c=new Color((int)array[0],(int)array[1],(int)array[2]);
			frmSoilPh.getContentPane().setBackground(c);
			float a=(array[3])*100;
			float round=Math.round(a);
			a=round/100;
			float result=0.0f;
			conn=new DBConnect();
			try {
				result=conn.DBData(a);
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			textArea.setText("R:"+(int) array[0]+" G:"+(int) array[1]+" B:"+(int) array[2]+"\nPh_Index:"+array[3]+"\npH:"+result);
		}
		private void initialize() {
	
			frmSoilPh = new JFrame();
			frmSoilPh.getContentPane().setBackground(Color.WHITE);
			frmSoilPh.setResizable(false);
			frmSoilPh.setTitle("Soil Ph");
			frmSoilPh.setBounds(100, 100, 1191, 697);
			frmSoilPh.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frmSoilPh.getContentPane().setLayout(null);
			design();
			
			
			
			create_segments.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 if(lblNewLabel.getIcon()==null)
					 JOptionPane.showMessageDialog(frmSoilPh, "No Image is selected");
				 else{
				 try{
				ob=new ImageToPixel(f);
				int ab[][]=ob.ab.clone();
					int size=ab.length;
					int list[]=new int[size];
					obj=new Kmean1();
					 list=obj.Kmean1(3,ab);
					    BufferedImage img =ImageIO.read(f);
					    int h=img.getHeight();
					    int w=img.getWidth();
					    int list2[][]=new int[w][h];
					     image1=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
					    image2=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
					     image3=new BufferedImage(w,h,BufferedImage.TYPE_INT_RGB);
					    Color myBlack = new Color(0,0,0); // Color white
						 int rgb = myBlack.getRGB();
						 for (int i = 0; i < h; i++) {
						        for (int j = 0; j < w; j++) {
						        	list2[j][i]=list[count++];
						        }
						    }
							 for(int i=0;i<h;i++){
								 for(int j=0;j<w;j++){
									 if(list2[j][i]==1)
										 image1.setRGB(j,i,img.getRGB(j,i));
									 else
										 image1.setRGB(j,i,rgb);
								 }
							 }
							 for(int i=0;i<h;i++){
								 for(int j=0;j<w;j++){
									 if(list2[j][i]==2)
										 image2.setRGB(j,i,img.getRGB(j,i));
									 else
										 image2.setRGB(j,i,rgb);
								 }
							 }
							 for(int i=0;i<h;i++){
								 for(int j=0;j<w;j++){
									 if(list2[j][i]==3)
										 image3.setRGB(j,i,img.getRGB(j,i));
									 else
										 image3.setRGB(j,i,rgb);
								 }
							 }
	
									ImageIO.write(image1, "png",  new File("D:\\Segment1.png"));
									ImageIO.write(image2, "png",  new File("D:\\Segment2.png"));
									ImageIO.write(image3, "png",  new File("D:\\Segment3.png"));
						    // retrieve image
						    
						    image_icon1= new ImageIcon(new ImageIcon(image1).getImage().getScaledInstance(306, 234, Image.SCALE_DEFAULT));
							label1.setIcon(image_icon1);
							image_icon2= new ImageIcon(new ImageIcon(image2).getImage().getScaledInstance(306, 234, Image.SCALE_DEFAULT));
							label2.setIcon(image_icon2);
							image_icon3= new ImageIcon(new ImageIcon(image3).getImage().getScaledInstance(306, 234, Image.SCALE_DEFAULT));
							label3.setIcon(image_icon3);
							count=0;
							
				    }catch(IOException e){}
				 }
	}
	});	
		}
	}
		
	
