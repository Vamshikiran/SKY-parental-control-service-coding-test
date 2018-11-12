package com.bskyb.internettv.thirdparty;

/**
 * Created by vamshikirangullapelly on 12/11/2018.
 */
public class MovieServiceImpl implements MovieService {

    @Override
    public String getParentalControlLevel(String movieId) throws TitleNotFoundException, TechnicalFailureException {
        switch (movieId) {
            case "1":
                return "U";
            case "2":
                return "PG";
            case "3":
                return "12";
            case "4":
                return "15";
            case "5":
                return "18";
            case "6":
                return "U";
            case "7":
                return "18";
            default:
                throw new TitleNotFoundException("Sorry, we could not find the movie " + movieId + " you are looking for.");
        }
    }
}
