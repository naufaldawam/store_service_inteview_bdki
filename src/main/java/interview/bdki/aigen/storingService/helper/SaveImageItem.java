package interview.bdki.aigen.storingService.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SaveImageItem {
    @Value("${url.image}")
    String urlStoreImage;

    public String saveFileIntoCloud(String imagePath) {
        String urlImage = urlStoreImage + imagePath;
        return urlImage;
    }
}
