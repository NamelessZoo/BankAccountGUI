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

public class GetBalancePanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public GetBalancePanel(ArrayList<BankAccount> original)
	{
		bank = original;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		
		JLabel actNum = new JLabel("Account Number: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(actNum, gbc);
		
		JTextField txtNum = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtNum.setPreferredSize(new Dimension(150,30));
		add(txtNum, gbc);
		
		JButton getBalance = new JButton("Get Balance");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		getBalance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtNum.getText().isEmpty()||!BankAccountFrame.isNumeric(txtNum.getText()))
					JOptionPane.showMessageDialog(null, "Please enter an account number.");
				else if(findBankAccount(Double.parseDouble(txtNum.getText()), bank) == null)
					JOptionPane.showMessageDialog(null, "Sorry. There are no bank accounts with that account number. Please try again.");
				else
					JOptionPane.showMessageDialog(null,  "Your balance is $" + findBankAccount(Double.parseDouble(txtNum.getText()), bank).getBalance());
				txtNum.setText("");
			}
		});
		add(getBalance, gbc);
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


