import java.util.Random;
import java.util.ArrayList;

class Board{
	public static int board_size = 25;
	public int[] queen_location;

	public Board(){
	}

	//Sets the initial board with queens
	public void set_queens_on_board(){
		queen_location = new int[board_size];
		Random r = new Random();
		for (int i = 0; i<board_size; i++){
			queen_location[i] = r.nextInt(board_size);
		}
	}

        public int get_fitness(){
            return get_number_of_non_attacking_queens();
        }
        
        public Board change_state(){
            Random rd = new Random();
            int num_states_changed = rd.nextInt(25);
            //cap states changed at 50%
            double rate = .5;
            num_states_changed *= rate;
            int[] board = queen_location.clone();
            for (int i =0; i<num_states_changed; i++){
                int random_col = rd.nextInt(25);
                int random_row = rd.nextInt(25);
                board[random_col] = random_row;
            }
            Board new_board = new Board();
            new_board.queen_location = board;
            return new_board;
        }

        public int get_number_of_non_attacking_queens(){
            int count = 0;
            for (int i =0; i<board_size; i++){
                int row = queen_location[i];
                int num_attacks = get_num_queens_attack(i, row);
                if (num_attacks == 0){
                    count +=1;
                }
            }
            return count;
        }

	public int get_num_queens_attack(int x, int y){
		//Generate all positions that would consider queen attacking
		//x, y -> 0,0
		//output -> [[1, 2, 3...], [0,1] etc...
		ArrayList<ArrayList<Integer>> attack_positions = new ArrayList<ArrayList<Integer>>();
		
		//fill vector with vectors for each row
		
		for (int i =0; i<board_size; i++){
			attack_positions.add(new ArrayList<Integer>());
		}
		
		for (int i =0; i<board_size; i++){
			//Get position of queens attacking up and down
			if (i!=y){
				attack_positions.get(x).add(i);
			}

			//Get position of queens attacking up and down
			if (i!=x){
				attack_positions.get(i).add(y);
			}
                        
                        //diagonal queens
                        int up_diag = i + (y - x);
                        if (up_diag >= 0 && up_diag < board_size && up_diag != y){
                            attack_positions.get(i).add(up_diag);
                        }
                        int down_diag = -1 * i + (y + x);
                        if (down_diag >=0 && down_diag < board_size && down_diag != y){
                            attack_positions.get(i).add(down_diag);
                        }
		}
                
                //System.out.println("Valid Attack positions for");
                //String s = String.format("%d %d", x, y);
                //System.out.println(s);
                //for (int i =0; i<25; i++){
                //    ArrayList<Integer> col = attack_positions.get(i);
                //    for (int j=0; j<col.size(); j++){
                //        String t = String.format("%d %d", i, col.get(j));
                //        System.out.println(t);
                //    }
                //}
                int count = 0;
                for (int i = 0; i<board_size; i++){
                    int queen_position = queen_location[i]; 
                    ArrayList<Integer> attacking_queen = attack_positions.get(i);
                    for (int j = 0; j < attacking_queen.size(); j++){
                        if (attacking_queen.get(j) == queen_position){
                            count +=1;
                        }
                    }
                }
                return count;
	}

        public boolean is_valid_solution(){
            for (int i =0; i<25; i++){
                int row_w_queen = queen_location[i];
                int count = get_num_queens_attack(i, row_w_queen);
                if (count !=0){
                    return false;
                }
            }
            return true;
        }
}
