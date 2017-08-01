package display;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import logic.Board;
import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;

import java.awt.Font;
import history.*;

public class Window implements KeyListener, ActionListener {
	// ActionListener
	private JFrame frame;
	private Board board = null;
	private JLabel jlabel, jlabel2, jlabel3, jlabel4, jlabel5;
	private HashMap<Integer, JButton> buttons =null;
	private File_operation file_oper=null;
	
	
	/**
	 * Launch the application.
	 */

	public void start(Board board) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Window window = new Window(board);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */

	public Window(Board board) {
		initialize(board);
	}
	public Window(Board board,File_operation file_oper ) {
		initialize(board);
		this.file_oper=file_oper;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Board board) {
		this.board = board;

		frame = new JFrame();
		frame.addKeyListener(this);
		frame.setFocusable(true);
		frame.setTitle("Tic Tak Toe");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);

		//main panel
		JPanel mainpanel = new JPanel();
		frame.getContentPane().add(mainpanel);
		mainpanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		mainpanel.setLayout(new GridLayout(0, 1, 0, 0));
		
		//panel with score
		JPanel scorepanel = new JPanel();
		scorepanel.setLayout(new FlowLayout());
		//FlowLayout flowLayout = (FlowLayout) scorepanel.getLayout();
		mainpanel.add(scorepanel);
		
		//panel with buttons
		JPanel panel = new JPanel();
		mainpanel.add(panel);
		
		//frame.setContentPane(panel);
		panel.setLayout(new GridLayout(0, 1, 0, 0));

		//panel with information
		JPanel panel2 = new JPanel();
		mainpanel.add(panel2);
		

		//buttons
		buttons = new HashMap<Integer, JButton>();
		for (int i = 0; i < 9; i++) {
			//JButton temp = new JButton("" + (i + 1));
			JButton temp = new JButton("_");
			temp.setFocusable(false);
			buttons.put(i + 1, temp);
			temp.addActionListener(this);
			temp.setActionCommand("" + (i + 1));

		}
		ArrayList<JPanel> panels = new ArrayList<JPanel>();

		for (int i = 0; i < 3; i++) {
			panels.add(new JPanel());
			panels.get(i).setLayout(new GridLayout(0, 3, 0, 0));
			panel.add(panels.get(i));
		}

		for (int i = 7; i < 10; i++) {
			panels.get(0).add(buttons.get(i));
		}

		for (int i = 4; i < 7; i++) {
			panels.get(1).add(buttons.get(i));
		}

		for (int i = 1; i < 4; i++) {
			panels.get(2).add(buttons.get(i));
		}

		//informations
		jlabel = new JLabel();
		jlabel2 = new JLabel("Graj");
		jlabel3 = new JLabel("F5 by zagraæ ponownie");
		jlabel4 = new JLabel();
		jlabel5 = new JLabel(board.getPlayers_wins1()+" : "+board.getPlayers_wins2());
		jlabel5.setFont(new Font("Tahoma", Font.PLAIN, 20));

		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.add(jlabel);
		panel2.add(jlabel2);
		panel2.add(jlabel3);
		panel2.add(jlabel4);
		scorepanel.add(jlabel5);

		
		try {
			jlabel.setText(board.toStringTable());
		} catch (Exception e) {
			e.printStackTrace();
		}

		frame.setVisible(true);
	}

	public Board getTablica() {
		return board;
	}

	public void setTablica(Board tablica) {
		this.board = tablica;
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub

		int key = ke.getKeyCode();
		switch (key) {
		case KeyEvent.VK_NUMPAD1:
			board.setField(1);
			setButton(1);
			break;

		case KeyEvent.VK_NUMPAD2:
			board.setField(2);
			setButton(2);
			break;

		case KeyEvent.VK_NUMPAD3:
			board.setField(3);
			setButton(3);
			break;

		case KeyEvent.VK_NUMPAD4:
			board.setField(4);
			setButton(4);
			break;

		case KeyEvent.VK_NUMPAD5:
			board.setField(5);
			setButton(5);
			break;

		case KeyEvent.VK_NUMPAD6:
			board.setField(6);
			setButton(6);
			break;

		case KeyEvent.VK_NUMPAD7:
			board.setField(7);
			setButton(7);
			break;

		case KeyEvent.VK_NUMPAD8:
			board.setField(8);
			setButton(8);
			break;

		case KeyEvent.VK_NUMPAD9:
			board.setField(9);
			setButton(9);
			
			break;
		case KeyEvent.VK_F5:
			//setTablica(new Board());
			board.clear_Board();
			clear_buttons();
			break;
		case KeyEvent.VK_F4:
			// setTablica(new Board());
			board.computerMove();
			break;
		}
		after_move();

	}

	@Override
	public void keyReleased(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent ke) {
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent ea) {
		// TODO Auto-generated method stub
		if(board.isWin()==false&&board.isDraw()==false) {
		board.setField(Integer.parseInt(ea.getActionCommand()));
		//System.out.println(ea.getActionCommand());
		after_move();		
		setButton(Integer.parseInt(ea.getActionCommand()));
		//jlabel4.setText(ea.getActionCommand());
		}

	}

	private void display_info() {
		jlabel.setText(board.toStringTable());
		if (board.isWin() == true) {
			jlabel2.setText("Wygrana");
		} else if (board.isDraw() == true) {
			jlabel2.setText("Remis");
		} else {
			jlabel2.setText("Graj");
		}
		jlabel5.setText(board.getPlayers_wins1()+" : "+board.getPlayers_wins2());
	}
	private void setButton(int i) {
		this.buttons.get(i).setText(board.getTable().get(i).getType()+"");
		this.buttons.get(i).repaint();
		
	}
	private void clear_buttons() {
		for(Entry<Integer, JButton> i : buttons.entrySet()) {
			i.getValue().setText("_");
			i.getValue().repaint();
		}
	}
	
	private void after_move() {
		display_info();
		save_history();
	}
	private void save_history() {
		this.file_oper.trySave();
	}
}
