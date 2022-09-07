import java.io.*;
import java.util.*;
import static java.util.stream.Collectors.joining;


class Result {

    public static List<String> processLogs(List<String> logs, int threshold){
                Set<Integer> allUniqueIDs = new HashSet<Integer>();
                List<Integer> senderAndReceiver = new ArrayList<Integer>();
                logs.stream().forEach(l ->{
                    String[] transactionSplitted = l.split(" ", 3);
                    senderAndReceiver.add(Integer.parseInt(transactionSplitted[0]));
                    senderAndReceiver.add(Integer.parseInt(transactionSplitted[1]));
                    allUniqueIDs.add(Integer.parseInt(transactionSplitted[0]));
                    allUniqueIDs.add(Integer.parseInt(transactionSplitted[1]));
                });

                List<String> IDsTransactionHigherThanTreshold = new ArrayList<String>();
                for(Integer id : allUniqueIDs) {
                    int transactionCounter = 0;
                    for (int i = 0; i < senderAndReceiver.size(); i = i + 2) {
                        if (id.intValue() == senderAndReceiver.get(i).intValue() || id.intValue() == senderAndReceiver.get(i + 1).intValue())
                            ++transactionCounter;
                    }
                    if(transactionCounter >= threshold){
                        IDsTransactionHigherThanTreshold.add(id.toString());
                    }
                }

        return IDsTransactionHigherThanTreshold;

    }
}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("LogExample"));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("OUTPUT"));

        List<String> logs = new ArrayList<String>();
        try {

            String currentLine;
            while ((currentLine = bufferedReader.readLine()) != null) {
                logs.add(currentLine);
            }
        }catch (IOException ex){
            throw new RuntimeException(ex);
        }

        System.out.println("Insert integer treshold: ");
        Scanner scanner = new Scanner(System.in);
        int threshold = scanner.nextInt();

        List<String> result = Result.processLogs(logs, threshold);

        bufferedWriter.write(
                result.stream()
                        .collect(joining("\n"))
                        + "\n"
        );

        bufferedReader.close();
        bufferedWriter.close();
    }
}

