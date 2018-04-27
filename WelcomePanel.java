import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WelcomePanel extends JPanel
{
	public WelcomePanel()
	{
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		JLabel lblWelcome = new JLabel("Welcome to Immigrants' Bank");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(lblWelcome, gbc);
		
		BufferedImage bankjpg = null;
		try
		{
			bankjpg = ImageIO.read(new File("immigration-clipart-banner_nation_grows.gif"));
		}
		catch (IOException e1)
		{
			e1.printStackTrace();
		}
		
		JLabel pic = new JLabel(new ImageIcon(bankjpg));
		gbc.gridx = 0;
		gbc.gridy = 1;
		add(pic, gbc);
		
		JLabel lblMessage = new JLabel("Where our only mission is the growth of your money and the nation");
		gbc.gridx = 0;
		gbc.gridy = 2;
		add(lblMessage, gbc);
	}
}
