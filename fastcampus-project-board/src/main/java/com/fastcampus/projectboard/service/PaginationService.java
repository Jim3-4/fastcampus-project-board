package com.fastcampus.projectboard.service;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PaginationService {

    //상태값
    private static final int BAR_LENGTH=5;
    //현재 페이지넘버를 알아야하고, 끝 숫자를 계산하기 위해 전체 페이지 번호(마지막페이지번호) 를 알아야한다.
    public List<Integer> getPaginationBarNumbers(int currentPageNumber, int totalPages){
        int startNumber = Math.max(currentPageNumber - (BAR_LENGTH / 2), 0);
        int endNumber = Math.min(startNumber + BAR_LENGTH, totalPages);

        return IntStream.range(startNumber, endNumber).boxed().toList();
    }
    public int currentBarLength(){
        return BAR_LENGTH;
    }

}
