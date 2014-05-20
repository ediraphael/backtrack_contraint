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

import javax.swing.JFileChooser;
import javax.swing.JSeparator;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.ButtonGroup;

import modele.AbstractSolver;
import modele.AbstractSolver.Heuristic;
import modele.Parser;
import modele.Parser.SolverType;

import javax.swing.JScrollPane;

import Exception.DomainBoundaryException;
import Exception.VariableValueException;
import java.awt.Font;
import java.awt.Color;

public class MainView 
{

	private JFrame frame;

	private JPanel mainPanel;
	private JPanel panMainSetting; 
	private JPanel panSeparatorTop;
	private JPanel panelSeparatorLow;
	private JPanel panelResult;
	private JPanel panResultSetting;
	private JPanel mainPanResult;
	private JPanel panelAction;
	private JPanel panelButton;
	private JPanel panelRun;
	private JPanel panContainRunBtn;
	private JPanel panelExit;
	private JPanel panContainExitBtn;
	private JPanel panAlgo;
	private JPanel panChoice;
	private JPanel panChoiceElement;
	private JPanel panChoiceAlgo;
	private JPanel panAlgoLeftSetting;
	private JPanel panAlgoSeparator;
	private JPanel panAlgoSeparatorLeftSetting;
	private JPanel panAlgoSeparatorRightSetting;
	private JPanel panRdbChoiceAlgo;
	private JPanel panChoiceHeuristicAndUpload;
	private JPanel panChoiceAc;
	private JPanel mainPanTopSetting;
	private JPanel mainPanRightSetting;
	private JPanel mainPanLeftSetting;
	private JPanel panAc;
	private JPanel panAcLeftSetting;
	private JPanel panAcSeparator;
	private JPanel panAcSeparatorLeftSetting;
	private JPanel panAlcSeparatorRightSetting;
	private JPanel panAcRdbLeftSetting;
	private JPanel panAcRdbContainer;
	private JPanel panChoiceHeuristic;
	private JPanel panUpload;
	private JPanel panHeuristic;
	private JPanel panHeristicLabelLeftSetting;
	private JPanel panHeristicSeparatorContainer;
	private JPanel panHeristicSeparator;
	private JPanel panHeuristicSeparatorLeftSetting;
	private JPanel panContainerHeuristicList;
	private JPanel panHeuristicList;
	private JPanel panHeuristicListTopSetting;
	private JPanel panHeuristicListLowSetting;
	private JPanel panHeuristicListLeftSetting;
	private JPanel panUploadFile;
	private JPanel panLabelFileLeftSetting;
	private JPanel panUploadSeparatorContainer;
	private JPanel panContainerLabelFile;
	private JPanel panUploadSeparatorLeftSetting;
	private JPanel panUploadSeparatorRightSetting;
	private JPanel panUploadSeparator;
	private JPanel panContainerUpload;
	private JPanel panBtnUploadContainer;
	private JPanel panBtnUploadLeftSetting;
	private JPanel panBtnUploadTopSetting;
	private JPanel panBtnUploadRightSetting;
	private JPanel panBtnUpload;
	private JPanel panShowResultContainer;
	private JPanel panSeparatorShow;
	private JPanel panBtnUploadLowSetting;
	private JPanel panelTimeExecution;
	
	private JScrollPane scrollPaneShowFile;
	private JScrollPane scrollPaneShowResult;

	private Box vbLeftSettingExit;
	private Box vbRightSettingExit;
	private Box vbChoice;
	private Box vbChoiceLeftSetting;
	private Box hbChoiceTopSetting;
	private Box hbChoiceLowSetting;
	private Box hbChoiceContainer;
	private Box hbTopChoiceAlgo;
	private Box hbResult;
	private Box hbResultSetting;
	private Box vbLeftSettingRun;
	private Box hbResultContainer;
	private Box hbAction;
	private Box vbButton;
	private Box hbRun;
	private Box hbTopSettingRun;
	private Box hbLowSettingRun;
	private Box hbContainerRunBtn;
	private Box vbRightSettingRun;
	private Box hbExit;
	private Box hbTopSettingExit;
	private Box hbLowSettingExit;
	private Box hbContainerExitBtn;
	private Box hbLowChoiceAlgo;
	private Box hbTopChoiceAc;
	private Box hbLowChoiceAc;
	private Box hbTopChoiceHeuristic;
	private Box hbLowChoiceHeuristic;

