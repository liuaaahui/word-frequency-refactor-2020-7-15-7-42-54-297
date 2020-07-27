import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_MATCH = "\\s+";
    private static final String NEXT_LINE = "\n";
    private static final String SPACE_SYMBOL = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_MATCH).length == 1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(SPACE_MATCH);

                List<Word> inputList = new ArrayList<>();
                for (String word : words) {
                    Word input = new Word(word, 1);
                    inputList.add(input);
                }

                Map<String, List<Word>> map = getListMap(inputList);

                List<Word> list = new ArrayList<>();
                for (Map.Entry<String, List<Word>> entry : map.entrySet()) {
                    Word input = new Word(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                inputList = list;

                inputList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner(NEXT_LINE);
                for (Word w : inputList) {
                    String s = w.getValue() + SPACE_SYMBOL + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<Word>> getListMap(List<Word> inputList) {
        Map<String, List<Word>> map = new HashMap<>();
        for (Word input : inputList) {
            if (!map.containsKey(input.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                map.put(input.getValue(), words);
            } else {
                map.get(input.getValue()).add(input);
            }
        }
        return map;
    }
}
