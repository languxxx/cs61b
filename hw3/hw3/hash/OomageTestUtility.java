package hw3.hash;

import java.util.ArrayList;
import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {

        int N = oomages.size(), bucketNum;
        int[] bucket = new int[M];

        for (int i = 0; i < M; i++) {
            bucket[i] = 0;
        }

        for ( Oomage o : oomages) {
            bucketNum = (o.hashCode() & 0x7FFFFFFF) % M;
            bucket[bucketNum]++;
        }

        for (int i = 0; i < M; i++) {
            if (bucket[i] <= N/50 || bucket[i] >= N/2.5)
                return false;
        }

        return true;
    }
}
