package com.ruoyi.common.utils.poi;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFPalette;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

/**
 * sheet操作
 *
 * @author Administrator
 */
@Slf4j
public class LuckySheetUtil {

    private final static String MODEL = "{\"c\":0,\"r\":0,\"v\":{\"m\":\"模板\",\"v\":\"模板\",\"bl\":1,\"ct\":{\"t\":\"g\",\"fa\":\"General\"}}}";
    private final static String BORDER_MODEL = "{\"rangeType\":\"cell\",\"value\":{\"b\":{\"color\":\"rgb(0, 0, 0)\",\"style\":1},\"r\":{\"color\":\"rgb(0, 0, 0)\",\"style\":1},\"col_index\":5,\"t\":{\"color\":\"rgb(0, 0, 0)\",\"style\":1},\"row_index\":7,\"l\":{\"color\":\"rgb(0, 0, 0)\",\"style\":1}}}";
    private static final String S = "0123456789ABCDEF";
    /**
     * 输出文件流
     * @param outputStream 流
     * @param isXlsx  是否是xlsx
     * @param dbObjectList 数据
     */
    public static void createWorkbook(OutputStream outputStream, Boolean isXlsx, List<JSONObject> dbObjectList) throws IOException, IOException {
        Workbook workbook = isXlsx ? new XSSFWorkbook() : new HSSFWorkbook();
        if (CollectionUtil.isNotEmpty(dbObjectList)) {
            for (int sheetIndex = 0; sheetIndex < dbObjectList.size(); sheetIndex++) {
                createSheet(workbook, sheetIndex, dbObjectList.get(sheetIndex));
            }
        }
        workbook.write(outputStream);
    }

    /**
     * 导出sheet
     *
     * @param workbook
     * @param sheetNum
     * @param jsonData
     */
    public static void createSheet(Workbook workbook, int sheetNum, JSONObject jsonData) {
        Sheet sheet = workbook.createSheet();
        //设置sheet位置，名称
        if (jsonData.containsKey("name") && jsonData.get("name") != null) {
            workbook.setSheetName(sheetNum, jsonData.get("name").toString());
        } else {
            workbook.setSheetName(sheetNum, "sheet" + sheetNum);
        }
        //是否隐藏
        if (jsonData.containsKey("hide") && jsonData.get("hide").toString().equals("1")) {
            workbook.setSheetHidden(sheetNum, true);
        }
        //是否当前选中页
        if (jsonData.containsKey("status") && jsonData.get("status").toString().equals("1")) {
            sheet.setSelected(true);
        }
        //循环数据
        if (jsonData.containsKey("celldata") && jsonData.get("celldata") != null) {
            //取到所有单元格集合
            List<JSONObject> cells_json = (List<JSONObject>) jsonData.get("celldata");
            Map<Integer, List<JSONObject>> cellMap = cellGroup(cells_json);
            //循环每一行
            for (Integer r : cellMap.keySet()) {
                Row row = sheet.createRow(r);
                //循环每一列
                for (JSONObject col : cellMap.get(r)) {
                    createCell(workbook, sheet, row, col);
                }
            }
        }
        setColumAndRow(jsonData, sheet);
    }

