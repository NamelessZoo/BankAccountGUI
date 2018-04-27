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

public class TransferPanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public TransferPanel(ArrayList<BankAccount> original)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		
		bank = original;
		
		JLabel lblActNum = new JLabel("Enter Account Number to Transfer From: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblActNum, gbc);
		
		JLabel lblActNum2 = new JLabel("Enter Account Number to Transfer To: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblActNum2, gbc);
		
		JLabel lblAmtTran = new JLabel("Transfer Amount: ");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblAmtTran, gbc);
		
		JTextField txtActNum = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtActNum.setPreferredSize(new Dimension(150,30));
		add(txtActNum, gbc);
		
		JTextField txtActNum2 = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtActNum2.setPreferredSize(new Dimension(150,30));
		add(txtActNum2, gbc);
		
		JTextField txtAmtTran = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 2;
		txtAmtTran.setPreferredSize(new Dimension(150,30));
		add(txtAmtTran, gbc);
		
		JButton transfer = new JButton("Transfer");
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.gridwidth = 2;
		transfer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtActNum.getText().isEmpty()||txtActNum2.getText().isEmpty()||txtAmtTran.getText().isEmpty()||!BankAccountFrame.isNumeric(txtActNum.getText())||!BankAccountFrame.isNumeric(txtActNum2.getText())||!BankAccountFrame.isNumeric(txtAmtTran.getText()))
					JOptionPane.showMessageDialog(null, "Please enter your account numbers and amount to transfer.");
				else if(findBankAccount(Double.parseDouble(txtActNum.getText()), bank) == null||findBankAccount(Double.parseDouble(txtActNum2.getText()), bank) == null)
					JOptionPane.showMessageDialog(null, "Sorry. There is no bank account with that account number. Please try again.");
				else if(Double.parseDouble(txtAmtTran.getText())  < 0)
					JOptionPane.showMessageDialog(null,  "Sorry. You can not transfer a negative value. Please try again.");
				else
				{
					try
					{
						findBankAccount(Double.parseDouble(txtActNum.getText()), bank).transfer(findBankAccount(Double.parseDouble(txtActNum2.getText()), bank), Double.parseDouble(txtAmtTran.getText()));
						JOptionPane.showMessageDialog(null, "Transaction is complete.");
					}
					catch(IllegalArgumentException f)
					{
						JOptionPane.showMessageDialog(null, "Sorry. Transaction Failed. Please check to make sure your inputs are valid.");
					}
				}
				txtActNum.setText("");
				txtActNum2.setText("");
				txtAmtTran.setText("");
			}
		});
		add(transfer, gbc);
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
