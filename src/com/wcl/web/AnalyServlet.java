package com.wcl.web;

import com.wcl.dao.EquipmentDao;
import com.wcl.domain.Equipment;
import com.wcl.domain.Loan;
import com.wcl.service.EquipmentService;
import com.wcl.service.LoanService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/AnalyServlet")
public class AnalyServlet extends BaseServlet {
    //分析员建议维修
    public String repair(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            EquipmentService equipmentService = new EquipmentService();
            Equipment equipment = equipmentService.getEquipmentWithId(id);
            equipment.setEquip_state("*维修审核中...");
            equipmentService.updateEquipment(equipment);

            return "/EquipmentServlet?action=getEquipmentsByEstate";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //分析员建议报废
    public String scrap(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            EquipmentService equipmentService = new EquipmentService();
            Equipment equipment = equipmentService.getEquipmentWithId(id);
            equipment.setEquip_state("*报废审核中...");
            equipmentService.updateEquipment(equipment);

            return "/EquipmentServlet?action=getEquipmentsByEstate";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // 获取所有  管理员
    public String getEquipmentsByCheckM(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EquipmentDao equipmentDao = new EquipmentDao();
        try {
            List<Equipment> equipments = equipmentDao.getEquipmentsByCheckM();
            // 把数据写到request域
            request.setAttribute("equipments", equipments);
            // 转发
            return "admin/manager_analy.jsp";
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    //管理员同意申请
    public String agree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            EquipmentDao equipmentDao = new EquipmentDao();
            Equipment equipment = equipmentDao.getEquipmentWithId(id);
            String equip_state = equipment.getEquip_state();
            if (equip_state.equals("*维修审核中...")){
                equipment.setEquip_state("*正在维修中...");
            } else if (equip_state.equals("*报废审核中...")){
                equipment.setEquip_state("*即将报废...");
            }
            equipmentDao.updateEquipment(equipment);
            return "/AnalyServlet?action=getEquipmentsByCheckM";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    //管理员驳回申请
    public String disAgree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String id = request.getParameter("id");
            EquipmentDao equipmentDao = new EquipmentDao();
            Equipment equipment = equipmentDao.getEquipmentWithId(id);
            String equip_state = equipment.getEquip_state();
            if (equip_state.equals("*维修审核中...")){
                equipment.setEquip_state("*维修被驳回");
            } else if (equip_state.equals("*报废审核中...")){
                equipment.setEquip_state("*报废被驳回");
            }
            equipmentDao.updateEquipment(equipment);
            return "/AnalyServlet?action=getEquipmentsByCheckM";
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
