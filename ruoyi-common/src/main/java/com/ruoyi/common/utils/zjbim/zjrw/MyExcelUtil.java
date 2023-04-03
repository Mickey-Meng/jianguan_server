package com.ruoyi.common.utils.zjbim.zjrw;

import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.ruoyi.common.constant.ComponentType;
import com.ruoyi.common.core.domain.entity.Conponent;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.misc.BASE64Encoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author ：lin_zhixiong
 * @date ：Created in 2021/6/18 10:18 上午
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码苦耕耘
 * @description:
 **/
public class MyExcelUtil {

    private static Logger logger = LoggerFactory.getLogger(MyExcelUtil.class);

    /**
     * @param sheetName 工作表的名字
     * @param column  列名
     * @param data  需要导出的数据    ( map的键定义为列的名字 一定要和column中的列明保持一致  )
     * @param response
     */
    public static void exportExcel(String sheetName, List<String> column, List<Map<String,Object>> data, HttpServletRequest request, HttpServletResponse response){
        //创建工作薄
        HSSFWorkbook hssfWorkbook = new HSSFWorkbook();
        //创建sheet
        HSSFSheet sheet = hssfWorkbook.createSheet(sheetName);
        // 表头
        HSSFRow headRow = sheet.createRow(0);
        for (int i = 0; i < column.size(); i++){
            headRow.createCell(i).setCellValue(column.get(i));
        }

        //设置样式
//        setExcelStyle(hssfWorkbook);

        for (int i = 0; i < data.size(); i++) {
            HSSFRow dataRow = sheet.createRow(sheet.getLastRowNum() + 1);
            for (int x = 0; x < column.size(); x++) {
                dataRow.createCell(x).setCellValue(data.get(i).get(column.get(x))==null?"":data.get(i).get(column.get(x)).toString());
            }
        }

        response.setContentType("application/vnd.ms-excel");

        try {
            //获取浏览器名称
            String agent=request.getHeader("user-agent");
            String filename=sheetName+".xls";
            //不同浏览器需要对文件名做特殊处理
            if (agent.contains("Firefox")) { // 火狐浏览器
                filename = "=?UTF-8?B?"
                        + new BASE64Encoder().encode(filename.getBytes("utf-8"))
                        + "?=";
                filename = filename.replaceAll("\r\n", "");
            } else { // IE及其他浏览器
                filename = URLEncoder.encode(filename, "utf-8");
                filename = filename.replace("+", " ");
            }
            //推送浏览器
            response.setHeader("Content-Disposition","attachment;filename=" + filename);
            hssfWorkbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void setExcelStyle(HSSFWorkbook workbook){
        HSSFCellStyle cellStyle= workbook.createCellStyle();
        HSSFFont font = workbook.createFont();
        //设置字体样式
        font.setFontName("微软雅黑");
        // 将字体对象赋值给单元格样式对象
        cellStyle.setFont(font);
        //设置水平居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //垂直居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * 课程excel
     * @param in
     * @param fileName
     * @return
     * @throws Exception
     */
    public static List getCourseListByExcel(InputStream in, String fileName) throws Exception {

        List list = new ArrayList<>();
        ExcelReader reader = ExcelUtil.getReader(in);
        List<List<Object>> read = reader.read();

        for (int i = 1; i < read.size(); i++) {
            list.add(read.get(i));
        }

        return list;
    }

    public static List getCourseListByExcel(InputStream in) throws Exception {

        List list = new ArrayList<>();
        ExcelReader reader = ExcelUtil.getReader(in);
        List<List<Object>> read = reader.read();

        for (int i = 1; i < read.size(); i++) {
            list.add(read.get(i));
        }

        return list;
    }

    /**
     * 判断文件格式
     * @param in
     * @param fileName
     * @return
     */
    private static Workbook getWorkbook(InputStream in, String fileName) throws Exception {

        Workbook book = null;
        String filetype = fileName.substring(fileName.lastIndexOf("."));

        if(".xls".equals(filetype)) {
            book = new HSSFWorkbook(in);
        } else if (".xlsx".equals(filetype)) {
            book = new XSSFWorkbook(in);
        } else {
            throw new Exception("请上传excel文件！");
        }

        return book;
    }

    public static Conponent toConponent(List<String> list){
        Conponent conponent = new Conponent();
        List<String> items = Arrays.asList(list.get(0).split("-"));

        //新逻辑   分部工程
        /**
         * BIM编码	            工区	    单位工程	                分部工程	       分部工程单元	分项工程	   分项工程单元	WBS编码	                        模型编码
         * GQ01-01Z-01-01-01-01	工区一	ZK1+789姜家坞中桥（左幅）	基础及下部构造	   0#台	        桩基	        0a-0#桩基	SJSG01-01-01-01-01-01-01
         */
        //分部工程
        String name = list.get(2);
        if (name.contains("隧道")){
            conponent = getSudaoConponet(list, items);
        } else if (name.contains("桥") || name.equals("BK0+187.794草塔互通B匝道")){
            String divisionalWorks = list.get(3);
            if (divisionalWorks.equals("基础及下部构造")){
                conponent = getQLXBConponent(list,items);
            } else if (divisionalWorks.equals("上部构造现场浇筑") || divisionalWorks.equals("上部构造预制和安装")){
                conponent = getQLSBConponent(list, items);
            } else if (divisionalWorks.equals("桥面系、附属工程及桥梁总体") || divisionalWorks.equals("防护工程")){
                conponent = getQLQMFSConponent(list, items);
            }
        } else if (name.contains("路基工程") || name.contains("路面工程")) {
            conponent = getLMLJConponent(list, items);
        }
        return conponent;
    }

    private static Conponent getLMLJConponent(List<String> list, List<String> items){
        Conponent conponent = new Conponent();
        //GQ-01J-01-01-01-01	全工区	K0+000-K10+000路基工程	路基土石方工程      K0+000～K0+620路基土石方工程	    土方路基    	清表
        //                       GQ             01J                 01                  01                       01        01
        //设置总项目
        conponent.setW1("G235国道项目一标段");
        conponent.setW1code("QL235");
        //设置工区 GQ01  工区01
        conponent.setW2(list.get(1));
        conponent.setW2code(list.get(1));
        //设置单体项目
        //QL03Z  次坞大桥（左线）
        conponent.setW3(list.get(2));
        if (list.get(1).equals("工区一")){
            conponent.setW3code("LM" + items.get(1) + "1");
        } else if (list.get(1).equals("工区二")){
            conponent.setW3code("LM" + items.get(1) + "2");
        } else if (list.get(1).equals("工区三")){
            conponent.setW3code("LM" + items.get(1) + "3");
        } else if (list.get(1).equals("工区四")){
            conponent.setW3code("LM" + items.get(1) + "4");
        }

        //设置第一层结构 sb  上部结构
        conponent.setW4(list.get(3));
        conponent.setW4code(items.get(2));
        //设置构件分类
        conponent.setW5(list.get(4));
        conponent.setW5code(items.get(3));
        //设置第二层结构
        conponent.setW6(list.get(5));
        conponent.setW6code(items.get(4));
        //设置构件名称以及id
        conponent.setW7(list.get(6));
        conponent.setW7code(items.get(5));

        conponent.setConponentcode(list.get(0));
        //设置构件的分类
        ComponentType.setLMLJConponenttype(list, conponent);
        conponent.setProjectcode(conponent.getW3code());
        conponent.setProjecttype("LM");
        conponent.setWbscode(list.get(7));

        return conponent;
    }

    private static Conponent getSudaoConponet(List<String> list, List<String> items) {
        Conponent conponent = new Conponent();

        //GQ02-37Y-01-01-01-01	工区二	K4+855～K5+100檀树山隧道右洞	洞口工程	K4+855～K4+870（进口端）	洞口边仰坡防护	    锚杆	    SJSG01-37-01-01-01-01-01	        锚杆
        //                      GQ02       37Y                         01          01                  01          01
        conponent.setW1("G235国道项目一标段");
        conponent.setW1code("QL235");
        //工区
        conponent.setW2(getGongQuByGongQuCode(items.get(0)));
        conponent.setW2code(list.get(1));
        //隧道名
        conponent.setW3(list.get(2));
        conponent.setW3code("SD" + items.get(1));
        //工程
        conponent.setW4(list.get(3));
        conponent.setW4code(items.get(2));
        //工程单元
        conponent.setW5(list.get(4));
        conponent.setW5code(items.get(3));
        //构件分类
        conponent.setW6(list.get(5));
        conponent.setW6code(items.get(4));

        conponent.setW7(list.get(6));
        conponent.setW7code(items.get(5));

        conponent.setConponentcode(list.get(0));
        //设置构件的分类
        ComponentType.setSuDaoConponenttype(list, conponent);
        conponent.setProjectcode(conponent.getW3code());
        conponent.setProjecttype("SD");
        conponent.setWbscode(list.get(7));
        //模型编码
        if (list.get(8) != null && !list.get(8).equals("")){
            conponent.setMouldid(list.get(8));
        }
        return conponent;
    }

    private static Conponent getQLQMFSConponent(List<String> list, List<String> items) {
        Conponent conponent = new Conponent();
        //GQ01-01Z-05-00-01-01	    工区一	ZK1+789姜家坞中桥（左幅）	防护工程	 -	 锥坡	  0#台锥坡
        //                           GQ01           01Z                05    00   01        01
        conponent.setW1("G235国道项目一标段");
        conponent.setW1code("QL235");
        //gongqu
        conponent.setW2(getGongQuByGongQuCode(items.get(0)));  //工区一
        conponent.setW2code(list.get(1));       //GQ01
        //qiao
        conponent.setW3(list.get(2));           //ZK1+789姜家坞中桥（左幅）
        conponent.setW3code("QL" + items.get(1));       //01Z

        //空间结构第一层
        conponent.setW4(list.get(3));       //防护工程
        conponent.setW4code(items.get(2));  //05
        //设置构件分类
        conponent.setW5(list.get(5));
        conponent.setW5code(items.get(4));
        //设置构件名称以及id
        conponent.setW6(list.get(6));
        conponent.setW6code(items.get(5));

        //设置构件的分类
        conponent.setConponentcode(list.get(0));
        ComponentType.setQLConponenttype(list, conponent);
        conponent.setProjectcode(conponent.getW3code());
        conponent.setProjecttype("QL");
        conponent.setWbscode(list.get(7));
        //设置老BIM编码
        if (list.get(9) != null && !list.get(9).equals("")){
            conponent.setOldconponentcode(list.get(9));
        }
        //设置模型编码
        if (list.get(8) != null && !list.get(8).equals("")){
            conponent.setMouldid(list.get(8));
        }

        return conponent;
    }

    private static Conponent getQLSBConponent(List<String> list, List<String> items) {
        Conponent conponent = new Conponent();
        //GQ01-01Z-03-01-01-01	工区一	ZK1+789姜家坞中桥（左幅）	上部构造现场浇筑	第1跨	湿接缝	第1跨湿接缝
        //                       GQ01            01Z                03            01     01         01
        //设置总项目
        conponent.setW1("G235国道项目一标段");
        conponent.setW1code("QL235");
        //设置工区 GQ01  工区01
        conponent.setW2(getGongQuByGongQuCode(items.get(0)));
        conponent.setW2code(list.get(1));
        //设置单体项目
        //QL03Z  次坞大桥（左线）
        conponent.setW3(list.get(2));
        conponent.setW3code("QL" + items.get(1));
        //设置第一层结构 sb  上部结构
        conponent.setW4(list.get(3));
        if (list.get(3).equals("上部构造预制和安装")) {
            conponent.setW4code("SBAZ");
        } else {
            conponent.setW4code("SBJZ");
        }
        //设置构件分类
        conponent.setW5(list.get(4));
        conponent.setW5code(items.get(3));
        //设置第二层结构
        conponent.setW6(list.get(5));   //湿接缝
        conponent.setW6code(items.get(4));
        //设置构件名称以及id
        conponent.setW7(list.get(6));
        conponent.setW7code(items.get(5));

        conponent.setConponentcode(list.get(0));
        //设置构件的分类
        ComponentType.setQLConponenttype(list, conponent);
        conponent.setProjectcode(conponent.getW3code());
        conponent.setProjecttype("QL");
        conponent.setWbscode(list.get(7));
        //设置老BIM编码
        if (list.get(9) != null && !list.get(9).equals("")){
            conponent.setOldconponentcode(list.get(9));
        }
        //设置模型编码
        if (list.get(8) != null && !list.get(8).equals("")){
            conponent.setMouldid(list.get(8));
        }


        return conponent;
    }

    private static Conponent getQLXBConponent(List<String> list,List<String> items) {
        Conponent conponent = new Conponent();

        //GQ01-01Z-01-01-01-01	工区一	ZK1+789姜家坞中桥（左幅）	基础及下部构造	 0#台	桩基	 0a-0#桩基	SJSG01-01-01-01-01-01-01
        //                      GQ01         01Z                     01       01     01     01
        conponent.setW1("G235国道项目一标段");
        conponent.setW1code("QL235");

        conponent.setW2(getGongQuByGongQuCode(items.get(0)));  //工区一
        conponent.setW2code(list.get(1));

        conponent.setW3(list.get(2));   //ZK1+789姜家坞中桥（左幅）
        conponent.setW3code("QL" + items.get(1));

        conponent.setW4(list.get(3));  //基础及下部构造
        conponent.setW4code("JCXB");

        conponent.setW5(list.get(4));   // 0#台
        conponent.setW5code(items.get(3));
        //构件分类
        conponent.setW6(list.get(5));  //桩基
        conponent.setW6code(items.get(4));

        conponent.setW7(list.get(6)); //0a-0#桩基
        conponent.setW7code(items.get(5));

        conponent.setConponentcode(list.get(0));
        //设置构件的分类
        ComponentType.setQLConponenttype(list, conponent);
        conponent.setProjectcode(conponent.getW3code());
        conponent.setProjecttype("QL");
        conponent.setWbscode(list.get(7));
        //设置老BIM编码
        if (list.get(9) != null && !list.get(9).equals("")){
            conponent.setOldconponentcode(list.get(9));
        }
        //设置模型编码
        if (list.get(8) != null && !list.get(8).equals("")){
            conponent.setMouldid(list.get(8));
        }
        return conponent;
    }

    private static String getGongQuByGongQuCode(String s) {
        if("GQ01".equals(s)){
            return "工区一";
        }else if("GQ02".equals(s)){
            return "工区二";
        }else if("GQ03".equals(s)){
            return "工区三";
        }else if("GQ04".equals(s)){
            return "工区四";
        }
        return "";
    }

}



