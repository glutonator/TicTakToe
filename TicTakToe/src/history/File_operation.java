package history;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import logic.Board;

public class File_operation {

	private Board board = null;
	private String file_name = "history.txt";
	private ArrayList<String> list_history;

	public File_operation() {
		board = null;
		file_name = "history.txt";
		list_history = new ArrayList<String>();
	}

	public File_operation(Board board) {
		this();
		this.board = board;
		// file_name = "history.txt";
		// list_history = new ArrayList<String>();
	}

	
	public ArrayList<String> getList_history() {
		return list_history;
	}

	public void save(String text_to_save) {
		try (FileWriter filewr = new FileWriter(this.file_name, true)) {
			filewr.write(text_to_save);
			filewr.write(System.lineSeparator());
		} catch (IOException e) {
			System.out.println("B³¹d wejscia-wyjœcia");
		}
	}

	public boolean trySave() {
		if (board.isWin() == true || board.isDraw() == true) {
			this.save(board.getMoves());
			return true;
		} else {
			// nothing
			return false;
		}
	}

	public void read() {
		String out = "";
		try (FileReader fileread = new FileReader(this.file_name)) {

			int c;
			do {
				c = fileread.read();
				switch (c) {
				case '\r':
					// nothing
					break;
				case '\n':
					// add element
					this.list_history.add(out);
					out = "";
					break;
				default:
					if (c != -1) {
						out += (char) c;
					} else {

					}
					break;
				}

			} while (c != -1);
		} catch (IOException e) {
			System.out.println("B³¹d wejscia-wyjœcia");
		}

		for (String t : this.list_history) {
			System.out.println(t);
		}
	}
}