    /**
     * 每一个单元格
     *
     * @param row
     * @param dbObject
     */
    private static void createCell(Workbook wb, Sheet sheet, Row row, JSONObject dbObject) {
        if (dbObject.containsKey("c")) {
            Integer c = getStrToInt(dbObject.get("c"));
            if (c != null) {
                Cell cell = row.createCell(c);
                //取单元格中的v_json
                if (dbObject.containsKey("v")) {
                    //获取v对象
                    Object obj = dbObject.get("v");
                    if (obj == null) {
                        //没有内容
                        return;
                    }
                    //如果v对象直接是字符串
                    if (obj instanceof String) {
                        if (((String) obj).length() > 0) {
                            cell.setCellValue(obj.toString());
                        }
                        return;
                    }

                    //转换v为对象(v是一个对象)
                    JSONObject v_json = (JSONObject) obj;
                    //样式
                    CellStyle style = wb.createCellStyle();
                    cell.setCellStyle(style);

                    /*//bs 边框样式 //bc 边框颜色
                    setBorderStyle(style,v_json,"bs","bc");
                    //bs_t 上边框样式   bc_t  上边框颜色
                    setBorderStyle(style,v_json,"bs_t","bc_t");
                    //bs_b 下边框样式   bc_b  下边框颜色
                    setBorderStyle(style,v_json,"bs_b","bc_b");
                    //bs_l 左边框样式   bc_l  左边框颜色
                    setBorderStyle(style,v_json,"bs_l","bc_l");
                    //bs_r 右边框样式   bc_r  右边框颜色
                    setBorderStyle(style,v_json,"bs_r","bc_r");*/

                    /**
                     * 不关注luckySheet是否配置有边框和颜色，默认设置黑色边框
                     */
                    Integer custom_v = 1;
                    style.setBorderTop(BorderStyle.valueOf(custom_v.shortValue()));
                    style.setBorderBottom(BorderStyle.valueOf(custom_v.shortValue()));
                    style.setBorderLeft(BorderStyle.valueOf(custom_v.shortValue()));
                    style.setBorderRight(BorderStyle.valueOf(custom_v.shortValue()));

                    Short custom_color = getColorByStr("rgb(0,0,0)");
                    style.setTopBorderColor(custom_color);
                    style.setBottomBorderColor(custom_color);
                    style.setLeftBorderColor(custom_color);
                    style.setRightBorderColor(custom_color);


                    //合并单元格
                    //参数1：起始行 参数2：终止行 参数3：起始列 参数4：终止列
                    //CellRangeAddress region1 = new CellRangeAddress(rowNumber, rowNumber, (short) 0, (short) 11);

                    //mc 合并单元格
                    if (v_json.containsKey("mc")) {
                        //是合并的单元格
                        JSONObject mc = v_json.getJSONObject("mc");
                        if (mc.containsKey("rs") && mc.containsKey("cs")) {
                            //合并的第一个单元格
                            if (mc.containsKey("r") && mc.containsKey("c")) {
                                Integer _rs = getIntByDBObject(mc, "rs") - 1;
                                Integer _cs = getIntByDBObject(mc, "cs") - 1;
                                Integer _r = getIntByDBObject(mc, "r");
                                Integer _c = getIntByDBObject(mc, "c");

                                CellRangeAddress region = new CellRangeAddress(_r.shortValue(),
                                        (_r.shortValue() + _rs.shortValue()), _c.shortValue(),
                                        (_c.shortValue() + _cs.shortValue()));
                                sheet.addMergedRegion(region);
                            }
                        } else {
                            //不是合并的第一个单元格
                            return;
                        }
                    }


                    //取v值 (在数据类型中处理)
                    //ct 单元格值格式 (fa,t)
                    setFormatByCt(wb, cell, style, v_json);

                    //font设置
                    setCellStyleFont(wb, style, v_json);

                    //bg 背景颜色
                    if (v_json.containsKey("bg")) {
                        String _v = getByDBObject(v_json, "bg");
                        if (_v != null) {
                            Short _color = getColorByStr(_v);
                            if (_color != null) {
//                                style.setFillBackgroundColor(_color);
                                style.setFillForegroundColor(_color);
                                style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                            }
                        }

                    }

                    //vt 垂直对齐    垂直对齐方式（0=居中，1=上，2=下）
                    if (v_json.containsKey("vt")) {
                        Integer _v = getIntByDBObject(v_json, "vt");
                        if (_v != null && _v >= 0 && _v <= 2) {
                            style.setVerticalAlignment(LuckySheetConstant.getVerticalType(_v));
                        }
                    }

                    //ht 水平对齐   水平对齐方式（0=居中，1=左对齐，2=右对齐）
                    if (v_json.containsKey("ht")) {
                        Integer _v = getIntByDBObject(v_json, "ht");
                        if (_v != null && _v >= 0 && _v <= 2) {
                            style.setAlignment(LuckySheetConstant.getHorizontaltype(_v));
                        }
                    }

                    //tr 文字旋转 文字旋转角度（0=0,1=45，2=-45，3=竖排文字，4=90，5=-90）
                    if (v_json.containsKey("tr")) {
                        Integer _v = getIntByDBObject(v_json, "tr");
                        if (_v != null) {
                            style.setRotation(LuckySheetConstant.getRotation(_v));
                        }
                    }

                    //tb  文本换行    0 截断、1溢出、2 自动换行
                    //   2：setTextWrapped     0和1：IsTextWrapped = true
                    if (v_json.containsKey("tb")) {
                        Integer _v = getIntByDBObject(v_json, "tb");
                        if (_v != null) {
                            if (_v >= 0 && _v <= 1) {
                                style.setWrapText(false);
                            } else {
                                style.setWrapText(true);
                            }
                        }
                    }

                    //f  公式
                    if (v_json.containsKey("f")) {
                        String _v = getByDBObject(v_json, "f");
                        if (_v.length() > 0) {
                            try {
                                if (_v.startsWith("=")) {
                                    cell.setCellFormula(_v.substring(1));
                                } else {
                                    cell.setCellFormula(_v);
                                }
                            } catch (Exception ex) {
                                log.error("公式 {};Error:{}", _v, ex.toString());
                            }
                        }
                    }


                }

            }
        }
    }

