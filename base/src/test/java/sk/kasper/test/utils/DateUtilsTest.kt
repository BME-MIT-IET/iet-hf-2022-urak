package sk.kasper.test.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.Month
import sk.kasper.base.utils.toLocalDateTime
import sk.kasper.base.utils.toTimeStamp


class DateUtilsTest {

    @Test
    fun toTimeStamp() {
        assertThat("June 4, 2010 18:45:00 UTC".toTimeStamp())
            .isEqualTo(1275677100000L)
    }

    @Test
    fun toTimeStampPartlyGood() {
        assertThat("June 4, 2010".toTimeStamp())
            .isEqualTo(0)
    }

    @Test
    fun toTimeStampWithoutMonth() {
        assertThat("4, 2010 18:45:00".toTimeStamp())
            .isEqualTo(0)
    }


    @Test
    fun toTimeStampWithoutUtc() {
        assertThat("June 4, 2010 18:45:00".toTimeStamp())
            .isEqualTo(0)
    }

    @Test
    fun toTimeStamp_nonSenseValue_wontCrash() {
        assertThat("Nonsense value xyz".toTimeStamp())
            .isEqualTo(0L)
    }

    @Test
    fun longToLocalDateTime() {
        val value: Long = 1275677100000L

        assertThat(value.toLocalDateTime()).isEqualTo(LocalDateTime.of(2010, Month.JUNE, 4, 20, 45))
    }

    @Test
    fun longToLocalDateTimeNull() {
        val value: Long = 0

        assertThat(value.toLocalDateTime()).isEqualTo(LocalDateTime.of(1970, Month.JANUARY, 1, 1, 0))
    }

    @Test
    fun longToLocalDateTimeNegative() {
        val value: Long = -1

        assertThat(value.toLocalDateTime()).isEqualTo(LocalDateTime.of(1970, Month.JANUARY, 1, 0, 59, 59, 999000000))
    }

}