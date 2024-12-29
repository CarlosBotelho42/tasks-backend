package br.ce.wcaquino.taskbackend.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class DateUtilsTest {

    @Test
    public void shouldReturnTrueForTheActualDate(){
        LocalDate date = LocalDate.now();
        Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnTrueForTheFutureDate(){
        LocalDate date = LocalDate.now().plusYears(10);
        Assertions.assertTrue(DateUtils.isEqualOrFutureDate(date));
    }

    @Test
    public void shouldReturnFalseForThePastDate(){
        LocalDate date = LocalDate.now().minusYears(10);
        Assertions.assertFalse(DateUtils.isEqualOrFutureDate(date));
    }
}