import java.util.Random;
import java.util.Vector();

class Board{
	public static int board_size = 25;
	public int[] queen_location;

	public Board(){
		queen_location = set_queens();
	}

	//Sets the initial board with queens
	public int[] set_queens(){
		queen_location = new int[board_size];
		Random r = new Random();
		for (int i = 0; i<board_size; i++){
			queen_location[i] = r.nextInt(board_size + 1);
		}
	}

	public int get_num_queens_attack(int x, int y){
		//Generate all positions that would consider queen attacking
		//x, y -> 0,0
		//output -> [[1, 2, 3...], [0,1] etc...
		Vector attack_positions = new Vector();
		
		//fill vector with vectors for each row
		
		for (int i =0; i<board_size; i++){
			attack_positions.add(new Vector();
		}
		
		for (int i =0; i<board_size; i++){
			//Get position of queens attacking right and left
			if (i!=y){
				attack_positions[x].append(i)
			}

			//Get position of queens attacking up and down
			if (i!=x){
				attack_positions[i].append(x)
			}
		}
	}
}
