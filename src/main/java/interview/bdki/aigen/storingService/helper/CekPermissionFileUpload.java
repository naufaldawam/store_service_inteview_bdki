package interview.bdki.aigen.storingService.helper;

import org.springframework.stereotype.Service;

@Service
public class CekPermissionFileUpload {
    public String getFileExtension(String fileName) {
        int extensionIndex = fileName.lastIndexOf(".");
        if (extensionIndex > 0) {
            return fileName.substring(extensionIndex + 1);
        } else {
            return "";
        }
    }
}
