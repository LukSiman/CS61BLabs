package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> nList = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int cycles = 1000;

        for(int i = 1; i <= 8; i++){
            nList.addLast(cycles);
            opCounts.addLast(cycles);
            Stopwatch sw = new Stopwatch();
            nCreator(cycles);
            double time = sw.elapsedTime();
            times.addLast(time);
            cycles*=2;
        }
        printTimingTable(nList, times, opCounts);
    }

    private static void nCreator(int max){
        AList<Integer> nList = new AList<>();
        for(int i = 1; i <= max; i++){
            nList.addLast(i);
        }
    }
}
