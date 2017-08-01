package logic;

import java.util.*;
import java.util.Map.Entry;


public class Board {
	private Map<Integer, Field> table = null;
	private char last = 'O';
	private boolean win = false;
	private boolean draw = false;
	private int occupancy = 9;
	private int players_wins1=0, players_wins2=0 ;	
	//true-player1, false-player2
	private boolean whos_move =true;
	private String moves ="";
	

	public Board() {
		table = new HashMap<Integer, Field>();
		for (int i = 1; i < 10; i++) {
			table.put(i, new Field());
		}
		// this.last = 'O';
	}
	
	
	public void clear_Board() {
		table=null;
		table = new HashMap<Integer, Field>();
		for (int i = 1; i < 10; i++) {
			table.put(i, new Field());
		}
		last = 'O';
		win = false;
		draw = false;
		occupancy = 9;
		whos_move=true;
		moves="";
	}

	public Board(SortedMap<Integer, Field> table) {
		this.table = table;
	}

	public Map<Integer, Field> getTable() {
		return table;
	}

	// change X to O and O to X
	private void changeLast() {

		if (this.last == 'O') {
			this.last = 'X';
		}
		// this.last=='X'
		else {
			this.last = 'O';
		}
		this.whos_move=!this.whos_move;
	}

	// set new board
	public void setTable(Map<Integer, Field> table) {
		this.table = table;
	}

	// set X or O in pressed position
	public void setField(int i) {
		if (this.win == false && this.draw == false) {
			if (this.table.get(i).getType() == '_') {
				this.changeLast();
				this.table.get(i).setType(this.last);
				moves+=i;
				this.win = win();
				this.occupancy--;
				this.draw = draw();
				if(this.isWin()==true) {
					if(this.whos_move==false) {
						players_wins1++;
					}
					else {
						players_wins2++;
					}
					
				}
				System.out.println(moves);
			} else {
				// nothing
			}
		}

	}

	// test if game is finished
	public boolean isWin() {
		return win;
	}

	// test if game is finished draw
	public boolean isDraw() {
		return draw;
	}

	// test if sb win
	public boolean win() {
		// poziomo
		for (int i = 1; i < 10; i = i + 3) {
			if (table.get(i).getType() != '_' && table.get(i).getType() == table.get(i + 1).getType()
					&& table.get(i + 1).getType() == table.get(i + 2).getType()) {
				return true;
			}
		}
		// pionowo
		for (int i = 1; i < 4; i++) {
			if (table.get(i).getType() != '_' && table.get(i).getType() == table.get(i + 3).getType()
					&& table.get(i + 3).getType() == table.get(i + 6).getType()) {
				return true;
			}
		}
		// skos
		if (table.get(1).getType() != '_' && table.get(1).getType() == table.get(5).getType()
				&& table.get(5).getType() == table.get(9).getType()) {
			return true;
		}
		// skos
		if (table.get(7).getType() != '_' && table.get(7).getType() == table.get(5).getType()
				&& table.get(5).getType() == table.get(3).getType()) {
			return true;
		}
		return false;
	}

	// test if it is a draw
	public boolean draw() {
		if (occupancy == 0) {
			return true;
		} else {
			return false;
		}
	}

	// convert board to html string
	public String toStringTable() {
		String result = "<html>";

		for (int i = 7; i < 10; i++) {
			result += (table.get(i).getType());
		}
		result += "<br>";
		for (int i = 4; i < 7; i++) {
			result += (table.get(i).getType());
		}
		result += "<br>";
		for (int i = 1; i < 4; i++) {
			result += (table.get(i).getType());
		}
		result += "</html>";

		return result;
	}
	// convert board to  string
	public String toStringTable2() {
		String result = "";

		for (int i = 7; i < 10; i++) {
			result += (table.get(i).getType());
		}
		result += "<br>";
		for (int i = 4; i < 7; i++) {
			result += (table.get(i).getType());
		}
		result += "<br>";
		for (int i = 1; i < 4; i++) {
			result += (table.get(i).getType());
		}
		

		return result;
	}

	public void computerMove() {
		if (this.draw == false && this.win == false) {
			Random rand = new Random();
			int temp;
			if (this.occupancy == 1) {
				temp = 1;
			} else {
				temp = rand.nextInt(this.occupancy - 1) + 1;
			}

			java.util.Iterator<Entry<Integer, Field>> itr = table.entrySet().iterator();
			int i = 1;
			while (itr.hasNext()) {
				Entry<Integer, Field> y = itr.next();
				if (y.getValue().getType() == '_') {
					if (i == temp) {
						setField(y.getKey());
						break;
					} else {
						i++;
					}
				} else {
					// nothing
				}
			}
		}

		else {
			// nothing
		}
	}

	public int getPlayers_wins1() {
		return players_wins1;
	}

	public int getPlayers_wins2() {
		return players_wins2;
	}


	public String getMoves() {
		return moves;
	}
	
}
