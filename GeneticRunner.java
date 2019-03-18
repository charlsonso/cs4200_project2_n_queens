
class GeneticRunner{
    public static void main(String[] args){
        long[] times = new long[3];
        
        for (int i = 0; i < 3; i++){
            System.out.print("Test ");
            System.out.println(i+1);
            long start = System.currentTimeMillis();
            boolean found_solution = false;
            Genetic genetic_algo = new Genetic();
            //System.out.println("Setting Initial Population");
            genetic_algo.set_initial_pop();
            int solution_idx = 0;
            int count = 0;
            while(!found_solution){
                //System.out.println("Generating proginy");
                Board[] proginy = genetic_algo.generate_new_population();
                //System.out.println("Finished Creating proginy");
                for (int j =0; j<proginy.length; j++){
                    if (proginy[i].is_valid_solution()){
                        found_solution = true;
                        solution_idx = j;
                        break;
                    }
                }
                count +=1;
                //String it = String.format(">>> Iteration #%d", count);
                //String fit = String.format("Current Highest Fitness Level %d", genetic_algo.get_highest_fitness());
                //System.out.println(it);
                //System.out.println(fit);
                genetic_algo.set_data(proginy); 
            }

            System.out.println("Solution (col, row)\n---------");
            long end = System.currentTimeMillis();
            times[i] = (start - end);
            for (int j = 0; j<25; j++){
                String st = String.format("%d, %d", j, genetic_algo.population[solution_idx].queen_location[j]);
                System.out.println(st);
            }
        }
        
        double total_time = 0;
        for (int i = 0; i<3; i++){
            total_time += times[i];
        }
        System.out.print("Average Time to find Solution(s): ");
        System.out.println(total_time/25000);
    }
}