	private JSeparator separatorTop;
	private JSeparator separatorLow;
	private JSeparator separatorAlgo;
	private JSeparator separatorAc;
	private JSeparator separator;
	private JSeparator separatorUpload;
	
	private JButton btnRun;
	private JButton btnExit;
	private JButton btnSearch;

	private JLabel lblAlgo;
	private JLabel lblAc;
	private JPanel panRdbChoiceAc;
	private JLabel lblHeuristique;
	private JLabel lblFichier;
	private JLabel lblTpsLoadFile;
	private JLabel lblTpsAlgo;
	
	private JRadioButton rdbtnForwardCheking;
	private JRadioButton rdbtnTestGenerate;
	private JRadioButton rdbtnAucun;
	private JRadioButton rdbtnAC1;
	private final ButtonGroup buttonGroupAc = new ButtonGroup();
	private final ButtonGroup buttonGroupAlgo = new ButtonGroup();

	private JTextArea textAreaShowFile;
	private JTextArea textAreaShowResult;
	
	private JComboBox<String> cbHeuristic;
	
	
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
		hbResultSetting.setPreferredSize(new Dimension(0, 30));
		panelResult.add(hbResultSetting, BorderLayout.SOUTH);
		
		panMainSetting = new JPanel();
		hbResultSetting.add(panMainSetting);
		panMainSetting.setLayout(new BorderLayout(0, 0));
		
		panSeparatorTop = new JPanel();
		panSeparatorTop.setPreferredSize(new Dimension(0, 20));
		panMainSetting.add(panSeparatorTop, BorderLayout.NORTH);
		panSeparatorTop.setLayout(new BorderLayout(0, 0));
		
		separatorTop = new JSeparator();
		panSeparatorTop.add(separatorTop, BorderLayout.SOUTH);
		
		panelTimeExecution = new JPanel();
		panSeparatorTop.add(panelTimeExecution, BorderLayout.WEST);
		panelTimeExecution.setLayout(new BorderLayout(0, 0));
		
		lblTpsLoadFile = new JLabel("");
		lblTpsLoadFile.setForeground(new Color(0, 0, 128));
		lblTpsLoadFile.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblTpsLoadFile.setPreferredSize(new Dimension(310, 15));
		panelTimeExecution.add(lblTpsLoadFile, BorderLayout.WEST);
		
		lblTpsAlgo = new JLabel("");
		lblTpsAlgo.setForeground(new Color(0, 0, 128));
		lblTpsAlgo.setFont(new Font("Dialog", Font.PLAIN, 12));
		panelTimeExecution.add(lblTpsAlgo, BorderLayout.CENTER);
		
		panelSeparatorLow = new JPanel();
		panelSeparatorLow.setPreferredSize(new Dimension(0, 10));
		panMainSetting.add(panelSeparatorLow, BorderLayout.SOUTH);
		panelSeparatorLow.setLayout(new BorderLayout(0, 0));
		
		separatorLow = new JSeparator();
		panelSeparatorLow.add(separatorLow, BorderLayout.SOUTH);
		
		hbResultContainer = Box.createHorizontalBox();
		panelResult.add(hbResultContainer, BorderLayout.CENTER);
		
		mainPanResult = new JPanel();
		hbResultContainer.add(mainPanResult);
		mainPanResult.setLayout(new BorderLayout(0, 0));
		
		panResultSetting = new JPanel();
		mainPanResult.add(panResultSetting, BorderLayout.SOUTH);
		
