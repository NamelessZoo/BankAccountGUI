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

public class RemoveAccountPanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public RemoveAccountPanel(ArrayList<BankAccount> original)
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		
		bank = original;
		
		JLabel lblAcctNum = new JLabel("Account Number: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblAcctNum, gbc);
		
		JLabel lblName = new JLabel("Name: ");
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(lblName, gbc);
		
		JTextField txtAcctNum = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtAcctNum.setPreferredSize(new Dimension(150,30));
		add(txtAcctNum, gbc);
		
		JTextField txtName = new JTextField();;
		gbc.gridx = 1;
		gbc.gridy = 1;
		txtName.setPreferredSize(new Dimension(150,30));
		add(txtName, gbc);
		
		JButton remove = new JButton("Remove Account");
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 2;
		remove.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtAcctNum.getText().isEmpty()||txtName.getText().isEmpty()||!BankAccountFrame.isNumeric(txtAcctNum.getText()))
					JOptionPane.showMessageDialog(null, "Please enter an account number");
				else if(findBankAccount(Double.parseDouble(txtAcctNum.getText()), bank) == null)
					JOptionPane.showMessageDialog(null, "Sorry there is no account with that number. Please try again");
				else if(findBankAccount(Double.parseDouble(txtAcctNum.getText()), bank).getName().compareTo(txtName.getText()) != 0)
					JOptionPane.showMessageDialog(null, "You do not own that bank account so you cannot remove it.");
				else
				{
					bank.remove(findBankAccount(Double.parseDouble(txtAcctNum.getText()), bank));
					JOptionPane.showMessageDialog(null, "Bank account successfully removed.");
				}
				txtAcctNum.setText("");
				txtName.setText("");
			}
		});
		add(remove, gbc);
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
