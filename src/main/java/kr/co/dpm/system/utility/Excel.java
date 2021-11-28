package kr.co.dpm.system.utility;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureServiceImpl;
import kr.co.dpm.system.script.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Component
public class Excel {
    private static final Logger logger = LogManager.getLogger(ScriptController.class);

    @Value("${excelPath}")
    private String excelPath;

    @Autowired
    private MeasureServiceImpl measureService;

    @Autowired
    private DeviceServiceImpl deviceService;

    /* 엑셀 생성 */
    public String create(Script script) {
        List<Measure> measures =
                measureService.getMeasures(new Measure(script.getNo()));

        // Workbook (엑셀 파일) 생성
        XSSFWorkbook workbook = new XSSFWorkbook();

        // Sheet 생성
        XSSFSheet sheet = workbook.createSheet(measures.get(0).getName());

        XSSFRow row = sheet.createRow(1);
        row.createCell(1).setCellValue("측정 결과 명");
        row.createCell(2).setCellValue("스크립트 명");

        row = sheet.createRow(2);
        row.createCell(1).setCellValue(measures.get(0).getName());
        row.createCell(2).setCellValue(script.getNo());

        row = sheet.createRow(4);
        row.createCell(0).setCellValue("번호");
        row.createCell(1).setCellValue("디바이스 명");
        row.createCell(2).setCellValue("실행 시간 (ms)");

        for (int i = 0; i < measures.size(); i++) {
            row = sheet.createRow(i + 5);

            Device device = deviceService.getDevice(
                    new Device(measures.get(i).getDeviceId()));
            measures.get(i).setDeviceName(device.getName());

            // Row에 Cell 생성
            row.createCell(0).setCellValue(i + 1);  // 번호
            row.createCell(1).setCellValue(measures.get(i).getDeviceName());  // 디바이스 명
            row.createCell(2).setCellValue(measures.get(i).getExecTime());    // 실행 시간
        }

        FileOutputStream fileOutputStream = null;
        String fileName = null;

        try {
            fileName = measures.get(0).getName() + "_" +
                    LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd")) + ".xlsx";

            fileOutputStream = new FileOutputStream(
                    excelPath + File.separator + fileName);
            workbook.write(fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return fileName;
    }

    /* 엑셀 파일 삭제 */
    public void delete(String fileName) {
        File file = new File(excelPath + File.separator + fileName);
        file.delete();
    }
}
