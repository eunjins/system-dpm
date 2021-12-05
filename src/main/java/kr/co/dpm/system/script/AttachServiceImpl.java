package kr.co.dpm.system.script;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    @Autowired
    private AttachRepository attachRepository;

    /* 스크립트별 첨부 파일 조회 */
    @Override
    public List<Attach> getAttaches(Attach attach) {
        return attachRepository.selectAll(attach);
    }

    @Value("${path}")
    private String path;

    /* 첨부 파일 조회 */
    @Override
    public Attach getAttach(Attach attach) {
        return attachRepository.select(attach);
    }
    
    /* 첨부 파일 등록 */
    @Override
    public void registerAttach(MultipartFile sourceFile,
                               MultipartFile classFile, Attach attach) throws IOException {
        LocalDate date = LocalDate.now();

        String sourceFileExtension = FilenameUtils.getExtension(sourceFile.getOriginalFilename());
        String classFileExtension = FilenameUtils.getExtension(classFile.getOriginalFilename());

        if ("java".equals(sourceFileExtension)) {
            String physicalName = String.valueOf(date) + UUID.randomUUID();

            attach.setPhysicName(physicalName);
            attach.setDivision("S");
            attach.setName(FilenameUtils.getBaseName(sourceFile.getOriginalFilename()));

            attachRepository.insert(attach);

            File directory = new File(path);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            sourceFile.transferTo(new File(path + File.separator + physicalName));
        }

        if ("class".equals(classFileExtension)) {
            String physicalName = String.valueOf(date) + UUID.randomUUID();

            attach.setPhysicName(physicalName);
            attach.setDivision("C");
            attach.setName(FilenameUtils.getBaseName(classFile.getOriginalFilename()));

            attachRepository.insert(attach);

            File directory = new File(path);
            if (!directory.isDirectory()) {
                directory.mkdir();
            }

            classFile.transferTo(new File(path + File.separator + physicalName));
    }
    }
}