		panShowResultContainer = new JPanel();
		mainPanResult.add(panShowResultContainer, BorderLayout.CENTER);
		panShowResultContainer.setLayout(new BorderLayout(0, 0));
		
		panSeparatorShow = new JPanel();
		panSeparatorShow.setPreferredSize(new Dimension(9, 10));
		panShowResultContainer.add(panSeparatorShow, BorderLayout.WEST);
		panSeparatorShow.setLayout(new BorderLayout(0, 0));
		
		scrollPaneShowResult = new JScrollPane();
		panShowResultContainer.add(scrollPaneShowResult, BorderLayout.CENTER);
		
		textAreaShowResult = new JTextArea();
		textAreaShowResult.setEditable(false);
		scrollPaneShowResult.setViewportView(textAreaShowResult);
		
		scrollPaneShowFile = new JScrollPane();
		scrollPaneShowFile.setPreferredSize(new Dimension(300, 3));
		mainPanResult.add(scrollPaneShowFile, BorderLayout.WEST);
		
		textAreaShowFile = new JTextArea();
		scrollPaneShowFile.setViewportView(textAreaShowFile);
		
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
				textAreaShowResult.setText("");
				Parser parserLunchAlgo = new Parser();
				SolverType svt=null;
				if(rdbtnForwardCheking.isSelected())
				{
					svt=SolverType.FORWARDCHECKING;
				}
				else
				{
					svt=SolverType.TESTANDGENERATE;
				}
				AbstractSolver solver=parserLunchAlgo.load(textAreaShowFile.getText(), svt);
				Heuristic heuristic=Heuristic.DEFAULT;
				
				if(!(cbHeuristic.getSelectedItem() == null || "".equals(cbHeuristic.getSelectedItem())))
				{
					heuristic=Heuristic.MINDOMAIN;
				}
				
				if(rdbtnAC1.isSelected())
				{
					try 
					{
						solver.doArcConsistency();
					} 
					catch (DomainBoundaryException e) 
					{
						textAreaShowResult.setText(e.getMessage());
					}
				}
				
				try 
				{
					long start = System.currentTimeMillis();
					solver.launch(heuristic);
					long end = System.currentTimeMillis();
					lblTpsAlgo.setText("\nTemps d'execution de l'algorithme : " + (end-start)+"ms");
				} 
				catch (VariableValueException e) 
				{
					textAreaShowResult.setText(textAreaShowResult.getText()+"\n\n"+e.getMessage());
				}
				textAreaShowResult.setText(solver.generateFinalOutput());
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
		
		hbContainerExitBtn = Box.createHorizontalBox();
		panelExit.add(hbContainerExitBtn, BorderLayout.CENTER);
		
		panContainExitBtn = new JPanel();
		panContainExitBtn.setMinimumSize(new Dimension(0, 0));
		hbContainerExitBtn.add(panContainExitBtn);
		panContainExitBtn.setLayout(new BorderLayout(0, 0));
		
