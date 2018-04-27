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

public class AccountInformationPanel extends JPanel
{
	private ArrayList<BankAccount> bank;
	
	public AccountInformationPanel(ArrayList<BankAccount> original)
	{
		bank = original;
		
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START; 
		
		JLabel name = new JLabel("Name: ");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(name, gbc);
		
		JTextField txtName = new JTextField();
		gbc.gridx = 1;
		gbc.gridy = 0;
		txtName.setPreferredSize(new Dimension(150,30));
		add(txtName, gbc);
		
		JButton getBankAccounts = new JButton("Get Bank Accounts");
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.gridwidth = 2;
		getBankAccounts.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if(txtName.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Please enter your name.");
				else if(findBankAccounts(txtName.getText(), bank).equals(""))
					JOptionPane.showMessageDialog(null, "Sorry. There are no bank accounts with that name. Please try again.");
				else
					JOptionPane.showMessageDialog(null,  findBankAccounts(txtName.getText(),bank));
				txtName.setText("");
			}
		});
		add(getBankAccounts, gbc);
	}
	
	public String findBankAccounts(String name, ArrayList<BankAccount> bank)
	{
		String bankAccounts = "";
		for(int i = 0; i < bank.size(); i++)
		{
			if(bank.get(i).getName().equals(name))
				bankAccounts += bank.get(i).toString() + "\n";
		}
		return bankAccounts;
	}
}
