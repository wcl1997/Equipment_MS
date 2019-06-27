package com.wcl.web;

import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Admin;
import com.wcl.domain.Equipment;
import com.wcl.domain.Page;
import com.wcl.service.EquipmentService;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@WebServlet("/EquipmentServlet")
public class EquipmentServlet extends BaseServlet {
    //添加
    public String add(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //获取所有参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(parameterMap);
        //把参数封装对象
        Equipment equipment = new Equipment();
        try {
            BeanUtils.populate(equipment, parameterMap);
//            System.out.println(equipment);
            //调用服务层
            EquipmentService equipmentService = new EquipmentService();
            equipmentService.addEquipment(equipment);
            //跳转列表，
            return "/EquipmentServlet?action=getPageData&currentPage=1";
        }  catch (Exception e) {
            if (e.getMessage().equals("设备编号不可为空！")){
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/admin/add.jsp").forward(request, response);
            }else if (e.getMessage().equals("该设备已存在!")){
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/admin/add.jsp").forward(request, response);
            }else {
                e.printStackTrace();
            }
        }
        return null;
    }

    // 编辑
    public String edit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        // 1.获取所有的参数
        Map<String, String[]> parameterMap = request.getParameterMap();
        Equipment equipment = new Equipment();
        // 2.封装成  对象
        try {
            BeanUtils.populate(equipment, parameterMap);
            // 3.根据id更新数据
//            System.out.println(equipment);
            // 4.调用服务层，更新数据
            EquipmentService equipmentService = new EquipmentService();
            equipmentService.updateEquipment(equipment);
            // 5.跳转回main.jsp 列表
            return "/EquipmentServlet?action=getPageData&currentPage=1";
        } catch (Exception e) {
            if (e.getMessage().equals("该设备已存在，无法修改！")){
                request.setAttribute("err", e.getMessage());
                request.getRequestDispatcher("/EquipmentServlet?action=editUI").forward(request, response);
            }else {
                e.printStackTrace();
            }
        }

        return null;
    }

    // 编辑  UI
    public String editUI(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取当前 的id
        String id = request.getParameter("id");
        try {
            // 1.获取当前
            EquipmentService equipmentService = new EquipmentService();
            Equipment equipment = equipmentService.getEquipmentWithId(id);
            // 写到域
            request.setAttribute("equipment", equipment);
            // 转发到edit.jsp
            return "/admin/edit.jsp";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 删除
    public String delEquipment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.接收参数 id
        String id = request.getParameter("id");
        // 2.调用服务层，让其删除
        EquipmentService equipmentService = new EquipmentService();
        try {
            equipmentService.deleteEquipment(id);
            return "/EquipmentServlet?action=getPageData&currentPage=1";
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // 获取所有
    public String getListEquipments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1.调用服务层
        EquipmentService equipmentService = new EquipmentService();
        try {
            List<Equipment> allEquipments = equipmentService.getAllEquipments();
            // 对集合进行反转
            Collections.reverse(allEquipments);
            // 把数据写到request域
            request.setAttribute("allEquipments", allEquipments);
            // 转发
            return "admin/main.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //根据id获取
    public String getEquipment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("search");
        // 1.调用服务层
        EquipmentService equipmentService = new EquipmentService();
        try {
            Page page = equipmentService.getDataById(id);
            request.setAttribute("page", page);
//            System.out.println(equipment);
            // 转发
            Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin.getType().equals("user")){
                return "admin/main2.jsp";
            }else if (admin.getType().equals("equit_manager")){
                return "admin/main.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //设备管理员使用
    public String getPageData(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String currentPage = request.getParameter("currentPage");
            EquipmentService equipmentService = new EquipmentService();
            Page page = equipmentService.getPage(Integer.parseInt(currentPage));
            request.setAttribute("page",page);

            /*Admin admin = (Admin) request.getSession().getAttribute("admin");
            if (admin.getType().equals("user")){
                return "admin/main2.jsp";
            }else if (admin.getType().equals("equit_manager")){
                return "admin/main.jsp";
            }else if (admin.getType().equals("buyer_manager")){
                return "admin/main3.jsp";
            }*/
            return "admin/main.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //访客使用
    public String getPageData2(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String currentPage = request.getParameter("currentPage");
            EquipmentService equipmentService = new EquipmentService();
            Page page = equipmentService.getPage(Integer.parseInt(currentPage));
            request.setAttribute("page",page);
            return "admin/main2.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //采购员使用
    public String getPageData3(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String currentPage = request.getParameter("currentPage");
            EquipmentService equipmentService = new EquipmentService();
            Page page = equipmentService.getPage(Integer.parseInt(currentPage));
            request.setAttribute("page",page);
            return "admin/main3.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //分析员使用
    public String getEquipmentsByEstate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Equipment> equipments = new EquipmentDao().getEquipmentsByEstate();
            request.setAttribute("equipments",equipments);
            return "admin/main4.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getEquipmentsByCheck(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Equipment> equipments = new EquipmentDao().getEquipmentsByCheck();
            request.setAttribute("equipments",equipments);
            return "admin/check_message.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //被驳回
    public String getEquipmentsByDisAgree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Equipment> equipments = new EquipmentDao().getEquipmentsByDisAgree();
            request.setAttribute("equipments",equipments);
            return "admin/main4.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //维修员查看
    public String getEquipmentsByRepair(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            List<Equipment> equipments = new EquipmentDao().getEquipmentsByRepair();
            request.setAttribute("equipments",equipments);
            return "admin/main5.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    //维修成功
    public String repairSuccess(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            EquipmentService equipmentService = new EquipmentService();
            Equipment equipment = equipmentService.getEquipmentWithId(id);
            equipment.setEquip_state("正常");
            equipmentService.updateEquipment(equipment);
            return "/EquipmentServlet?action=getEquipmentsByRepair";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //维修失败
    public String repairFailed(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String id = request.getParameter("id");
            EquipmentService equipmentService = new EquipmentService();
            Equipment equipment = equipmentService.getEquipmentWithId(id);
            equipment.setEquip_state("故障");
            equipmentService.updateEquipment(equipment);
            return "/EquipmentServlet?action=getEquipmentsByRepair";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //养护员查看
    public String getEquipmentsMaintain(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Equipment> equipments = new EquipmentDao().getEquipmentsMaintain();
            request.setAttribute("equipments",equipments);
            return "admin/main6.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
