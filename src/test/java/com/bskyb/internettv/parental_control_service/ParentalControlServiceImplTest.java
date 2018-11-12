package com.bskyb.internettv.parental_control_service;

import com.bskyb.internettv.thirdparty.MovieService;
import com.bskyb.internettv.thirdparty.TechnicalFailureException;
import com.bskyb.internettv.thirdparty.TitleNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class ParentalControlServiceImplTest {

    private MovieService movieService;

    @Before
    public void setup() throws TechnicalFailureException, TitleNotFoundException {
        movieService = mock(MovieService.class);
    }

    @Test
    public void whenUserParentLevelIsULevelAndMovieIsULevelThenReturnTrue() throws Exception {
        final String movieId = "1";
        final String userLevel = "U";
        final String movieLevel = "U";
        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertTrue(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void whenParentalLevelIsHigherAndMovieLevelIsLowerThenReturnTrue() throws Exception {
        final String movieId = "1";
        final String userLevel = "18";
        final String movieLevel = "15";
        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertTrue(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }

    @Test
    public void whenParentalLevelIsLowerAndMovieLevelIsHigherThenReturnFalse() throws Exception {
        final String movieId = "1";
        final String userLevel = "PG";
        final String movieLevel = "15";
        when(movieService.getParentalControlLevel(movieId)).thenReturn(movieLevel);
        ParentalControlService pcs = new ParentalControlServiceImpl(movieService);
        assertFalse(pcs.isMovieAllowedByParentalControlLevel(movieId, userLevel));
    }
}
