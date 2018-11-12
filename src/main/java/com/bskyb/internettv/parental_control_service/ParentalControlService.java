package com.bskyb.internettv.parental_control_service;


import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

public interface ParentalControlService {
    boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException,
            TitleNotFoundException, InvalidInputException;
}
