package logic;

import java.util.*;
import java.util.Map.Entry;

public class Board {
	private Map<Integer, Field> table = null;
	private char last = 'O';
	private boolean win = false;
	private boolean draw = false;
	private int occupancy = 9;
	private int players_wins1 = 0, players_wins2 = 0;
	// true-player1, false-player2
	private boolean whos_move = true;
	private String moves = "";
	private LinkedList<LinkedList<Field>> list;

	public Board() {
		table = new HashMap<Integer, Field>();
		for (int i = 1; i < 10; i++) {
			table.put(i, new Field());
		}
		create_lists();
		// this.last = 'O';
		System.out.println("sdsdassa");
	}

	private void create_lists() {
		list = new LinkedList<>();
		// pionowo
		for (int i = 1; i < 4; i++) {
			LinkedList<Field> temp = new LinkedList<Field>();
			for (int j = 0; j < 9; j = j + 3) {
				Field temp_field = table.get(i + j);
				temp.add(temp_field);
			}
			list.add(temp);
		}
		// poziomo
		for (int i = 0; i < 9; i = i + 3) {
			LinkedList<Field> temp = new LinkedList<Field>();
			for (int j = 1; j < 4; j++) {
				Field temp_field = table.get(i + j);
				temp.add(temp_field);
			}
			list.add(temp);
		}
		// skos 1
		{
			LinkedList<Field> temp = new LinkedList<Field>();
			for (int i = 1; i < 10; i = i + 4) {
				Field temp_field = table.get(i);
				temp.add(temp_field);
			}
			list.add(temp);
		}

		// skos 2
		{
			LinkedList<Field> temp = new LinkedList<Field>();
			for (int i = 3; i < 8; i = i + 2) {
				Field temp_field = table.get(i);
				temp.add(temp_field);
			}
			list.add(temp);
		}
	}

	public void clear_Board() {
		table = null;
		table = new HashMap<Integer, Field>();
		for (int i = 1; i < 10; i++) {
			table.put(i, new Field());
		}
		list = null;
		create_lists();
		last = 'O';
		win = false;
		draw = false;
		occupancy = 9;
		whos_move = true;
		moves = "";
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
		this.whos_move = !this.whos_move;
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
				moves += i;
				this.win = win();
				this.occupancy--;
				this.draw = draw();
				if (this.isWin() == true) {
					if (this.whos_move == false) {
						players_wins1++;
					} else {
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

	// convert board to string
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

	public int computerMove() {
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
						return y.getKey();
						// break;
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
		return -1;
	}

	public int smartMove() {
		if (this.draw == false && this.win == false) {
			int countX = 0;
			int countO = 0;
			int count_ = 0;
			int number_ = 0;
			int number_X = 0;
			int number_O = 0;

			// tu skoñczy³em
			// pionowo
			for (LinkedList<Field> it : list) {
				for (Field fiel : it) {
					if (fiel.getType() == 'X') {
						++countX;
						++number_;
					} else if (fiel.getType() == 'O') {
						++countO;
						++number_;
					} else {
						++count_;
					}
				}
				if ((countX == 2 || countO == 2) && count_ == 1) {
					int tyty;
					for( tyty=0, Iterator<Field> fiel=it.iterator(); fiel.hasNext()!=false&&; ) {
						Field elem=fiel.next();
					}
					setField(number_);
					return number_;
				}
				countX = 0;
				countO = 0;
				count_ = 0;
				number_ = 0;

			}

			// pionowo
			// for (int i = 1; i < 4; i++) {
			// for (int j = 0; j < 9; j = j + 3) {
			// if (table.get(i + j).getType() == 'X') {
			// ++countX;
			// } else if (table.get(i + j).getType() == 'O') {
			// ++countO;
			// } else {
			// ++count_;
			// number_ = i + j;
			// }
			// if ((countX == 2 || countO == 2) && count_ == 1) {
			// setField(number_);
			// return number_;
			// }
			// }
			// countX = 0;
			// countO = 0;
			// count_ = 0;
			// number_ = 0;
			// }
			//
			// // poziomo
			// for (int i = 0; i < 9; i = i + 3) {
			// for (int j = 1; j < 4; j++) {
			// if (table.get(i + j).getType() == 'X') {
			// ++countX;
			// } else if (table.get(i + j).getType() == 'O') {
			// ++countO;
			// } else {
			// ++count_;
			// number_ = i + j;
			// }
			// if ((countX == 2 || countO == 2) && count_ == 1) {
			// setField(number_);
			// return number_;
			// }
			// }
			// countX = 0;
			// countO = 0;
			// count_ = 0;
			// number_ = 0;
			// }
			// // skos 1
			// for (int i = 1; i < 10; i = i + 4) {
			// if (table.get(i).getType() == 'X') {
			// ++countX;
			// } else if (table.get(i).getType() == 'O') {
			// ++countO;
			// } else {
			// ++count_;
			// number_ = i;
			// }
			// if ((countX == 2 || countO == 2) && count_ == 1) {
			// setField(number_);
			// return number_;
			// }
			// }
			// countX = 0;
			// countO = 0;
			// count_ = 0;
			// number_ = 0;
			// // skos 2
			// for (int i = 3; i < 8; i = i + 2) {
			// if (table.get(i).getType() == 'X') {
			// ++countX;
			// } else if (table.get(i).getType() == 'O') {
			// ++countO;
			// } else {
			// ++count_;
			// number_ = i;
			// }
			// if ((countX == 2 || countO == 2) && count_ == 1) {
			// setField(number_);
			// return number_;
			// }
			// }
			// countX = 0;
			// countO = 0;
			// count_ = 0;
			// number_ = 0;
			//

		}
		return -1;
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
