package org.liyd.core.sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author liyd
 **/
public class SortTest {


  public static final String[] preData = {
          "N2010",
          "2010",
          "N1010",
          "1010",
          "3010",
          "4010",
          "1010",
          "N1011"
  };

  public static void main(String[] args) {
    System.out.println("排序前：");
    for (String data : preData) {
      System.out.print(data + " ");
    }

    System.out.println();
    System.out.println("开始排序...");

    List<String> sortedList = Arrays.stream(preData)
            .sorted(
                    Comparator.comparing(str -> str.substring(str.length() - 4))
            ).collect(Collectors.toList());

    sortedList.forEach(str -> System.out.print(str + " "));

  }


}
