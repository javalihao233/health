package cn.lihao.test;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;

public class PoiTest {

    /**
     * 读取文件
     * @throws IOException
     */
    @Test
    public void testReadPoi() throws IOException {
        //创建工作簿
        XSSFWorkbook workbook = new XSSFWorkbook("C:\\Users\\root\\Desktop\\hui.xlsx");

        //获取工作表，既可以根据工作表的顺序获取，也可以根据工作表的名称获取
        XSSFSheet sheet = workbook.getSheetAt(0);

        for (Row row : sheet) {//遍历出来的是每一行

            for (Cell cell : row) {//每一列  即：单个单元格
                String s = cell.getStringCellValue();
                System.out.println(s);
            }
        }

        workbook.close();
    }

    /**
     * 上传文件写入poi
     * @throws Exception
     */
    @Test
    public void testUpPoi() throws Exception {
        //在内存中创建一个Excel文件
        XSSFWorkbook workbook = new XSSFWorkbook();
        //创建工作表，指定工作表名称
        XSSFSheet sheet = workbook.createSheet("poi测试");

        //创建行，0表示第一行
        XSSFRow row = sheet.createRow(0);
        //创建单元格，0表示第一个单元格
        row.createCell(0).setCellValue("编号");
        row.createCell(1).setCellValue("名称");
        row.createCell(2).setCellValue("年龄");

        //创建行，1表示第二行
        XSSFRow row1 = sheet.createRow(1);
        row1.createCell(0).setCellValue("1");
        row1.createCell(1).setCellValue("小明");
        row1.createCell(2).setCellValue("10");

        //通过输出流将workbook对象下载到磁盘
        FileOutputStream out = new FileOutputStream("C:\\Users\\root\\Desktop\\测试写入poi.xlsx");
        workbook.write(out);
        out.flush();
        out.close();
        workbook.close();
    }
}
