package br.ce.wcaquino.taskbackend.utils;


import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    @Test
    public void shouldReturnTrueForTheActualDate(){
        LocalDate date = LocalDate.now();
        assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnTrueForTheFutureDate(){
        LocalDate date = LocalDate.now().plusYears(10);
        assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnFalseForThePastDate(){
        LocalDate date = LocalDate.now().minusYears(10);
        assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
}