package com.cscc43.mybnb.services;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cscc43.mybnb.models.ListingReview;

@Service
public class NounPhrases {
  public static Map<Integer, Map<String, Integer>> getPopularNounPhrases(List<ListingReview> review_list) {
    Map<Integer, Map<String, Integer>> listingPhrasesCountMap = new HashMap<>();

    for (ListingReview cur_review : review_list) {
      String[] phrases = cur_review.getComment().split("\\?|\\.|\\!");
      List<String> comments = Arrays.stream(phrases).map(sentence -> sentence.toLowerCase().trim())
          .collect(Collectors.toList());

      if (!listingPhrasesCountMap.containsKey(cur_review.getListingId())) {
        Map<String, Integer> phraseCount = new HashMap<>();
        listingPhrasesCountMap.put(cur_review.getListingId(), phraseCount);
      }

      for (String sentence : comments) {
        listingPhrasesCountMap.get(cur_review.getListingId()).merge(sentence, 1, Integer::sum);
      }
    }
    return listingPhrasesCountMap;
  }
}
