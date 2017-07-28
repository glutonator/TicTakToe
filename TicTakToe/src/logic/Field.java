package logic;

public class Field {
	private char type='_';

	public Field() {
		this.type = '_';
	}

	public char getType() {
		return type;
	}

	public void setType(char type) {
		if(type=='X'||type=='O') {
			this.type = type;
		}
		else {
			//nothing
		}
	}
	
	
}
