package view;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;

import javax.swing.JButton;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class GenerateView
{

	private JFrame frame;
	private JTextField textFieldVariable;
	private JTextField textFieldConstaint;
	private JTextField textFieldMinDomain;
	private JTextField textFieldMaxDomain;

	

	/**
	 * Create the application.
	 */
	public GenerateView() 
	{
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{
		frame = new JFrame();
		frame.setBounds(100, 100, 335, 250);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnValider = new JButton("Valider");
		btnValider.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(!textFieldVariable.getText().matches("[0-9]*") || !textFieldConstaint.getText().matches("[0-9]*")
						|| !textFieldMinDomain.getText().matches("[0-9]*") || !textFieldMaxDomain.getText().matches("[0-9]*"))
				{
					JOptionPane.showMessageDialog(frame,"Les champs de saisie ne doivent contenir que des chiffres");
				}
				else if("".equals(textFieldVariable.getText()) || "".equals(textFieldConstaint.getText()) 
						|| "".equals(textFieldMinDomain.getText()) || "".equals(textFieldMaxDomain.getText()))
				{
					JOptionPane.showMessageDialog(frame,"Tous les champs sont obligatoires");
				}
				else
				{
					MainView.nbVariable = Integer.parseInt(textFieldVariable.getText());
					MainView.nbConstraint = Integer.parseInt(textFieldConstaint.getText());
					MainView.minDomain = Integer.parseInt(textFieldMinDomain.getText());
					MainView.maxDomain = Integer.parseInt(textFieldMaxDomain.getText());
					frame.dispose();
				}
			}
		});
		btnValider.setPreferredSize(new Dimension(80, 25));
		btnValider.setBounds(117, 180, 100, 25);
		panel.add(btnValider);
		
		JPanel panVariable = new JPanel();
		panVariable.setBounds(15, 25, 300, 25);
		panel.add(panVariable);
		panVariable.setLayout(new BorderLayout(0, 0));
		
		textFieldVariable = new JTextField();
		textFieldVariable.setMinimumSize(new Dimension(1, 1));
		textFieldVariable.setPreferredSize(new Dimension(5, 15));
		panVariable.add(textFieldVariable, BorderLayout.EAST);
		textFieldVariable.setColumns(10);
		
		JLabel lblNbVariable = new JLabel("Nombre de variables : ");
		panVariable.add(lblNbVariable, BorderLayout.WEST);
		
		JPanel panConstraint = new JPanel();
		panConstraint.setBounds(15, 62, 300, 25);
		panel.add(panConstraint);
		panConstraint.setLayout(new BorderLayout(0, 0));
		
		textFieldConstaint = new JTextField();
		panConstraint.add(textFieldConstaint, BorderLayout.EAST);
		textFieldConstaint.setColumns(10);
		
		JLabel lblNbConstraint = new JLabel("Nombre de contraintes :");
		panConstraint.add(lblNbConstraint, BorderLayout.WEST);
		
		JPanel panMinDomain = new JPanel();
		panMinDomain.setBounds(15, 99, 300, 25);
		panel.add(panMinDomain);
		panMinDomain.setLayout(new BorderLayout(0, 0));
		
		textFieldMinDomain = new JTextField();
		panMinDomain.add(textFieldMinDomain, BorderLayout.EAST);
		textFieldMinDomain.setColumns(10);
		
		JLabel lblMinDomain = new JLabel("Domaine minimum : ");
		panMinDomain.add(lblMinDomain, BorderLayout.WEST);
		
		JPanel panMaxDomain = new JPanel();
		panMaxDomain.setBounds(15, 136, 300, 25);
		panel.add(panMaxDomain);
		panMaxDomain.setLayout(new BorderLayout(0, 0));
		
		textFieldMaxDomain = new JTextField();
		panMaxDomain.add(textFieldMaxDomain, BorderLayout.EAST);
		textFieldMaxDomain.setColumns(10);
		
		JLabel lblMaxDomain = new JLabel("Domain maximum :");
		panMaxDomain.add(lblMaxDomain, BorderLayout.WEST);
	}

	public JFrame getFrame() 
	{
		return frame;
	}

	public void setFrame(JFrame frame) 
	{
		this.frame = frame;
	}
}
