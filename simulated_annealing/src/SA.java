import java.lang.Math;
import java.util.Random;

class SA{
    public Board bd;
    SA(){
        bd = new Board();
        bd.set_queens_on_board();
    }

    public void anneal(){
        Random rd = new Random();
        boolean lower_cost = false;
        double temperature = 1;
        double threshold = 0.001;
        double decay_rate = 0.99999;

        while(temperature > threshold){
            Board new_board = bd.change_state();
            int new_fitness = new_board.get_fitness();
            int old_fitness = bd.get_fitness();
            if (new_fitness > old_fitness){
                bd = new_board;
                if (new_fitness == 25){
                    System.out.print("Solution Found");
                    break;
                }
            }
            else{
                int cost = (new_fitness - old_fitness);
                double probability = Math.pow(Math.E, (cost  / temperature));
                int prob_change = rd.nextInt(1000);
                int prob = (int) probability * 1000;
                if (prob_change < prob){
                    bd = new_board;
                }
            }
            temperature *= decay_rate;
        }
    }
}
