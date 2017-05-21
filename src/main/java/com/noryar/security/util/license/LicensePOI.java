package com.noryar.security.util.license;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.noryar.security.dto.MsgInfoDTO;

/**
 * 功能描述：licnese信息解析工具类.
 * @author Leon.
 *
 */
public class LicensePOI {

	/**
	 * 功能描述：解析用户信息.
	 * @param msgbyteArr 用户信息字节数组.
	 * @return 用户信息列表.
	 * @throws Exception e.
	 */
	public static List<MsgInfoDTO> excelToDTO(byte[] msgbyteArr) throws Exception{
		return readExcel(new ByteArrayInputStream(msgbyteArr));
	}
	
	/**
	 * 功能描述：读取excel.
	 * @param is 文件输入流.
	 * @return 结果.
	 * @throws Exception e.
	 * @throws IOException ioe.
	 */
	private static List<MsgInfoDTO> readExcel(InputStream is) throws ParseException, IOException{
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
        MsgInfoDTO msgInfo = null;
        List<MsgInfoDTO> list = new ArrayList<MsgInfoDTO>();
        // 循环工作表Sheet
        for (int numSheet = 0; numSheet < xssfWorkbook.getNumberOfSheets(); numSheet++) {
            XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(numSheet);
            if (xssfSheet == null) {
                continue;
            }
            // 循环行Row
            for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
                XSSFRow xssfRow = xssfSheet.getRow(rowNum);
                if (xssfRow == null) {
                    continue;
                }
                msgInfo = new MsgInfoDTO();
                // 循环列Cell
                // 0用户名 1开始时间 2结束时间
                XSSFCell cell = xssfRow.getCell(0);
                msgInfo.setUsername(getValue(cell));
                cell = xssfRow.getCell(1);
                msgInfo.setStart(getValue(cell));
                cell = xssfRow.getCell(2);
                msgInfo.setEnd(getValue(cell));
                list.add(msgInfo);
            }
        }
        return list;
	}
	
    /**
     * 功能描述：得到Excel表中的值.
     * @param hssfCell Excel中的每一个格子.
     * @return Excel中每一个格子中的值.
     */
    @SuppressWarnings("static-access")
    private static String getValue(XSSFCell xssfCell) {
        if (xssfCell.getCellType() == xssfCell.CELL_TYPE_BOOLEAN) {
            // 返回布尔类型的值
            return String.valueOf(xssfCell.getBooleanCellValue());
        } else if (xssfCell.getCellType() == xssfCell.CELL_TYPE_NUMERIC) {
            // 返回数值类型的值
            return String.valueOf(xssfCell.getNumericCellValue());
        } else {
            // 返回字符串类型的值
            return String.valueOf(xssfCell.getStringCellValue());
        }
    }
}
