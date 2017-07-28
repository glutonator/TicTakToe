package display;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import logic.Board;

public class Gui extends JFrame implements KeyListener, ActionListener {
	private Board table = null;

	private JLabel jlabel, jlabel2, jlabel3;

	public Board getTablica() {
		return table;
	}

	public void setTablica(Board tablica) {
		this.table = tablica;
	}

	public void display() {
		for (int i = 7; i < 10; i++) {
			System.out.print(table.getTable().get(i).getType());
		}
		System.out.println("");
		for (int i = 4; i < 7; i++) {
			System.out.print(table.getTable().get(i).getType());
		}
		System.out.println("");
		for (int i = 1; i < 4; i++) {
			System.out.print(table.getTable().get(i).getType());
		}
		for (int i = 0; i < 6; i++) {
			System.out.println("");
		}
	}

	// not used
	public Gui(Board tablica) {
		this.table = tablica;
		addKeyListener(this);

		setTitle("Tic Tak Toe");
		setSize(400, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		// BoxLayout boxlayout = new BoxLayout(panel, BoxLayout.Y_AXIS);

		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// panel.setBorder(new EmptyBorder(new Insets(50, 180, 50, 0)));

		HashMap<Integer, JButton> buttons = new HashMap<Integer, JButton>();
		for (int i = 0; i < 9; i++) {
			JButton temp = new JButton("" + (i + 1));
			buttons.put(i + 1, temp);
			temp.addActionListener(this);
			temp.setActionCommand("" + (i + 1));

		}
		ArrayList<JPanel> panels = new ArrayList<JPanel>();

		for (int i = 0; i < 3; i++) {
			panels.add(new JPanel());
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

		// for( Entry<Integer, JButton> el:buttons.entrySet()) {
		// panel.add(el.getValue());
		// }

		jlabel = new JLabel();
		jlabel2 = new JLabel();
		jlabel3 = new JLabel("F5 by zagraæ ponownie");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		add(panel);
		panel.add(jlabel, BorderLayout.CENTER);
		panel.add(jlabel2, BorderLayout.CENTER);
		panel.add(jlabel3, BorderLayout.CENTER);
		jlabel.setText(table.toStringTable());

		setVisible(true);
	}

	@Override
	public void keyPressed(KeyEvent ke) {
		// TODO Auto-generated method stub
		int key = ke.getKeyCode();
		switch (key) {
		case KeyEvent.VK_NUMPAD1:
			table.setField(1);
			break;

		case KeyEvent.VK_NUMPAD2:
			table.setField(2);
			break;

		case KeyEvent.VK_NUMPAD3:
			table.setField(3);
			break;

		case KeyEvent.VK_NUMPAD4:
			table.setField(4);
			break;

		case KeyEvent.VK_NUMPAD5:
			table.setField(5);
			break;

		case KeyEvent.VK_NUMPAD6:
			table.setField(6);
			break;

		case KeyEvent.VK_NUMPAD7:
			table.setField(7);
			break;

		case KeyEvent.VK_NUMPAD8:
			table.setField(8);
			break;

		case KeyEvent.VK_NUMPAD9:
			table.setField(9);
			break;
		case KeyEvent.VK_F5:
			// setTablica(new Board());
			table.clear_Board();
			break;
		case KeyEvent.VK_F4:
			// setTablica(new Board());
			table.computerMove();
			break;
		}
		jlabel.setText(table.toStringTable());

		if (table.isWin() == true) {
			jlabel2.setText("Wygrana");
		} else if (table.isDraw() == true) {
			jlabel2.setText("Remis");
		} else {
			jlabel2.setText("Graj");
		}
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
		System.out.println(ea.getActionCommand());
	}

}