    /**
     * 设置单元格，宽、高
     *
     * @param dbObject
     * @param sheet
     */
    private static void setColumAndRow(JSONObject dbObject, Sheet sheet) {
        if (dbObject.containsKey("config")) {
            JSONObject config = dbObject.getJSONObject("config");

            if (config.containsKey("columlen")) {
                JSONObject columlen = config.getJSONObject("columlen");
                if (columlen != null) {
                    for (String k : columlen.keySet()) {
                        Integer _i = getStrToInt(k);
                        Integer _v = getStrToInt(columlen.get(k).toString());
                        if (_i != null && _v != null) {
                            //sheet.setColumnWidth(_i,MSExcelUtil.heightUnits2Pixel(_v.shortValue()))
                            sheet.setColumnWidth(_i, _v.shortValue());
                        }
                    }
                }
            }
            if (config.containsKey("rowlen")) {
                JSONObject rowlen = config.getJSONObject("rowlen");
                if (rowlen != null) {
                    for (String k : rowlen.keySet()) {
                        Integer _i = getStrToInt(k);
                        Integer _v = getStrToInt(rowlen.get(k).toString());
                        if (_i != null && _v != null) {
                            Row row = sheet.getRow(_i);
                            if (row != null) {
                                //row.setHeightInPoints(MSExcelUtil.pixel2WidthUnits(_v.shortValue()))
                                row.setHeightInPoints(_v.shortValue());
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * 单元格字体相关样式
     *
     * @param wb
     * @param style
     * @param dbObject
     */
    private static void setCellStyleFont(Workbook wb, CellStyle style, JSONObject dbObject) {
        Font font = wb.createFont();
        style.setFont(font);

        //ff 字体
        if (dbObject.containsKey("ff")) {
            if (dbObject.get("ff") instanceof Integer) {
                Integer _v = getIntByDBObject(dbObject, "ff");
                if (_v != null && LuckySheetConstant.ff_IntegerToName.containsKey(_v)) {
                    font.setFontName(LuckySheetConstant.ff_IntegerToName.get(_v));
                }
            } else if (dbObject.get("ff") instanceof String) {
                font.setFontName(getByDBObject(dbObject, "ff"));
            }
        }
        //fc 字体颜色
        if (dbObject.containsKey("fc")) {
            String _v = getByDBObject(dbObject, "fc");
            if (_v != null) {
                Short _color = getColorByStr(_v);
                if (_color != null) {
                    font.setColor(_color);
                }
            }
        }
        //bl 粗体
        if (dbObject.containsKey("bl")) {
            Integer _v = getIntByDBObject(dbObject, "bl");
            if (_v != null) {
                if (_v.equals(1)) {
                    //是否粗体显示
                    font.setBold(true);
                } else {
                    font.setBold(false);
                }
            }
        }
        //it 斜体
        if (dbObject.containsKey("it")) {
            Integer _v = getIntByDBObject(dbObject, "it");
            if (_v != null) {
                if (_v.equals(1)) {
                    font.setItalic(true);
                } else {
                    font.setItalic(false);
                }
            }
        }
        //fs 字体大小
        if (dbObject.containsKey("fs")) {
            Integer _v = getStrToInt(getObjectByDBObject(dbObject, "fs"));
            if (_v != null) {
                font.setFontHeightInPoints(_v.shortValue());
            }
        }
        //cl 删除线 (导入没有)   0 常规 、 1 删除线
        if (dbObject.containsKey("cl")) {
            Integer _v = getIntByDBObject(dbObject, "cl");
            if (_v != null) {
                if (_v.equals(1)) {
                    font.setStrikeout(true);
                }
            }
        }
        //ul 下划线
        if (dbObject.containsKey("ul")) {
            Integer _v = getIntByDBObject(dbObject, "ul");
            if (_v != null) {
                if (_v.equals(1)) {
                    font.setUnderline(Font.U_SINGLE);
                } else {
                    font.setUnderline(Font.U_NONE);
                }
            }
        }

    }

    /**
     * 设置cell边框颜色样式
     *
     * @param style    样式
     * @param dbObject json对象
     * @param bs       样式
     * @param bc       样式
     */
    private static void setBorderStyle(CellStyle style, JSONObject dbObject, String bs, String bc) {
        //bs 边框样式
        if (dbObject.containsKey(bs)) {
            Integer _v = getStrToInt(getByDBObject(dbObject, bs));
            if (_v != null) {
                //边框没有，不作改变
                if (bs.equals("bs") || bs.equals("bs_t")) {
                    style.setBorderTop(BorderStyle.valueOf(_v.shortValue()));
                }
                if (bs.equals("bs") || bs.equals("bs_b")) {
                    style.setBorderBottom(BorderStyle.valueOf(_v.shortValue()));
                }
                if (bs.equals("bs") || bs.equals("bs_l")) {
                    style.setBorderLeft(BorderStyle.valueOf(_v.shortValue()));
                }
                if (bs.equals("bs") || bs.equals("bs_r")) {
                    style.setBorderRight(BorderStyle.valueOf(_v.shortValue()));
                }

                //bc 边框颜色
                String _vcolor = getByDBObject(dbObject, bc);
                if (_vcolor != null) {
                    Short _color = getColorByStr(_vcolor);
                    if (_color != null) {
                        if (bc.equals("bc") || bc.equals("bc_t")) {
                            style.setTopBorderColor(_color);
                        }
                        if (bc.equals("bc") || bc.equals("bc_b")) {
                            style.setBottomBorderColor(_color);
                        }
                        if (bc.equals("bc") || bc.equals("bc_l")) {
                            style.setLeftBorderColor(_color);
                        }
                        if (bc.equals("bc") || bc.equals("bc_r")) {
                            style.setRightBorderColor(_color);
                        }
                    }
                }
            }
        }

        /**
         * 不关注luckySheet是否配置有边框和颜色，默认设置黑色边框
         */
        /*Integer custom_v = 1;
        style.setBorderTop(BorderStyle.valueOf(custom_v.shortValue()));
        style.setBorderBottom(BorderStyle.valueOf(custom_v.shortValue()));
        style.setBorderLeft(BorderStyle.valueOf(custom_v.shortValue()));
        style.setBorderRight(BorderStyle.valueOf(custom_v.shortValue()));

        Short custom_color = getColorByStr("rgb(0,0,0)");
        style.setTopBorderColor(custom_color);
        style.setBottomBorderColor(custom_color);
        style.setLeftBorderColor(custom_color);
        style.setRightBorderColor(custom_color);*/
    }


    /**
     * 设置单元格格式  ct 单元格值格式 (fa,t)
     *
     * @param cell
     * @param style
     * @param dbObject
     */
    private static void setFormatByCt(Workbook wb, Cell cell, CellStyle style, JSONObject dbObject) {

        if (!dbObject.containsKey("v") && dbObject.containsKey("ct")) {
            /* 处理以下数据结构
             {
                "celldata": [{
                    "c": 0,
                    "r": 8,
                    "v": {
                        "ct": {
                            "s": [{
                                "v": "sdsdgdf\r\ndfgdfg\r\ndsfgdfgdf\r\ndsfgdfg"
                            }],
                            "t": "inlineStr",
                            "fa": "General"
                        }
                    }
                }]
            }
             */
            JSONObject ct = dbObject.getJSONObject("ct");
            if (ct.containsKey("s")) {
                Object s = ct.get("s");
                if (s instanceof List && ((List) s).size() > 0) {
                    JSONObject _s1 = (JSONObject) ((List) s).get(0);
                    if (_s1.containsKey("v") && _s1.get("v") instanceof String) {
                        dbObject.put("v", _s1.get("v"));
                        style.setWrapText(true);
                    }
                }

            }
        }

        //String v = "";  //初始化
        if (dbObject.containsKey("v")) {
            //v = v_json.get("v").toString();
            //取到v后，存到poi单元格对象
            //设置该单元格值
            //cell.setValue(v);

            //String v=getByDBObject(v_json,"v");
            //cell.setValue(v);
            Object obj = getObjectByDBObject(dbObject, "v");
            if (obj instanceof Number) {
                cell.setCellValue(Double.valueOf(obj.toString()));
            } else if (obj instanceof Double) {
                cell.setCellValue((Double) obj);
            } else if (obj instanceof Date) {
                cell.setCellValue((Date) obj);
            } else if (obj instanceof Calendar) {
                cell.setCellValue((Calendar) obj);
            } else if (obj instanceof RichTextString) {
                cell.setCellValue((RichTextString) obj);
            } else if (obj instanceof String) {
                cell.setCellValue((String) obj);
            } else {
                cell.setCellValue(obj.toString());
            }

        }

        if (dbObject.containsKey("ct")) {
            JSONObject ct = dbObject.getJSONObject("ct");
            if (ct.containsKey("fa") && ct.containsKey("t")) {
                //t 0=bool，1=datetime，2=error，3=null，4=numeric，5=string，6=unknown
                String fa = getByDBObject(ct, "fa"); //单元格格式format定义串
                String t = getByDBObject(ct, "t"); //单元格格式type类型

                Integer _i = LuckySheetConstant.getNumberFormatMap(fa);
                switch (t) {
                    case "s": {
                        //字符串
                        if (_i >= 0) {
                            style.setDataFormat(_i.shortValue());
                        } else {
                            style.setDataFormat((short) 0);
                        }
                        cell.setCellType(CellType.STRING);
                        break;
                    }
                    case "d": {
                        //日期
                        Date _d = null;
                        String v = getByDBObject(dbObject, "m");
                        if (v.length() == 0) {
                            v = getByDBObject(dbObject, "v");
                        }
                        if (v.length() > 0) {
                            if (v.indexOf("-") > -1) {
                                if (v.indexOf(":") > -1) {
                                    _d = LuckySheetConstant.stringToDateTime(v);
                                } else {
                                    _d = LuckySheetConstant.stringToDate(v);
                                }
                            } else {
                                _d = LuckySheetConstant.toDate(v);
                            }
                        }
                        if (_d != null) {
                            //能转换为日期
                            cell.setCellValue(_d);
                            DataFormat format = wb.createDataFormat();
                            style.setDataFormat(format.getFormat(fa));

                        } else {
                            //不能转换为日期
                            if (_i >= 0) {
                                style.setDataFormat(_i.shortValue());
                            } else {
                                style.setDataFormat((short) 0);
                            }
                        }
                        break;
                    }
                    case "b": {
                        //逻辑
                        cell.setCellType(CellType.BOOLEAN);
                        if (_i >= 0) {
                            style.setDataFormat(_i.shortValue());
                        } else {
                            DataFormat format = wb.createDataFormat();
                            style.setDataFormat(format.getFormat(fa));
                        }
                        break;
                    }
                    case "n": {
                        //数值
                        cell.setCellType(CellType.NUMERIC);
                        if (_i >= 0) {
                            style.setDataFormat(_i.shortValue());
                        } else {
                            DataFormat format = wb.createDataFormat();
                            style.setDataFormat(format.getFormat(fa));
                        }
                        break;
                    }
                    case "u":
                    case "g": {
                        //general 自动类型
                        //cell.setCellType(CellType._NONE);
                        if (_i >= 0) {
                            style.setDataFormat(_i.shortValue());
                        } else {
                            DataFormat format = wb.createDataFormat();
                            style.setDataFormat(format.getFormat(fa));
                        }
                        break;
                    }
                    case "e": {
                        //错误
                        cell.setCellType(CellType.ERROR);
                        if (_i >= 0) {
                            style.setDataFormat(_i.shortValue());
                        } else {
                            DataFormat format = wb.createDataFormat();
                            style.setDataFormat(format.getFormat(fa));
                        }
                        break;
                    }

                }

            }

        }
    }

    /**
     * 内容按行分组
     *
     * @param cells
     * @return
     */
    private static Map<Integer, List<JSONObject>> cellGroup(List<JSONObject> cells) {
        Map<Integer, List<JSONObject>> cellMap = new HashMap<>(100);
        for (JSONObject dbObject : cells) {
            //行号
            if (dbObject.containsKey("r")) {
                Integer r = getStrToInt(dbObject.get("r"));
                if (r != null) {
                    if (cellMap.containsKey(r)) {
                        cellMap.get(r).add(dbObject);
                    } else {
                        List<JSONObject> list = new ArrayList<>(10);
                        list.add(dbObject);
                        cellMap.put(r, list);
                    }
                }
            }

        }
        return cellMap;
    }


    /**
     * 获取一个k的值
     *
     * @param b
     * @param k
     * @return
     */
    public static String getByDBObject(JSONObject b, String k) {
        if (b.containsKey(k)) {
            if (b.get(k) != null && b.get(k) instanceof String) {
                return b.getString(k);
            }
        }
        return null;
    }

    /**
     * 获取一个k的值
     *
     * @param b
     * @param k
     * @return
     */
    public static Object getObjectByDBObject(JSONObject b, String k) {
        if (b.containsKey(k)) {
            if (b.get(k) != null) {
                return b.get(k);
            }
        }
        return "";
    }

    /**
     * 没有/无法转换 返回null
     *
     * @param b
     * @param k
     * @return
     */
    public static Integer getIntByDBObject(JSONObject b, String k) {
        if (b.containsKey(k)) {
            if (b.get(k) != null) {
                try {
                    String _s = b.getString(k).replace("px", "");
                    Double _d = Double.parseDouble(_s);
                    return _d.intValue();
                } catch (Exception ex) {
                    log.error(ex.getMessage());
                    return null;
                }
            }
        }
        return null;
    }

    /**
     * 转int
     *
     * @param str
     * @return
     */
    private static Integer getStrToInt(Object str) {
        try {
            if (str != null) {
                return Integer.parseInt(str.toString());
            }
            return null;
        } catch (Exception ex) {
            log.error("String:{};Error:{}", str, ex.getMessage());
            return null;
        }
    }

    public static Short getColorByStr(String colorStr){
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFPalette palette = workbook.getCustomPalette();

        if(colorStr == null){return null;}
        if(colorStr.toLowerCase().startsWith("rgb")){
            colorStr=colorStr.toLowerCase().replace("rgb(","").replace(")","");
            String[] colors=colorStr.split(",");
            if(colors.length==3){
                try{
                    int red = Integer.parseInt(colors[0].trim(),16);
                    int green = Integer.parseInt(colors[1].trim(),16);
                    int blue = Integer.parseInt(colors[2].trim(),16);

                    HSSFColor hssfColor=palette.findSimilarColor(red,green,blue);
                    return hssfColor.getIndex();
                }catch (Exception ex){
                    log.error(ex.toString());
                    return null;
                }
            }
            return null;
        }

        if(colorStr.equals("#000")){
            colorStr="#000000";
        }
        if(colorStr!=null && colorStr.length()>=6){
            try{
                if(colorStr.length()==8){
                    colorStr=colorStr.substring(2);
                }
                if(colorStr.length()==7){
                    colorStr=colorStr.substring(1);
                }
                String str2 = colorStr.substring(0,2);
                String str3 = colorStr.substring(2,4);
                String str4 = colorStr.substring(4,6);
                int red = Integer.parseInt(str2,16);
                int green = Integer.parseInt(str3,16);
                int blue = Integer.parseInt(str4,16);

                HSSFColor hssfColor=palette.findSimilarColor(red,green,blue);
                return hssfColor.getIndex();
            }catch (Exception ex){
                log.error(ex.toString());
                return null;
            }
        }
        return null;
    }

    /**
     * RGB转换成十六进制
     *
     * @param r
     * @param g
     * @param b
     * @return
     */
    public static String convertRGBToHex(short r, short g, short b) {
        String hex = "";
        if (r >= 0 && r < 256 && g >= 0 && g < 256 && b >= 0 && b < 256) {
            int x, y, z;
            x = r % 16;
            r = (short) ((r - x) / 16);
            y = g % 16;
            g = (short) ((g - y) / 16);
            z = b % 16;
            b = (short) ((b - z) / 16);
            hex = "#" + S.charAt(r) + S.charAt(x) + S.charAt(g) + S.charAt(y) + S.charAt(b) + S.charAt(z);
        }
        return hex;
    }

    /**
     * @param cell 单元格
     * @return 转换RGB颜色值
     * @description tint转换RBG
     * @author zhouhang
     * @date 2021/4/26
     */
    public static String getFillColorHex(Cell cell) {
        String fillColorString = null;
        if (cell != null) {
            CellStyle cellStyle = cell.getCellStyle();
            Color color = cellStyle.getFillForegroundColorColor();
            if (color instanceof XSSFColor) {
                XSSFColor xssfColor = (XSSFColor) color;
                byte[] argb = xssfColor.getARGB();
                fillColorString = convertRGBToHex((short) (argb[1] & 0xFF), (short) (argb[2] & 0xFF), (short) (argb[3] & 0xFF));
                // TODO: 2021/4/26 添加透明度
//                if (xssfColor.hasTint()) {
//                    fillColorString += " * " + xssfColor.getTint();
//                    byte[] rgb = xssfColor.getRGBWithTint();
//                    fillColorString += " = [" + (argb[0] & 0xFF) + ", " + (rgb[0] & 0xFF) + ", " + (rgb[1] & 0xFF) + ", " + (rgb[2] & 0xFF) + "]";
//                }
            } else if (color instanceof HSSFColor) {
                HSSFColor hssfColor = (HSSFColor) color;
                short[] rgb = hssfColor.getTriplet();
                fillColorString = convertRGBToHex((short) (rgb[0] & 0xFF), (short) (rgb[1] & 0xFF), (short) (rgb[2] & 0xFF));
                //去除黑色背景
                if (StringUtils.equals("#000000", fillColorString)) {
                    return null;
                }
            }
        }
        return fillColorString;
    }
    
    private static Short getStrToShort(Object str) {
        try {
            if (str != null) {
                return Short.parseShort(str.toString());
            }
            return null;
        } catch (Exception ex) {
            log.error("String:{};Error:{}", str, ex.getMessage());
            return null;
        }
    }
}

