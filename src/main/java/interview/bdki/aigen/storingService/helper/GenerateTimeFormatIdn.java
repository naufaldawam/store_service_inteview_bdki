package interview.bdki.aigen.storingService.helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import org.springframework.stereotype.Service;

@Service
public class GenerateTimeFormatIdn {

    public String generateTimeWithFormatIdn(Date createdAtData) {
        TimeZone timeZone = TimeZone.getTimeZone("Asia/Jakarta");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        formatter.setTimeZone(timeZone);
        return formatter.format(createdAtData);
    }
}
