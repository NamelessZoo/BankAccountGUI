import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class DepositPanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public DepositPanel(ArrayList<BankAccount> original)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		
		bank = original;
				
		JLabel lblActNum = new JLabel("Enter Account Number: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblActNum, gbc);
		
		JLabel lblAmtDep = new JLabel("Deposit Amount: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblAmtDep, gbc);
		
		JTextField txtActNum = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtActNum.setPreferredSize(new Dimension(150,30));
		add(txtActNum, gbc);
		
		JTextField txtAmtDep = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtAmtDep.setPreferredSize(new Dimension(150,30));
		add(txtAmtDep, gbc);
		
		JButton deposit = new JButton("Deposit");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		deposit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtActNum.getText().isEmpty()||txtAmtDep.getText().isEmpty()||!BankAccountFrame.isNumeric(txtActNum.getText())||!BankAccountFrame.isNumeric(txtAmtDep.getText()))
					JOptionPane.showMessageDialog(null, "Please enter your account number and amount to deposit.");
				else if(findBankAccount(Double.parseDouble(txtActNum.getText()), bank) == null)
					JOptionPane.showMessageDialog(null, "Sorry. There is no bank account with that account number. Please try again.");
				else if(Double.parseDouble(txtAmtDep.getText())  < 0)
					JOptionPane.showMessageDialog(null,  "Sorry. You can not deposit a negative value. Please try again.");
				else
				{
					try
					{
						findBankAccount(Double.parseDouble(txtActNum.getText()), bank).deposit(Double.parseDouble(txtAmtDep.getText()));
						JOptionPane.showMessageDialog(null, "Transaction is complete.");
					}
					catch(IllegalArgumentException f)
					{
						JOptionPane.showMessageDialog(null, "Sorry. Transaction Failed. Please check to make sure your inputs are valid.");
					}
				}
				txtAmtDep.setText("");
				txtActNum.setText("");
			}
		});
		add(deposit, gbc);
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
