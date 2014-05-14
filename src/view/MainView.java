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

public class MainView {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainView window = new MainView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainView() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 800, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		Box hbResult = Box.createHorizontalBox();
		mainPanel.add(hbResult, BorderLayout.CENTER);
		
		JPanel panelResult = new JPanel();
		hbResult.add(panelResult);
		panelResult.setLayout(new BorderLayout(0, 0));
		
		Box hbResultSetting = Box.createHorizontalBox();
		hbResultSetting.setPreferredSize(new Dimension(0, 20));
		panelResult.add(hbResultSetting, BorderLayout.SOUTH);
		
		JPanel panMainSetting = new JPanel();
		hbResultSetting.add(panMainSetting);
		panMainSetting.setLayout(new BorderLayout(0, 0));
		
		JPanel panSeparatorTop = new JPanel();
		panSeparatorTop.setPreferredSize(new Dimension(0, 10));
		panMainSetting.add(panSeparatorTop, BorderLayout.NORTH);
		panSeparatorTop.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorTop = new JSeparator();
		panSeparatorTop.add(separatorTop, BorderLayout.CENTER);
		
		JPanel panelSeparatorLow = new JPanel();
		panelSeparatorLow.setPreferredSize(new Dimension(0, 10));
		panMainSetting.add(panelSeparatorLow, BorderLayout.SOUTH);
		panelSeparatorLow.setLayout(new BorderLayout(0, 0));
		
		JSeparator separatorLow = new JSeparator();
		panelSeparatorLow.add(separatorLow, BorderLayout.CENTER);
		
		Box hbResultContainer = Box.createHorizontalBox();
		panelResult.add(hbResultContainer, BorderLayout.CENTER);
		
		JPanel mainPanResult = new JPanel();
		hbResultContainer.add(mainPanResult);
		mainPanResult.setLayout(new BorderLayout(0, 0));
		
		JPanel panResultSetting = new JPanel();
		mainPanResult.add(panResultSetting, BorderLayout.SOUTH);
		
		JScrollPane scrollPaneResult = new JScrollPane();
		mainPanResult.add(scrollPaneResult, BorderLayout.CENTER);
		
		JTextArea textAreaResult = new JTextArea();
		scrollPaneResult.setViewportView(textAreaResult);
		
		Box hbAction = Box.createHorizontalBox();
		hbAction.setPreferredSize(new Dimension(0, 100));
		mainPanel.add(hbAction, BorderLayout.SOUTH);
		
		JPanel panelAction = new JPanel();
		hbAction.add(panelAction);
		panelAction.setLayout(new BorderLayout(0, 0));
		
		Box vbButton = Box.createVerticalBox();
		vbButton.setPreferredSize(new Dimension(130, 0));
		panelAction.add(vbButton, BorderLayout.EAST);
		
		JPanel panelButton = new JPanel();
		vbButton.add(panelButton);
		panelButton.setLayout(new BorderLayout(0, 0));
		
		Box hbRun = Box.createHorizontalBox();
		hbRun.setPreferredSize(new Dimension(0, 50));
		panelButton.add(hbRun, BorderLayout.NORTH);
		
		JPanel panelRun = new JPanel();
		hbRun.add(panelRun);
		panelRun.setLayout(new BorderLayout(0, 0));
		
		Box hbTopSettingRun = Box.createHorizontalBox();
		hbTopSettingRun.setPreferredSize(new Dimension(0, 15));
		panelRun.add(hbTopSettingRun, BorderLayout.NORTH);
		
		Box hbLowSettingRun = Box.createHorizontalBox();
		hbLowSettingRun.setPreferredSize(new Dimension(0, 8));
		panelRun.add(hbLowSettingRun, BorderLayout.SOUTH);
		
		Box hbContainerRunBtn = Box.createHorizontalBox();
		panelRun.add(hbContainerRunBtn, BorderLayout.CENTER);
		
		JPanel panContainRunBtn = new JPanel();
		panContainRunBtn.setMinimumSize(new Dimension(0, 0));
		hbContainerRunBtn.add(panContainRunBtn);
		panContainRunBtn.setLayout(new BorderLayout(0, 0));
		
		JButton btnRun = new JButton("Lancer");
		btnRun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		panContainRunBtn.add(btnRun, BorderLayout.CENTER);
		
		Box vbLeftSettingRun = Box.createVerticalBox();
		vbLeftSettingRun.setPreferredSize(new Dimension(10, 0));
		panelRun.add(vbLeftSettingRun, BorderLayout.WEST);
		
		Box vbRightSettingRun = Box.createVerticalBox();
		vbRightSettingRun.setPreferredSize(new Dimension(10, 0));
		panelRun.add(vbRightSettingRun, BorderLayout.EAST);
		
		Box hbExit = Box.createHorizontalBox();
		hbExit.setPreferredSize(new Dimension(0, 50));
		panelButton.add(hbExit, BorderLayout.SOUTH);
		
		JPanel panelExit = new JPanel();
		hbExit.add(panelExit);
		panelExit.setLayout(new BorderLayout(0, 0));
		
		Box hbTopSettingExit = Box.createHorizontalBox();
		hbTopSettingExit.setPreferredSize(new Dimension(8, 10));
		panelExit.add(hbTopSettingExit, BorderLayout.NORTH);
		
		Box hbLowSettingExit = Box.createHorizontalBox();
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
		hbChoiceLowSetting.setPreferredSize(new Dimension(0, 20));
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
		
		Box hbLowChoiceAlgo = Box.createHorizontalBox();
		panChoiceAlgo.add(hbLowChoiceAlgo, BorderLayout.CENTER);
		
		JPanel panel = new JPanel();
		hbLowChoiceAlgo.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JRadioButton rdbtnForwardCheking = new JRadioButton("Forward Cheking");
		panel.add(rdbtnForwardCheking, BorderLayout.NORTH);
		
		JRadioButton rdbtnTestGenerate = new JRadioButton("Test & Generate");
		panel.add(rdbtnTestGenerate, BorderLayout.SOUTH);
		
		JPanel panChoiceHeuristic = new JPanel();
		panChoiceHeuristic.setPreferredSize(new Dimension(200, 10));
		panChoiceElement.add(panChoiceHeuristic, BorderLayout.EAST);
		
		JPanel panChoiceAc = new JPanel();
		panChoiceElement.add(panChoiceAc, BorderLayout.CENTER);
		
		JPanel mainPanTopSetting = new JPanel();
		mainPanel.add(mainPanTopSetting, BorderLayout.NORTH);
		
		JPanel mainPanRightSetting = new JPanel();
		mainPanel.add(mainPanRightSetting, BorderLayout.WEST);
		
		JPanel mainPanLeftSetting = new JPanel();
		mainPanel.add(mainPanLeftSetting, BorderLayout.EAST);
	}

}
