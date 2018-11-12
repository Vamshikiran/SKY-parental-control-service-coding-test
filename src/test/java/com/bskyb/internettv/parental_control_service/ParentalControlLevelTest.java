package com.bskyb.internettv.parental_control_service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

@RunWith(JUnit4.class)
public class ParentalControlLevelTest {

    @Test
    public void parentalControlShouldBeConstructedAndShouldReturnDesiredFormat() {
        ParentalControlLevel dateFormat = ParentalControlLevel.EIGHTEEN;
        assertThat(dateFormat.getId(), is("18"));
        assertThat(dateFormat.getVal(), is(4));
    }

    @Test
    public void stringIdShouldBeConvertedToEnum() {
        String givenId = "18";
        ParentalControlLevel eighteen = ParentalControlLevel.EIGHTEEN;
        Optional<ParentalControlLevel> level = ParentalControlLevel.of(givenId);
        assertTrue(level.isPresent());
        assertThat(level.get(), is(ParentalControlLevel.EIGHTEEN));
    }

    @Test
    public void givenComparisionOfHigherHighThanLowerShouldBeTrue() {
        ParentalControlLevel eighteenLevel = ParentalControlLevel.EIGHTEEN;
        ParentalControlLevel uLevel = ParentalControlLevel.U;
        assertTrue(eighteenLevel.isHigherThanOrEqualTo(uLevel));
    }

    @Test
    public void givenComparisionOfLowerHighThanHigherShouldBeFalse() {
        ParentalControlLevel uLevel = ParentalControlLevel.U;
        ParentalControlLevel eighteenLevel = ParentalControlLevel.EIGHTEEN;
        assertFalse(uLevel.isHigherThanOrEqualTo(eighteenLevel));
    }
}