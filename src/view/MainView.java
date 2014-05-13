package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.Box;
import java.awt.Dimension;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Component;

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
		
		JTextArea textAreaResult = new JTextArea();
		panelResult.add(textAreaResult, BorderLayout.CENTER);
		
		Box hbAction = Box.createHorizontalBox();
		hbAction.setPreferredSize(new Dimension(0, 100));
		mainPanel.add(hbAction, BorderLayout.SOUTH);
		
		JPanel panelAction = new JPanel();
		hbAction.add(panelAction);
		panelAction.setLayout(new BorderLayout(0, 0));
		
		Box vbButton = Box.createVerticalBox();
		vbButton.setPreferredSize(new Dimension(100, 0));
		panelAction.add(vbButton, BorderLayout.EAST);
		
		JPanel panelButton = new JPanel();
		vbButton.add(panelButton);
		panelButton.setLayout(new BorderLayout(0, 0));
		
		Box hbRun = Box.createHorizontalBox();
		hbRun.setPreferredSize(new Dimension(0, 40));
		panelButton.add(hbRun, BorderLayout.NORTH);
		
		JPanel panelRun = new JPanel();
		hbRun.add(panelRun);
		panelRun.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Lancer");
		btnNewButton.setPreferredSize(new Dimension(10, 10));
		panelRun.add(btnNewButton, BorderLayout.CENTER);
		
		Box hbExit = Box.createHorizontalBox();
		hbExit.setPreferredSize(new Dimension(0, 40));
		panelButton.add(hbExit, BorderLayout.SOUTH);
		
		Box vbChoice = Box.createVerticalBox();
		panelAction.add(vbChoice, BorderLayout.CENTER);
	}

}
