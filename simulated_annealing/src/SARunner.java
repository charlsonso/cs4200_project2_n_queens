class SARunner{
    public static void main(String[] args){
        long[] times = new long[3];

        for (int i=0; i<3; i++){
            System.out.print("Test ");
            System.out.println(i+1);
            long start = System.currentTimeMillis();
            SA anneal_algo = new SA();
            anneal_algo.anneal();
            Board sol = anneal_algo.bd;
            System.out.println("Solution (col, row)\n -------");
            for (int j =0; j <25; j++){
                String st = String.format("%d, %d", j, sol.queen_location[j]);
                System.out.println(st);
            }
            System.out.println("Fitness Score: ");
            System.out.print(sol.get_fitness());
            long end = System.currentTimeMillis();
            long time = (end - start)/1000;
            times[i] = time;
        }

        long total_time = 0;
        for (int i =0; i<3; i++){
            total_time += times[i];
        }
        System.out.print("Average Time (s): ");
        System.out.println(total_time/3);

    }
}