		btnExit = new JButton("Quitter");
		btnExit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				frame.dispose();
			}
		});
		panContainExitBtn.add(btnExit, BorderLayout.CENTER);
		
		vbLeftSettingExit = Box.createVerticalBox();
		vbLeftSettingExit.setPreferredSize(new Dimension(10, 0));
		panelExit.add(vbLeftSettingExit, BorderLayout.WEST);
		
		vbRightSettingExit = Box.createVerticalBox();
		vbRightSettingExit.setPreferredSize(new Dimension(10, 0));
		panelExit.add(vbRightSettingExit, BorderLayout.EAST);
		
		vbChoice = Box.createVerticalBox();
		panelAction.add(vbChoice, BorderLayout.CENTER);
		
		panChoice = new JPanel();
		vbChoice.add(panChoice);
		panChoice.setLayout(new BorderLayout(0, 0));
		
		vbChoiceLeftSetting = Box.createVerticalBox();
		vbChoiceLeftSetting.setPreferredSize(new Dimension(10, 0));
		panChoice.add(vbChoiceLeftSetting, BorderLayout.WEST);
		
		hbChoiceTopSetting = Box.createHorizontalBox();
		hbChoiceTopSetting.setPreferredSize(new Dimension(0, 15));
		panChoice.add(hbChoiceTopSetting, BorderLayout.NORTH);
		
		hbChoiceLowSetting = Box.createHorizontalBox();
		hbChoiceLowSetting.setPreferredSize(new Dimension(0, 15));
		panChoice.add(hbChoiceLowSetting, BorderLayout.SOUTH);
		
		hbChoiceContainer = Box.createHorizontalBox();
		panChoice.add(hbChoiceContainer, BorderLayout.CENTER);
		
		panChoiceElement = new JPanel();
		hbChoiceContainer.add(panChoiceElement);
		panChoiceElement.setLayout(new BorderLayout(0, 0));
		
		panChoiceAlgo = new JPanel();
		panChoiceAlgo.setPreferredSize(new Dimension(170, 10));
		panChoiceElement.add(panChoiceAlgo, BorderLayout.WEST);
		panChoiceAlgo.setLayout(new BorderLayout(0, 0));
		
		hbTopChoiceAlgo = Box.createHorizontalBox();
		hbTopChoiceAlgo.setPreferredSize(new Dimension(0, 25));
		panChoiceAlgo.add(hbTopChoiceAlgo, BorderLayout.NORTH);
		
		panAlgo = new JPanel();
		hbTopChoiceAlgo.add(panAlgo);
		panAlgo.setLayout(new BorderLayout(0, 0));
		
		lblAlgo = new JLabel("Algorithme");
		panAlgo.add(lblAlgo, BorderLayout.CENTER);
		
		panAlgoLeftSetting = new JPanel();
		panAlgoLeftSetting.setPreferredSize(new Dimension(35, 10));
		panAlgo.add(panAlgoLeftSetting, BorderLayout.WEST);
		
		panAlgoSeparator = new JPanel();
		panAlgoSeparator.setPreferredSize(new Dimension(10, 2));
		panAlgo.add(panAlgoSeparator, BorderLayout.SOUTH);
		panAlgoSeparator.setLayout(new BorderLayout(0, 0));
		
		panAlgoSeparatorLeftSetting = new JPanel();
		panAlgoSeparatorLeftSetting.setPreferredSize(new Dimension(5, 0));
		panAlgoSeparator.add(panAlgoSeparatorLeftSetting, BorderLayout.WEST);
		
		panAlgoSeparatorRightSetting = new JPanel();
		panAlgoSeparatorRightSetting.setPreferredSize(new Dimension(28, 0));
		panAlgoSeparator.add(panAlgoSeparatorRightSetting, BorderLayout.EAST);
		
		separatorAlgo = new JSeparator();
		panAlgoSeparator.add(separatorAlgo, BorderLayout.CENTER);
		
		hbLowChoiceAlgo = Box.createHorizontalBox();
		panChoiceAlgo.add(hbLowChoiceAlgo, BorderLayout.CENTER);
		
		panRdbChoiceAlgo = new JPanel();
		hbLowChoiceAlgo.add(panRdbChoiceAlgo);
		panRdbChoiceAlgo.setLayout(new BorderLayout(0, 0));

		rdbtnForwardCheking = new JRadioButton("Forward Cheking");
		buttonGroupAlgo.add(rdbtnForwardCheking);
		panRdbChoiceAlgo.add(rdbtnForwardCheking, BorderLayout.SOUTH);
		
		rdbtnTestGenerate = new JRadioButton("Test & Generate");
		rdbtnTestGenerate.setSelected(true);
		buttonGroupAlgo.add(rdbtnTestGenerate);
		panRdbChoiceAlgo.add(rdbtnTestGenerate, BorderLayout.NORTH);
		
		panChoiceHeuristicAndUpload = new JPanel();
		panChoiceHeuristicAndUpload.setPreferredSize(new Dimension(330, 10));
		panChoiceElement.add(panChoiceHeuristicAndUpload, BorderLayout.EAST);
		panChoiceHeuristicAndUpload.setLayout(new BorderLayout(0, 0));
		
		panChoiceHeuristic = new JPanel();
		panChoiceHeuristic.setPreferredSize(new Dimension(150, 10));
		panChoiceHeuristicAndUpload.add(panChoiceHeuristic, BorderLayout.WEST);
		panChoiceHeuristic.setLayout(new BorderLayout(0, 0));
		
		hbTopChoiceHeuristic = Box.createHorizontalBox();
		hbTopChoiceHeuristic.setPreferredSize(new Dimension(0, 25));
		panChoiceHeuristic.add(hbTopChoiceHeuristic, BorderLayout.NORTH);
		
		panHeuristic = new JPanel();
		hbTopChoiceHeuristic.add(panHeuristic);
		panHeuristic.setLayout(new BorderLayout(0, 0));
		
		lblHeuristique = new JLabel("Heuristique");
		panHeuristic.add(lblHeuristique, BorderLayout.CENTER);
		
		panHeristicLabelLeftSetting = new JPanel();
		panHeristicLabelLeftSetting.setPreferredSize(new Dimension(35, 10));
		panHeuristic.add(panHeristicLabelLeftSetting, BorderLayout.WEST);
		
		panHeristicSeparatorContainer = new JPanel();
		panHeristicSeparatorContainer.setPreferredSize(new Dimension(10, 2));
		panHeuristic.add(panHeristicSeparatorContainer, BorderLayout.SOUTH);
		panHeristicSeparatorContainer.setLayout(new BorderLayout(0, 0));
		
		panHeristicSeparator = new JPanel();
		panHeristicSeparatorContainer.add(panHeristicSeparator, BorderLayout.CENTER);
		panHeristicSeparator.setLayout(new BorderLayout(0, 0));
		
		separator = new JSeparator();
		panHeristicSeparator.add(separator, BorderLayout.CENTER);
		
		panHeuristicSeparatorLeftSetting = new JPanel();
		panHeristicSeparatorContainer.add(panHeuristicSeparatorLeftSetting, BorderLayout.WEST);
		
		hbLowChoiceHeuristic = Box.createHorizontalBox();
		panChoiceHeuristic.add(hbLowChoiceHeuristic, BorderLayout.CENTER);
		
		panContainerHeuristicList = new JPanel();
		hbLowChoiceHeuristic.add(panContainerHeuristicList);
		panContainerHeuristicList.setLayout(new BorderLayout(0, 0));
		
		panHeuristicList = new JPanel();
		panContainerHeuristicList.add(panHeuristicList, BorderLayout.CENTER);
		panHeuristicList.setLayout(new BorderLayout(0, 0));
		
		cbHeuristic = new JComboBox<String>();
		cbHeuristic.setMinimumSize(new Dimension(25, 24));
		cbHeuristic.setPreferredSize(new Dimension(25, 24));
		cbHeuristic.addItem("");
		cbHeuristic.addItem("Domaine minimum");
		panHeuristicList.add(cbHeuristic, BorderLayout.CENTER);
		
		panHeuristicListTopSetting = new JPanel();
		panHeuristicListTopSetting.setPreferredSize(new Dimension(10, 12));
		panContainerHeuristicList.add(panHeuristicListTopSetting, BorderLayout.NORTH);
		
		panHeuristicListLowSetting = new JPanel();
		panHeuristicListLowSetting.setPreferredSize(new Dimension(10, 12));
		panContainerHeuristicList.add(panHeuristicListLowSetting, BorderLayout.SOUTH);
		
		panHeuristicListLeftSetting = new JPanel();
		panContainerHeuristicList.add(panHeuristicListLeftSetting, BorderLayout.WEST);
		
		panUpload = new JPanel();
		panChoiceHeuristicAndUpload.add(panUpload, BorderLayout.CENTER);
		panUpload.setLayout(new BorderLayout(0, 0));
		
		panUploadFile = new JPanel();
		panUploadFile.setPreferredSize(new Dimension(10, 25));
		panUpload.add(panUploadFile, BorderLayout.NORTH);
		panUploadFile.setLayout(new BorderLayout(0, 0));
		
		panLabelFileLeftSetting = new JPanel();
		panLabelFileLeftSetting.setPreferredSize(new Dimension(65, 10));
		panUploadFile.add(panLabelFileLeftSetting, BorderLayout.WEST);
		
		panUploadSeparatorContainer = new JPanel();
		panUploadSeparatorContainer.setPreferredSize(new Dimension(10, 2));
		panUploadFile.add(panUploadSeparatorContainer, BorderLayout.SOUTH);
		panUploadSeparatorContainer.setLayout(new BorderLayout(0, 0));
		
		panUploadSeparatorLeftSetting = new JPanel();
		panUploadSeparatorLeftSetting.setPreferredSize(new Dimension(30, 10));
		panUploadSeparatorContainer.add(panUploadSeparatorLeftSetting, BorderLayout.WEST);
		
		panUploadSeparatorRightSetting = new JPanel();
		panUploadSeparatorRightSetting.setPreferredSize(new Dimension(30, 10));
		panUploadSeparatorContainer.add(panUploadSeparatorRightSetting, BorderLayout.EAST);
		
		panUploadSeparator = new JPanel();
		panUploadSeparatorContainer.add(panUploadSeparator, BorderLayout.CENTER);
		panUploadSeparator.setLayout(new BorderLayout(0, 0));
		
		separatorUpload = new JSeparator();
		panUploadSeparator.add(separatorUpload, BorderLayout.CENTER);
		
		panContainerLabelFile = new JPanel();
		panUploadFile.add(panContainerLabelFile, BorderLayout.CENTER);
		panContainerLabelFile.setLayout(new BorderLayout(0, 0));
		
		lblFichier = new JLabel("Fichier");
		panContainerLabelFile.add(lblFichier, BorderLayout.CENTER);
		
		panContainerUpload = new JPanel();
		panUpload.add(panContainerUpload, BorderLayout.CENTER);
		panContainerUpload.setLayout(new BorderLayout(0, 0));
		
		panBtnUploadContainer = new JPanel();
		panBtnUploadContainer.setPreferredSize(new Dimension(10, 25));
		panContainerUpload.add(panBtnUploadContainer, BorderLayout.CENTER);
		panBtnUploadContainer.setLayout(new BorderLayout(0, 0));
		
		panBtnUploadLeftSetting = new JPanel();
		panBtnUploadLeftSetting.setPreferredSize(new Dimension(30, 10));
		panBtnUploadContainer.add(panBtnUploadLeftSetting, BorderLayout.WEST);
		
		panBtnUploadTopSetting = new JPanel();
		panBtnUploadContainer.add(panBtnUploadTopSetting, BorderLayout.NORTH);
		
		panBtnUploadRightSetting = new JPanel();
		panBtnUploadRightSetting.setPreferredSize(new Dimension(30, 10));
		panBtnUploadContainer.add(panBtnUploadRightSetting, BorderLayout.EAST);
		
		panBtnUpload = new JPanel();
		panBtnUploadContainer.add(panBtnUpload, BorderLayout.CENTER);
		panBtnUpload.setLayout(new BorderLayout(0, 0));
		
		btnSearch = new JButton("Parcourir");
		btnSearch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				JFileChooser chooser;
				chooser = new JFileChooser(); 
				chooser.setCurrentDirectory(new java.io.File("."));
				chooser.setDialogTitle("Parcourir");
				chooser.setAcceptAllFileFilterUsed(false); 
				if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) 
				{ 
					long start = System.currentTimeMillis();
					textAreaShowFile.setText(Parser.getFileContent(chooser.getSelectedFile().toString()));
					long end = System.currentTimeMillis();
					lblTpsLoadFile.setText("\nTemps de chargement du fichier : " + (end-start)+"ms");
				}
			}
		});
		panBtnUpload.add(btnSearch, BorderLayout.CENTER);
		
		panBtnUploadLowSetting = new JPanel();
		panBtnUploadContainer.add(panBtnUploadLowSetting, BorderLayout.SOUTH);
		
		panChoiceAc = new JPanel();
		panChoiceAc.setPreferredSize(new Dimension(100, 10));
		panChoiceElement.add(panChoiceAc, BorderLayout.CENTER);
		panChoiceAc.setLayout(new BorderLayout(0, 0));
		
		hbTopChoiceAc = Box.createHorizontalBox();
		hbTopChoiceAc.setPreferredSize(new Dimension(0, 25));
		panChoiceAc.add(hbTopChoiceAc, BorderLayout.NORTH);
		
		panAc = new JPanel();
		hbTopChoiceAc.add(panAc);
		panAc.setLayout(new BorderLayout(0, 0));
		
		lblAc = new JLabel("Arc-consientcy");
		panAc.add(lblAc, BorderLayout.CENTER);
		
		panAcLeftSetting = new JPanel();
		panAcLeftSetting.setPreferredSize(new Dimension(25, 10));
		panAc.add(panAcLeftSetting, BorderLayout.WEST);
		
		panAcSeparator = new JPanel();
		panAcSeparator.setPreferredSize(new Dimension(10, 2));
		panAc.add(panAcSeparator, BorderLayout.SOUTH);
		panAcSeparator.setLayout(new BorderLayout(0, 0));
		
		panAcSeparatorLeftSetting = new JPanel();
		panAcSeparatorLeftSetting.setPreferredSize(new Dimension(15, 0));
		panAcSeparator.add(panAcSeparatorLeftSetting, BorderLayout.WEST);
		
		panAlcSeparatorRightSetting = new JPanel();
		panAlcSeparatorRightSetting.setPreferredSize(new Dimension(20, 0));
		panAcSeparator.add(panAlcSeparatorRightSetting, BorderLayout.EAST);
		
		separatorAc = new JSeparator();
		separatorAc.setMaximumSize(new Dimension(20, 20));
		panAcSeparator.add(separatorAc, BorderLayout.CENTER);
		
		hbLowChoiceAc = Box.createHorizontalBox();
		panChoiceAc.add(hbLowChoiceAc, BorderLayout.CENTER);
		
		panRdbChoiceAc = new JPanel();
		hbLowChoiceAc.add(panRdbChoiceAc);
		panRdbChoiceAc.setLayout(new BorderLayout(0, 0));
		
		panAcRdbLeftSetting = new JPanel();
		panAcRdbLeftSetting.setPreferredSize(new Dimension(11, 0));
		panRdbChoiceAc.add(panAcRdbLeftSetting, BorderLayout.WEST);

		panAcRdbContainer = new JPanel();
		panRdbChoiceAc.add(panAcRdbContainer, BorderLayout.CENTER);
		panAcRdbContainer.setLayout(new BorderLayout(0, 0));

		rdbtnAucun = new JRadioButton("Aucun");
		rdbtnAucun.setSelected(true);
		buttonGroupAc.add(rdbtnAucun);
		panAcRdbContainer.add(rdbtnAucun, BorderLayout.NORTH);

		rdbtnAC1 = new JRadioButton("AC 1");
		buttonGroupAc.add(rdbtnAC1);
		panAcRdbContainer.add(rdbtnAC1, BorderLayout.SOUTH);

		mainPanTopSetting = new JPanel();
		mainPanel.add(mainPanTopSetting, BorderLayout.NORTH);

		mainPanRightSetting = new JPanel();
		mainPanRightSetting.setPreferredSize(new Dimension(10, 0));
		mainPanel.add(mainPanRightSetting, BorderLayout.WEST);
		mainPanRightSetting.setLayout(null);

		mainPanLeftSetting = new JPanel();
		mainPanel.add(mainPanLeftSetting, BorderLayout.EAST);
	}
}
