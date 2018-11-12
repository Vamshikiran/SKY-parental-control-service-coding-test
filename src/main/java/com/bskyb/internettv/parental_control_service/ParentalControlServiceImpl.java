package com.bskyb.internettv.parental_control_service;


import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;

import java.util.Optional;

public class ParentalControlServiceImpl implements ParentalControlService {
    private final MovieService movieService;

    ParentalControlServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    public boolean isMovieAllowedByParentalControlLevel(String movieId, String userLevel) throws TechnicalFailureException,
            TitleNotFoundException, InvalidInputException {
        try {
            Optional<ParentalControlLevel> userParentalLevel = ParentalControlLevel.of(userLevel);
            if (!userParentalLevel.isPresent())
                throw new InvalidInputException("Invalid control Level entered as input");
            ParentalControlLevel movieParentalLevel = getMovieParentalControl(movieId);
            return userParentalLevel.get().isHigherThanOrEqualTo(movieParentalLevel);
        } catch (TechnicalFailureException | TitleNotFoundException | InvalidInputException ex) {
            throw ex;
        } catch (Exception e) {
            throw new TechnicalFailureException("Service error with Movie Service", e);
        }
    }

    private ParentalControlLevel getMovieParentalControl(String movieId) throws TechnicalFailureException, TitleNotFoundException {
        String controlLevel = movieService.getParentalControlLevel(movieId);
        Optional<ParentalControlLevel> movieControlLevelOptional
                = ParentalControlLevel.of(controlLevel);
        if (!movieControlLevelOptional.isPresent())
            throw new TechnicalFailureException("Invalid control Level returned from movie service");
        return movieControlLevelOptional.get();
    }
}