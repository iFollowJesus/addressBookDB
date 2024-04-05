/*
 * Carlos Gonzales
 * COSC 3315.501
 * Fall 23
 * Final Project
 * Due date 12/07
 */

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

public class AddressBook  {



   public static void main( String args[] )
   {
      GUI window = new GUI();
      window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
      window.setVisible(true);      
     
}

		
}

class WindowDestroyer extends WindowAdapter
{
	public void windowClosing(WindowEvent e)
	{
	    System.exit(0);
	}

}

