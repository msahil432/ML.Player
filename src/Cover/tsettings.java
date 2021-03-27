package Cover;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JRadioButton;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Skeleton.myFile;
import XML.xml;
import XML.xml_energy;
import XML.xml_recents;
import XML.xml_soul;

/**
 * 
 * <b>ML.Player - Frame of Settings</b>
 * <p> - The settings/preferences UI frame is this
 * 
 * @version 0.2c
 * <p><b> Class-wise Change Log: 0.1c -> 0.2c</b>
 * <p> - added formal documentation
 * <p> - constructor {@link #tsettings()} and {@link #refreshinstances()} made private from public
 * 
 * @author msahil432
 *
 */
public class tsettings extends JFrame
{
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static tsettings tframe;
	private int size, repeat;
	private String magicno;
	private boolean shuffle, saveplaylist;
	private xml_soul xs;
	private xml_energy xe;
	private xml x;
	private double l, h;
	
	/**
	 * To show frame, this method is called
	 */
	public static void main()
	{
		tframe = new tsettings();
		
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					tframe.setVisible(true);
					try
			        {
			            tframe.setIconImage(ImageIO.read(new myFile("data/img/logo.png")));
			        }
			        catch (Exception ex)
			        {
			        	JOptionPane.showMessageDialog(null, ex.getMessage()+" Icon of Settings", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
			        }
					tframe.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
					tframe.addWindowListener(new WindowAdapter()
			        {
			        	@Override
			        	public void windowClosing(WindowEvent we)
			        	{
			        		tframe.dispose();
			        	}
			        });
				}
				catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage()+" Settings", "Error - ML.Player",JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Get instances of various XML classes to save received preferences
	 */
	private void refreshinstances()
	{
		xe = xml_energy.getInstance();
		xs = xml_soul.getInstance();
		x = xml.getInstance();
	}
	
	/**
	 * Constructor which also creates the components and add them, position them and packages the frame .
	 */
	@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
	private tsettings()
	{
		refreshinstances();
		
		magicno = xs.get("magicno");
		size = Integer.parseInt(x.get("size"));
		repeat = Integer.parseInt(xe.get("repeat"));
		shuffle = Boolean.parseBoolean(xe.get("shuffle"));
		saveplaylist = Boolean.parseBoolean(xe.get("saveplaylist"));
		
		
		l = Toolkit.getDefaultToolkit().getScreenSize().width / 28;
		h = Toolkit.getDefaultToolkit().getScreenSize().height / 26;
		
		setTitle("ML.Player - Settings");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds((int)(2.08*l), (int)(3.45*h), (int)(13.11*l), (int)(18.03*h));
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMagicNo = new JLabel("Magic Time");
		lblMagicNo.setBounds((int)(0.2*l), (int)(0.94*h), (int)(2.27*l), (int)(0.78*h));
		lblMagicNo.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(lblMagicNo);
		
		JLabel lblTheMinimumNumber = new JLabel("The minimum time in which, a key must be pressed, after which Player asks ");
		lblTheMinimumNumber.setBounds((int)(0.72*l), (int)(1.8*h), (int)(12.23*l), (int)(0.8*h));
		contentPane.add(lblTheMinimumNumber);
		
		JLabel lblDefaultSize = new JLabel("Default Size");
		lblDefaultSize.setBounds((int)(0.2*l), (int)(3.48*h), (int)(2.31*l), (int)(0.48*h));
		lblDefaultSize.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(lblDefaultSize);
		
		JLabel lblTheSizeWith = new JLabel("The size with which the player must always start:");
		lblTheSizeWith.setBounds((int)(0.96*l), (int)(4.34*h), (int)(6.69*l), (int)(0.48*h));
		contentPane.add(lblTheSizeWith);
		
		JLabel lblActionToBe = new JLabel("for action to be choosen for the pressed key.");
		lblActionToBe.setBounds((int)(0.42*l), (int)(2.62*h), (int)(7.23*l), (int)(0.48*h));
		contentPane.add(lblActionToBe);
		
		JRadioButton rdbtnMaximum = new JRadioButton("Maximum");
		rdbtnMaximum.setBounds((int)(7.77*l), (int)(4.2*h), (int)(2.27*l), (int)(0.79*h));
		rdbtnMaximum.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				size = 1;
			}
		});
		contentPane.add(rdbtnMaximum);
		
		JRadioButton rdbtnMedium = new JRadioButton("Medium");
		rdbtnMedium.setBounds((int)(10.34*l), (int)(4.2*h), (int)(2.27*l), (int)(0.79*h));
		rdbtnMedium.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				size = 2;
			}
		});
		contentPane.add(rdbtnMedium);
		
		JRadioButton rdbtnMinimum = new JRadioButton("Minimum");
		rdbtnMinimum.setBounds((int)(7.77*l), (int)(5.1*h), (int)(2.27*l), (int)(0.79*h));
		rdbtnMinimum.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				size = 3;
			}
		});
		contentPane.add(rdbtnMinimum);
		
		JRadioButton rdbtnLikeClosed = new JRadioButton("Like closed");
		rdbtnLikeClosed.setBounds((int)(10.26*l), (int)(5.1*h),(int)(2.27*l), (int)(0.79*h));
		rdbtnLikeClosed.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				size = 4;
			}
		});
		
		contentPane.add(rdbtnLikeClosed);
		
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdbtnMinimum);
		bg.add(rdbtnMedium);
		bg.add(rdbtnMaximum);
		bg.add(rdbtnLikeClosed);
		
		refreshinstances();
		switch(Integer.parseInt(x.get("size")))
		{
		case 1:
			{
				rdbtnMaximum.setSelected(true);
				break;
			}
		case 2:
			{
				rdbtnMedium.setSelected(true);
				break;
			}
		case 3:
			{
				rdbtnMinimum.setSelected(true);
				break;
			}
		default:
			{
				break;
			}
		}
		
		JLabel lblRepeat = new JLabel("Repeat");
		lblRepeat.setBounds((int)(0.2*l), (int)(6.1*h), (int)(1.67*l), (int)(0.8*h));
		lblRepeat.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(lblRepeat);
		
		JLabel lblHowShouldSongs = new JLabel("How should songs should be repeated:");
		lblHowShouldSongs.setBounds((int)(0.96*l), (int)(7*h), (int)(4.88*l), (int)(0.48*h));
		contentPane.add(lblHowShouldSongs);
		
		JRadioButton rdbtnOne = new JRadioButton("One");
		rdbtnOne.setBounds((int)(7.77*l), (int)(6.87*h),(int)(2.27*l), (int)(0.79*h));
		rdbtnOne.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				repeat =1;
			}
			
		});
		contentPane.add(rdbtnOne);
		
		JRadioButton rdbtnAll = new JRadioButton("All");
		rdbtnAll.setBounds((int)(10.34*l), (int)(6.87*h),(int)(2.27*l), (int)(0.79*h));
		rdbtnAll.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				repeat =2;
			}
			
		});
		contentPane.add(rdbtnAll);
		
		JRadioButton rdbtnNone = new JRadioButton("None");
		rdbtnNone.setBounds((int)(5.98*l), (int)(6.87*h), (int)(1.67*l), (int)(0.8*h));
		rdbtnNone.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent ae)
			{
				repeat =0;
			}
			
		});
		contentPane.add(rdbtnNone);
		
		ButtonGroup bg1 = new ButtonGroup();
		bg1.add(rdbtnOne);
		bg1.add(rdbtnAll);
		bg1.add(rdbtnNone);
		
		if(repeat==0)
			rdbtnNone.setSelected(true);
		else if(repeat==1)
			rdbtnOne.setSelected(true);
		else
			rdbtnAll.setSelected(true);
		
		JLabel lblShuffle = new JLabel("Shuffle");
		lblShuffle.setBounds((int)(0.21*l), (int)(7.86*h), (int)(1.42*l), (int)(1.24*h));
		lblShuffle.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(lblShuffle);
		
		JLabel lblTheSongsShould = new JLabel("The songs should be played in order or not? :");
		lblTheSongsShould.setBounds((int)(0.96*l), (int)(9.48*h), (int)(6.69*l), (int)(0.48*h));
		contentPane.add(lblTheSongsShould);
		
		final JCheckBox chckbxYes = new JCheckBox("Yes");
		if(!shuffle)
			chckbxYes.setSelected(true);
		chckbxYes.setBounds((int)(7.77*l), (int)(9.34*h), (int)(2.27*l), (int)(0.79*h));
		chckbxYes.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if(chckbxYes.isSelected())
					shuffle = false;
				else
					shuffle = true;
				System.out.println(""+shuffle);
			}
		});
		contentPane.add(chckbxYes);
		
		JButton btnResetAll = new JButton("All");
		btnResetAll.setBounds((int)(7.77*l), (int)(13.9*h), (int)(1.2*l), (int)(0.79*h));
		btnResetAll.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				//code to reset all
				refreshinstances();
				xe.reset(" by Settings of Player");
				xs.reset(" by Settings of Player");
				x.reset(" by Settings of Player");
				xml_recents.getInstance().reset(" by settings of Player");
			}
		});
		contentPane.add(btnResetAll);
		
		JButton btnResetML = new JButton("ML");
		btnResetML.setBounds((int)(8.97*l), (int)(13.9*h), (int)(1.2*l), (int)(0.79*h));
		btnResetML.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				//code to reset all
				refreshinstances();
				xs.reset(" by Settings of Player");
			}
		});
		contentPane.add(btnResetML);
		
		JButton btnResetplaylist = new JButton("PlayList");
		btnResetplaylist.setBounds((int)(10.17*l), (int)(13.9*h), (int)(2.27*l), (int)(0.79*h));
		btnResetplaylist.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				//code to reset all
				refreshinstances();
				xe.reset(" by Settings of Player");
				xml_recents.getInstance().reset(" by settings of Player");
			}
		});
		contentPane.add(btnResetplaylist);
		
		JLabel lblResetMl = new JLabel("Reset Player");
		lblResetMl.setBounds((int)(0.21*l), (int)(12.76*h), (int)(2.5*l), (int)(1.24*h));
		lblResetMl.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		contentPane.add(lblResetMl);
		
		JLabel lblResetAllThe = new JLabel("Reset configurations to Factory Default:");
		lblResetAllThe.setBounds((int)(0.96*l), (int)(14.03*h), (int)(6.69*l), (int)(0.48*h));
		contentPane.add(lblResetAllThe);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBounds((int)(10.71*l), (int)(15.56*h), (int)(2*l), (int)(0.79*h));
		btnSave.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				refreshinstances();
				
				System.out.println(saveplaylist+" "+shuffle+" "+repeat+" "+magicno+" "+size);
				xe.set("saveplaylist", String.valueOf(saveplaylist));
				xe.set("shuffle",String.valueOf(shuffle));
				xe.set("repeat", String.valueOf(repeat));
				if(magicno!=null)
					xs.set("magicno", String.valueOf(magicno));
				if((size<4)&&(size>0))
					x.set("size", String.valueOf(size));
				tframe.dispose();
			}
		});
		contentPane.add(btnSave);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds((int)(6.81*l), (int)(15.56*h), (int)(2*l), (int)(0.79*h));
		btnCancel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				tframe.dispose();
			}
		});
		contentPane.add(btnCancel);
		
		final JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"2", "5", "10", "15", "20", "25", "30", "60"}));
		comboBox.setBounds((int)(10.38*l), (int)(1.86*h), (int)(2.27*l), (int)(0.79*h));
		comboBox.setSelectedItem((xs.get("magicno")));
		comboBox.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				magicno = String.valueOf(comboBox.getSelectedItem());
			}
		});
		contentPane.add(comboBox);
		
		JLabel lblSavePlaylist = new JLabel("Save Playlist");
		lblSavePlaylist.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 15));
		lblSavePlaylist.setBounds((int)(0.21*l), (int)(10.69*h), (int)(3.08*l), (int)(1.24*h));
		contentPane.add(lblSavePlaylist);
		
		JLabel lblThePlaylistWill = new JLabel("The playlist will be saved and loaded again on next run:");
		lblThePlaylistWill.setBounds((int)(0.95*l), (int)(11.9*h), (int)(6.7*l), (int)(0.48*h));
		contentPane.add(lblThePlaylistWill);
		
		final JCheckBox chckbxYes_1 = new JCheckBox("Yes");
		chckbxYes_1.setBounds((int)(7.77*l), (int)(11.76*h), (int)(2.27*l), (int)(0.79*h));
		if(saveplaylist)
			chckbxYes_1.setSelected(true);
		chckbxYes_1.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ae)
			{
				if(chckbxYes_1.isSelected())
					saveplaylist=true;
				else
					saveplaylist=false;
				System.out.println(" "+saveplaylist);
			}
		});
		contentPane.add(chckbxYes_1);
	}
}