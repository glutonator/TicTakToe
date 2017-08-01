import javax.swing.SwingUtilities;


import display.Window;
import logic.*;
import history.*;

public class TicTakToe {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Board plansza = new Board();
		//Window win=new Window(plansza);
		//Window.start(plansza);
		File_operation fff= new File_operation(plansza);

		new Window(plansza,fff);
		//fff.trySave();
		fff.read();
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//Gui gui = new Gui(plansza);
				//new Window(plansza);
			}
		});
		
		System.out.println(plansza.toStringTable());
		System.out.println("sdadasd");
		//test
		//nowy branch
	}

}
