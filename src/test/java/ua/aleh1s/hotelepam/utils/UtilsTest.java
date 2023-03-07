package ua.aleh1s.hotelepam.utils;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class UtilsTest {

    @Mock
    private HttpServletRequest httpServletRequest;

    @Test
    void canGetIntValue() {
        Integer expected = 1;

        given(httpServletRequest.getParameter(any()))
                .willReturn(String.valueOf(expected));

        Integer actual = Utils.getIntValue(httpServletRequest, "test");

        assertEquals(expected, actual);
    }

    @Test
    void canGetLongValue() {
        Long expected = 1L;

        given(httpServletRequest.getParameter(any()))
                .willReturn(String.valueOf(expected));

        Long actual = Utils.getLongValue(httpServletRequest, "test");

        assertEquals(expected, actual);
    }

    @Test
    void canGetLocalDateValue() {
        String dateStr = "2023-10-01";
        LocalDate expected = LocalDate.parse(dateStr);

        given(httpServletRequest.getParameter(any()))
                .willReturn(dateStr);

        LocalDate actual = Utils.getLocalDateValue(httpServletRequest, "test");

        assertEquals(expected, actual);
    }

    @Test
    void getBigDecimalValue() {
        String value = "10.0";
        BigDecimal expected = BigDecimal.valueOf(Double.parseDouble(value));

        given(httpServletRequest.getParameter(any()))
                .willReturn(value);

        BigDecimal actual = Utils.getBigDecimalValue(httpServletRequest, "test");

        assertEquals(expected, actual);
    }

    @Test
    void canGetIntValueOrDefault1() {
        String value = "1";
        Integer expected = Integer.parseInt(value);

        given(httpServletRequest.getParameter(any()))
                .willReturn(value);

        Integer actual = Utils.getIntValueOrDefault(httpServletRequest, "test", 1);

        assertEquals(expected, actual);
    }

    @Test
    void canGetIntValueOrDefault2() {
        Integer expected = 1;

        given(httpServletRequest.getParameter(any()))
                .willReturn("");

        Integer actual = Utils.getIntValueOrDefault(httpServletRequest, "test", expected);

        assertEquals(expected, actual);
    }

    @Test
    void canGetIntValueOrDefault3() {
        Integer expected = 1;

        given(httpServletRequest.getParameter(any()))
                .willReturn(null);

        Integer actual = Utils.getIntValueOrDefault(httpServletRequest, "test", expected);

        assertEquals(expected, actual);
    }

    @Test
    void canToUtilDate1() {
        LocalDateTime ldt = LocalDateTime.of(
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 10, 10)
        );

        Date expected = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());

        Date actual = Utils.toDate(ldt);

        assertEquals(expected, actual);
    }

    @Test
    void canToUtilDate2() {
        assertNull(Utils.toDate((LocalDateTime) null));
    }

    @Test
    void canToSqlDate1() {
        LocalDate ld = LocalDate.of(2023, 10, 10);

        java.sql.Date expected = java.sql.Date.valueOf(ld);

        java.sql.Date actual = Utils.toDate(ld);

        assertEquals(expected, actual);
    }

    @Test
    void canToSqlDate2() {
        assertNull(Utils.toDate((LocalDate) null));
    }

    @Test
    void canGetNumberOfPages() {
        int totalCount = 10_032,
            pageSize = 10,
            expected = (int) Math.ceil(totalCount / (double) pageSize),
            actual = Utils.getNumberOfPages(totalCount, pageSize);

        assertEquals(expected, actual);
    }

    @Test
    void canCheckIsReservationPeriodValid1() {
        Period period = Period.between(
                LocalDate.now(),
                LocalDate.now().plusDays(7)
        );

        assertTrue(Utils.isReservationPeriodValid(period));
    }

    @Test
    void canCheckIsReservationPeriodValid2() {
        Period period = Period.between(
                LocalDate.now().plusDays(7),
                LocalDate.now()
        );

        assertFalse(Utils.isReservationPeriodValid(period));
    }

    @Test
    void canCheckIsReservationPeriodValid3() {
        Period period = Period.between(
                LocalDate.now(),
                LocalDate.now()
        );

        assertFalse(Utils.isReservationPeriodValid(period));
    }

    @Test
    void canToTimestamp1() {
        LocalDateTime ldt = LocalDateTime.of(
                LocalDate.of(2023, 10, 1),
                LocalTime.of(10, 10, 10)
        );

        Timestamp expected = Timestamp.valueOf(ldt);

        Timestamp actual = Utils.toTimestamp(ldt);

        assertEquals(expected, actual);
    }

    @Test
    void canToTimestamp2() {
        assertNull(Utils.toTimestamp(null));
    }
}