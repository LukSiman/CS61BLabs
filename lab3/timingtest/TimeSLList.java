package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
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
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> nList = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();
        int cycles = 1000;
        int getLastCycles = 10000;

        for(int i = 1; i <= 8; i++){
            nList.addLast(cycles);
            SLList<Integer> nSLList = nCreator(cycles);
            opCounts.addLast(getLastCycles);

            Stopwatch sw = new Stopwatch();
            for(int j = 1; j <= getLastCycles;j++){
                nSLList.getLast();
            }
            double time = sw.elapsedTime();
            times.addLast(time);
            cycles*=2;
        }
        printTimingTable(nList, times, opCounts);
    }

    private static SLList nCreator(int max){
        SLList<Integer> nList = new SLList<>();
        for(int i = 1; i <= max; i++){
            nList.addLast(i);
        }
        return nList;
    }

}
