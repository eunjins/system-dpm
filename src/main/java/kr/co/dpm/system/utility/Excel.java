package kr.co.dpm.system.utility;

import kr.co.dpm.system.device.Device;
import kr.co.dpm.system.device.DeviceServiceImpl;
import kr.co.dpm.system.measure.Measure;
import kr.co.dpm.system.measure.MeasureServiceImpl;
import kr.co.dpm.system.script.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
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

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet(measures.get(0).getName());

        sheet.setColumnWidth(0, 1400);
        sheet.setColumnWidth(1, 4000);
        sheet.setColumnWidth(2, 4000);

        XSSFCellStyle centerStyle = workbook.createCellStyle();
        centerStyle.setAlignment(HorizontalAlignment.CENTER);
        centerStyle.setBorderLeft(BorderStyle.THICK);
        centerStyle.setBorderRight(BorderStyle.THICK);

        XSSFCellStyle title = workbook.createCellStyle();
        title.setAlignment(HorizontalAlignment.CENTER);

        title.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        title.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        title.setBorderTop(BorderStyle.THICK);
        title.setBorderRight(BorderStyle.THICK);
        title.setBorderLeft(BorderStyle.THICK);
        title.setBorderBottom(BorderStyle.THICK);

        XSSFCellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setBorderLeft(BorderStyle.THICK);
        rowStyle.setBorderRight(BorderStyle.THICK);

        XSSFCellStyle bottomStyle = workbook.createCellStyle();
        bottomStyle.setBorderTop(BorderStyle.THICK);


        XSSFRow row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        cell.setCellValue("측정 결과 명");
        cell.setCellStyle(title);

        cell = row.createCell(2);
        cell.setCellValue("스크립트 명");
        cell.setCellStyle(title);

        row = sheet.createRow(2);

        cell = row.createCell(1);
        cell.setCellValue(measures.get(0).getName());
        cell.setCellStyle(rowStyle);

        cell = row.createCell(2);
        cell.setCellValue(script.getName());
        cell.setCellStyle(rowStyle);

        row = sheet.createRow(3);

        cell = row.createCell(1);
        cell.setCellStyle(bottomStyle);

        cell = row.createCell(2);
        cell.setCellStyle(bottomStyle);

        row = sheet.createRow(4);
        cell = row.createCell(0);
        cell.setCellValue("번호");
        cell.setCellStyle(title);

        cell = row.createCell(1);
        cell.setCellValue("디바이스 명");
        cell.setCellStyle(title);

        cell = row.createCell(2);
        cell.setCellValue("실행 시간 (ms)");
        cell.setCellStyle(title);

        int i = 0;
        for (i = 0; i < measures.size(); i++) {
            row = sheet.createRow(i + 5);

            Device device = deviceService.getDevice(
                    new Device(measures.get(i).getDeviceId()));
            measures.get(i).setDeviceName(device.getName());

            // Row에 Cell 생성
            Cell noCell = row.createCell(0);
            noCell.setCellValue(i + 1);  // 번호
            noCell.setCellStyle(centerStyle);

            Cell deviceCell = row.createCell(1);    // 디바이스 명
            deviceCell.setCellValue(measures.get(i).getDeviceName());
            deviceCell.setCellStyle(rowStyle);

            Cell execTimeCell = row.createCell(2); // 실행 시간
            execTimeCell.setCellValue(measures.get(i).getExecTime());
            execTimeCell.setCellStyle(rowStyle);
        }

        row = sheet.createRow(i + 5);
        row.createCell(0).setCellStyle(bottomStyle);
        row.createCell(1).setCellStyle(bottomStyle);
        row.createCell(2).setCellStyle(bottomStyle);

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
