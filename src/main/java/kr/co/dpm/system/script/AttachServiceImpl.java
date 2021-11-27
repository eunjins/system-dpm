package kr.co.dpm.system.script;

import org.apache.commons.io.FilenameUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDate;
import java.util.UUID;

import java.util.List;

@Service
public class AttachServiceImpl implements AttachService {
    private static final Logger logger = LogManager.getLogger(AttachServiceImpl.class);
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
                               MultipartFile classFile, Attach attach) {
        logger.debug("##### 첨부파일 등록 진입");
        LocalDate date = LocalDate.now();

        String sourceFileExtension = FilenameUtils.getExtension(sourceFile.getOriginalFilename());
        String classFileExtension = FilenameUtils.getExtension(classFile.getOriginalFilename());

        if ("java".equals(sourceFileExtension)) {
            String physicalName = String.valueOf(date) + UUID.randomUUID();

            attach.setPhysicName(physicalName);
            attach.setDivision("S");
            attach.setName(FilenameUtils.getBaseName(sourceFile.getOriginalFilename()));

            logger.debug(attach.getDivision() + attach.getName() + attach.getPhysicName() + attach.getScriptNo());
            attachRepository.insert(attach);
            logger.debug("##### 첨부파일 자바 등록 완료");
            try {
                File directory = new File(path);
                if (!directory.isDirectory()) {
                    logger.debug("##### 디렉터리 생성 중");
                    directory.mkdir();
                    logger.debug("##### 디렉터리 생성 중 완료");
                }
                boolean check = directory.mkdir();
                sourceFile.transferTo(new File(
                        path + File.separator + physicalName));

                logger.debug("##### 디렉터리 생성 완료");
            } catch (Exception e) {
                e.printStackTrace();

                logger.debug("##### 디렉터리 생성 실패");

            }
        }

        if ("class".equals(classFileExtension)) {
            String physicalName = String.valueOf(date) + UUID.randomUUID();

            attach.setPhysicName(physicalName);
            attach.setDivision("C");
            attach.setName(FilenameUtils.getBaseName(classFile.getOriginalFilename()));

            logger.debug(attach.getDivision() + attach.getName() + attach.getPhysicName() + attach.getScriptNo());
            attachRepository.insert(attach);
            logger.debug("##### 첨부파일 클래스 등록 완료");
            try {
                File directory = new File(path);
                if (!directory.isDirectory()) {
                    directory.mkdir();
                }
                sourceFile.transferTo(new File(
                        path + File.separator + physicalName));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
