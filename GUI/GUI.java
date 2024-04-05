import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Component;


public class GUI extends JFrame implements Printable, ActionListener{

	
	private static final long serialVersionUID = 1L;
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public JInternalFrame mFrame;
	public JTextField f1;
	public JTextField l1;
	public JTextField address;
	public JTextField address2;
	public JTextField c1;
	public JTextField state;
	public JTextField zipcode;
	public JTextField phonenum;
	public JTextField emailaddress;

	
	

	// you need to add more text fields here

	public String firstname;
	public String lastname;
	public String addr1;
	public String addr2;
	public String City;
	public String State;
	public String Zipcode;
	public String Phone;
	public String email;


public GUI()
{

  	super();
	setSize(WIDTH, HEIGHT);
	setLocation(80,80); // setting the location on the screen
	setTitle("AddressBook");
	Container contentPane = getContentPane();
	contentPane.setBackground(Color.GRAY);
	addWindowListener(new WindowDestroyer());

	JDesktopPane dtp = new JDesktopPane();
	setContentPane(dtp);

	JPanel buttonPanel = new JPanel(); // Java panel to hold buttons
	buttonPanel.setLayout(new FlowLayout());// Setting the layout of buttons

	JPanel textPanel = new JPanel();// Java panel to hold labels and text fields
	textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.PAGE_AXIS));


	JPanel FirstName = new JPanel();
	FirstName.setLayout(new FlowLayout());
	JLabel fname = new JLabel("First Name");
	f1 = new JTextField(10);

	JPanel LastName = new JPanel();
	LastName.setLayout(new FlowLayout());
	JLabel lname = new JLabel("Last Name");
	l1 = new JTextField(10);


	JPanel addrPanel = new JPanel();
	addrPanel.setLayout(new FlowLayout());
	JLabel addr = new JLabel("Address");
	address = new JTextField(10);

	JPanel addrPanel2 = new JPanel();
	addrPanel2.setLayout(new FlowLayout());
	JLabel addr2 = new JLabel("Address 2");
	address2 = new JTextField(10);
	//added more text panels and fields
	JPanel CityName = new JPanel();
	CityName.setLayout(new FlowLayout());
	JLabel cname = new JLabel("City");
	c1 = new JTextField(10);
	
	JPanel StateName = new JPanel();
	StateName.setLayout(new FlowLayout());
	JLabel sname = new JLabel("State");
	state = new JTextField(10);
	
	JPanel ZipCode = new JPanel();
	ZipCode.setLayout(new FlowLayout());
	JLabel znum = new JLabel("Zipcode");
	zipcode = new JTextField(10);

	JPanel PhoneNum = new JPanel();
	PhoneNum.setLayout(new FlowLayout());
	JLabel pnum = new JLabel("Phone");
	phonenum = new JTextField(10);
	
	JPanel EmailAdd = new JPanel();
	EmailAdd.setLayout(new FlowLayout());
	JLabel email = new JLabel("Email");
	emailaddress = new JTextField(10);
	
	FirstName.add(fname);
	FirstName.add(f1);
	LastName.add(lname);
	LastName.add(l1);
	addrPanel.add(addr);
	addrPanel.add(address);
	addrPanel2.add(addr2);
	addrPanel2.add(address2);
	CityName.add(cname);
	CityName.add(c1);
	StateName.add(sname);
	StateName.add(state);
	ZipCode.add(znum);
	ZipCode.add(zipcode);
	PhoneNum.add(pnum);
	PhoneNum.add(phonenum);
	EmailAdd.add(email);
	EmailAdd.add(emailaddress);
	   	
	

	//continue adding the text fields

	textPanel.add(FirstName);
	textPanel.add(LastName);
	textPanel.add(addrPanel);
	textPanel.add(addrPanel2);
	textPanel.add(CityName);
	textPanel.add(StateName);
	textPanel.add(ZipCode);
	textPanel.add(PhoneNum);
	textPanel.add(EmailAdd);
	
	
	mFrame = new JInternalFrame("Address Book Entry", true,true, true, true);
	mFrame.setLayout(new BorderLayout());

	JButton insertButton = new JButton("Insert");
	insertButton.addActionListener(this);

	JButton deleteButton = new JButton("Delete");
	deleteButton.addActionListener(this);

	JButton newButton = new JButton("New");
	newButton.addActionListener(this);
	//made a new button
	JButton Search = new JButton("Search");
	Search.addActionListener(this);
	
	JButton updateButton = new JButton("Update");
	updateButton.addActionListener(this);
	
	JButton printButton = new JButton("Print");
	printButton.addActionListener(this);

	buttonPanel.add(newButton);
	buttonPanel.add(insertButton);
	buttonPanel.add(deleteButton);
	buttonPanel.add(Search);
	buttonPanel.add(updateButton);
	buttonPanel.add(printButton);
	//changed the size of the window to fit all the text fields better
	mFrame.setSize(700, 500);
	mFrame.setLocation(50, 50);

	mFrame.add(textPanel, BorderLayout.WEST); // adds textPanel to the main frame of the window
	mFrame.add(buttonPanel, BorderLayout.SOUTH);// adds buttons to the main frame
	mFrame.setVisible(true);
	dtp.add(mFrame);


}
public void actionPerformed(ActionEvent e)
{
	if(e.getActionCommand().equals("Insert"))
	{

		   firstname = f1.getText(); 
	        lastname = l1.getText();
	        addr1 = address.getText();
	        addr2 = address2.getText();
			City= c1.getText();
			State = state.getText();
			Zipcode = zipcode.getText();
			Phone = phonenum.getText();
			email = emailaddress.getText();
	        
	        DBconnection in = new DBconnection();

	        in.insert(firstname, lastname, addr1, addr2, City, State, Zipcode, Phone, email);
	}
	
	else if(e.getActionCommand().equals("New"))
	{
		
		DBconnection newness = new DBconnection();
		newness.newfields(f1, l1, address,address2, c1, state, zipcode,emailaddress,phonenum);
	}
	else if(e.getActionCommand().equals("Delete"))
	{
		DBconnection deletedness = new DBconnection();
		String first = f1.getText(), last =l1.getText();
		deletedness.deleteF1F2(first, last);

	}
	else if(e.getActionCommand().equals("Search"))
	{
		firstname = f1.getText(); 
        lastname = l1.getText();
        DBconnection searching = new DBconnection();
        searching.search(firstname, lastname, address,address2, c1, state, zipcode, phonenum,emailaddress);
	}
	else if(e.getActionCommand().equals("Update")) {
		System.out.println("Update button activated");
		firstname = f1.getText(); 
        lastname = l1.getText();
        addr1 = address.getText();
        addr2 = address2.getText();
		City= c1.getText();
		State = state.getText();
		Zipcode = zipcode.getText();
		Phone = phonenum.getText();
		email = emailaddress.getText();
		
		DBconnection updatedness = new DBconnection();
		updatedness.updatePerson(firstname, lastname, addr1, addr2, email, Phone, City, State, Zipcode);
	}
	else if(e.getActionCommand().equalsIgnoreCase("Print")) {
        try {
            PrinterJob job = PrinterJob.getPrinterJob();
            job.setPrintable(this);
            boolean doPrint = job.printDialog();
            if (doPrint) {
                job.print();
            }
        } catch (PrinterException ex) {
            ex.printStackTrace();
            }
	}
		
	
	}
public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
    if (pageIndex > 0) {
        return NO_SUCH_PAGE;
    }

    // Create a buffered image of the frame
    BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_INT_RGB);
    Graphics2D g2dImage = image.createGraphics();
    this.paint(g2dImage);

    // Save the image to a file
    try {
        File outputFile = new File("c:/Frames/frame_image.png"); // Specify your file name and path here
        ImageIO.write(image, "png", outputFile);
    } catch (IOException ex) {
        ex.printStackTrace();
    }

    // Print the buffered image
    Graphics2D g2dPrinter = (Graphics2D) graphics;
    g2dPrinter.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
    g2dPrinter.drawImage(image, 0, 0, this);

    return PAGE_EXISTS;
}



}
