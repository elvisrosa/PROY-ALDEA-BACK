
package com.aldea.cristo.persistencia.interfaces;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageInterface {
    void init();
    String store(MultipartFile file);
    Resource loadResource(String filename);
    String save_bitacora(Integer id_casa);
}
