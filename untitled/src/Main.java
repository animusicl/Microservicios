import java.util.List;
import java.util.Optional;
import java.util.stream.LongStream;

public class Main {


    public static void main(String[] args) {
        //firstChallenge();
        secondChallenge(8589934591L);

    }

    public static int firstChallenge (List<Integer> numbers) {
        numbers.sort(Integer::compareTo);
        int max = numbers.get(numbers.size() - 1);
        int dif = max - numbers.get(0);
        return dif - numbers.size() + 1;

    }

    public static void secondChallenge(Long num) {
        long n = 0L;
        long acc = 0L;

        while (acc <= num) {
            n++;
            acc = (n * (n + 1)) / 2;
            System.out.println("n = " + n); //


            if (acc == num || (acc - 1) == num) {
                System.out.println("Numero Financiero");
                break;

            } else if (acc > num) {
                System.out.println("EL numero " + num + " no es financiero");
                System.out.println("acc = " + acc);
            }
        }
    }
}

/*
- num = 281474976710656L
- acc = 281474978939461L
- n = 23726566
-------------------------------------------
- num = 8589934591L
- acc = 8590000128L
- n = 131072
*/