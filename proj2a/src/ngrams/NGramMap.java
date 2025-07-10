package ngrams;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

import static ngrams.TimeSeries.MAX_YEAR;
import static ngrams.TimeSeries.MIN_YEAR;

/**
 * An object that provides utility methods for making queries on the
 * Google NGrams dataset (or a subset thereof).
 *
 * An NGramMap stores pertinent data from a "words file" and a "counts
 * file". It is not a map in the strict sense, but it does provide additional
 * functionality.
 *
 * @author Josh Hug
 */
public class NGramMap {

    private Map<String, TimeSeries> wordHistories;
    private TimeSeries totalCounts;

    /**
     * Constructs an NGramMap from WORDSFILENAME and COUNTSFILENAME.
     */
    public NGramMap(String wordsFilename, String countsFilename) {
        wordHistories = new HashMap<>();
        totalCounts = new TimeSeries();

        // 读取单词历史文件
        try (BufferedReader reader = new BufferedReader(new FileReader(wordsFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 跳过空行
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split("\t");
                // 确保行格式正确：至少包含单词和一个年份-计数对
                if (parts.length < 2) {
                    System.err.println("警告: 格式不正确的行: " + line);
                    continue;
                }

                String word = parts[0];
                TimeSeries ts = new TimeSeries();

                // 从第二个部分开始解析每个年份-计数对
                for (int i = 1; i < parts.length; i++) {
                    String[] entry = parts[i].split(",");
                    // 确保年份-计数对格式正确
                    if (entry.length < 2) {
                        System.err.println("警告: 格式不正确的年份-计数对: " + parts[i]);
                        continue;
                    }

                    try {
                        int year = Integer.parseInt(entry[0]);
                        long count = Long.parseLong(entry[1]);
                        ts.put(year, (double) count);
                    } catch (NumberFormatException e) {
                        System.err.println("警告: 无法解析年份或计数: " + parts[i]);
                    }
                }

                wordHistories.put(word, ts);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 读取总计数文件
        try (BufferedReader reader = new BufferedReader(new FileReader(countsFilename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // 跳过空行
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(",");
                // 确保行格式正确
                if (parts.length < 2) {
                    System.err.println("警告: 格式不正确的总计数行: " + line);
                    continue;
                }

                try {
                    int year = Integer.parseInt(parts[0]);
                    long count = Long.parseLong(parts[1]);
                    totalCounts.put(year, (double) count);
                } catch (NumberFormatException e) {
                    System.err.println("警告: 无法解析年份或总计数: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Provides the history of WORD between STARTYEAR and ENDYEAR, inclusive of both ends. The
     * returned TimeSeries should be a copy, not a link to this NGramMap's TimeSeries. In other
     * words, changes made to the object returned by this function should not also affect the
     * NGramMap. This is also known as a "defensive copy". If the word is not in the data files,
     * returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word, int startYear, int endYear) {
        TimeSeries result = new TimeSeries();
        TimeSeries original = wordHistories.getOrDefault(word, new TimeSeries());

        for (Integer year : original.years()) {
            if (year >= startYear && year <= endYear) {
                result.put(year, original.get(year));
            }
        }
        return result;
    }

    /**
     * Provides the history of WORD. The returned TimeSeries should be a copy, not a link to this
     * NGramMap's TimeSeries. In other words, changes made to the object returned by this function
     * should not also affect the NGramMap. This is also known as a "defensive copy". If the word
     * is not in the data files, returns an empty TimeSeries.
     */
    public TimeSeries countHistory(String word) {
        return countHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Returns a defensive copy of the total number of words recorded per year in all volumes.
     */
    public TimeSeries totalCountHistory() {
        return new TimeSeries(totalCounts, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD between STARTYEAR
     * and ENDYEAR, inclusive of both ends. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word, int startYear, int endYear) {
        TimeSeries counts = countHistory(word, startYear, endYear);
        TimeSeries total = new TimeSeries(totalCounts, startYear, endYear);
        return counts.dividedBy(total);
    }

    /**
     * Provides a TimeSeries containing the relative frequency per year of WORD compared to all
     * words recorded in that year. If the word is not in the data files, returns an empty
     * TimeSeries.
     */
    public TimeSeries weightHistory(String word) {
        return weightHistory(word, MIN_YEAR, MAX_YEAR);
    }

    /**
     * Provides the summed relative frequency per year of all words in WORDS between STARTYEAR and
     * ENDYEAR, inclusive of both ends. If a word does not exist in this time frame, ignore it
     * rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words,
                                          int startYear, int endYear) {
        TimeSeries sum = new TimeSeries();
        for (String word : words) {
            TimeSeries wordWeight = weightHistory(word, startYear, endYear);
            sum = sum.plus(wordWeight);
        }
        return sum;
    }

    /**
     * Returns the summed relative frequency per year of all words in WORDS. If a word does not
     * exist in this time frame, ignore it rather than throwing an exception.
     */
    public TimeSeries summedWeightHistory(Collection<String> words) {
        return summedWeightHistory(words, MIN_YEAR, MAX_YEAR);
    }

}
