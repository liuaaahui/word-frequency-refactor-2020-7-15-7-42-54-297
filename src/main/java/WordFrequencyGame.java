import java.util.*;

public class WordFrequencyGame {

    private static final String SPACE_MATCH = "\\s+";
    private static final String NEXT_LINE = "\n";
    private static final String SPACE_SYMBOL = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {
        
            try {

                String[] words = sentence.split(SPACE_MATCH);

                List<Word> wordInfo = new ArrayList<>();
                for (String word : words) {
                    Word input = new Word(word, 1);
                    wordInfo.add(input);
                }

                Map<String, List<Word>> wordMap = getListMap(wordInfo);

                List<Word> list = new ArrayList<>();
                for (Map.Entry<String, List<Word>> entry : wordMap.entrySet()) {
                    Word input = new Word(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfo = list;

                wordInfo.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                return gerenateResult(wordInfo);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
    }

    private String gerenateResult(List<Word> wordInfo) {
        StringJoiner joiner = new StringJoiner(NEXT_LINE);
        for (Word w : wordInfo) {
            String s = w.getValue() + SPACE_SYMBOL + w.getWordCount();
            joiner.add(s);
        }
        return joiner.toString();
    }

    private Map<String, List<Word>> getListMap(List<Word> wordInfo) {
        Map<String, List<Word>> wordMap = new HashMap<>();
        for (Word input : wordInfo) {
            if (!wordMap.containsKey(input.getValue())) {
                ArrayList words = new ArrayList<>();
                words.add(input);
                wordMap.put(input.getValue(), words);
            } else {
                wordMap.get(input.getValue()).add(input);
            }
        }
        return wordMap;
    }
}
