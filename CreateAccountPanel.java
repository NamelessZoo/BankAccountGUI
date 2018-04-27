import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CreateAccountPanel extends JPanel
{	
	private ArrayList<BankAccount> bank;
	
	private double intRate = 0.0025;
	private final double MIN_BAL = 100;
	private final double MIN_BAL_FEE = 20;
	private final double OVER_DRAFT_FEE = 30;
	private final double TRANSACTION_FEE = 1;
	private final int FREE_TRANS = 10;
	
	public CreateAccountPanel(ArrayList<BankAccount> original)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		
		String[] arr = {"Checking","Savings"};	
		bank = original;
		
		JLabel lblName = new JLabel("Name:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblName, gbc);
		
		JLabel lblDep = new JLabel("Initial Deposit:");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblDep, gbc);
		
		JLabel actType = new JLabel("Account Type:");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(actType, gbc);
		
		JTextField txtName = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtName.setPreferredSize(new Dimension(150,30));
		add(txtName, gbc);
		
		JTextField txtDep = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtDep.setPreferredSize(new Dimension(150,30));
		add(txtDep, gbc);
		
		JList listType = new JList(arr);
		gbc.gridx = 1;
		gbc.gridy = 2;
		listType.setPreferredSize(new Dimension(75,40));
		add(listType, gbc);
		
		JButton submit = new JButton("Create Account");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		submit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtName.getText().isEmpty()||txtDep.getText().isEmpty()||listType.isSelectionEmpty()||!BankAccountFrame.isNumeric(txtDep.getText()))
					JOptionPane.showMessageDialog(null, "Please enter in your name, deposit amount, and account type selection.");
				else if(listType.getSelectedIndex() == 0)
				{
					bank.add(new CheckingAccount(txtName.getText(), Double.parseDouble(txtDep.getText()), OVER_DRAFT_FEE, TRANSACTION_FEE, FREE_TRANS));
					JOptionPane.showMessageDialog(null, "Checking Account successfully created.");
				}
				else if(listType.getSelectedIndex() == 1)
				{
					bank.add(new SavingsAccount(txtName.getText(), Double.parseDouble(txtDep.getText()), intRate, MIN_BAL, MIN_BAL_FEE));
					JOptionPane.showMessageDialog(null, "Savings Account successfully created.");
				}
				txtName.setText("");
				txtDep.setText("");
				listType.clearSelection();
			}
		});
		add(submit, gbc);
	}

	public BankAccount findBankAccount(double acctNum, ArrayList<BankAccount> bank)
	{
		for(int i = 0; i < bank.size(); i++)
		{
			if(bank.get(i).getAccountNumber() == acctNum)
				return bank.get(i);
		}
		return null;
	}
}
