import java.util.Random;

class Genetic{
    public Board[] population;
    public int[] fitness;
    public int num_queens = 700;

    public Genetic(){
       
    }
     
    
    public Board[] generate_new_population(){
        Board[] parent_population = new Board[10];
        Board[] child_population = new Board[700];
        int count = 0;
        double ep = 0.01;
        int prob = get_highest_fitness();
        //System.out.println(prob);
        //Selection
        //System.out.println("Selection");
        while(count != 10){
            for (int i =0; i<700; i++){
                //System.out.println(fitness[i]);
                if (fitness[i] == prob){
                    parent_population[count] = population[i];
                    count += 1;
                    if (count == 10){
                        break;
                    }
                } 
            }
            if (prob < 0){
                throw new Error("Probability is negative");
            }
            prob -= 1;
        }

        //System.out.println("Crossover");
        Random rd = new Random();
        for (int i =0; i < 700; i+=2){
            int first_parent = rd.nextInt(10);
            int second_parent = rd.nextInt(10);
            while (first_parent == second_parent){
                second_parent = rd.nextInt(10);
            }
            //Crossover
            int co_idx = rd.nextInt(25);
            int[] child_1 = new int[25];
            int[] child_2 = new int[25];
            for (int j =0; j<25; j++){
                if (j < co_idx){
                    child_1[j] = parent_population[first_parent].queen_location[j];
                    child_2[j] = parent_population[second_parent].queen_location[j];
                }
                else{
                    child_2[j] = parent_population[first_parent].queen_location[j];
                    child_1[j] = parent_population[second_parent].queen_location[j];
                }
            }
            child_population[i] = new Board();
            child_population[i].queen_location = child_1;
            child_population[i+1] = new Board();
            child_population[i+1].queen_location = child_2;
        }
        if (check_valid(child_population)){
            return child_population;
        }
        //System.out.println("Mutation");
        int random_mutations = rd.nextInt(700);
        for (int i =0; i<random_mutations; i++){
            int random_board = rd.nextInt(700);
            //max 10% mutation rate
            int num_genes = rd.nextInt(25);
            num_genes *= .5;
            for (int j =0; j<num_genes; j++){
                int random_gene = rd.nextInt(25);
                int random_loc = rd.nextInt(25);
                child_population[random_board].queen_location[random_gene] = random_loc;
            }
        }
        return child_population;
        //return 700 prodigy
    }
    
    public boolean check_valid(Board[] pop){
        for (int i = 0; i<pop.length; i++){
            if (pop[i].is_valid_solution()){
                return true;
            }
        }
        return false;
    }

    public int get_highest_fitness(){
        int top_fitness = 0;
        for (int i = 0; i<num_queens; i++){
            if (population[i].get_fitness() > top_fitness){
                top_fitness = population[i].get_fitness();
            }
        }
        return top_fitness;
    }
    
    public void set_initial_pop(){
        population = new Board[num_queens];
        fitness = new int[num_queens];
        for (int i =0; i<num_queens; i++){
            boolean is_valid = false;
            while(!is_valid){
                population[i] = new Board();
                population[i].set_queens_on_board();
                fitness[i] = population[i].get_fitness();
                double eps = 0.01;
                if ((population[i].get_fitness() < 1.0 - eps)){
                    is_valid = true;
                }
            }
        }
 
    }
    public void set_data(Board[] pop){
        population = pop;
        fitness = new int[num_queens];
        for (int i =0; i<num_queens; i++){
            fitness[i] = population[i].get_fitness();
        }
    }
}
