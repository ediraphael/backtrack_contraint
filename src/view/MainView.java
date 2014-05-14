package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JSeparator;
import javax.swing.JScrollPane;
import javax.swing.JRadioButton;
import javax.swing.JLabel;

public class MainView 
{

	private JFrame frame;
	private JPanel mainPanel;
	private Box hbResult;
	private JSeparator separatorTop;
	private JPanel panelResult;
	private Box hbResultSetting;
	private JPanel panMainSetting; 
	private JPanel panSeparatorTop;
	private JPanel panelSeparatorLow;
	private JSeparator separatorLow;
	private Box vbLeftSettingRun;
	private Box hbResultContainer;
	private JPanel mainPanResult;
	private JPanel panResultSetting;
	private JScrollPane scrollPaneResult;
	private JTextArea textAreaResult;
	private Box hbAction;
	private JPanel panelAction;
	private Box vbButton;
	private JPanel panelButton;
	private Box hbRun;
	private JPanel panelRun;
	private Box hbTopSettingRun;
	private Box hbLowSettingRun;
	private Box hbContainerRunBtn;
	private JPanel panContainRunBtn;
	private JButton btnRun;
	private Box vbRightSettingRun;
	private Box hbExit;
	private JPanel panelExit;
	private Box hbTopSettingExit;
	private Box hbLowSettingExit;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					MainView window = new MainView();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		hbResult = Box.createHorizontalBox();
		mainPanel.add(hbResult, BorderLayout.CENTER);
		
		panelResult = new JPanel();
		hbResult.add(panelResult);
		panelResult.setLayout(new BorderLayout(0, 0));
		
		hbResultSetting = Box.createHorizontalBox();
		hbResultSetting.setPreferredSize(new Dimension(0, 20));
		panelResult.add(hbResultSetting, BorderLayout.SOUTH);
		
		panMainSetting = new JPanel();
		hbResultSetting.add(panMainSetting);
		panMainSetting.setLayout(new BorderLayout(0, 0));
		
		panSeparatorTop = new JPanel();
		panSeparatorTop.setPreferredSize(new Dimension(0, 10));
		panMainSetting.add(panSeparatorTop, BorderLayout.NORTH);
		panSeparatorTop.setLayout(new BorderLayout(0, 0));
		
		separatorTop = new JSeparator();
		panSeparatorTop.add(separatorTop, BorderLayout.CENTER);
		
		panelSeparatorLow = new JPanel();
		panelSeparatorLow.setPreferredSize(new Dimension(0, 10));
		panMainSetting.add(panelSeparatorLow, BorderLayout.SOUTH);
		panelSeparatorLow.setLayout(new BorderLayout(0, 0));
		
		separatorLow = new JSeparator();
		panelSeparatorLow.add(separatorLow, BorderLayout.CENTER);
		
		hbResultContainer = Box.createHorizontalBox();
		panelResult.add(hbResultContainer, BorderLayout.CENTER);
		
		mainPanResult = new JPanel();
		hbResultContainer.add(mainPanResult);
		mainPanResult.setLayout(new BorderLayout(0, 0));
		
		panResultSetting = new JPanel();
		mainPanResult.add(panResultSetting, BorderLayout.SOUTH);
		
		scrollPaneResult = new JScrollPane();
		mainPanResult.add(scrollPaneResult, BorderLayout.CENTER);
		
		textAreaResult = new JTextArea();
		scrollPaneResult.setViewportView(textAreaResult);
		
		hbAction = Box.createHorizontalBox();
		hbAction.setPreferredSize(new Dimension(0, 100));
		mainPanel.add(hbAction, BorderLayout.SOUTH);
		
		panelAction = new JPanel();
		hbAction.add(panelAction);
		panelAction.setLayout(new BorderLayout(0, 0));
		
		vbButton = Box.createVerticalBox();
		vbButton.setPreferredSize(new Dimension(130, 0));
		panelAction.add(vbButton, BorderLayout.EAST);
		
		panelButton = new JPanel();
		vbButton.add(panelButton);
		panelButton.setLayout(new BorderLayout(0, 0));
		
		hbRun = Box.createHorizontalBox();
		hbRun.setPreferredSize(new Dimension(0, 50));
		panelButton.add(hbRun, BorderLayout.NORTH);
		
