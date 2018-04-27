import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class BankAccountFrame extends JFrame
{
	private ArrayList<BankAccount> bank;
	
	public BankAccountFrame()
	{
		bank = new ArrayList<BankAccount>();
		
		CardLayout cl = new CardLayout();
		JPanel overall = new JPanel();
		overall.setLayout(cl);
		
		overall.add(new WelcomePanel(), "Home");
		overall.add(new CreateAccountPanel(bank), "Create Accounts");
		overall.add(new RemoveAccountPanel(bank), "Remove Accounts");
		overall.add(new AccountInformationPanel(bank), "Account Info");
		overall.add(new DepositPanel(bank), "Deposit");
		overall.add(new WithdrawPanel(bank), "Withdraw");
		overall.add(new TransferPanel(bank), "Transfer");
		overall.add(new GetBalancePanel(bank), "Get Balance");
		
		cl.show(overall, "Home");
		add(overall);
		
		setTitle("Bank");
		setBounds(300,100,800,600);
		
		JMenuItem home = new JMenuItem("Home");
		home.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Home");
			}
		});
		
		JMenu accounts = new JMenu("Accounts");
		JMenuItem createAccounts = new JMenuItem("Create Account");
		createAccounts.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Create Accounts");
			}
		});
		JMenuItem removeAccounts = new JMenuItem("Remove Account");
		removeAccounts.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Remove Accounts");
			}
		});
		JMenuItem infoAccounts = new JMenuItem("Account Info");
		infoAccounts.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Account Info");
			}
		});
		
		accounts.add(createAccounts);
		accounts.add(removeAccounts);
		accounts.add(infoAccounts);
		
		JMenu transaction = new JMenu("Transaction");
		JMenuItem deposit = new JMenuItem("Deposit");
		deposit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Deposit");
			}
		});
		JMenuItem withdraw = new JMenuItem("Withdraw");
		withdraw.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Withdraw");
			}
		});
		JMenuItem transfer = new JMenuItem("Transfer");
		transfer.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				cl.show(overall, "Transfer");
			}
		});
		JMenuItem getBalance = new JMenuItem("Get Balance");
		getBalance.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				cl.show(overall, "Get Balance");
			}
		});
		
		transaction.add(deposit);
		transaction.add(withdraw);
		transaction.add(transfer);
		transaction.add(getBalance);
		
		JMenuBar mBar = new JMenuBar();
		mBar.add(accounts);
		mBar.add(transaction);
		mBar.add(home);
		setJMenuBar(mBar);
		
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args)
	{
		new BankAccountFrame();
	}
	
	public static boolean isNumeric(String str) 
	{
		try
		{
			Double.parseDouble(str);
			return true;
		}
		catch(IllegalArgumentException e)
		{
			return false;
		}
	}
}
