package com.bskyb.internettv.parental_control_service;


import com.bskyb.internettv.thirdparty.MovieServiceImpl;

public class MainClient {

    public static void main(String[] args) {
        ParentalControlService parentalControlService = new ParentalControlServiceImpl(new MovieServiceImpl());
        if (args.length != 2) {
            System.out.println("The usage is java -jar pcs.jar <Movie ID> <user PC level>");
            System.exit(0);
        }
        String movieName = args[0];
        String userPCLevel = args[1];
        try {
            boolean canWatch = parentalControlService.isMovieAllowedByParentalControlLevel(movieName, userPCLevel);
            if (canWatch)
                System.out.println("You can watch the movie " + movieName);
            else
                System.out.println("You cannot watch the movie " + movieName);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