		panelRun = new JPanel();
		hbRun.add(panelRun);
		panelRun.setLayout(new BorderLayout(0, 0));
		
		hbTopSettingRun = Box.createHorizontalBox();
		hbTopSettingRun.setPreferredSize(new Dimension(0, 15));
		panelRun.add(hbTopSettingRun, BorderLayout.NORTH);
		
		hbLowSettingRun = Box.createHorizontalBox();
		hbLowSettingRun.setPreferredSize(new Dimension(0, 8));
		panelRun.add(hbLowSettingRun, BorderLayout.SOUTH);
		
		hbContainerRunBtn = Box.createHorizontalBox();
		panelRun.add(hbContainerRunBtn, BorderLayout.CENTER);
		
		panContainRunBtn = new JPanel();
		panContainRunBtn.setMinimumSize(new Dimension(0, 0));
		hbContainerRunBtn.add(panContainRunBtn);
		panContainRunBtn.setLayout(new BorderLayout(0, 0));
		
		btnRun = new JButton("Lancer");
		btnRun.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				//TODO lancer algo
			}
		});
		panContainRunBtn.add(btnRun, BorderLayout.CENTER);
		
		vbLeftSettingRun = Box.createVerticalBox();
		vbLeftSettingRun.setPreferredSize(new Dimension(10, 0));
		panelRun.add(vbLeftSettingRun, BorderLayout.WEST);
		
		vbRightSettingRun = Box.createVerticalBox();
		vbRightSettingRun.setPreferredSize(new Dimension(10, 0));
		panelRun.add(vbRightSettingRun, BorderLayout.EAST);
		
		hbExit = Box.createHorizontalBox();
		hbExit.setPreferredSize(new Dimension(0, 50));
		panelButton.add(hbExit, BorderLayout.SOUTH);
		
		panelExit = new JPanel();
		hbExit.add(panelExit);
		panelExit.setLayout(new BorderLayout(0, 0));
		
		hbTopSettingExit = Box.createHorizontalBox();
		hbTopSettingExit.setPreferredSize(new Dimension(8, 10));
		panelExit.add(hbTopSettingExit, BorderLayout.NORTH);
		
		hbLowSettingExit = Box.createHorizontalBox();
		hbLowSettingExit.setPreferredSize(new Dimension(0, 15));
		panelExit.add(hbLowSettingExit, BorderLayout.SOUTH);
		
		Box hbContainerExitBtn = Box.createHorizontalBox();
		panelExit.add(hbContainerExitBtn, BorderLayout.CENTER);
		
		JPanel panContainExitBtn = new JPanel();
		panContainExitBtn.setMinimumSize(new Dimension(0, 0));
		hbContainerExitBtn.add(panContainExitBtn);
		panContainExitBtn.setLayout(new BorderLayout(0, 0));
		
		JButton btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
		panContainExitBtn.add(btnExit, BorderLayout.CENTER);
		
		Box vbLeftSettingExit = Box.createVerticalBox();
		vbLeftSettingExit.setPreferredSize(new Dimension(10, 0));
		panelExit.add(vbLeftSettingExit, BorderLayout.WEST);
		
		Box vbRightSettingExit = Box.createVerticalBox();
		vbRightSettingExit.setPreferredSize(new Dimension(10, 0));
		panelExit.add(vbRightSettingExit, BorderLayout.EAST);
		
		Box vbChoice = Box.createVerticalBox();
		panelAction.add(vbChoice, BorderLayout.CENTER);
		
		JPanel panChoice = new JPanel();
		vbChoice.add(panChoice);
		panChoice.setLayout(new BorderLayout(0, 0));
		
		Box vbChoiceLeftSetting = Box.createVerticalBox();
		vbChoiceLeftSetting.setPreferredSize(new Dimension(20, 0));
		panChoice.add(vbChoiceLeftSetting, BorderLayout.WEST);
		
		Box hbChoiceTopSetting = Box.createHorizontalBox();
		hbChoiceTopSetting.setPreferredSize(new Dimension(0, 15));
		panChoice.add(hbChoiceTopSetting, BorderLayout.NORTH);
		
		Box hbChoiceLowSetting = Box.createHorizontalBox();
		hbChoiceLowSetting.setPreferredSize(new Dimension(0, 15));
		panChoice.add(hbChoiceLowSetting, BorderLayout.SOUTH);
		
		Box horizontalBox = Box.createHorizontalBox();
		panChoice.add(horizontalBox, BorderLayout.CENTER);
		
		JPanel panChoiceElement = new JPanel();
		horizontalBox.add(panChoiceElement);
		panChoiceElement.setLayout(new BorderLayout(0, 0));
		
		JPanel panChoiceAlgo = new JPanel();
		panChoiceAlgo.setPreferredSize(new Dimension(200, 10));
		panChoiceElement.add(panChoiceAlgo, BorderLayout.WEST);
		panChoiceAlgo.setLayout(new BorderLayout(0, 0));
		
		Box hbTopChoiceAlgo = Box.createHorizontalBox();
		hbTopChoiceAlgo.setPreferredSize(new Dimension(0, 25));
		panChoiceAlgo.add(hbTopChoiceAlgo, BorderLayout.NORTH);
		
		JPanel panAlgo = new JPanel();
		hbTopChoiceAlgo.add(panAlgo);
		panAlgo.setLayout(new BorderLayout(0, 0));
		
		JLabel lblAlgo = new JLabel("Algorithme");
		panAlgo.add(lblAlgo, BorderLayout.CENTER);
		
		JPanel panAlgoLeftSetting = new JPanel();
		panAlgoLeftSetting.setPreferredSize(new Dimension(35, 10));
		panAlgo.add(panAlgoLeftSetting, BorderLayout.WEST);
		
		JPanel panAlgoSeparator = new JPanel();
		panAlgoSeparator.setPreferredSize(new Dimension(10, 2));
		panAlgo.add(panAlgoSeparator, BorderLayout.SOUTH);
		panAlgoSeparator.setLayout(new BorderLayout(0, 0));
		
		JPanel panAlgoSeparatorLeftSetting = new JPanel();
		panAlgoSeparatorLeftSetting.setPreferredSize(new Dimension(5, 0));
		panAlgoSeparator.add(panAlgoSeparatorLeftSetting, BorderLayout.WEST);
		
		JPanel panAlgoSeparatorRightSetting = new JPanel();
		panAlgoSeparatorRightSetting.setPreferredSize(new Dimension(58, 0));
		panAlgoSeparator.add(panAlgoSeparatorRightSetting, BorderLayout.EAST);
		
		JSeparator separatorAlgo = new JSeparator();
		panAlgoSeparator.add(separatorAlgo, BorderLayout.CENTER);
		
		Box hbLowChoiceAlgo = Box.createHorizontalBox();
		panChoiceAlgo.add(hbLowChoiceAlgo, BorderLayout.CENTER);
		
		JPanel panRdbChoiceAlgo = new JPanel();
		hbLowChoiceAlgo.add(panRdbChoiceAlgo);
		panRdbChoiceAlgo.setLayout(new BorderLayout(0, 0));
		
		JRadioButton rdbtnForwardCheking = new JRadioButton("Forward Cheking");
		panRdbChoiceAlgo.add(rdbtnForwardCheking, BorderLayout.SOUTH);
		
		JRadioButton rdbtnTestGenerate = new JRadioButton("Test & Generate");
		panRdbChoiceAlgo.add(rdbtnTestGenerate, BorderLayout.NORTH);
		
		JPanel panChoiceHeuristic = new JPanel();
		panChoiceHeuristic.setPreferredSize(new Dimension(200, 10));
		panChoiceElement.add(panChoiceHeuristic, BorderLayout.EAST);
		
		JPanel panChoiceAc = new JPanel();
		panChoiceElement.add(panChoiceAc, BorderLayout.CENTER);
		panChoiceAc.setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanTopSetting = new JPanel();
		mainPanel.add(mainPanTopSetting, BorderLayout.NORTH);
		
		JPanel mainPanRightSetting = new JPanel();
		mainPanel.add(mainPanRightSetting, BorderLayout.WEST);
		
		JPanel mainPanLeftSetting = new JPanel();
		mainPanel.add(mainPanLeftSetting, BorderLayout.EAST);
	}

}
