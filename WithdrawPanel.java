import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class WithdrawPanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public WithdrawPanel(ArrayList<BankAccount> original)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
	
		bank = original;
		
		JLabel lblActNum = new JLabel("Enter Account Number: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblActNum, gbc);
		
		JLabel lblAmtWith = new JLabel("Withdraw Amount: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblAmtWith, gbc);
		
		JTextField txtActNum = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtActNum.setPreferredSize(new Dimension(150,30));
		add(txtActNum, gbc);
		
		JTextField txtAmtWith = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtAmtWith.setPreferredSize(new Dimension(150,30));
		add(txtAmtWith, gbc);
		
		JButton withdraw = new JButton("Withdraw");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		withdraw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtActNum.getText().isEmpty()||txtAmtWith.getText().isEmpty()||!BankAccountFrame.isNumeric(txtActNum.getText())||!BankAccountFrame.isNumeric(txtAmtWith.getText()))
					JOptionPane.showMessageDialog(null, "Please enter your account number and amount to withdraw.");
				else if(findBankAccount(Double.parseDouble(txtActNum.getText()), bank) == null)
					JOptionPane.showMessageDialog(null, "Sorry. There is no bank account with that account number. Please try again.");
				else if(Double.parseDouble(txtAmtWith.getText())  < 0)
					JOptionPane.showMessageDialog(null,  "Sorry. You can not withdraw a negative value. Please try again.");
				else
				{
					try
					{
						findBankAccount(Double.parseDouble(txtActNum.getText()), bank).withdraw(Double.parseDouble(txtAmtWith.getText()));
						JOptionPane.showMessageDialog(null, "Transaction is complete.");
					}
					catch(IllegalArgumentException f)
					{
						JOptionPane.showMessageDialog(null, "Sorry. Transaction Failed. Please check to make sure your inputs are valid.");
					}
				}
				txtAmtWith.setText("");
				txtActNum.setText("");
			}
		});
		add(withdraw, gbc);
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
